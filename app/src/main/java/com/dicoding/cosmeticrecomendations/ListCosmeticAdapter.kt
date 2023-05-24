package com.dicoding.cosmeticrecomendations

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.cosmeticrecomendations.databinding.ItemRowCosmeticBinding

class ListCosmeticAdapter(private val listCosmetic: ArrayList<Cosmetic>) : RecyclerView.Adapter<ListCosmeticAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowCosmeticBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCosmeticBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, price, size, benefit) = listCosmetic[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailCosmetic::class.java).apply {
                putExtra("key_cosmetic", listCosmetic[holder.adapterPosition])
                putExtra("price", price)
                putExtra("size", size)
                putExtra("benefit", benefit)
            }
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listCosmetic.size
    interface OnItemClickCallback {
        fun onItemClicked(data: Cosmetic)
    }
}