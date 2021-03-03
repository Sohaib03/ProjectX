package com.threedots.projectx.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.data.entities.QuestionSet
import kotlinx.coroutines.Job

class CreateQuestionViewModel(
    private val repository: QuestionSet
) : ViewModel() {
    private val _questions = MutableLiveData<List<Question>>()
    private lateinit var job : Job
    val questions : LiveData<List<Question>>
        get() = _questions

    fun getQuestions() {
        job = Coroutines.ioThenMain(
            {repository.questions},
            {
                _questions.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}