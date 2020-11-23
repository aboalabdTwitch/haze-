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
import com.example.wl3ha.user_page.ticket.FriendsListTecket
import kotlinx.android.synthetic.main.activity_search_for_friends.*
import kotlinx.android.synthetic.main.friends_list_ticket.view.*

class SearchForFriends : AppCompatActivity() {


    var ListOfEvents=ArrayList<FriendsListTecket>()
    var Dapter: EventTicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_friends)

        BUFriends.setOnClickListener {
            val intent= Intent(this, FriendsLayout::class.java)
            startActivity(intent)
        }
        BURequested.setOnClickListener {
            val intent= Intent(this,
                RequestedFriends::class.java)
            startActivity(intent)
        }

        LoadEvents()

        Dapter=EventTicketAdapter(ListOfEvents,this)
        LVSearch.adapter=Dapter
    }

    fun LoadEvents(){
        ListOfEvents.add(
            FriendsListTecket(
                "Hazem Bodair",
                1,
                R.drawable.man
            )
        )
        ListOfEvents.add(
            FriendsListTecket(
                "Yousof ",
                2,
                R.drawable.man
            )
        )
    }

    inner class EventTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<FriendsListTecket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<FriendsListTecket>,
            context: Context
        ){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.friends_list_ticket,null)
            EventView.TVFriendName.text=Events.Name!!
            EventView.IVFriendImage.setImageResource(Events.Image!!)

            EventView.FriendsProfile.setOnClickListener {
                val intent= Intent(context,
                    ProfileFriends::class.java)
                startActivity(intent)
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