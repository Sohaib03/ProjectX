package com.threedots.projectx.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.data.entities.QuestionSet
import com.threedots.projectx.data.network.Api
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionRepository {
    fun postQuestion(accessToken : String, questionSet: QuestionSet) : LiveData<String> {
        val feedbackResponse = MutableLiveData<String>()
        val token = "Bearer "  +accessToken.trim()
        Api().createQuestionSet(token, questionSet)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        feedbackResponse.value = response.body()?.string()
                    } else {
                        feedbackResponse.value = response.errorBody()?.string()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    feedbackResponse.value = t.message
                }

            })
        return feedbackResponse
    }

    fun getQuestion(accessToken: String, examId : String) : LiveData<String> {
        val feedbackResponse = MutableLiveData<String>()
        val token = "Bearer " + accessToken.trim()
        Api().getQuestionSet(examId, token)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        feedbackResponse.value = response.body()?.string()
                        //Log.i("DEBUG", "Response Success " + feedbackResponse.value)
                    } else {
                        //Log.i("DEBUG", "Response Failed")
                        feedbackResponse.value = response.errorBody()?.string()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    feedbackResponse.value = t.message
                }

            })
        return feedbackResponse
    }

    fun parseQuestionSet(questionString : String) : QuestionSet{
        return QuestionSet(0, mutableListOf<Question>())
    }
}