package com.example.firestoreexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.firestoreexample.R
import com.example.firestoreexample.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initBinding()
        initClick()
        initData()
        initObserver()
    }

    private  fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.view = viewModel
    }

    private fun initData(){
        viewModel.loadGreet()
    }

    private fun initClick() {
        binding.apply {
            btUpdate.setOnClickListener {
                viewModel.loadGreet()
            }
            btSend.setOnClickListener {
                viewModel.sendGreet()
                binding.setGreet.setText("")
            }
        }
    }

    private fun initObserver(){

        viewModel.apply {

            greetList.observe(this@MainActivity, Observer {
                binding.greetView.customAdapter.refresh(it)
                Timber.d("TimberObserve:${it.size}")
            })

            editGreet.observe(this@MainActivity, Observer {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EditGreetFragment.getData(it))
                        .commit()
            })
        }
    }
}