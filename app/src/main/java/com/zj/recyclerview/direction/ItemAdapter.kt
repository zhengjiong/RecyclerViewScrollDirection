package com.zj.recyclerview.direction

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_card_layout.view.*

/**
 *
 * CreateTime:2018/10/30  11:34
 * @author 郑炯
 * @version 1.0
 */
class ItemAdapter(val items: MutableList<String>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_layout, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.title.text = items[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "item click", Toast.LENGTH_SHORT).show()
        }
        holder.itemView.btn.setOnClickListener {
            Toast.makeText(holder.itemView.context, "按钮click", Toast.LENGTH_SHORT).show()
        }
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}