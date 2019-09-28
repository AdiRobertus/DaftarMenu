package com.example.daftarmenu.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.daftarmenu.R
import com.example.daftarmenu.data.MenuMakananModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_menu.iv_menu
import kotlinx.android.synthetic.main.item_list_menu.tv_harga
import kotlinx.android.synthetic.main.item_list_menu.tv_menu

class RvMakananAdapter(private val data: MutableList<MenuMakananModel>) : RecyclerView.Adapter<RvMakananAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_menu,parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class MenuViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView)
    , LayoutContainer {
        fun bindData(item:MenuMakananModel){
            tv_menu.text=item.namaMenu
            tv_harga.text=item.hargaMenu
            iv_menu.setImageResource(item.gambarMenu)

            itemView.setOnClickListener {
                Toast.makeText(containerView.context,item.namaMenu,Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}