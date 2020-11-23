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
import com.example.wl3ha.user_page.ticket.HistoryTicket
import kotlinx.android.synthetic.main.activity_history_events.*
import kotlinx.android.synthetic.main.event_tecket.view.*

class HistoryEvents : AppCompatActivity() {
    var ListOfEvents=ArrayList<HistoryTicket>()
    var Dapter: HistoryTicketAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_events)

        LoadEvents()

        Dapter=HistoryTicketAdapter(ListOfEvents,this)
        LVHistory.adapter=Dapter
    }

    fun LoadEvents(){
        ListOfEvents.add(
            HistoryTicket(
                "Cool Of Summer",
                "Dance",
                "11/12/2020",
                R.drawable.event3
            )
        )
        ListOfEvents.add(
            HistoryTicket(
                "Hip Hop",
                "Signer",
                "11/7/2020",
                R.drawable.event4
            )
        )

    }

    inner class HistoryTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<HistoryTicket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<HistoryTicket>,
            context: Context){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.history_events,null)
            EventView.TVTitle.text=Events.Title!!
            EventView.TVType.text=Events.Type!!
            EventView.TVDate.text=Events.Date!!
            EventView.TVMoreDetails.text="More Details"
            EventView.IVEvent.setImageResource(Events.Image!!)

            EventView.setOnClickListener{
                val intent = Intent(context,
                    HistoryDetails::class.java)
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