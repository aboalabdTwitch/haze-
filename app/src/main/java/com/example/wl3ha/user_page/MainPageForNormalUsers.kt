package com.example.wl3ha.user_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.wl3ha.*
import com.example.wl3ha.user_page.ticket.EventTicket
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_page_for_normal_users.*
import kotlinx.android.synthetic.main.event_tecket.view.*
import kotlinx.android.synthetic.main.header.*

class MainPageForNormalUsers : AppCompatActivity() {


    var ListOfEvents=ArrayList<EventTicket>()
    var Dapter: EventTicketAdapter?=null

    private var database= FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("Events") // to access to root
    private var mStorge: StorageReference?=null
    private var EventType:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_for_normal_users)

        var intent: Intent =getIntent()
        EventType=intent.getStringExtra("Type")







    }

    override fun onStart() {
        super.onStart()
        LoadEvents()

        Dapter=EventTicketAdapter(ListOfEvents,this)
        LVEvents.adapter=Dapter
    }






    fun LoadEvents(){

        var type:String
        var title:String
        var date:String
        var cap:String
        var img:String
        val postListener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds:DataSnapshot in dataSnapshot.children){
                    if (ds.child("type").value?.toString().equals("Singing")!!){
                        title=ds.child("title").value.toString()
                        type=ds.child("type").value.toString()
                        date=ds.child("date").value.toString()
                        cap=ds.child("capacity").value.toString()
                        img=ds.child("img").value.toString()

                        ListOfEvents.add(EventTicket(title,type,date,cap,img))

                    }

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, databaseError.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        mRef.addValueEventListener(postListener)

    }

    inner class EventTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<EventTicket>()
        var context:Context?=null
        constructor(ListOfEvents:ArrayList<EventTicket>, context:Context){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.event_tecket,null)

            /*val postListener = object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds:DataSnapshot in dataSnapshot.children){
                        if (ds.child("type").value?.toString().equals(EventType)!!){
                            EventView.TVTitle.text=ds.child("title").value.toString()
                            EventView.TVType.text=ds.child("type").value.toString()
                            EventView.TVDate.text=ds.child("date").value.toString()
                            EventView.TVPeaopleCount.text=ds.child("capacity").value.toString()
                            EventView.TVMoreDetails.text="More Details"
                            Picasso.with(applicationContext).load(ds.child("image").value.toString()).into(EventView.IVEvent)

                        }

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(applicationContext, databaseError.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            mRef.addValueEventListener(postListener)*/

            EventView.TVType.text=Events.Type
            EventView.TVTitle.text=Events.Title
            EventView.TVMoreDetails.text="more details"
            EventView.TVDate.text=Events.Date
            EventView.TVPeaopleCount.text=Events.PeopleLeft
            Picasso.with(context).load(Events.Image).into(EventView.IVEvent)


            //EventView.IVEvent.setImageResource(Events.Image!!)

            EventView.EventLayout.setOnClickListener{
                val intent = Intent(context,
                    EventDetails::class.java)
                intent.putExtra("Image",Events.Image!!)
                context!!.startActivity(intent)
            }

            return EventView
        }

        override fun getItem(p0: Int): Any {
            return ListOfEventsLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return ListOfEventsLocal.size
        }

    }

}