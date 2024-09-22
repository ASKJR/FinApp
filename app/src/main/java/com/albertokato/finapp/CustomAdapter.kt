package com.albertokato.finapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomAdapter(context: Context, items: List<String>) : ArrayAdapter<String>(context, R.layout.custom_list_item, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(R.id.customTextView)
        val item = getItem(position)
        if (item != null) {
            if (item.contains("Crédito")) {
                textView.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            } else {
                textView.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            }
            textView.text = item
        }
        return view
    }
}