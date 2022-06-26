package com.example.pokedex.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.ui.detail.DetailActivity
import com.example.pokedex.ui.home.adapter.RecyclerViewAdapter
import com.example.pokedex.ui.home.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var rvPokemons: RecyclerView
    lateinit var pbLoadPokemons: ProgressBar
    private lateinit var viewModel: MainActivityViewModel
    private var offset = 0


    val layoutManager = LinearLayoutManager(this)
    private lateinit var scrollListener: RecyclerView.OnScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pbLoadPokemons = findViewById(R.id.pbLoadPokemons)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.pokemonList.observe(this){
            (rvPokemons.adapter as RecyclerViewAdapter).setData(it)
        }
        viewModel.isLoading.observe(this){
            pbLoadPokemons.isVisible = it
        }

        initRecycleView()
    }

    private fun initRecycleView(){
        rvPokemons = findViewById(R.id.rvPokemons)
        rvPokemons.layoutManager = layoutManager
        rvPokemons.adapter = RecyclerViewAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it)
            startActivity(intent)
        }

        viewModel.getPokemons(offset)

        rvPokemons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)){
                    Log.d("TAG", "onScrolled: Final lista")
                    offset+= 20
                    viewModel.getPokemons(offset)
                }
            }
        })

    }


}