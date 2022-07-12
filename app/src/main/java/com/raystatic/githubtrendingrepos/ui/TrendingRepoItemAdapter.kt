package com.raystatic.githubtrendingrepos.ui

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raystatic.githubtrendingrepos.R
import com.raystatic.githubtrendingrepos.databinding.BuiltByItemLayoutBinding
import com.raystatic.githubtrendingrepos.databinding.TrendingRepoLayoutItemBinding
import com.raystatic.githubtrendingrepos.domain.models.TrendingRepoItem

class TrendingRepoItemAdapter(
    private val onClick:(TrendingRepoItem) -> Unit
): RecyclerView.Adapter<TrendingRepoItemAdapter.TrendingRepoItemViewHolder>() {

    private var builtByAdapter:BuiltByAdapter?=null

    private val viewPool :RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()


    inner class TrendingRepoItemViewHolder(private val binding: TrendingRepoLayoutItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(data:TrendingRepoItem){
            binding.apply {
                val repoName =  "${data.username}/${data.repositoryName}"
                tvRepoName.text = repoName
                tvDesc.text = data.description
                data.languageColor?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        langColor.background.colorFilter = BlendModeColorFilter(Color.parseColor(it), BlendMode.SRC_ATOP)
                    }else{
                        langColor.background.setColorFilter(Color.parseColor(it), PorterDuff.Mode.SRC_ATOP)
                    }
                }

                tvLang.text = data.language ?: "Unknown"
                tvStars.text = "%,d".format(data.totalStars)
                tvForks.text = "%,d".format(data.forks)

                if (!data.builtBy.isNullOrEmpty()){
                    tvBuiltBy.isVisible = true
                    builtByAdapter = BuiltByAdapter()
                    binding.rvBuiltBy.apply {
                        layoutManager = LinearLayoutManager(root.context,LinearLayoutManager.HORIZONTAL,false)
                        adapter = builtByAdapter
                    }

                    builtByAdapter?.submitData(data.builtBy?.map { it.avatar ?: "" } ?: listOf())

                }


                overlay.isVisible = data.isSelected == true

                root.setOnClickListener { onClick(data) }

            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<TrendingRepoItem>(){
        override fun areItemsTheSame(oldItem: TrendingRepoItem, newItem: TrendingRepoItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TrendingRepoItem,
            newItem: TrendingRepoItem
        ) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitData(list: List<TrendingRepoItem>){
        //originalList.addAll(list)
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRepoItemViewHolder {
        val binding = TrendingRepoLayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.rvBuiltBy.setRecycledViewPool(viewPool)
        return TrendingRepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingRepoItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

//    override fun getFilter(): Filter {
//        return repoFilter
//    }
//
//    private val repoFilter = object : Filter(){
//        override fun performFiltering(filter: CharSequence?): FilterResults {
//            val filteredList = mutableListOf<TrendingRepoItem>()
//            if (filter.isNullOrEmpty()){
//                filteredList.addAll(originalList)
//            }else{
//                val pattern = filter.toString().lowercase().trim()
//                for (item in originalList){
//                    if (item.repositoryName?.lowercase()?.contains(pattern) == true){
//                        filteredList.add(item)
//                    }
//                }
//            }
//            val results = FilterResults()
//            results.values = filteredList
//            return results
//        }
//
//        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//            val list = p1?.values as MutableList<TrendingRepoItem>?
//            differ.submitList(list)
//        }
//    }
}
