package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wl3ha.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_for_artist.*

class SignUpForArtist : AppCompatActivity() {
     var mAuth: FirebaseAuth?=null // for firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_for_artist)

        mAuth=FirebaseAuth.getInstance() // for firebase

    }

    //for firebase
    fun SignUpArtist(view: View){
    progressSignUpArtist.visibility= View.VISIBLE

    var FirstName=TextFirstNameArtist.text.toString()
    var LastName=TextLastNameArtist.text.toString()
    var Username=TextUsernameArtist.text.toString()
    var Password=TextPasswordArtist.text.toString()
    var Birthday=TextBirthdayArtist.text.toString()
    var Email=TextEmailArtist.text.toString()
    var PhoneNumber=TextPhoneNumberArtist.text.toString()

   if (Email.isNotEmpty() && Password.isNotEmpty()) {
        mAuth?.createUserWithEmailAndPassword(Email, Password)
            ?.addOnCompleteListener {// to see if it done correct
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                    progressSignUpArtist.visibility= View.GONE

                    val intent= Intent(this,MainPageForNormalUsers::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(applicationContext,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    progressSignUpArtist.visibility= View.GONE
                }
            }
    }  else{
            Toast.makeText(applicationContext, "empty", Toast.LENGTH_SHORT).show()
            progressSignUpArtist.visibility=View.GONE
        }



}

}