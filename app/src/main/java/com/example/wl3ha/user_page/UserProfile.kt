package com.example.wl3ha.user_page

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wl3ha.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.lang.Exception
import java.net.URI

class UserProfile : AppCompatActivity() {

    private var Email:String?=null //to get the email from database and save it on this variable
    private var database= FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("NormalUsers") // to access to root
    private var ID:String?=null

    private var mStorge:StorageReference?=null


    private var FullName:String?=null
    private var UserName:String?=null
    private var Image:String?=null
    private var AboutMe:String?=null

  



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var intent:Intent=getIntent()
        Email=intent.getStringExtra("Email")
        ID=intent.getStringExtra("ID")

        mStorge=FirebaseStorage.getInstance().reference //to access the root for Firebase storage



        IVEditInformationUsers.setOnClickListener {
            val intent= Intent(this,EditPersonalInformationForUsers::class.java)
            intent.putExtra("Email",Email)
            intent.putExtra("ID",ID)
            startActivity(intent)
        }






    }

    override fun onStart() {
        super.onStart()
        LoadUserInfo()
    }

    fun LoadUserInfo(){


        val postListener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds: DataSnapshot in dataSnapshot.children){
                    if (ds.child("email").value?.equals(Email)!!){
                        TVFullName.text=ds.child("firstName").value.toString()+" "+ds.child("lastName").value.toString()
                        TVUsername.text=ds.child("username").value.toString()
                        if (ds.child("aboutMe").value.toString()!="null"){
                            TVAboutMe.text=ds.child("aboutMe").value.toString()
                        }
                        if (ds.child("image").value.toString()!="null"){
                            Picasso.with(applicationContext).load(ds.child("image").value.toString()).into(IVUserProfileHeader)
                        }

                    }

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, databaseError.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        mRef.addValueEventListener(postListener)

    }

    //to let the user open the stdio and pik a image , dont forget the permission
    fun imageonclick(view: View) {
        progressBar3.visibility=View.VISIBLE

        val int = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        int.type="image/*"
        startActivityForResult(int, 50)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // check if the number is equal and if i piked a photo
        if (requestCode == 50 && resultCode == Activity.RESULT_OK) {
            try {
                val e = data!!.data
                val FilePath=mStorge?.child("image"+e?.lastPathSegment)
                if (e!=null){
                    FilePath?.putFile(e)?.addOnSuccessListener {
                        FilePath.downloadUrl.addOnSuccessListener {
                            mRef.child(ID.toString()).child("image").setValue(it.toString())
                            progressBar3.visibility=View.GONE
                            IVUserProfileHeader.setImageURI(e)
                            Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_SHORT).show()
                        }
                    }?.addOnCanceledListener {
                        Toast.makeText(applicationContext, "Try Again", Toast.LENGTH_SHORT).show()
                        progressBar3.visibility=View.GONE

                    }
                }
            } catch (ex: Exception) {
                progressBar3.visibility=View.GONE
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
            }


        }
    }



}