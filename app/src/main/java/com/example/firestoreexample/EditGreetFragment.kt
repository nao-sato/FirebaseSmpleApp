package com.example.firestoreexample

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firestoreexample.databinding.EditGreetFragmentBinding

private const val KEY_GREET = "key_greet"

class EditGreetFragment : Fragment() {

    private val viewModel: EditGreetViewModel by viewModels()
    lateinit var binding: EditGreetFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EditGreetFragmentBinding.inflate(inflater,container,false)
        initialize()
        return binding.root
    }

    private fun initialize(){
        initView()
        initBundle()
        initClick()
    }

    private fun initView(){
        binding.view = viewModel
    }

    private fun initBundle(){
        arguments?.let {
            viewModel.dataGreet = it.getParcelable(KEY_GREET)
        }
    }

    private fun initClick(){
        binding.apply {
            btSend.setOnClickListener {
                viewModel.updateData()
                closeFragment()

            }
            btCancel.setOnClickListener{
               closeFragment()
            }
        }
    }

    private fun closeFragment(){
        activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this@EditGreetFragment)
                ?.commit()
    }

    companion object {

        fun getData(greet: Greet) =
            EditGreetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GREET,greet)
            }
        }
    }

}