package com.raystatic.githubtrendingrepos.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raystatic.githubtrendingrepos.R
import com.raystatic.githubtrendingrepos.databinding.ActivityMainBinding
import com.raystatic.githubtrendingrepos.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val viewModel by viewModels<TrendingRepoViewModel>()

    private lateinit var trendingRepoItemAdapter: TrendingRepoItemAdapter

    private lateinit var blinkAnimation: Animation

    private var mSearchView:SearchView?=null
    private var mSearch:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trendingRepoItemAdapter = TrendingRepoItemAdapter(
            onClick = {data->
                viewModel.selectRepoItem(data)
            }
        )

        blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)

        initUI()

        subscribeToObservers()



    }

    private fun subscribeToObservers() {

        viewModel.trendingLiveData.distinctUntilChanged().observe(this){
            trendingRepoItemAdapter.submitData(it)
        }
        viewModel.trendingReposResponse.observe(this){
           binding.apply {
               when(it.status){
                   Status.SUCCESS -> {
                       relLoading.isVisible = false
                   }

                   Status.LOADING -> {
                       relLoading.isVisible = true
                       tvFetching.startAnimation(blinkAnimation)
                   }

                   Status.ERROR -> {

                       tvFetching.animation = null
                        tvFetching.text = it.message
                       lifecycleScope.launch {
                           delay(5000L)
                           relLoading.isVisible = false
                       }
                   }
               }
           }
        }
    }

    private fun initUI() {
        binding.apply {

            rvTrendingRepos.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = trendingRepoItemAdapter
                addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        mSearch = menu.findItem(R.id.appSearchBar)
        mSearchView = mSearch?.actionView as SearchView
        mSearchView?.queryHint = "Start typing..."
        mSearchView?.setOnQueryTextListener(
        object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchQuery(query = newText ?: "")
                   return false
                }
            }
        )

        val currentQuery = viewModel.currentQuery.value

        if (!currentQuery.isNullOrEmpty()){
            mSearch?.expandActionView()
            mSearchView?.setQuery(currentQuery, false)
            viewModel.searchQuery(currentQuery)
            mSearchView?.clearFocus()
        }

        return super.onCreateOptionsMenu(menu)

    }

}