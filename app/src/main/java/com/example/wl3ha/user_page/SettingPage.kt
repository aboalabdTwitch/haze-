package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wl3ha.R
import kotlinx.android.synthetic.main.activity_setting_page.*

class SettingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page)

        ChangePassContiner.setOnClickListener {
            val intent= Intent(this, ChangePassPage::class.java)
            startActivity(intent)
        }


    }
}