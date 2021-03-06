package com.kagion.toko.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagion.toko.R
import com.kagion.toko.model.DataItem
import kotlinx.android.synthetic.main.item_data.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DataAdapter(val data: List<DataItem>? , private val click: onClickItem) : RecyclerView.Adapter<DataAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
        holder.itemView.btnHapus.setOnClickListener {
            click.delete(data?.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: DataItem?) {
            itemView.tvName.text = get?.tokoName
            itemView.tvPhone.text = get?.tokoHp
            itemView.tvAddress.text = get?.tokoAlamat
        }
    }

    interface onClickItem{
        fun clicked (item: DataItem?)
        fun delete(item: DataItem?)

    }
}