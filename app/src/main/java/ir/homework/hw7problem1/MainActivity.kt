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
        numberList = mutableListOf(3.0, 2.0, 5.0)
        signList = mutableListOf('/', '-')

        Toast.makeText(this,"${doCalculations(numberList, signList)} ${signList.size}",Toast.LENGTH_LONG).show()
    }
    fun toNumber(digitList: MutableList<String>): Double{
       return digitList.reduce{str1: String, str2: String -> str1 + str2}.toDouble()
    }

    fun doCalculations(numberList: MutableList<Double>, signList: MutableList<Char>): Double{
        var index: Int
        while(signList.size != 0){
            if (signList.contains('*')) {
                index = signList.lastIndexOf('*')
                numberList[index] = numberList[index] * numberList[index + 1]
                numberList.removeAt(index + 1)
                signList.removeAt(index)
                continue
            }
            else if (signList.contains('/')) {
                index = signList.lastIndexOf('/')
                numberList[index] = numberList[index] / numberList[index + 1]
                numberList.removeAt(index + 1)
                signList.removeAt(index)
                continue
            }
            else if (signList.contains('+')) {
                index = signList.lastIndexOf('+')
                numberList[index] = numberList[index] + numberList[index + 1]
                numberList.removeAt(index + 1)
                signList.removeAt(index)
            }
            else if (signList.contains('-')) {
                index = signList.lastIndexOf('-')
                numberList[index] = numberList[index] - numberList[index + 1]
                numberList.removeAt(index + 1)
                signList.removeAt(index)
            }
        }
        return numberList.first()
    }
}