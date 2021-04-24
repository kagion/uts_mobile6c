package com.kagion.toko.presenter

import android.util.Log
import com.kagion.toko.model.ResultToko
import com.kagion.toko.model.ResultStatus
import com.kagion.toko.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter (val crudView: CrudView) {

    //Fungsi GetData
    fun getData(){
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<ResultToko>{
                override fun onFailure(call: Call<ResultToko>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultToko>, response: Response<ResultToko>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.toko
                            crudView.onSuccessGet(data)
                        } else{
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }

            })
    }


    //Add data
    fun addData(name : String, hp : String, alamat : String){
        NetworkConfig.getService()
            .addToko(name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }

            })
    }


    //Hapus Data
    fun hapusData(id: String?){
        NetworkConfig.getService()
            .deleteToko(id)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDelete(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDelete(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data
    fun updateData(id: String, name: String, hp: String, alamat: String){
        NetworkConfig.getService()
            .updateToko(id, name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }

                }

            })
    }

}