package com.example.exercise4

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val recyclerView: RecyclerView, private val data: MutableList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val grosseries: TextView = itemView.findViewById(R.id.grosseries)
        val deleteonebtn: ImageButton = itemView.findViewById(R.id.deleteonebtn)
        val done: CheckBox = itemView.findViewById((R.id.checkBox))
        val editbtn: ImageButton = itemView.findViewById(R.id.editbtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.grosseries.text = data[position]

        holder.deleteonebtn.setOnClickListener {
            val position = holder.adapterPosition
            data.removeAt(position)
            recyclerView.adapter?.notifyItemRemoved(position)
        }

        holder.done.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.grosseries.paintFlags =
                    holder.grosseries.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.grosseries.paintFlags =
                    holder.grosseries.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        holder.editbtn.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            val editText = EditText(holder.itemView.context)
            builder.setView(editText)
            builder.setPositiveButton("OK") { _, _ ->
                val newValue = editText.text.trim()
                if (newValue.isNotEmpty() && newValue.length <= 10) {
                    data[position] = newValue.toString()
                    notifyItemChanged(position)
                }
            }
            builder.setNegativeButton("Cancel") { _, _ -> }
            builder.show()
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }
}

