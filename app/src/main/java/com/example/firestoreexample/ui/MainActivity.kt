package com.example.firestoreexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.example.firestoreexample.R
import com.example.firestoreexample.databinding.ActivityMainBinding
import com.example.firestoreexample.databinding.ProgressBinding
import com.google.android.material.dialog.MaterialDialogs
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var progressDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initBinding()
        initClick()
        initData()
        initViewModel()
    }

    private  fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.view = viewModel
    }

    private fun initData(){
        viewModel.initData()
    }

    private fun initClick() {

        binding.apply {

            btUpdate.setOnClickListener {
                viewModel.initData()
            }

            btSend.setOnClickListener {
                viewModel.sendGreet()
                binding.setGreet.setText("")
            }
        }
    }

    private fun initViewModel(){

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

            isShownProgress.observe(this@MainActivity, Observer {
                if (it) {
                    showProgress()
                }else{
                    hideProgress()
                }
            })

        }
    }

    private fun showProgress(){
        hideProgress()
        progressDialog = MaterialDialog(this).show {
            cancelable(false)
            val binding = ProgressBinding.inflate(LayoutInflater.from(context),null,false)
            setContentView(binding.root)
        }
    }
    private fun hideProgress(){
        progressDialog?.dismiss()
        progressDialog = null
    }
}