package com.example.barrier_free

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load Main page after 3s
        var intent = Intent(this, MapActivity::class.java)
        GlobalScope.launch {
            delay(2000)
            startActivity(intent)
        }
    }
}