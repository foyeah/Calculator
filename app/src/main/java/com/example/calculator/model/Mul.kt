package com.example.calculator.model

class Mul : Operation{
    override fun operation(first: Double, second: Double): Double {
        return first * second
    }
}