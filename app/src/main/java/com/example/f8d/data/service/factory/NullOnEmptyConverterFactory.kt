package com.example.f8d.data.service.factory

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.io.IOException

class NullOnEmptyConverterFactory(private val delegate: Converter.Factory) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegateConverter = delegate.responseBodyConverter(type, annotations, retrofit)
        return Converter<ResponseBody, Any?> { body ->
            try {
                val source = body.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer()
                if (buffer.size() == 0L) {
                    null
                } else {
                    delegateConverter?.convert(body)
                }
            } catch (e: IOException) {
                null
            }
        }
    }
}