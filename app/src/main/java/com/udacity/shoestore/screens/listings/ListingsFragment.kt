package com.udacity.shoestore.screens.listings

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingsFragmentBinding
import timber.log.Timber

/**
 * Fragment for the starting or title screen of the app
 */
class ListingsFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView called")

        // Inflate the layout for this fragment
        val binding: ListingsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.listings_fragment, container, false
        )

        // Ensure ActionBar showing for listing and details flow
        (activity as AppCompatActivity).supportActionBar?.show()

        // Observe the shoe list
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoes ->
            shoes.forEach {
                val newView = this.layoutInflater.inflate(R.layout.shoe_list_item, null)
                newView.findViewById<TextView>(R.id.listItemShoeNameTextView).text = it.name
                newView.findViewById<TextView>(R.id.listItemShoeCompanyTextView).text = it.company
                newView.findViewById<TextView>(R.id.listItemShoeSizeTextView).text =
                    it.size.toString()
                newView.findViewById<TextView>(R.id.listItemShoeDescriptionTextView).text =
                    it.description
                binding.listingsLinearLayout.addView(newView)
            }
        })

        // listings details FAB goes to the details fragment
        binding.listingsDetailsFAB.setOnClickListener {
            Timber.i("listingsDetailsFAB onClick called")
            viewModel.init()
            findNavController().navigate(ListingsFragmentDirections.actionListingsFragmentToDetailsFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflate the menu for logout to show up only on this fragment
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Timber.i("onCreateOptionsMenu called")
        inflater.inflate(R.menu.menu, menu)
    }

    /**
     * Return true if NavigationUI.onNavDestinationSelected returns true, else return super.onOptionsItemSelected.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected - Logout - called")
        viewModel.clearAllShoes()
        // Using a trick where logout is using the id of loginFragment (the idea is meh, but it's a small project)
        // it's also allowing for backwards navigation from login after logout, but again, no true authentication happening here
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
