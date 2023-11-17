package com.project.sameer.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.sameer.Adaptor.SearchByNameAdaptor
import com.project.sameer.AppDatabase
import com.project.sameer.Constant
import com.project.sameer.Constant.Companion.allDrinks
import com.project.sameer.Constant.Companion.database
import com.project.sameer.DataClass.Drink
import com.project.sameer.DataClass.RoomData
import com.project.sameer.NetworkUtils
import com.project.sameer.SharedPrefernce.SharedPreferencesManager
import com.project.sameer.ViewModel.HomeViewModel
import com.project.sameer.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment(){
    var TAG="HomeFragmentTag"
    lateinit var binding:FragmentHomeBinding
    lateinit var viewmodel:HomeViewModel
    val adaptor: SearchByNameAdaptor by lazy{SearchByNameAdaptor()}
    lateinit var sharedPreferences:SharedPreferencesManager
    lateinit var networkUtils:NetworkUtils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchByNameRecyclerView.adapter=adaptor
        sharedPreferences= SharedPreferencesManager(requireContext())
         networkUtils = NetworkUtils(requireContext())
        database=AppDatabase.getDatabase(requireContext())
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        CoroutineScope(Dispatchers.IO).launch {
            allDrinks = database.userDao().getAllDrinks()
        }
        //Setting Radio Button
        if (sharedPreferences.getgroup()!=null){
            if (sharedPreferences.getgroup().equals(Constant.byname)) {
                binding.radioButtonByFirstAlphabet.isChecked = false
                binding.radioButtonByName.isChecked = true
            }else{
                binding.radioButtonByFirstAlphabet.isChecked = true
                binding.radioButtonByName.isChecked = false
            }
        }


        //Checking Internet
        if (networkUtils.isInternetConnected()){

        if (sharedPreferences.getgroup().equals(Constant.byname))
        viewmodel.searchByName("margarita")
        else
            viewmodel.searchByAlpha("a")
            }
        else{
            if (binding.progress.isVisible){
                binding.progress.visibility=View.GONE
            }
            Toast.makeText(requireContext(), "Internet No Connected", Toast.LENGTH_SHORT).show()
        }

        //Observing Live Data, for the easy use i use live data not flow
        viewmodel.repository.itemData.observe(viewLifecycleOwner, Observer { data ->
            if (binding.progress.isVisible){
                binding.progress.visibility=View.GONE
            }

            //Log.i(TAG, "onViewCreated: ${data}")
            adaptor.setData(data.drinks)
        })


        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
       binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
           val checkedRadioButton: RadioButton = view.findViewById(checkedId)
            val selectedOptionText = checkedRadioButton.text.toString()
           Log.i(TAG, "onViewCreated: ${selectedOptionText}")
            sharedPreferences.savegroup(selectedOptionText)

        }


        adaptor.clickListener= object:SearchByNameAdaptor.onPositionSelected{
            override fun sendingPostion(position: Int, drinkingData: List<Drink>) {
                CoroutineScope(Dispatchers.IO).launch {
                    val dbDring = RoomData(name =drinkingData[position].strDrink, id = 0, itemid = drinkingData[position].idDrink,
                        img = drinkingData[position].strDrinkThumb, discription = drinkingData[position].strInstructions, alchole = drinkingData[position].strAlcoholic)
                    database.userDao().insertDrink(dbDring)
                }
            }

        }
    }


    private fun performSearch(query: String?) {
        if (networkUtils.isInternetConnected()){
        if (sharedPreferences.getgroup().equals(Constant.byname)){
            viewmodel.searchByName(query!!)
        }else{
            viewmodel.searchByAlpha(query!!)
        }
            }
        else{
            if (binding.progress.isVisible){
                binding.progress.visibility=View.GONE
            }
            Toast.makeText(requireContext(), "Internet No Connected", Toast.LENGTH_SHORT).show()
        }
    }



}