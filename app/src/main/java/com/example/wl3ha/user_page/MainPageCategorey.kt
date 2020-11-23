package com.example.wl3ha.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.wl3ha.MainActivity
import com.example.wl3ha.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_page_categorey.*
import kotlinx.android.synthetic.main.header.*

class MainPageCategorey : AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle

    private var Email:String?=null //to get the email from database and save it on this variable
    private var database= FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("NormalUsers") // to access to root
    private var ID:String?=null








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_categorey)

        var intent:Intent=getIntent()
        Email=intent.getStringExtra("Email")
        GetUserID()
        //ID=intent.getStringExtra("ID")





        //for nav menu
        NavMenu()





    }

    // to load all info
    override fun onStart() {
        super.onStart()
        LoadHeaderInfo()

    }

    // Session For Users
    fun GetUserID(){

        val postListener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds:DataSnapshot in dataSnapshot.children){
                    if (ds.child("email").value?.equals(Email)!!){
                       ID=ds.child("id").value.toString()

                    }

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, databaseError.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        mRef.addValueEventListener(postListener)


    }



    //for nav menu
    fun NavMenu(){

            toggle = ActionBarDrawerToggle(this, DrwerLaierMain,
                R.string.open,
                R.string.close
            )
            DrwerLaierMain.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {

                when(it.itemId){
                    R.id.CreateEventID ->{
                        val intent= Intent(this,
                            CreateAnEvent::class.java)
                        intent.putExtra("Email",Email)
                        intent.putExtra("Id",ID)
                        startActivity(intent)
                    }
                    R.id.HistoryEvent ->{
                        val intent= Intent(this,
                            BookingHistory::class.java)
                        startActivity(intent)
                    }
                    R.id.Inbox ->{
                        val intent= Intent(this,
                            InboxForNormalUsers::class.java)
                        startActivity(intent)
                    }
                    R.id.Friends ->{
                        val intent= Intent(this,
                            FriendsLayout::class.java)
                        startActivity(intent)
                    }
                    R.id.Notification ->{
                        val intent= Intent(this,
                            NotificationPage::class.java)
                        startActivity(intent)
                    }
                    R.id.History ->{
                        val intent= Intent(this,
                            HistoryEvents::class.java)
                        startActivity(intent)
                    }
                    R.id.Artist ->{
                        val intent= Intent(this,
                            ArtistPage::class.java)
                        startActivity(intent)
                    }
                    R.id.MyEvent ->{
                        val intent= Intent(this, MyEvents::class.java)
                        startActivity(intent)
                    }
                    R.id.Favorite ->{
                        val intent= Intent(this,
                            FavoriteArtist::class.java)
                        startActivity(intent)
                    }
                    R.id.Setting ->{
                        val intent= Intent(this, SettingPage::class.java)
                        startActivity(intent)
                    }
                    R.id.Help ->{
                        val intent= Intent(this,
                            HelpPage::class.java)
                        startActivity(intent)
                    }
                    R.id.LogOut ->{
                        val intent= Intent(this,
                            MainActivity::class.java)
                        startActivity(intent)

                    }

                }
                true
            }


    }

    fun ProfileUser(view: View){
        val intent=Intent(this, UserProfile::class.java)
        intent.putExtra("Email",Email)
        intent.putExtra("ID",ID)
        startActivity(intent)
    }

    //for nav menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    fun SingerOnClick(view: View){
        val intent= Intent(this,MainPageForNormalUsers::class.java)
        intent.putExtra("Type","Singing")
        startActivity(intent)
    }


    fun LoadHeaderInfo(){
        var view:NavigationView=findViewById(R.id.navView)
        var headerView=view.getHeaderView(0)
        var FullName:TextView=headerView.findViewById(R.id.TVFullUserName)
        var email:TextView=headerView.findViewById(R.id.TVEmail)



        val postListener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds:DataSnapshot in dataSnapshot.children){
                    if (ds.child("email").value?.equals(Email)!!){
                       FullName.text=ds.child("firstName").value.toString()+" "+ds.child("lastName").value.toString()
                        email.text=ds.child("email").value.toString()
                        if (ds.child("image").value.toString()!="null"){
                            Picasso.with(applicationContext).load(ds.child("image").value.toString()).into(IVUserProfileImage)
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







}