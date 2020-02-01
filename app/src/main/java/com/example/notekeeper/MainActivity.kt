package com.example.notekeeper

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button.setOnClickListener {
            val (originalValue, newValue) = multiplyDisplayedValueBy2()
            showSnackbar(it, originalValue, newValue)
        }

        fab.setOnClickListener { view ->
            val (originalValue, newValue) = multiplyDisplayedValueBy2()
            showSnackbar(view, originalValue, newValue)
        }
    }

    private fun showSnackbar(view: View, originalValue: Int, newValue: Int) {
        Snackbar
            .make(view, "Value: $originalValue changed to $newValue", Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun multiplyDisplayedValueBy2(): Pair<Int, Int> {
        val originalValue = textDisplayedValue.text.toString().toInt()
        val newValue = originalValue * 2
        textDisplayedValue.text = newValue.toString()
        return Pair(originalValue, newValue)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
