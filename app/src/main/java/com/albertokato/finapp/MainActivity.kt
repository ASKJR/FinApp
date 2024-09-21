package com.albertokato.finapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var financialEntries = ArrayList<FinancialEntry>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.extras
        if (intent.hasExtra("financialEntries") && bundle != null) {
            var entries : ArrayList<FinancialEntry>? = null
            if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.TIRAMISU) {
                entries =  bundle.getParcelableArrayList("financialEntries", FinancialEntry::class.java)
            } else {
                entries =  bundle.getParcelableArrayList("financialEntries")
            }
            if (entries != null) {
                financialEntries = entries
            }
        }
    }

    fun close(view: View) {
        finishAffinity();
    }

    fun financialEntryActivity(view: View) {
        val intent = Intent(this, CreateFinancialEntryActivity::class.java)
        intent.putExtra("financialEntries", ArrayList(financialEntries))
        startActivity(intent)
    }

    fun financialListActivity(view: View) {
        val intent = Intent(this, FinancialListActivity::class.java)
        if (financialEntries.isEmpty()) {
            Toast.makeText(this, "Nenhuma operação cadastrada.", Toast.LENGTH_SHORT).show()
            return;
        }
        intent.putExtra("financialEntries", ArrayList(financialEntries))
        startActivity(intent)
    }
}