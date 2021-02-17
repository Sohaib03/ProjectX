package com.threedots.projectx.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.threedots.projectx.R
import com.threedots.projectx.databinding.FragmentLoginBinding
import com.threedots.projectx.util.toast
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), AuthListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentLoginBinding = inflate(inflater, R.layout.fragment_login, container, false)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        var view = binding.root
        return view;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_button.setOnClickListener {
            var transaction = parentFragmentManager.beginTransaction()
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

    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            requireContext().toast(it)
        })
        requireContext().toast("Success")
        progressBar.visibility = View.INVISIBLE
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.INVISIBLE
        requireContext().toast("Failed : $message")
    }
}