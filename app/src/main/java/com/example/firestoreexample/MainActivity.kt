package com.example.firestoreexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        // TODO: 2020/12/31 SwipeLayout from btUpdate
        // TODO: 2021/01/01 Update
    }

    private  fun initBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.view = viewModel
    }

    private fun initData(){
        viewModel.loadBoard()
    }

    private fun initClick() {
        binding.apply {
            btUpdate.setOnClickListener {
                viewModel.loadBoard()
            }
            btSend.setOnClickListener {
                viewModel.sendGreet()
                binding.setGreet.setText("")
            }
        }
    }

    private fun initObserver(){

        Repository().greetList.observe(this@MainActivity, Observer {
            binding.greetView.customAdapter.refresh(it)
            Timber.d("Timberfragment2:${it.size}")

        })

        viewModel.editGreet.observe(this@MainActivity, Observer {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, EditGreetFragment.getData(it))
                    .commit()
        })
    }
}