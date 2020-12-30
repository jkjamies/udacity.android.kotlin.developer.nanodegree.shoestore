package com.udacity.shoestore.screens.listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailsFragmentBinding
import timber.log.Timber

/**
 * Fragment for the starting or title screen of the app
 */
class DetailsFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView called")

        // Inflate the layout for this fragment
        val binding: DetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.details_fragment, container, false
        )

        // binding viewModel to layout - let binding know about viewModel using layout shoeViewModel variable
        binding.shoeViewModel = viewModel
        // sets lifecycle owner to this (fragment) to allow live data to automatically update data binding layouts
        binding.lifecycleOwner = this

        // If shoeDetailsCompleted, go back to listings screen and reset flag in viewModel
        viewModel.shoeDetailsCompleted.observe(
            viewLifecycleOwner,
            Observer { shoeDetailsCompleted ->
                Timber.i("shoeDetailsCompleted Observed")
                // If newShoeAdded is true, the shoe was added successfully, wait for it to flip to false to navigate
                if (shoeDetailsCompleted) {
                    Timber.i("shoeDetailsCompleted Observed true")
                    findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToListingsFragment())
                }
            })

        // When save is pressed save the shoe details
        binding.detailsShoeSaveMaterialButton.setOnClickListener {
            Timber.i("detailsShoeSaveMaterialButton onClick called")
            viewModel.addShoe()
        }
        return binding.root
    }

    /**
     * The flag for the completed details must be reset when the details fragment is destroyed so
     * it is not preserved when re-entering the details screen again.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetAddShoeComplete()
    }
}
