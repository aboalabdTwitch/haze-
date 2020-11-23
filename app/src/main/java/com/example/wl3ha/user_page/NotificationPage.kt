package com.example.wl3ha.user_page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wl3ha.R
import com.example.wl3ha.user_page.ticket.NotificationTicket
import kotlinx.android.synthetic.main.activity_notification_page.*
import kotlinx.android.synthetic.main.notification_ticket.view.*


class NotificationPage : AppCompatActivity() {
    var ListOfNotification=ArrayList<NotificationTicket>()
    var Dapter: NotificationTicketAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_page)
        LoadEvents()

        Dapter=NotificationTicketAdapter(ListOfNotification,this)
        LVNotification.adapter=Dapter
    }


    fun LoadEvents(){
        ListOfNotification.add(
            NotificationTicket(
                "the event will start soon",
                "12:00AM"
            )
        )
        ListOfNotification.add(
            NotificationTicket(
                "the event will staet after 5 hoers",
                "2:00PM"
            )
        )

    }

    inner class NotificationTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<NotificationTicket>()
        var context: Context?=null
        constructor(
            ListOfNotification: ArrayList<NotificationTicket>,
            context: Context){
            ListOfEventsLocal=ListOfNotification
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.notification_ticket,null)
            EventView.TVtitle.text=Events.Title!!
            EventView.TVDate.text=Events.Date!!


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