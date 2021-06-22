package com.example.madefikr.favourite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madefikr.core.adapter.MovieAdapter
import com.example.madefikr.favourite.databinding.ActivityFavouriteBinding
import com.example.madefikr.favourite.di.favViewModelModule
import com.example.madefikr.favourite.viewmodel.FavViewModel
import com.example.madefikr.ui.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private val favViewModel: FavViewModel by viewModel()
    private val mAdapter: MovieAdapter by lazy { MovieAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FavActivity", "Hello")
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favViewModelModule)

        mAdapter.onItemClick = { selected ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA, selected)
            startActivity(intent)
        }

        getData()
        setupRecyclerView(binding.recyclerviewFavMovie)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@FavouriteActivity)
    }

    private fun getData() {
        showLoading()
        favViewModel.favMovieList.observe(this, { response ->
            if(!response.isEmpty()){
                hideLoading()
                response?.let {
                    hideNotFound()
                    mAdapter.setData(it)
                }
            } else {
                hideLoading()
                showNotFound()
            }

        })
    }

    private fun hideNotFound() {
        binding.notFoundMessage.visibility = View.GONE
        binding.notFoundImage.visibility = View.GONE
        binding.recyclerviewFavMovie.visibility = View.VISIBLE

    }

    private fun showNotFound() {
        binding.notFoundMessage.visibility = View.VISIBLE
        binding.notFoundImage.visibility = View.VISIBLE
        binding.recyclerviewFavMovie.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerviewFavMovie.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerviewFavMovie.visibility = View.VISIBLE
    }


}