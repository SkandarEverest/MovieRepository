package com.example.madefikr.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.util.Constants.Companion.BASE_IMAGEURL
import com.example.madefikr.core.util.UsersDiffUtil
import com.example.madefikr.core.util.extension.loadImage
import com.example.madefikr.core.databinding.MovieRowLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var recipes = emptyList<MovieDomain>()
    var onItemClick: ((MovieDomain) -> Unit)? = null


    inner class MyViewHolder(private val binding: MovieRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: MovieDomain){
            binding.result = result
            val urlString = BASE_IMAGEURL + result.poster_path
            binding.recipeImageView.loadImage(
                urlString
            )
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(recipes[absoluteAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = MovieRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: List<MovieDomain>){
        val recipesDiffUtil =
            UsersDiffUtil(recipes, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}