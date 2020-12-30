package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding
import timber.log.Timber

/**
 * Fragment for the starting or title screen of the app
 */
class WelcomeFragment : Fragment() {

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView called")

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()

        // Welcome next button goes to the instructions fragment
        binding.welcomeNextMaterialButton.setOnClickListener {
            Timber.i("welcomeNextMaterialButton onClick called")
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
        }
        return binding.root
    }
}
