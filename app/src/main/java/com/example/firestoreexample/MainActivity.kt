package com.example.firestoreexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.firestoreexample.databinding.ActivityMainBinding


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
        initEdit()
        // TODO: 2020/12/31 SwipeLayout from btUpdate
        // TODO: 2021/01/01 Update
    }

    private  fun initBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.view = viewModel
    }

    private fun initData(){
        loadAndShow()
    }

    private fun initClick() {
        binding.apply {
            btUpdate.setOnClickListener {
                loadAndShow()
            }
            btSend.setOnClickListener {
                viewModel.sendGreet()
                binding.setGreet.setText("")
            }
        }
    }

    private fun initEdit(){
            launcherEditGreetFragment()
    }

    private fun loadAndShow(){
        viewModel.apply {
            loadBoard()
            greetList.observe(this@MainActivity, Observer {
                binding.greetView.customAdapter.refresh(it)
            })
        }
    }

    private fun launcherEditGreetFragment(){
        viewModel.editGreet.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,EditGreetFragment.getData(it.id, it.greeting))
                .commit()
        })
    }
}