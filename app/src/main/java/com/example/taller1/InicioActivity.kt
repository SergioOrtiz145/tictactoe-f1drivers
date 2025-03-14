package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.data.network.FuelClient
import com.example.taller1.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tictactoeButton.setOnClickListener{
            startActivity(Intent(baseContext, TictactoeActivity::class.java))
        }
        binding.f1Button.setOnClickListener{
            startActivity(Intent(baseContext, F1Activity::class.java))
        }
    }
}