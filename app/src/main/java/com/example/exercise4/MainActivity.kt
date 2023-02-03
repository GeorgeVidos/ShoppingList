package com.example.exercise4

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var shopInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var addbtn: ImageButton
    private lateinit var deleteallbtn: ImageButton
    var data = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopInput = findViewById(R.id.ShopInput)
        addbtn = findViewById(R.id.addbtn)
        deleteallbtn = findViewById(R.id.deleteallbtn)


        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = findViewById(R.id.ShoprecyclerView)
        recyclerView.adapter = MyAdapter(recyclerView, data)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addbtn.setOnClickListener {
            val newItem = shopInput.text.toString().trim()
            if (newItem.isNotEmpty() && newItem.length <= 10) {
                val position = data.size
                data.add(newItem)
                recyclerView.adapter?.notifyItemInserted(position)
                shopInput.setText("")
            }
        }
        deleteallbtn.setOnClickListener {
            if (recyclerView.adapter != null && recyclerView.adapter!!.itemCount > 0) {
                val deletepopup = AlertDialog.Builder(this)
                deletepopup.setTitle("Delete everything")
                deletepopup.setMessage("You are about to delete every content of the list")
                deletepopup.setPositiveButton("Delete") { _, _ ->
                    data.clear()
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                deletepopup.setNegativeButton("Cancel") { _, _ -> }
                val dialog: AlertDialog = deletepopup.create()
                dialog.show()
            }
        }


    }
}