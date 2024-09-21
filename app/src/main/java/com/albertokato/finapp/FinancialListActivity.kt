package com.albertokato.finapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinancialListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_financial_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listView = findViewById<ListView>(R.id.listView)
        val financialEntries = intent.getSerializableExtra("financialEntries") as? ArrayList<FinancialEntry>
        //how to make type green or red depending on the type

        val financialEntriesString = financialEntries?.map { "${it.type} - ${it.description} - R$${String.format("%.2f", it.value)}" }?.toTypedArray()
        if (financialEntriesString != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, financialEntriesString)
            listView.adapter = adapter
        }
    }
}