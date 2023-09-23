package com.example.calculator.model

class Sub : Operation{
    override fun operation(first: Double, second: Double): Double {
        return first - second
    }
}