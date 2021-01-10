package com.example.firestoreexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firestoreexample.databinding.EditGreetFragmentBinding




class EditGreetFragment : Fragment() {
    lateinit var binding: EditGreetFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()
    var dataGreet:Greet? = null


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
        binding.view = this
    }

    private fun initBundle(){
        arguments?.let {
             dataGreet = it.getParcelable(Companion.KEY_GREET)
        }
    }

    private fun initClick(){
        binding.apply {
            btSend.setOnClickListener {
                dataGreet?.let { greet -> viewModel.upData(greet) }
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

        private const val KEY_GREET = "key_greet"

        fun getData(greet: Greet) =
            EditGreetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Companion.KEY_GREET,greet)
            }
        }
    }
}