package com.ravikoradiya.toolbarcentertitle

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.ravikoradiya.toolbarcentertitle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.isTitleInCenter = true
        setSupportActionBar(binding.toolbar)
        title = getString(R.string.app_name)
        binding.toolbar.subtitle = getString(R.string.app_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.switch1.setOnCheckedChangeListener { compoundButton, b -> binding.isTitleInCenter = b }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.menu_main, menu)
        binding.rvCount.layoutManager = LinearLayoutManager(this)
        binding.rvCount.adapter = CustomAdapter(this, menu, binding.toolbar)

        return true
    }

}