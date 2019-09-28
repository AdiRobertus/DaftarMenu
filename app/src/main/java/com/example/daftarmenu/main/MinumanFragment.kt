package com.example.daftarmenu.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daftarmenu.R
import com.example.daftarmenu.R.layout
import com.example.daftarmenu.data.MenuMakananModel
import com.example.daftarmenu.data.MenuMinumanModel
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makanan
import kotlinx.android.synthetic.main.minuman_fragmen.rv_minuman

class MinumanFragment: Fragment() {

    companion object{
        fun getInstance(): MinumanFragment {
            return MinumanFragment()
        }
    }

    val  dataMinuman = mutableListOf<MenuMinumanModel>()
    val rvAdapter=RvMinumanAdapter(dataMinuman)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.minuman_fragmen,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_minuman.adapter=rvAdapter
        rv_minuman.layoutManager=LinearLayoutManager(context)

        addDummyData()
    }

    private  fun  addDummyData(){
        dataMinuman.add(MenuMinumanModel("Es Jeruk","Rp. 5.000", R.drawable.es_jeruk))
        dataMinuman.add(MenuMinumanModel("Es Teh","Rp. 5.000", R.drawable.es_teh))
        dataMinuman.add(MenuMinumanModel("Es Dawet","Rp. 5.000", R.drawable.es_dawet))

        rvAdapter.notifyDataSetChanged()
    }
}