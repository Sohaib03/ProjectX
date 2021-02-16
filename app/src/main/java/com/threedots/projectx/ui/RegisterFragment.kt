package com.threedots.projectx.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.threedots.projectx.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
    }

    companion object {
        fun newInstance(): RegisterFragment {
            var registerFragment: RegisterFragment = RegisterFragment()
            var args : Bundle = Bundle()
            // args.putInt("key", value)
            // Later arguments can be used to get the key value pair
            registerFragment.arguments = args
            return registerFragment
        }
    }
}