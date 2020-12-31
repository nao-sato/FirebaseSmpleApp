package com.example.firestoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
        // TODO: 2020/12/31 swipe 
    }

    private  fun initBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.view = viewModel
    }

    private fun initData(){
        viewModel.loadBoard()
        binding.greetView.customAdapter.refresh(viewModel.repository.greetList)
    }

    private fun initClick(){
        binding.btUpdate.setOnClickListener{
            viewModel.loadBoard()
            binding.greetView.customAdapter.refresh(viewModel.repository.greetList)
        }
        binding.btSend.setOnClickListener{
            viewModel.sendGreet()
            binding.edGreet.setText("")
        }
    }
}