package com.albertokato.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateFinancialEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_financial_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun createFinancialEntry(view: View) {
        val description = findViewById<EditText>(R.id.editTextText).text.toString()
        val value = findViewById<EditText>(R.id.editTextNumber).text.toString().toDoubleOrNull()
        if (description.isEmpty() || value == null) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }
        val type = if (findViewById<RadioButton>(R.id.radioButtonDebito).isChecked) "Débito" else "Crédito"
        val financialEntry = FinancialEntry(type, description, value)
        val financialEntries = intent.getSerializableExtra("financialEntries") as? ArrayList<FinancialEntry>
        financialEntries?.add(financialEntry);
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("financialEntries", financialEntries)
        Toast.makeText(this, "Operação cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}