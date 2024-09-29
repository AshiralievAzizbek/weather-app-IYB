package com.test.weather.com.test.weather.data.remote.network

import com.github.kittinunf.result.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCall<T>(private val call: Call<T>) : Call<Result<T, Exception>> {

    override fun clone(): Call<Result<T, Exception>> {
        return ResultCall(call.clone())
    }

    override fun execute(): Response<Result<T, Exception>> {
        throw NotImplementedError()
    }

    override fun enqueue(callback: Callback<Result<T, Exception>>) {
        call.enqueue(ResultCallback(callback, this))
    }

    override fun isExecuted(): Boolean = call.isExecuted
    override fun cancel() = call.cancel()
    override fun isCanceled() = call.isCanceled
    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()


    @Suppress("UNCHECKED_CAST")
    private class ResultCallback<T>(
        private val callback: Callback<Result<T, Exception>>,
        private val resultCall: Call<Result<T, Exception>>
    ) : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {

            callback.onResponse(resultCall, Response.success(Result.success(response.body() as T)))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val exception = Exception(error.message, error.cause)
            callback.onResponse(resultCall, Response.success(Result.failure(exception)))
        }
    }
}

class ResultAdapter<T> private constructor(
    private val type: Type
) : CallAdapter<T, Call<Result<T, Exception>>> {

    override fun responseType(): Type = type

    override fun adapt(call: Call<T>): Call<Result<T, Exception>> {
        return ResultCall(call)
    }

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            val rawReturnType: Class<*> = getRawType(returnType)
            if (rawReturnType == Call::class.java && returnType is ParameterizedType) {
                val callInnerType: Type = getParameterUpperBound(0, returnType)
                if (getRawType(callInnerType) == Result::class.java) {
                    if (callInnerType is ParameterizedType) {
                        val resultInnerType = getParameterUpperBound(0, callInnerType)
                        return ResultAdapter<Any?>(resultInnerType)
                    }
                    return ResultAdapter<Nothing>(Nothing::class.java)
                }
            }

            return null
        }
    }
}