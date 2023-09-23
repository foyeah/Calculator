package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.model.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var operations: Operation = NotOperation()
    private var isPointPressed: Boolean = false
    private var firstValue = ""
    private var secondValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttons = listOf(
            binding.ButtonZero,
            binding.ButtonOne,
            binding.ButtonTwo,
            binding.ButtonThree,
            binding.ButtonFour,
            binding.ButtonFive,
            binding.ButtonSix,
            binding.ButtonSeven,
            binding.ButtonEight,
            binding.ButtonNine,
            binding.ButtonClear,
            binding.ButtonBackspace,
            binding.ButtonDiv,
            binding.ButtonMul,
            binding.ButtonSub,
            binding.ButtonSum,
            binding.ButtonPoint,
            binding.ButtonEquals
        )
        buttons.forEach { button ->
            button.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        val button = view as? Button
        button ?: return
        val digits = Digits.values().find { it.resourceId == button.id }
        val operation = OperationsEnum.values().find {it.resourceId == button.id}
        when {
            button.id == binding.ButtonEquals.id -> equals()
            button.id == binding.ButtonBackspace.id -> backspace()
            button.id == binding.ButtonClear.id -> clear()
            button.id == binding.ButtonPoint.id -> point()
            digits != null -> setValue(digits)
            operation != null -> {
                if (operations is NotOperation){
                    setOperation(operation)
                }else{
                    equals()
                    setOperation(operation)
                }
            }
        }
    }

    private fun setDefaultValue(){
        when{
            firstValue == "" && operations is Mul -> firstValue = "1"
            firstValue == "" && operations is Div -> firstValue = "1"
            firstValue == "" -> firstValue = "0"
        }
        when{
            secondValue == "" && operations is Mul -> secondValue = "1"
            secondValue == "" && operations is Div -> secondValue = "1"
            secondValue == "" -> secondValue = "0"
        }
    }

    private fun equals(){
        if (operations !is NotOperation){
            setDefaultValue()
            firstValue = operations.operation(firstValue.toDouble(), secondValue.toDouble()).toString()
            if(firstValue[firstValue.length - 1] == '0' && firstValue[firstValue.length - 2] == '.'){
                firstValue = firstValue.dropLast(2)
            }
            binding.InputField.text = firstValue
            operations = NotOperation()
            isPointPressed = false
            secondValue = ""

            binding.MathEx.text = firstValue
        }
    }

    private fun point(){
        if (!isPointPressed){
            if (operations is NotOperation){
                if (firstValue == "")firstValue = "0"
                firstValue += "."
                binding.InputField.text = firstValue
            }else{
                if (secondValue == "")firstValue = "0"
                secondValue += "."
                binding.InputField.text = secondValue
                binding.MathEx.text = binding.MathEx.text.toString() + "."
            }
            isPointPressed = true
        }
    }

    private fun setOperation(operation: OperationsEnum){
        operations = operation.operation
        isPointPressed = false
        binding.InputField.text = ""

        var operationStr: String = when (operation) {
            OperationsEnum.SUM -> "+"
            OperationsEnum.SUB -> "-"
            OperationsEnum.MUL -> "*"
            OperationsEnum.DIV -> "/"
        }
        if (firstValue == "0."){
            firstValue = "0"
        }
        binding.MathEx.text = "$firstValue $operationStr "
    }

    private fun backspace(){
        if (firstValue != "" && operations is NotOperation){
            firstValue = firstValue.dropLast(1)
            binding.InputField.text = firstValue
            isPointPressed = false
        }else if(secondValue != ""){
            secondValue = secondValue.dropLast(1)
            binding.InputField.text = secondValue
            isPointPressed = false
            if(binding.MathEx.text[binding.MathEx.text.lastIndex].toString() != " ") {
                binding.MathEx.text = binding.MathEx.text.toString().dropLast(1)
            }
        }
    }

    private fun setValue(digits: Digits){
        if (operations is NotOperation){
            firstValue += digits.text
            binding.InputField.text = firstValue
        }else{
            secondValue += digits.text
            binding.InputField.text = secondValue

            binding.MathEx.text = binding.MathEx.text.dropLast(secondValue.length - 1).toString() + secondValue
        }
    }

    private fun clear(){
        operations = NotOperation()
        isPointPressed = false
        firstValue = ""
        secondValue = ""
        binding.InputField.text = "0"
        binding.MathEx.text = ""
    }
}



