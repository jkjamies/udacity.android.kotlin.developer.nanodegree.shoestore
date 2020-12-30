package com.udacity.shoestore.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

/**
 * Fragment for the starting or title screen of the app
 */
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView called")

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        // Ensure ActionBar hidden for login and onboarding flow
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.loginCreateAccountMaterialButton.setOnClickListener {
            Timber.i("loginCreateAccountMaterialButton onClick called")
            completeLoginScreen()
        }

        binding.loginLoginMaterialButton.setOnClickListener {
            Timber.i("loginLoginMaterialButton onClick called")
            completeLoginScreen()
        }

        return binding.root
    }

    private fun completeLoginScreen() {
        Timber.i("completeLoginScreen called")
        // Hide the keyboard.
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}

