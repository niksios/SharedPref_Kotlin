package com.niks.sharedprefkotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameText : EditText
    private lateinit var ageText : EditText

    private lateinit var sf : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.edtName)
        ageText = findViewById(R.id.edtAge)

        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()
    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt() // as age edittext accepts int value
        editor.apply() {
            putString("my_name", name)
            putInt("my_age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("my_name", null)
        val age = sf.getInt("my_age", 0)
        nameText.setText(name)
        if (age!=0){
            ageText.setText(age.toString())
        }

    }
}