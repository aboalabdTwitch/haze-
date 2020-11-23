package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wl3ha.R
import kotlinx.android.synthetic.main.activity_artist_page.*

class ArtistPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_page)



        IVSinger.setOnClickListener {
            val intent= Intent(this, ArtistList::class.java)
            startActivity(intent)
        }
    }
}