package com.kagion.toko.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable{

    @field:SerializedName("toko_name")
    val tokoName: String? = null

    @field:SerializedName("toko_id")
    val tokoId: String? = null

    @field:SerializedName("toko_hp")
    val tokoHp: String? = null

    @field:SerializedName("toko_alamat")
    val tokoAlamat: String? = null
}

