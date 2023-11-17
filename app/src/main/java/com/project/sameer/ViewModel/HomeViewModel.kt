package com.project.sameer.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.sameer.DataClass.Drink
import com.project.sameer.DataClass.DrinkData


import com.project.sameer.Reporisity.Reporsity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var TAG="HomeViewModelTag"
     val repository: Reporsity = Reporsity()

    fun searchByName(name:String){
       CoroutineScope(Dispatchers.IO).launch {
           repository.searchByName(name)
       }

    }
    fun searchByAlpha(alpha:String){
       CoroutineScope(Dispatchers.IO).launch {
           repository.searchByAlpha(alpha)
       }

    }



}