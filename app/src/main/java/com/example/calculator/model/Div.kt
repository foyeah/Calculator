package com.example.calculator.model

class Div : Operation{
    override fun operation(first: Double, second: Double): Double {
        return first / second
    }
}