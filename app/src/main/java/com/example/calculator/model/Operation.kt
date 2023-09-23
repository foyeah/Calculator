package com.example.calculator.model

interface Operation {
    fun operation(first: Double, second: Double): Double
}