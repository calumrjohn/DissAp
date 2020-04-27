package com.example.dissap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun buttonJoinHunt(view: View) {}
    fun buttonCreateHunt(view: View) {
        val createIntent : Intent = Intent(this, CreateActivity::class.java).apply {}
        startActivity(createIntent)
    }
}
