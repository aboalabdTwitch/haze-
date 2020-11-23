package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wl3ha.R
import kotlinx.android.synthetic.main.activity_my_events_details.*

class MyEventsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events_details)

        EditMyEvents.setOnClickListener {
            val intent= Intent(this, com.example.wl3ha.user_page.EditMyEvents::class.java)
            startActivity(intent)
        }

    }
}