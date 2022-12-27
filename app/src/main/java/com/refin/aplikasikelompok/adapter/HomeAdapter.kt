package com.refin.aplikasikelompok.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.refin.aplikasikelompok.DetailActivity
import com.refin.aplikasikelompok.HomeItem
import com.refin.aplikasikelompok.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_item.*

class HomeAdapter(
    private val context: Context
    , private val items: ArrayList<HomeItem>
):
RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: HomeItem) {
            iv_gambar.setImageResource(item.gambar)
            tv_nama_tempat.text=item.judul
            tv_deskripsi_tempat.text=item.deskripsi
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(items.get(position))
        val item = items[position]
        holder.bindItem(item)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("judul", item.judul)
            intent.putExtra("deskripsi", item.deskripsi)
            intent.putExtra("gambar", item.gambar)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(context).inflate(
            R.layout.home_item, parent, false
        ))
}
