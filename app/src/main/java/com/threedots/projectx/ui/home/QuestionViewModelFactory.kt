package com.threedots.projectx.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.threedots.projectx.data.entities.QuestionSet
import com.threedots.projectx.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class QuestionViewModelFactory(
    private val repository: QuestionSet
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateQuestionViewModel(repository) as T
    }
}