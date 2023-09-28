package com.hy.wanandroid.ui.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.hy.wanandroid.ui.DataModelPreference
import java.io.InputStream
import java.io.OutputStream

object DataModelSerializer : Serializer<DataModelPreference> {
    override val defaultValue: DataModelPreference
        get() = DataModelPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DataModelPreference {
        try {
            return DataModelPreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: DataModelPreference, output: OutputStream) {
        t.writeTo(output)
    }
}