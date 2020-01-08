package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateUI("")
    }

    val opList: MutableList<String> = arrayListOf()
    val numList: MutableList<String> = arrayListOf()

    fun extractString(item: List<String>, connect: String = ""): String {
        if (item.isEmpty()) return ""
        return item.reduce { acc, s -> acc + connect + s }
    }

    fun updateUI ( mainUIString: String ) {
        var calculationString = extractString(opList, "")
        var calculationTxtView = findViewById<View>(R.id.txtResult) as TextView
        calculationTxtView.text = calculationString

        val ans = findViewById<View>(R.id.txtOperator) as TextView
        ans.text = mainUIString
    }

    fun numHandle (view: View) {
        val button = view as Button
        val numString = button.text

        numList.add (numString.toString())
        val text = extractString(numList)
        updateUI(text)
    }

    fun opHandle (view: View) {
        val button = view as Button
        if (numList.isEmpty()) return
        opList.add(extractString(numList))
        numList.clear()
        opList.add(button.text.toString())
        updateUI(button.text.toString())
    }

    private fun clearCache () {
        opList.clear()
        numList.clear()
    }

    fun clear (view: View) {
        clearCache()
        updateUI("")
    }

    fun equalHandle (view: View) {
        opList.add (extractString(numList))
        numList.clear()

        val calculator = Calculator()
        val answer = calculator.calculate(opList)

        updateUI("=" + answer.toString())
        clearCache()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}
