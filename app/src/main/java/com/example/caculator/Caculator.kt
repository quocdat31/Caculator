package com.example.caculator

class Calculator {
    fun calculate(calculationList: List<String>): Int {
        var currentOp = ""
        var currentNum = 1
        calculationList.forEach{
                token-> when {
            token.equals("+") ||  token.equals("-") || token.equals("/") || token.equals("*") -> currentOp = token
            currentOp.equals("-") -> currentNum -= token.toInt()
            currentOp.equals("+") -> currentNum += token.toInt()
            currentOp.equals("/") -> currentNum /= token.toInt()
            else -> currentNum *= token.toInt()
        }
        }
        return currentNum
    }
}