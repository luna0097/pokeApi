package com.example.pokedex.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.ui.detail.DetailActivity
import com.example.pokedex.ui.home.adapter.RecyclerViewAdapter
import com.example.pokedex.ui.home.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbLoadPokemons
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.pokemonList.observe(this){
            (binding.rvPokemons.adapter as RecyclerViewAdapter).setData(it)
        }
        viewModel.isLoading.observe(this){
            binding.pbLoadPokemons.isVisible = it
        }

        initRecycleView()
    }

    private fun initRecycleView(){
        binding.rvPokemons.layoutManager = LinearLayoutManager(this)
        binding.rvPokemons.adapter = RecyclerViewAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it)
            startActivity(intent)
        }

        viewModel.getPokemons(offset)

        binding.rvPokemons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(dy)){
                    offset+= 20
                    viewModel.getPokemons(offset)
                }
            }
        })

    }


}