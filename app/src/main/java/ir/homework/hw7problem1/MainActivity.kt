package ir.homework.hw7problem1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ir.homework.hw7problem1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var digitList = mutableListOf<String>()
    var numberList = mutableListOf<Double>()
    var signList = mutableListOf<Char>()
    lateinit var screen: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvScreen.text = ""
//        digitList = mutableListOf("1", "0", ".", "5", "1")
//        numberList = mutableListOf(3.0, 2.0, 5.0)
//        signList = mutableListOf('/', '-')

        //Toast.makeText(this,"${doCalculations(numberList, signList)} ${signList.size}",Toast.LENGTH_LONG).show()

        binding.digit1.setOnClickListener{
            onDigitClick('1')
        }
        binding.addition.setOnClickListener{
            if (digitList.size == 0){
                Toast.makeText(this,"Invalid format used.",Toast.LENGTH_LONG).show()
            }
            else {
                binding.tvScreen.text = binding.tvScreen.text.toString() + " + "
                numberList.add(toNumber(digitList))
                digitList.clear()
                signList.add('+')
            }
        }
        binding.point.setOnClickListener{
            binding.tvScreen.text = binding.tvScreen.text.toString() + "."
            digitList.add(".")
        }
        binding.equality.setOnClickListener{
            if (digitList.size == 0){
                Toast.makeText(this,"Invalid format used.",Toast.LENGTH_LONG).show()
            }
            else if (signList.size == 0){ }
            else {
                numberList.add(toNumber(digitList))
                digitList.clear()
                //binding.tvScreen.text = binding.tvScreen.text.toString() + " = "
                binding.tvScreen.text = doCalculations(numberList, signList).toString()
                Toast.makeText(this, " ${numberList.first()}", Toast.LENGTH_LONG).show()
            }
        }
        binding.division.setOnClickListener{
            if (digitList.size == 0){
                Toast.makeText(this,"Invalid format used.",Toast.LENGTH_LONG).show()
            }
            else {
                binding.tvScreen.text = binding.tvScreen.text.toString() + " / "
                numberList.add(toNumber(digitList))
                digitList.clear()
                signList.add('/')
                Toast.makeText(this, " ${numberList.first()}", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun toNumber(digitList: MutableList<String>): Double{
       return digitList.reduce{str1: String, str2: String -> str1 + str2}.toDouble()
    }

    private fun doCalculations(numberList: MutableList<Double>, signList: MutableList<Char>): Double{
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

    private fun clearScreen(){
        digitList.clear()
        numberList.clear()
        signList.clear()
        binding.tvScreen.text = ""
    }

    private fun onDigitClick(char: Char){
        if (numberList.size > signList.size)    clearScreen()
        binding.tvScreen.text = binding.tvScreen.text.toString() + char.toString()
        digitList.add(char.toString())
    }
}