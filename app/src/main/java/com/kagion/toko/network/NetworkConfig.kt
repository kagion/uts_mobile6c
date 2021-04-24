package com.kagion.toko.network

import com.kagion.toko.model.ResultStatus
import com.kagion.toko.model.ResultToko
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //Retrofit

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.185/server_gio/index.php/ServerApi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(TokoService::class.java)
}
interface TokoService{

    //Fungsi Create Data
    @FormUrlEncoded
    @POST("addToko")
    fun addToko(@Field("name") name : String,
                 @Field("hp") hp : String,
                 @Field("alamat") alamat : String) : Call<ResultStatus>



    //Fungsi Get Data
    @GET("getDataToko")

    fun getData() : Call<ResultToko>

    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("deleteToko")
    fun deleteToko(@Field("id") id: String?) : Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
    @POST("updateToko")
    fun updateToko(@Field("id") id: String,
                    @Field("name") name: String,
                    @Field("hp") hp : String,
                    @Field("alamat") alamat : String) : Call<ResultStatus>
}