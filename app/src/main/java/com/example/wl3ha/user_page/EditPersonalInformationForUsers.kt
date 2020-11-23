package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.wl3ha.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_personal_information_for_users.*
import java.util.regex.Pattern

class EditPersonalInformationForUsers : AppCompatActivity() {
    private var Email:String?=null //to get the email from database and save it on this variable
    private var database= FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("NormalUsers") // to access to root
    private var ID:String?=null
    var x=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_personal_information_for_users)

        var intent: Intent =getIntent()
        Email=intent.getStringExtra("Email")
        ID=intent.getStringExtra("ID")


        textPhoneNO.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mobileValidate(textPhoneNO.text.toString()))
                    BtnSave.isEnabled=true
                else{
                    textPhoneNO.setError("Inavlid Mobile Number")
                }
                }

            override fun afterTextChanged(p0: Editable?) {
            }
        })


    }

    private fun mobileValidate(text: String?): Boolean {
        var p=Pattern.compile("[0][7][7-9][0-9]{7}")
        var m=p.matcher(text)
        return m.matches()

    }



    fun SaveOnclick(view:View){
        PB.visibility=View.VISIBLE

        //TODO validate all Feailds

        if (TextFirstName.length()>0||TextLastName.length()>0||textPhoneNO.length()>0||TextAboutMe.length()>0){
            if(TextFirstName.length()>0){
                mRef.child(ID.toString()).child("firstName").setValue(TextFirstName.text.toString())
            }
            if(TextLastName.length()>0){
                mRef.child(ID.toString()).child("lastName").setValue(TextLastName.text.toString())
            }
            if(textPhoneNO.length()>0){
                mRef.child(ID.toString()).child("phoneNumber").setValue(textPhoneNO.text.toString())
            }
            if(TextAboutMe.length()>0){
                mRef.child(ID.toString()).child("aboutMe").setValue(TextAboutMe.text.toString())
            }
            PB.visibility=View.GONE
            Toast.makeText(this, "The Information Has been Updated successfully", Toast.LENGTH_SHORT).show()
        }
        else {
            PB.visibility=View.GONE
            Toast.makeText(this, "Missing some Information", Toast.LENGTH_SHORT).show()
        }


    }
}