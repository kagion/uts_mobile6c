//ResultToko
package com.kagion.toko.model

import com.google.gson.annotations.SerializedName

data class ResultToko (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("toko")
    val toko: List<DataItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)