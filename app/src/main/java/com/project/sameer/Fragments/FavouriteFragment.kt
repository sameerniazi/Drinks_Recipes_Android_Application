package com.project.sameer.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.sameer.Adaptor.FvtAdaptor
import com.project.sameer.AppDatabase
import com.project.sameer.Constant.Companion.database
import com.project.sameer.DataClass.RoomData
import com.project.sameer.databinding.FragmentFavouriteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteFragment : Fragment() {

    lateinit var binding:FragmentFavouriteBinding
    val adaptor: FvtAdaptor by lazy{ FvtAdaptor() }
    lateinit var  drinks:List<RoomData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavouriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       database = AppDatabase.getDatabase(requireContext())
        binding.fvtRecyclerview.adapter=adaptor
        CoroutineScope(Dispatchers.IO).launch {
         drinks=    database.userDao().getAllDrinks()
            withContext(Dispatchers.Main){
                adaptor.setData(drinks)
            }
        }


    }


}