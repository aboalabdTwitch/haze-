package com.example.wl3ha.user_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wl3ha.R
import com.example.wl3ha.user_page.ticket.RequestedFriendsTicket
import kotlinx.android.synthetic.main.activity_requested_friends.*
import kotlinx.android.synthetic.main.requested_friends_ticket.view.*

class RequestedFriends : AppCompatActivity() {

    var ListOfEvents=ArrayList<RequestedFriendsTicket>()
    var Dapter: EventTicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requested_friends)

        BUFriends.setOnClickListener {
            val intent= Intent(this, FriendsLayout::class.java)
            startActivity(intent)
        }

        BUSearch.setOnClickListener {
            val intent= Intent(this, SearchForFriends::class.java)
            startActivity(intent)
        }

        LoadEvents()

        Dapter=EventTicketAdapter(ListOfEvents,this)
        LVRequested.adapter=Dapter
    }

    fun LoadEvents(){
        ListOfEvents.add(
            RequestedFriendsTicket(
                "Hazem Bodair",
                1,
                R.drawable.man,
                0,
                0
            )
        )
        ListOfEvents.add(
            RequestedFriendsTicket(
                "Hazem Samer",
                1,
                R.drawable.man,
                0,
                0
            )
        )
    }

    inner class EventTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<RequestedFriendsTicket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<RequestedFriendsTicket>,
            context: Context
        ){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.requested_friends_ticket,null)
            EventView.TVFriendNamee.text=Events.Name!!
            EventView.IVFriendImage.setImageResource(Events.Image!!)

           /* EventView.FriendsProfile.setOnClickListener {
                val intent= Intent(context,ProfileFriends::class.java)
                startActivity(intent)
            }*/



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