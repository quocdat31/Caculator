package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOperationList: MutableList<String> = arrayListOf()
    private val mNumberList: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateUI("")
    }

    private fun extractString(item: List<String>, connect: String = ""): String {
        if (item.isEmpty()) return ""
        return item.reduce { acc, s -> acc + connect + s }
    }

    private fun updateUI(mainUIString: String ) {
        resultTextView.text = extractString(mOperationList, "")

        val ans = findViewById<View>(R.id.operatorTextView) as TextView
        ans.text = mainUIString
    }

    fun onNumberPressHandler(view: View) {
        val button = view as Button
        val numString = button.text

        mNumberList.add(numString.toString())
        val text = extractString(mNumberList)
        updateUI(text)
    }

    fun onOperationPressHandler(view: View) {
        val button = view as Button
        if (mNumberList.isEmpty()) return
        mOperationList.add(extractString(mNumberList))
        mNumberList.clear()
        mOperationList.add(button.text.toString())
        updateUI(button.text.toString())
    }

    private fun clearCache() {
        mOperationList.clear()
        mNumberList.clear()
    }

    fun onClearPressHandler(view: View) {
        clearCache()
        updateUI("")
    }

    fun onEqualPressHandler(view: View) {
        mOperationList.add (extractString(mNumberList))
        mNumberList.clear()

        val answer = Calculator.calculate(mOperationList)

        updateUI("= $answer")
        clearCache()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}
