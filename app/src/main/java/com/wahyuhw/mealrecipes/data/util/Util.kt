package com.wahyuhw.mealrecipes.data.util

import com.google.gson.Gson
import com.wahyuhw.mealrecipes.base.BaseErrorResponse
import com.wahyuhw.mealrecipes.domain.util.Resource
import com.wahyuhw.mealrecipes.utils.emptyString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T, U> Flow<ApiResult<T>>.mapToDomain(mapper: (T) -> U): Flow<Resource<U>> =
    this.map {
        when (it) {
            is ApiResult.Success -> {
                Resource.Success(it.result?.let { mappedData -> mapper.invoke(mappedData) })
            }
            else -> {
                Resource.Error(it.errorCode ?: 999, it.errorMessage ?: emptyString())
            }
        }
    }

fun <T> Response<T>.call(): Flow<ApiResult<T>> =
    flow<ApiResult<T>> {
        try {
            val response = this@call
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()))
            } else {
                response.errorBody()?.let {
                    val errorResponse = Gson().fromJson(it.string(), BaseErrorResponse::class.java)
                    val message = "CODE ${errorResponse.code}:\n${errorResponse.message.orEmpty()}"
                    emit(ApiResult.Error(errorResponse.code, message))
                }
            }
        } catch (e: UnknownHostException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: ConnectException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: SocketTimeoutException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: SocketException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: IOException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: EOFException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: Exception) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        }
    }.flowOn(Dispatchers.IO)