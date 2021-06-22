package com.example.madefikr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.util.Constants
import com.example.madefikr.R
import com.example.madefikr.databinding.ActivityDetailBinding
import com.example.madefikr.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA = "extra"
    }

    private var recipeSaved = false
    private var savedRecipeId = 0


    private lateinit var menuItem: MenuItem
    private lateinit var favourite: MovieDomain

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val argument = intent.getParcelableExtra<MovieDomain>(EXTRA)
        favourite = argument!!
        setView(argument)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        menuItem = menu!!.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(menuItem)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        viewModel.favMovieList.observe(this, { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.id == favourite.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id!!
                        recipeSaved = true
                    }

                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun saveToFavorites(item: MenuItem) {
        viewModel.insertFavMovie(favourite)
        changeMenuItemColor(item, R.color.yellow)
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        viewModel.deleteFavMovie(favourite)
        changeMenuItemColor(item, R.color.darker)
        recipeSaved = false
    }


    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

    private fun setView(user: MovieDomain?) {
        user?.let {
            val context = applicationContext
            val ratingText = "Rating = " + user.vote_average.toString()
            val votesText = "Total Votes = " +user.vote_count.toString()
            val popularityText = "Popularity = " + user.popularity
            Glide.with(context)
                .load(Constants.BASE_IMAGEURL + user.poster_path)
                .error(R.drawable.ic_error_placeholder)
                .into(binding.poster)
            Glide.with(context)
                .load(Constants.BASE_IMAGEURL + user.backdrop_path)
                .error(R.drawable.ic_error_placeholder)
                .into(binding.backdrop)
            //binding.imageDisplayPicture.load(user.avatar_url)
            binding.title.text = user.original_title
            binding.language.text = user.original_language
            binding.rating.text =
                ratingText
            binding.votes.text =
                votesText
            binding.toolbar.title = user.original_title
            binding.overviewText.text=user.overview
            binding.popularity.text =
                popularityText

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItemColor(menuItem, R.color.darker)
    }

}