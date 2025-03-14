package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taller1.data.model.Driver
import com.example.taller1.data.network.FuelClient
import com.example.taller1.databinding.ActivityF1Binding

class F1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityF1Binding
    private lateinit var driverList: List<Driver>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityF1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getDrivers()
    }
    private fun getDrivers(){
        FuelClient.getDrivers(9684){ drivers, error ->
            runOnUiThread{
            if (error != null){
                Log.e("F1", "Error: $error")
            } else {
                    if(drivers != null){
                        drivers.forEach{driver -> Log.e("F1", "🏎️ ${driver.full_name} (${driver.driver_number}) - ${driver.headshot_url} - ${driver.team_colour} - ${driver.team_name} - ${driver.country_code}")}
                        driverList = drivers
                        val driversName = driverList.map{"${it.driver_number}. ${it.full_name}"}
                        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, driversName)
                        binding.ListView.adapter = adapter

                        binding.ListView.setOnItemClickListener{_, _, position, _ ->
                            //Guardamos la información del conductor elegido
                            val driverSelected = driverList[position]
                            val i = Intent(this, DriverF1Activity::class.java)
                            i.putExtra("full_name", driverSelected.full_name)
                            i.putExtra("team_name", driverSelected.team_name)
                            i.putExtra("name_acronym", driverSelected.name_acronym)
                            i.putExtra("country_code", driverSelected.country_code)
                            i.putExtra("headshot_url", driverSelected.headshot_url)
                            i.putExtra("team_colour", driverSelected.team_colour)
                            //Vamos a la otra actividad
                            startActivity(i)
                        }
                }
            }
                }
        }
    }
}