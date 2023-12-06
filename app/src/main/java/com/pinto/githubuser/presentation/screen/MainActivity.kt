package com.pinto.githubuser.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinto.githubuser.R
import com.pinto.githubuser.databinding.ActivityMainBinding
import com.pinto.githubuser.presentation.adapter.UserAdapter
import com.pinto.githubuser.presentation.viewmodel.MainViewModel
import com.pinto.githubuser.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()

        binding.rvRepo.layoutManager = LinearLayoutManager(this)
        binding.rvRepo.adapter = adapter

        initObserve()



    }

    private fun initObserve() {
        mainViewModel.repository.observe(this) {
            val rootView = findViewById<View>(R.id.rootView)
            when(it.status){
                Status.SUCCESS ->{
                    binding.progress.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                    adapter.submitList(it.data!!)
                }

                Status.LOADING ->{
                    binding.progress.visibility = View.VISIBLE
                    binding.rvRepo.visibility = View.GONE
                }

                Status.ERROR ->{
                    binding.progress.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                }
            }
        }
    }
}