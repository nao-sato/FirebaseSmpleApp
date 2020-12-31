package com.example.firestoreexample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firestoreexample.databinding.GreetBinding

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

    fun refresh(list: List<Greet>){
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
        val item = itemList[position]
        holder.binding.greetItem = item
    }


    class ItemViewHolder(var binding: GreetBinding):RecyclerView.ViewHolder(binding.root)
}



