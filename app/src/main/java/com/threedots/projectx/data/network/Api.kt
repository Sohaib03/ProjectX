package com.threedots.projectx.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api{
    @FormUrlEncoded
    @POST("auth/token")
    fun userLogin(
        @Field("username") email : String,
        @Field("password") password : String
    ) : Call<ResponseBody>

    companion object {
        operator fun invoke() : Api {
            return Retrofit.Builder()
                .baseUrl("https://project-x-01.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}