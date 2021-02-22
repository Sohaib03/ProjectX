package com.threedots.projectx.data.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.threedots.projectx.data.dao.UserDao
import com.threedots.projectx.data.database.UserDatabase
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.data.entities.QuestionSet
import com.threedots.projectx.data.entities.User
import com.threedots.projectx.data.network.Api
import com.threedots.projectx.util.validEmail
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(context: Context) {

    private var userDao : UserDao
    init {
        userDao = UserDatabase.getDatabase(context).userDao();
    }

    fun userLogin(email : String, password: String) : LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        if (validEmail(email)) {
            Api().userLoginWithEmail(email, password)
                .enqueue(object : Callback<ResponseBody>{
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            loginResponse.value = response.body()?.string()
                        } else {
                            loginResponse.value = response.errorBody()?.string()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        loginResponse.value = t.message
                    }

                })
        } else {
            Api().userLogin(email, password)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            loginResponse.value = response.body()?.string()
                        } else {
                            loginResponse.value = response.errorBody()?.string()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        loginResponse.value = t.message
                    }

                })
        }
        return loginResponse
    }

    fun userRegister(username : String, email : String, password: String) : LiveData<String> {
        val registerResponse = MutableLiveData<String>()
        Api().userRegistration(username, email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        registerResponse.value = response.body()?.string()
                    } else {
                        registerResponse.value = response.errorBody()?.string()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    registerResponse.value = t.message
                }

            })
        return registerResponse
    }

     suspend fun addUser(user: User){
         userDao.addUser(user)
     }

    fun getAllUsers() : LiveData<List<User>> {
        return userDao.readAllData()
    }

    fun nukeTable() {
        userDao.nukeTable()
    }

}