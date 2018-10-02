package com.worldsnas.sliderlibrary

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.noavaran.imageslider.Slider
import com.noavaran.imageslider.SliderClickListener

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var slider : Slider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        slider = findViewById(R.id.slider)

        val banners = listOf("https://unsplash.com/photos/hud8Oa4D5Gs/download?force=true",
                "https://unsplash.com/photos/0r1gjbBrGeM/download?force=true",
                "https://unsplash.com/photos/FOUxD6zodR4/download?force=true",
                "https://unsplash.com/photos/M19_crm09Zw/download?force=true",
                "https://unsplash.com/photos/F-JUGSYNjZU/download?force=true",
                "https://unsplash.com/photos/N2X20_MlF20/download?force=true",
                "https://unsplash.com/photos/OeGImGbmxls/download?force=true")


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            slider.submitList(banners)
            slider.setInterval(2000)
        }

        slider.mSliderSliderClickListener = object : SliderClickListener {
            override fun onClick(position: Int, url: String) {
                Toast.makeText(applicationContext, "position=$position with url=$url clicked", Toast.LENGTH_SHORT).show()
            }
        }


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
