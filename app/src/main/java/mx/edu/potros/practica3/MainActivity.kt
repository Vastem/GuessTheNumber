package mx.edu.potros.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.view.View
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue=0
    var maxValue=100
    var num:Int=0
    var won=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessings)
        val down: TextView = findViewById(R.id.down)
        val up: TextView = findViewById(R.id.up)
        val generate: TextView = findViewById(R.id.generate)
        val guessed: TextView = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num= Random.nextInt(minValue,maxValue)
            guessing.setText(num.toString())
            generate.visibility=View.INVISIBLE
            guessed.visibility= View.VISIBLE

            guessed.setText("GUESSED")
        }

        up.setOnClickListener {
            minValue=num
            if(chechingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessing.setText(num.toString())
            }
            else{
                guessing.setText("No puede ser, ganaste")
                guessed.setText("Volver a jugar")
                won=true
            }
        }

        down.setOnClickListener {
            maxValue=num
            if(chechingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessing.setText(num.toString())
            }
            else{
                guessing.setText("No puede ser, ganaste")
                guessed.setText("Volver a jugar")
                won=true
            }
        }

        guessed.setOnClickListener {
            if(!won){
                guessing.setText("Adiviné, tu número es el "+ num)
                guessed.setText("Volver a jugar")
                won=true
            }
            else{
                generate.visibility=View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.visibility=View.GONE
                resetValues()
            }
        }

    }

    fun resetValues(){
        minValue=0
        maxValue=100
        num=0
        won=false
    }

    fun chechingLimits(): Boolean{
        return minValue!=maxValue
    }
}