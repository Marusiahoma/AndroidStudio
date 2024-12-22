package com.example.listviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BookAdapter(private val context: Context, private val books: List<Book>) : BaseAdapter() {

    override fun getCount(): Int = books.size

    override fun getItem(position: Int): Any = books[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_list_item_2, parent, false
        )

        val book = books[position]

        val titleView = view.findViewById<TextView>(android.R.id.text1)
        val authorView = view.findViewById<TextView>(android.R.id.text2)

        titleView.text = book.title
        authorView.text = "Автор: ${book.author}"

        return view
    }
}