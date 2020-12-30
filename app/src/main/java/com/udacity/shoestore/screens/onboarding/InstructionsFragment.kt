package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import timber.log.Timber

/**
 * Fragment for the starting or title screen of the app
 */
class InstructionsFragment : Fragment() {

    private lateinit var binding: InstructionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView called")

        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)

        // Instructions begin button goes to the shoe listings fragment
        binding.instructionsBeginMaterialButton.setOnClickListener {
            Timber.i("instructionsNextMaterialButton onClick called")
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToListingsFragment())
        }
        return binding.root
    }
}
