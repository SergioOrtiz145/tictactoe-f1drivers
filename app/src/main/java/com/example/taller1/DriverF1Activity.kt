package com.example.taller1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.taller1.data.network.FuelClient
import com.example.taller1.databinding.ActivityDriverF1Binding

class DriverF1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDriverF1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverF1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        loadDriverData()
    }
    private fun loadDriverData(){
        binding.driverName.text = intent.getStringExtra("full_name")
        binding.driverTeam.text = intent.getStringExtra("team_name")
        binding.driverTeam.setTextColor(Color.parseColor("#"+intent.getStringExtra("team_colour")))
        binding.driverAcronym.text = intent.getStringExtra("name_acronym")
        binding.driverImage.load(intent.getStringExtra("headshot_url"))
        val code = intent.getStringExtra("country_code")
        if (code != null) {
            FuelClient.getCountryFlag(code){flag ->
                if(flag != null){
                    binding.driverFlag.load(flag)
                }else
                    Log.e("F1", "error al cargar bandera o no pais")

            }
        }
    }
}