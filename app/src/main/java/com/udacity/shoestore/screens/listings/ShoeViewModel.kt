package com.udacity.shoestore.screens.listings

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    // Variable for binding in views for adding new shoe details
    var shoe: Shoe? = null

    // Following https://developer.android.com/topic/libraries/data-binding/two-way
    val shoeSizeModel = ShoeSizeModel()

    // Private variable containing a list of shoes
    private var shoes = mutableListOf<Shoe>()

    // The shoe list with Kotlin backing property, one mutable private internal only variable
    // and one public LiveData variable for read only purposes.
    private var _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    // The flag indicating a new shoe details was completed with Kotlin backing property,
    // one mutable private internal only variable and one public LiveData variable for read only purposes.
    private var _shoeDetailsCompleted = MutableLiveData<Boolean>()
    val shoeDetailsCompleted: LiveData<Boolean>
        get() = _shoeDetailsCompleted

    fun init() {
        Timber.i("init called")
        // initialize a new shoe during init
        shoe = Shoe("", 0.0, "", "")
    }

    /**
     * Add a new shoe to the viewModel, also sets event for whether it was successful, so that
     * the save button can properly navigate back to the listing screen
     * (this app won't handle error, but this would allow it otherwise - future expansion maybe)
     */
    fun addShoe() {
        Timber.i("addShoe called")
        shoe?.size = shoeSizeModel.shoeSize.ifBlank { "0.0" }.toDouble()
        // Ensure the shoe isn't set to null
        shoe?.let {
            shoes.add(it)
            _shoeList.value = shoes
            addShoeComplete()
        }
    }

    /**
     * Finished adding shoe details - or canceling adding shoe details
     */
    fun addShoeComplete() {
        Timber.i("addShoeComplete called")
        _shoeDetailsCompleted.value = true
    }

    /**
     * Reset add shoe complete flag
     */
    fun resetAddShoeComplete() {
        Timber.i("resetAddShoeComplete called")
        _shoeDetailsCompleted.value = false
    }

    /**
     * Clear all shoes - happens during a logout so there is no need to reassign the mutable live
     * data, as it will get assigned properly again later if logged back in.
     */
    fun clearAllShoes() {
        shoes.clear()
    }

    // Following https://developer.android.com/topic/libraries/data-binding/two-way
    inner class ShoeSizeModel : BaseObservable() {
        var shoeSize: String = ""

        @Bindable
        fun getSize(): String {
            return shoeSize
        }

        fun setSize(value: String) {
            // Avoids infinite loops.
            if (shoeSize != value) {
                shoeSize = value

                // Notify observers of a new value.
                notifyPropertyChanged(BR.size)
            }
        }
    }

}