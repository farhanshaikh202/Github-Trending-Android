package com.farhanapps.githubtrending.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farhanapps.githubtrending.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
    }
}