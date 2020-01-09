package com.example.caculator

object Calculator {
    fun calculate(calculationList: List<String>): Int {
        var mCurrentOperation = ""
        var mCurrentNumber = 1
        calculationList.forEach{
                token-> when {
            token == "+" || token == "-" || token=="/" || token=="*" -> mCurrentOperation = token
            mCurrentOperation==("-") -> mCurrentNumber -= token.toInt()
            mCurrentOperation==("+") -> mCurrentNumber += token.toInt()
            mCurrentOperation==("/") -> mCurrentNumber /= token.toInt()
            else -> mCurrentNumber *= token.toInt()
        }
        }
        return mCurrentNumber
    }
}
