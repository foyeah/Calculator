package com.example.calculator.model

class Sum : Operation{
    override fun operation(first: Double, second: Double): Double {
        return first + second
    }
}