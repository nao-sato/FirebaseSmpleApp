package com.example.firestoreexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firestoreexample.databinding.EditGreetFragmentBinding

private const val KEY_GREETING = "key_greeting"
private const val KEY_ID = "key_id"

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
            viewModel.dataId = it.getLong(KEY_ID)
            viewModel.dataGreeting = it.getString(KEY_GREETING)
        }
    }

    private fun initClick(){
        binding.apply {
            btSend.setOnClickListener {
                viewModel.updateData()
            }
            btCancel.setOnClickListener{
                childFragmentManager.beginTransaction()
                        .remove(this@EditGreetFragment)
                        .commit()
            }
        }
    }

    companion object {

        fun getData(id:Long,greeting:String) =
            EditGreetFragment().apply {
                arguments = Bundle().apply {
                putLong(KEY_ID,id)
                putString(KEY_GREETING,greeting)
            }

        }
    }

}