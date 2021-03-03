package com.threedots.projectx.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.threedots.projectx.R
import com.threedots.projectx.data.entities.Question
import com.threedots.projectx.databinding.RecyclerviewItemBinding

class QuestionAdapter(
    private val questions : List<Question>
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>(){


    inner class QuestionViewHolder(val recyclerviewItemBinding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(recyclerviewItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.recyclerviewItemBinding.question = questions[position]
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}