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
import com.example.wl3ha.user_page.ticket.MyEventsTicket
import kotlinx.android.synthetic.main.activity_my_events.*
import kotlinx.android.synthetic.main.my_events_icket.view.*
import kotlinx.android.synthetic.main.my_events_icket.view.MyEventLayout

class MyEvents : AppCompatActivity() {
    var ListOfEvent=ArrayList<MyEventsTicket>()
    var Dapter: TicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events)

        LoadEvents()

        Dapter=TicketAdapter(ListOfEvent,this)
        LVMyEvents.adapter=Dapter
    }

    fun LoadEvents(){
        ListOfEvent.add(
            MyEventsTicket(
                "Hazem Bodair",
                "any",
                "11/4/1998",
                "500",
                R.drawable.event4
            )
        )
        ListOfEvent.add(
            MyEventsTicket(
                "Hazem Bodair",
                "any",
                "11/4/1998",
                "500",
                R.drawable.event4
            )
        )
        ListOfEvent.add(
            MyEventsTicket(
                "Hazem Bodair",
                "any",
                "11/4/1998",
                "500",
                R.drawable.event4
            )
        )
        ListOfEvent.add(
            MyEventsTicket(
                "Hazem Bodair",
                "any",
                "11/4/1998",
                "500",
                R.drawable.event4
            )
        )

    }

    inner class TicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<MyEventsTicket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<MyEventsTicket>,
            context: Context
        ){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.my_events_icket,null)
            EventView.TVTitlee.text=Events.Title!!
            EventView.TVTypee.text=Events.Type!!
            EventView.TVDatee.text=Events.Date!!
            EventView.TVPeaopleCountt.text=Events.PeopleLeft!!
            EventView.TVMoreDetailss.text="Manege"
            EventView.IVEventt.setImageResource(Events.Image!!)


            EventView.MyEventLayout.setOnClickListener {
                 val intent= Intent(context, MyEventsDetails::class.java)
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