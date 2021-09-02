package com.example.Naviinterview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naviinterview.HomeFragment
import com.example.naviinterview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHomeFragment()
    }

    private fun addHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            HomeFragment.newInstance())
        .addToBackStack(HomeFragment::class.simpleName).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}