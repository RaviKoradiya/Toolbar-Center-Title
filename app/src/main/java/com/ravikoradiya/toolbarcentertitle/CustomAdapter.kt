package com.ravikoradiya.toolbarcentertitle

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ravikoradiya.library.orZero


class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    lateinit var inflater: LayoutInflater
    private var context: Context? = null
    private var menu: Menu? = null
    private var toolbar: Toolbar? = null

    constructor(context: Context, menu: Menu, toolbar: Toolbar) : this() {
        inflater = LayoutInflater.from(context)
        this.context = context
        this.menu = menu
        this.toolbar = toolbar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        val holder = MyViewHolder(view);
        return holder;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.serial_number.text = "Menu Count ${position}"
        holder.itemView.setOnClickListener {
            for (i in 1..menu?.size().orZero()) {
                menu?.getItem(i - 1)?.isVisible = position >= i
            }
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var serial_number: TextView

        init {
            serial_number = itemView.findViewById<TextView>(android.R.id.text1)
        }
    }

}