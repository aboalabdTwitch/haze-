package com.example.wl3ha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wl3ha.artist_page.MainPageForArtist
import com.example.wl3ha.user_page.MainPageCategorey
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



val Session:String?=null


class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "connected", Toast.LENGTH_LONG).show()


            SignUpBu.setOnClickListener {
                val intent= Intent(this, SignUp::class.java)
                startActivity(intent)

            }

            BUArtistPage.setOnClickListener {
                val intent= Intent(this, MainPageForArtist::class.java)
                startActivity(intent)
            }


        mAuth=FirebaseAuth.getInstance()




    }


    //LogIn Button
    fun LogIn(view: View){
        progressBar.visibility=View.VISIBLE

        var mail=MailText.text.toString()
        var pass=PassText.text.toString()

        if(mail.isNotEmpty()&&pass.isNotEmpty()){
            mAuth!!.signInWithEmailAndPassword(mail,pass).addOnCompleteListener {
                if (it.isSuccessful){
                    progressBar.visibility=View.GONE
                    val intent2= Intent(this, MainPageCategorey::class.java)
                    intent2.putExtra("Email",mail)
                    startActivity(intent2)
                }
                else{
                    progressBar.visibility=View.GONE
                    Toast.makeText(this, "Wrong Password Or Email", Toast.LENGTH_SHORT).show()

                }
            }
        }
        else{
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
        }

    }
}