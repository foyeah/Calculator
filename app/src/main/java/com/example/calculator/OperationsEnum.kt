package com.example.calculator

import androidx.annotation.IdRes
import com.example.calculator.model.*

enum class OperationsEnum(var operation : Operation, @IdRes var resourceId: Int){
    SUM(Sum(), R.id.Button_Sum),
    SUB(Sub(), R.id.Button_Sub),
    MUL(Mul(), R.id.Button_Mul),
    DIV(Div(), R.id.Button_Div);
}