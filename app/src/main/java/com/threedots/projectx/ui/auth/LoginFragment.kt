package com.threedots.projectx.ui.auth

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonParser
import com.threedots.projectx.R
import com.threedots.projectx.data.entities.User
import com.threedots.projectx.data.repositories.UserRepository
import com.threedots.projectx.databinding.FragmentLoginBinding
import com.threedots.projectx.ui.home.HomeActivity
import com.threedots.projectx.util.toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.threedots.projectx.util.isInternetAvailable

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

    override fun isConnected(): Boolean {
        return isInternetAvailable(requireContext())
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            //requireContext().toast(it)
            var jsonObject = JSONObject(it)
            if (jsonObject.has("error")) {
                requireContext().toast(jsonObject.getString("error"))
            } else if (jsonObject.has("access_token")) {
                requireContext().toast(jsonObject.getString("access_token"))
                GlobalScope.launch {
                    Log.i("TOKEN", "onSuccess: " + jsonObject.getString("access_token"))
                    UserRepository(requireActivity().applicationContext).nukeTable()
                    UserRepository(requireActivity().applicationContext).addUser(
                        User(
                            1,
                            jsonObject.getString("access_token")
                        )
                    )
                }
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            } else {
                requireContext().toast("An error has occured")
            }
            progressBar.visibility = View.INVISIBLE
        })
        //requireContext().toast("Success")
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.INVISIBLE
        requireContext().toast("Failed : $message")
    }
}