package com.threedots.projectx.data.network

import com.threedots.projectx.data.entities.QuestionSet
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api{
    @FormUrlEncoded
    @POST("auth/token")
    fun userLogin(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("auth/token")
    fun userLoginWithEmail(
        @Field("email") email : String,
        @Field("password") password: String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/registration")
    fun userRegistration(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("password") password: String
    ) : Call<ResponseBody>

    @POST("/teacher/createquestionset")
    fun createQuestionSet(
        @Header("Authorization") access_token : String,
        @Body questionSet : QuestionSet
    ) : Call<ResponseBody>

    @POST("/user/getquestionset/{examId}")
    fun getQuestionSet(
        @Path("examId") examId : String,
        @Header("Authorization") access_token : String,
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