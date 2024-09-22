package com.albertokato.finapp

import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

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
        val bundle = intent.extras
        val financialEntries =  if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelableArrayList("financialEntries", FinancialEntry::class.java)
        } else {
            bundle?.getParcelableArrayList("financialEntries")
        }
        val financialEntriesString = financialEntries?.map {
                val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                val formattedAmount = format.format(it.value)
                if (it.type == "Crédito") {
                    "${it.type}: ${it.description} +${formattedAmount}";
                } else {
                    "${it.type}: ${it.description} -${formattedAmount}";
                }
        }?.toList()
        if (financialEntriesString != null) {
            val adapter = CustomAdapter(this, financialEntriesString)
            listView.adapter = adapter
        }
    }
}