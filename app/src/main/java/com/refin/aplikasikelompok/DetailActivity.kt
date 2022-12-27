package com.refin.aplikasikelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.refin.aplikasikelompok.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    var binding: ActivityDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")
        val gambar = intent.getIntExtra("gambar", R.drawable.gambar_5)

        binding!!.tvDtNamaTempat.text = judul
        binding!!.tvDtDeskripsiTempat.text = deskripsi
        binding!!.ivDtGambar.setImageResource(gambar)

    }
}