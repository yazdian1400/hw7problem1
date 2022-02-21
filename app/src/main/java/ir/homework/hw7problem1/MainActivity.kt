package ir.homework.hw7problem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.homework.hw7problem1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var digitList: MutableList<String>
    lateinit var numberList: MutableList<Double>
    lateinit var signList: MutableList<Char>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        digitList = mutableListOf("1", "0", ".", "5", "1")
        Toast.makeText(this,"${toNumber(digitList)} ${digitList.size}",Toast.LENGTH_LONG).show()
    }
    fun toNumber(digitList: MutableList<String>): Double{
       return digitList.reduce{str1: String, str2: String -> str1 + str2}.toDouble()
    }
}