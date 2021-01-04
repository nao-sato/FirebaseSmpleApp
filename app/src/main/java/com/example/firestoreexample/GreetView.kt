package com.example.firestoreexample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firestoreexample.databinding.GreetBinding
import timber.log.Timber

class GreetView:RecyclerView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, detStyleAttr: Int): super(context, attrs, detStyleAttr)

    val customAdapter by lazy { Adapter(context) }

    init {
        adapter = customAdapter
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
}

class Adapter(private val context: Context):RecyclerView.Adapter<Adapter.ItemViewHolder>(){
    private var itemList = mutableListOf<Greet>()
    private val viewModel: MainViewModel by (context as ComponentActivity).viewModels()


    fun refresh(list:List<Greet>){
        itemList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(GreetBinding.inflate(LayoutInflater.from(context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = itemList[position]
        holder.binding.apply {
            greetItem = data
            editView.setOnClickListener{
                viewModel.editGreet.postValue(data)
                Timber.d("Timberfragment1")
            }
            Timber.d("Timberfragment:${editView}")
        }

    }


    class ItemViewHolder(var binding: GreetBinding):RecyclerView.ViewHolder(binding.root)
}



