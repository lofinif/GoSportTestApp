package com.lofinif.gosporttestapp.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import retrofit2.HttpException

sealed class CallResult<out T> {
    data class Success<out T>(val value: T): CallResult<T>()
    data class HttpError(val code: Int? = null, val message: String?): CallResult<Nothing>()
    data object  IOError: CallResult<Nothing>()
    data object  UnknownError: CallResult<Nothing>()
}

open class BaseRepo {
    companion object {
        private const val TAG = "BaseRepo"
    }

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                apiCall: suspend () -> T): CallResult<T> {
        return withContext(dispatcher) {
            try {
                CallResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.e(TAG, "Error while loading data", throwable)
                when (throwable) {
                    is IOException -> CallResult.IOError
                    is HttpException -> CallResult.HttpError(
                        code = throwable.code(),
                        message = throwable.message
                    )

                    else -> CallResult.UnknownError
                }
            }
        }
    }
}
