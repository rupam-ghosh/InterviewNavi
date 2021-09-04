package com.example.Naviinterview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.naviinterview.HomeFragment
import com.example.naviinterview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        addHomeFragment()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(resources.getColor(R.color.purple_500))
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
    }

    private fun addHomeFragment() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    HomeFragment.newInstance()
            )
                    .addToBackStack(HomeFragment::class.simpleName).commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}