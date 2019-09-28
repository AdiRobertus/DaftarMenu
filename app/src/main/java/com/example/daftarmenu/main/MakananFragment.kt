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
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makanan

class MakananFragment: Fragment() {

    companion object{
        fun getInstance(): MakananFragment {
            return MakananFragment()
        }
    }

    val  dataMakanan = mutableListOf<MenuMakananModel>()
    val rvAdapter=RvMakananAdapter(dataMakanan)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            layout.makanan_fragmen,
            container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_makanan.adapter=rvAdapter
        rv_makanan.layoutManager=LinearLayoutManager(context)

        addDummyData()
    }

    private  fun  addDummyData(){
        dataMakanan.add(MenuMakananModel("sate","Rp. 5.000", R.drawable.sate))
        dataMakanan.add(MenuMakananModel("martabak","Rp. 15.000", R.drawable.martabak))
        dataMakanan.add(MenuMakananModel("Ikan Goreng","Rp. 10.000",R.drawable.ikan))

        rvAdapter.notifyDataSetChanged()
    }
}