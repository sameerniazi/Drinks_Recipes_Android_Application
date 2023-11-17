package com.project.sameer.Reporisity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.sameer.API.ApiService
import com.project.sameer.API.DrinkInstance
import com.project.sameer.DataClass.Drink
import com.project.sameer.DataClass.DrinkData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reporsity {

     var itemData: MutableLiveData<DrinkData> = MutableLiveData()
    //Please Note --> I make only call for successful because its a test project
     fun searchByName(name: String){
      DrinkInstance.api.searchCocktail(name).enqueue(object  : Callback<DrinkData> {
            override fun onResponse(call: Call<DrinkData>, response: Response<DrinkData>) {
                if (response.isSuccessful){
                    itemData.value=response.body()!!
                }

            }
            override fun onFailure(call: Call<DrinkData>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun searchByAlpha(name: String){
        DrinkInstance.api.searchByAlpha(name).enqueue(object  : Callback<DrinkData> {
            override fun onResponse(call: Call<DrinkData>, response: Response<DrinkData>) {
                if (response.isSuccessful){
                    itemData.value=response.body()!!
                }

            }
            override fun onFailure(call: Call<DrinkData>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }


}