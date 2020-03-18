package com.worldsnas.sliderlibrary

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.snackbar.Snackbar
import com.worldsnas.slider.slider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var list: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        list = findViewById(R.id.list)

        val banners = listOf("https://unsplash.com/photos/hud8Oa4D5Gs/download?force=true",
                "https://unsplash.com/photos/0r1gjbBrGeM/download?force=true",
                "https://unsplash.com/photos/FOUxD6zodR4/download?force=true",
                "https://unsplash.com/photos/M19_crm09Zw/download?force=true",
                "https://unsplash.com/photos/F-JUGSYNjZU/download?force=true",
                "https://unsplash.com/photos/N2X20_MlF20/download?force=true",
                "https://unsplash.com/photos/OeGImGbmxls/download?force=true",
                "https://unsplash.com/photos/-g7axSVst6Y/download?force=true",
                "https://unsplash.com/photos/KZC7BJo0Cl0/download?force=true",
                "https://unsplash.com/photos/tzzj8LCLujU/download?force=true"
        )


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        list.layoutManager = LinearLayoutManager(this)

        list.withModels {
            slider {
                id("slider")
                infinite(true)
                cycleDelay(3_000)
                copier { oldModel ->
                    oldModel as BannerViewModel_
                    BannerViewModel_().apply {
                        id(oldModel.id())
                        bindImage(banners[oldModel.id().toInt()])
                        listener { imageUrl ->
                            showImageUrl(imageUrl)
                        }
                    }
                }

                models(
                        banners.mapIndexed { index, s ->
                            BannerViewModel_().apply {
                                id(index.toLong())
                                bindImage(s)
                                listener {
                                    showImageUrl(it)
                                }
                            }
                        }
                )
            }
        }
    }

    private fun showImageUrl(imageUrl: String?) {
        Toast.makeText(
                this@MainActivity,
                imageUrl,
                Toast.LENGTH_SHORT
        ).show()
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
