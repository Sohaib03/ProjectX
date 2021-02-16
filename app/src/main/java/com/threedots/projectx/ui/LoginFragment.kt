package com.threedots.projectx.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.threedots.projectx.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_button.setOnClickListener {
            var transaction  = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.main_activity_fragment, RegisterFragment.newInstance(), "register")
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    companion object {
        fun newInstance(): LoginFragment {
            var loginFragment: LoginFragment = LoginFragment()

            var args : Bundle = Bundle()
            // args.putInt("key", value)
            // Later arguments can be used to get the key value pair
            loginFragment.arguments = args
            return loginFragment
        }
    }
}