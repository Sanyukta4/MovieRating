package com.example.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieList.IListItemClick
import com.example.movieList.databinding.RvListItemBinding
import com.example.movieList.db.entity.Results

class MovieDataAdapter(private var iListItemClick: IListItemClick): ListAdapter<Results, RecyclerView.ViewHolder>(ImageDiffCallback()){

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            RvListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),iListItemClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ImageViewHolder).bind(getItem(position) as Results)
    }


    class ImageViewHolder(
        private val binding: RvListItemBinding,private val iListItemClick: IListItemClick
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
                item: Results
        ) {
            binding.apply {
                movieList = item
                executePendingBindings()
            }
            binding.clList.setOnClickListener {
                iListItemClick.viewMovieData(item)
            }

        }
    }


}
class ImageDiffCallback : DiffUtil.ItemCallback<Results>() {




    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }
}