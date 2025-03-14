package com.example.taller1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1.databinding.ActivityTictactoeBinding

class TictactoeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTictactoeBinding
    private var p1 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTictactoeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttons = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )
        for (button in buttons) {
            button.setOnClickListener {
                if (button.text.isEmpty()) {
                    button.text = if (p1) "X" else "O"
                    button.setBackgroundColor(if (p1) 0xFFFF69B4.toInt() else 0x8003A9F4.toInt())
                    p1 = !p1
                    winValidator()
                }
            }
        }
        binding.buttonNewGame.setOnClickListener{resetGame()}
    }

    private fun winValidator(){
        val botones = listOf(binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9)

        //Posibles combinaciones de victorias

        val combinaciones = listOf(listOf(0,1,2), listOf(3,4,5), listOf(6,7,8), //Horizontales
                            listOf(0,3,6), listOf(1,4,7), listOf(2,5,8), //Verticales
                            listOf(0,4,8), listOf(2,4,6) //Diagonales
        )

        for(it in combinaciones){
            if(botones[it[0]].text == botones[it[1]].text && botones[it[1]].text == botones[it[2]].text){
                if(botones[it[0]].text =="X"){
                    Toast.makeText(baseContext, "El jugador 1 gano!!", Toast.LENGTH_LONG).show()
                    botones[it[0]].setBackgroundColor(0xFF2ECC40.toInt())
                    botones[it[1]].setBackgroundColor(0xFF2ECC40.toInt())
                    botones[it[2]].setBackgroundColor(0xFF2ECC40.toInt())
                    return
                }else if(botones[it[0]].text =="O") {
                    Toast.makeText(baseContext, "El jugador 2 gano!! ", Toast.LENGTH_LONG).show()
                    botones[it[0]].setBackgroundColor(0xFF2ECC40.toInt())
                    botones[it[1]].setBackgroundColor(0xFF2ECC40.toInt())
                    botones[it[2]].setBackgroundColor(0xFF2ECC40.toInt())
                    return
                }
            }
        }


    }
    private fun resetGame(){
        val botones = listOf(binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9)

        for(it in botones){
            it.text = ""
            it.setBackgroundColor(0xFF5D0EE7.toInt())
        }
        p1 = true
    }
}