package com.example.barrier_free

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var mapFragment: MapFragment
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var rentalFloatMenuLayout: LinearLayout
    private lateinit var btn_return: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        Toast.makeText(this, "app launched", Toast.LENGTH_LONG).show()

        val cameraIntent = Intent(this, CameraActivity::class.java) // Change MainActivity into Camera Activity
        rentalFloatMenuLayout = findViewById(R.id.rental_layout)
        btn_return = findViewById(R.id.btn_return)

        fragmentManager = supportFragmentManager
        mapFragment = MapFragment()
        fragmentManager.beginTransaction().replace(R.id.map_container, mapFragment).commit()

        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // rental selected
                R.id.nav_rental -> {
                    rentalFloatMenuLayout.visibility = View.VISIBLE
                }
                // map selected
                R.id.nav_map -> {
                    rentalFloatMenuLayout.visibility = View.INVISIBLE
                }
                // mypage selected
                R.id.nav_mypage -> {
                    startActivity(cameraIntent)
                }
            }
            true
        }

        // 'return' button selected
        btn_return.setOnClickListener {
            startActivity(cameraIntent)
        }
    }
}