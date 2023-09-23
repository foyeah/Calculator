package com.example.calculator.model

class NotOperation : Operation {
    override fun operation(first: Double, second: Double): Double {
        throw Exception("Not operation")
    }
}