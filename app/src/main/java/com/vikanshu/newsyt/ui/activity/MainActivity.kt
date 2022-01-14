package com.vikanshu.newsyt.ui.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.ActivityMainBinding
import com.vikanshu.newsyt.model.Filters
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var filters: Filters
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = nav_host.findNavController()
        viewModel.getSearchResults().observe(this, {
            filters = it.first
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.menu_main_search).actionView as SearchView
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.querySearch(filters.apply {
                    if (query != null) {
                        this.query = query
                    }
                })
                return false
            }

            override fun onQueryTextChange(query: String?) = false
        })
        MenuItemCompat.setOnActionExpandListener(
            menu.findItem(R.id.menu_main_search),
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    searchView.setQuery(filters.query, false)
                    navController.navigate(R.id.action_headlinesFragment_to_searchFragment)
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    navController.navigateUp()
                    return true
                }
            })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_main_saved) {
            startActivity(Intent(this, SavedArticlesActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}