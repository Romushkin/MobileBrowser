package com.example.mobile_browser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var gridGV: GridView

    private val websites = mutableListOf(
        GridViewModal("Яндекс", R.drawable.yandex, "https://ya.ru/"),
        GridViewModal("Gismeteo", R.drawable.gismeteo, "https://www.gismeteo.ru/"),
        GridViewModal("Google", R.drawable.google, "https://www.google.com/"),
        GridViewModal("Yahoo", R.drawable.yahoo, "https://www.yahoo.com/")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        gridGV = findViewById(R.id.gridGV)

        val adapter = GridViewAdapter(websites, this@MainActivity)
        gridGV.adapter = adapter

        gridGV.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val website = websites[position]
            val intent = Intent(this, BrowserActivity::class.java)
            intent.putExtra("url", website.url)
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}