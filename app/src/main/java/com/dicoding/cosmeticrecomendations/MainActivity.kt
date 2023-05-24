package com.dicoding.cosmeticrecomendations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCosmetics: RecyclerView
    private val list = ArrayList<Cosmetic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Rekomendasi Cosmetic"

        rvCosmetics = findViewById(R.id.rv_cosmetics)
        rvCosmetics.setHasFixedSize(true)

        list.addAll(getListCosmetics())
        showRecyclerList()
    }

    private fun getListCosmetics(): ArrayList<Cosmetic> {
        val dataName = resources.getStringArray(R.array.data_cosmetics)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataSize = resources.getStringArray(R.array.data_size)
        val dataBenefit = resources.getStringArray(R.array.data_benefit)
        val listCosmetic = ArrayList<Cosmetic>()
        for (i in dataName.indices) {
            val cosmetic = Cosmetic(dataName[i], dataDescription[i], dataPhoto[i], dataPrice[i], dataSize[i], dataBenefit[i])
            listCosmetic.add(cosmetic)
        }
        return listCosmetic
    }

    private fun showRecyclerList() {
        rvCosmetics.layoutManager = LinearLayoutManager(this)
        val listCosmeticAdapter = ListCosmeticAdapter(list)
        rvCosmetics.adapter = listCosmeticAdapter

        listCosmeticAdapter.setOnItemClickCallback(object : ListCosmeticAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cosmetic) {
                showSelectedCosmetic(data)
            }
        })
    }

    private fun showSelectedCosmetic(cosmetic: Cosmetic) {
        Toast.makeText(this, "Kamu memilih " + cosmetic.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}