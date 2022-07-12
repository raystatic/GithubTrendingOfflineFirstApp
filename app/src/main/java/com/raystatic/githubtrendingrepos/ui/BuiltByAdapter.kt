package com.raystatic.githubtrendingrepos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raystatic.githubtrendingrepos.databinding.BuiltByItemLayoutBinding

class BuiltByAdapter: RecyclerView.Adapter<BuiltByAdapter.BuiltByViewHolder>() {

    inner class BuiltByViewHolder(private val binding: BuiltByItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:String){
            Glide.with(itemView)
                .load(data)
                .into(binding.imgBuiltBy)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitData(list: List<String>) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuiltByViewHolder {
        val binding = BuiltByItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BuiltByViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuiltByViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}