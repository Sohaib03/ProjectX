package com.threedots.projectx.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.threedots.projectx.R
import com.threedots.projectx.databinding.FragmentLoginBinding
import com.threedots.projectx.databinding.FragmentRegisterBinding
import com.threedots.projectx.util.isInternetAvailable
import com.threedots.projectx.util.toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), AuthListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        val viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        var view = binding.root
        return view;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            parentFragmentManager.popBackStack()
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

    override fun isConnected(): Boolean {
        return isInternetAvailable(requireContext())
    }

    override fun onStarted() {
        requireContext().toast("Started")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            requireContext().toast(it)
        })
    }

    override fun onFailure(message: String) {
        requireContext().toast("Failed : $message")
    }
}