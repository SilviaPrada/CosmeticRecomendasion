package com.dicoding.cosmeticrecomendations

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.cosmeticrecomendations.DetailCosmetic.Companion.KEY_COSMETIC
import com.dicoding.cosmeticrecomendations.databinding.ActivityDetailCosmeticBinding

class DetailCosmetic : AppCompatActivity() {

    companion object {
        val KEY_COSMETIC = "key_cosmetic"
    }
    var b: Bundle? = null

    private lateinit var binding: ActivityDetailCosmeticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCosmeticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Cosmetic"

        val cosmetic = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_COSMETIC, Cosmetic::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_COSMETIC)
        }
        b = intent.extras
        binding.tvDataCosmetics.text = cosmetic?.name
        Glide.with(this)
            .load(cosmetic?.photo)
            .into(binding.imgDataPhoto)
        binding.tvDataDesc.text = cosmetic?.description
        binding.tvDataPrice.text = b?.getString("price")
        binding.tvDataSize.text = b?.getString("size")
        binding.tvDataBenefit.text = b?.getString("benefit")


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                when (item.itemId) {
                    R.id.share_action -> {

                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
