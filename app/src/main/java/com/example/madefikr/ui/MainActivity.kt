package com.example.madefikr.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madefikr.core.adapter.MovieAdapter
import com.example.madefikr.core.util.vo.Resource
import com.example.madefikr.R
import com.example.madefikr.databinding.ActivityMainBinding
import com.example.madefikr.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val mAdapter: MovieAdapter by lazy { MovieAdapter() }
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        mAdapter.onItemClick = { selected ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA, selected)
            startActivity(intent)
        }
        getData()
        setupRecyclerView(binding.recyclerviewMovie)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menuItem = menu!!.findItem(R.id.go_to_favorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.go_to_favorite) {
            val uri = Uri.parse(getString(R.string.uri_name))
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun getData() {
        showLoading()
        viewModel.movieList.observe(this, { response ->
            when (response) {

                is Resource.Loading -> {
                    showLoading()
                    hideNotFound()
                }
                is Resource.Success -> {
                    hideLoading()
                    val movie = response.data
                    movie?.let {
                        hideNotFound()
                        mAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    hideLoading()
                    showNotFound()
                    Toast.makeText(
                        this@MainActivity,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun hideNotFound() {
        binding.notFoundMessage.visibility = View.GONE
        binding.notFoundImage.visibility = View.GONE
        binding.recyclerviewMovie.visibility = View.VISIBLE

    }

    private fun showNotFound() {
        binding.notFoundMessage.visibility = View.VISIBLE
        binding.notFoundImage.visibility = View.VISIBLE
        binding.recyclerviewMovie.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerviewMovie.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerviewMovie.visibility = View.VISIBLE
    }

}