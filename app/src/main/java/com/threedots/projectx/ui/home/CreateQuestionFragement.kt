package com.threedots.projectx.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.threedots.projectx.R
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.data.entities.QuestionSet
import com.threedots.projectx.data.repositories.QuestionRepository
import kotlinx.android.synthetic.main.create_question_fragement.*

class CreateQuestionFragement : Fragment() {
    private lateinit var factory: QuestionViewModelFactory
    private lateinit var viewModel: CreateQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_question_fragement, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mock = Question("How", 1, mutableListOf("Option 1", "2", "3", "4"))
        val repository = QuestionSet(0, mutableListOf<Question>(mock))
        viewModel = QuestionViewModelFactory(repository).create(CreateQuestionViewModel::class.java)
        viewModel.getQuestions()
        viewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            question_recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = QuestionAdapter(questions)
                Log.i("T", "onActivityCreated: " + "Adapter Attached!")
            }
        })
    }

}