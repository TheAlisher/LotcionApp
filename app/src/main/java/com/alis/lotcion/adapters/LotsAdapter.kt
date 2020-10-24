package com.alis.lotcion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alis.lotcion.R
import com.alis.lotcion.extensions.loadImage
import com.alis.lotcion.interfaces.OnItemClickListener
import com.alis.lotcion.models.Lot
import kotlinx.android.synthetic.main.item_lots.view.*

class LotsAdapter : RecyclerView.Adapter<LotsAdapter.LotsViewHolder>() {

    private var list = mutableListOf<Lot>()
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotsViewHolder {
        return LotsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lots, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LotsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addAll(list: MutableList<Lot>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

    class LotsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(lot: Lot) {
            itemView.image_item_lot.loadImage(
                lot.image.toString(),
            )
            itemView.text_item_lot_name.text = lot.name
            itemView.text_item_lot_description.text = lot.description
        }
    }
}