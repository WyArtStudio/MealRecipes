package com.wahyuhw.mealrecipes.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.chuckerteam.chucker.api.ChuckerInterceptor.Builder
import com.wahyuhw.mealrecipes.data.recipe.RecipeDataStore
import com.wahyuhw.mealrecipes.data.recipe.RecipeRepository
import com.wahyuhw.mealrecipes.data.recipe.source.local.RecipeDatabase
import com.wahyuhw.mealrecipes.data.recipe.source.network.RecipeApi
import com.wahyuhw.mealrecipes.data.recipe.source.network.RecipeApiClient
import com.wahyuhw.mealrecipes.data.util.HeaderInterceptor
import com.wahyuhw.mealrecipes.domain.recipe.RecipeInteractor
import com.wahyuhw.mealrecipes.domain.recipe.RecipeUseCase
import com.wahyuhw.mealrecipes.utils.BASE_URL
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
	  factory { CompositeDisposable() }
	
	  val httpLogging = "http_logging"
	  val chuckerLogging = "chucker_logging"
	
	  val gson: Gson = GsonBuilder().setLenient().create()

	  single<Interceptor>(named(httpLogging)) {
	    HttpLoggingInterceptor().setLevel(
		    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
		    else HttpLoggingInterceptor.Level.NONE
		)
	  }
	
	  single<Interceptor>(named(chuckerLogging)) { Builder(get()).build() }
	
	  fun cache(context: Context): Cache {
	    val cacheSize = (5.times(1024).times(1024)).toLong()
	    return Cache(context.cacheDir, cacheSize)
	  }
	
	  single {
	    OkHttpClient.Builder()
	        .cache(cache(get()))
	        .addInterceptor(getHeaderInterceptor())
	        .addInterceptor(interceptor = get(named(httpLogging)))
	        .addInterceptor(interceptor = get(named(chuckerLogging)))
	        .addInterceptor { chain ->
	          val request = chain.request()
	          request.newBuilder().header("Cache-Control", "public, max-age=" + 10).build()
	          chain.proceed(request)
	        }
	        .connectTimeout(120, TimeUnit.SECONDS)
	        .build()
	  }
	
	single<RecipeApiClient> {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(get())
			.build()
			.create(RecipeApiClient::class.java)
	}
	
	single {
		RecipeApi(get())
	}
}

val repositoryModule = module {
	single<RecipeRepository> { RecipeDataStore(get(), get()) }
}

val useCaseModule = module {
	single<RecipeUseCase> { RecipeInteractor(get()) }
}

val viewModelModule = module {
	viewModel { RecipeViewModel(get(), get()) }
}

val dbModule = module {
	single { RecipeDatabase.getAppDatabase(get()) }
	
	single {
		val appDatabase: RecipeDatabase = get()
		return@single appDatabase.recipeDao()
	}
}

private fun getHeaderInterceptor(): Interceptor {
	val headers = HashMap<String, String>()
	return HeaderInterceptor(headers)
}