package com.threedots.projectx.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.threedots.projectx.R
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.data.entities.QuestionSet
import com.threedots.projectx.data.entities.User
import com.threedots.projectx.data.repositories.QuestionRepository
import com.threedots.projectx.data.repositories.UserRepository
import com.threedots.projectx.util.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var liveUserList = UserRepository(requireContext().applicationContext).getAllUsers();
        liveUserList.observe(viewLifecycleOwner, {
            if (!it.isEmpty()){
                var user = it.get(0);
                login_token_text_View.text = user.user_access_token
                getQuestion(user.user_access_token)
            }
        })
    }
    fun postQuestion(accessToken: String) {
        var question = Question("How ?", 1, mutableListOf("Option 1", "Option 2", "Option 3", "Option 4"))
        var questionSet = QuestionSet(15, mutableListOf(question))
        var result = QuestionRepository().postQuestion(accessToken, questionSet)
        result.observe(viewLifecycleOwner, {
            debug_text_view.text = it
        })
    }
    fun getQuestion(accessToken : String) {
        var question = QuestionRepository().getQuestion(accessToken, "699d32c23d");
        question.observe(viewLifecycleOwner, {
            debug_text_view.text = it
            //Log.i("DEBUG", "getQuestion: " + question.value)
        })
    }
}

// Test Exam Id = 4437d8e9c7