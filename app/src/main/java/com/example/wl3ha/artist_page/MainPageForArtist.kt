package com.example.wl3ha.artist_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.wl3ha.R
import kotlinx.android.synthetic.main.activity_main_page_for_artist.*

class MainPageForArtist : AppCompatActivity() {

    //for nav menu
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_for_artist)

        //for nav menu
        if (true){
            toggle = ActionBarDrawerToggle(this, DLForArtist,
                R.string.open,
                R.string.close
            )
            DLForArtist.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            ArtistNav.setNavigationItemSelectedListener {

                when(it.itemId){
                   /* R.id.CreateEventID ->{
                        val intent= Intent(this,
                            CreateAnEvent::class.java)
                        startActivity(intent)
                    }*/

                }
                true
            }
        }

    }

    //for nav menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}