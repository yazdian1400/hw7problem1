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
    var lastInput = InputType.DIGIT
    lateinit var screen: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.digit0.setOnClickListener {
            onDigitClick('0')
        }
        binding.digit1.setOnClickListener {
            onDigitClick('1')
        }
        binding.digit2.setOnClickListener {
            onDigitClick('2')
        }
        binding.digit3.setOnClickListener {
            onDigitClick('3')
        }
        binding.digit4.setOnClickListener {
            onDigitClick('4')
        }
        binding.digit5.setOnClickListener {
            onDigitClick('5')
        }
        binding.digit6.setOnClickListener {
            onDigitClick('6')
        }
        binding.digit7.setOnClickListener {
            onDigitClick('7')
        }
        binding.digit8.setOnClickListener {
            onDigitClick('8')
        }
        binding.digit9.setOnClickListener {
            onDigitClick('9')
        }

        binding.point.setOnClickListener {
            onPointClick()
        }
        binding.division.setOnClickListener {
            onSignClick('/')
        }
        binding.multiplication.setOnClickListener {
            onSignClick('*')
        }
        binding.addition.setOnClickListener {
            onSignClick('+')
        }
        binding.subtraction.setOnClickListener {
            onSignClick('-')
        }
        binding.equality.setOnClickListener {
            onEqualityClick()
        }
    }

    private fun toNumber(digitList: MutableList<String>): Double{
       return digitList.reduce{str1: String, str2: String -> str1 + str2}.toDouble()
    }

    private fun doCalculations(numberList: MutableList<Double>, signList: MutableList<Char>): Double{
        var index: Int
        while(signList.size != 0){
            if (signList.contains('*') || signList.contains('/')) {
                index = signList.indexOfFirst{it == '*' || it == '/'}
                numberList[index] = when(signList[index]){
                    '*' -> numberList[index] * numberList[index + 1]
                    else -> numberList[index] / numberList[index + 1]
                }
                numberList.removeAt(index + 1)
                signList.removeAt(index)
            }
            else if (signList.contains('+') || signList.contains('-')) {
                index = signList.indexOfFirst{it == '+' || it == '-'}
                numberList[index] = when(signList[index]) {
                    '+' -> numberList[index] + numberList[index + 1]
                    else -> numberList[index] - numberList[index + 1]
                }
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
        if (lastInput == InputType.EQUALITY){
            clearScreen()
            numberList.clear()
        }
        binding.tvScreen.text = binding.tvScreen.text.toString() + char.toString()
        digitList.add(char.toString())
        lastInput = InputType.DIGIT
    }

    private fun onPointClick(){
        if (lastInput != InputType.DIGIT)   messageInvalidFormat()
        else {
            binding.tvScreen.text = binding.tvScreen.text.toString() + "."
            digitList.add(".")
            lastInput = InputType.POINT
        }
    }

    private fun onSignClick(sign: Char) {
        if (lastInput == InputType.SIGN || lastInput == InputType.POINT)   messageInvalidFormat()
        else {
            binding.tvScreen.text = binding.tvScreen.text.toString() + " " + sign.toString() + " "
            if (lastInput == InputType.DIGIT) {
                numberList.add(toNumber(digitList))
                digitList.clear()
            }
            signList.add(sign)
            lastInput = InputType.SIGN
        }
    }

    private fun onEqualityClick() {
        if (lastInput != InputType.DIGIT)   messageInvalidFormat()
        else if (signList.size == 0){ }     //invalid
        else {
            binding.tvScreen.text = binding.tvScreen.text.toString() + " = "
            numberList.add(toNumber(digitList))
            digitList.clear()
            binding.tvScreen.text = removeZeroFromNumber(doCalculations(numberList, signList))
            signList.clear()
            lastInput = InputType.EQUALITY
        }
    }

    private fun messageInvalidFormat(){
        Toast.makeText(this, "Invalid format used.", Toast.LENGTH_LONG).show()
    }

    private fun removeZeroFromNumber(num: Double): String{
        if (num == num.toInt().toDouble())  return num.toInt().toString()
        return num.toString()
    }
}

enum class InputType{
    DIGIT,
    SIGN,
    POINT,
    EQUALITY
}