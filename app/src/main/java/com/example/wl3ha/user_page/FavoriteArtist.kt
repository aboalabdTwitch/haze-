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
import com.example.wl3ha.user_page.ticket.ArtistTicket
import kotlinx.android.synthetic.main.activity_favorite_artist.*
import kotlinx.android.synthetic.main.artist_ticket.view.*

class FavoriteArtist : AppCompatActivity() {

    var ListOfEvents=ArrayList<ArtistTicket>()
    var Dapter: EventTicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_artist)

        LoadEvents()

        Dapter=EventTicketAdapter(ListOfEvents,this)
        LVArtistFavorite.adapter=Dapter

    }
    fun LoadEvents(){
        ListOfEvents.add(
            ArtistTicket(
                "Hazem Bodair",
                "$10",
                R.drawable.man
            )
        )
        ListOfEvents.add(
            ArtistTicket(
                "Yousof ",
                "$200",
                R.drawable.man
            )
        )
    }

    inner class EventTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<ArtistTicket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<ArtistTicket>,
            context: Context
        ){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.artist_ticket,null)
            EventView.TVName.text=Events.Name!!
            EventView.TVPrice.text=Events.Price!!
            EventView.IVArtist.setImageResource(Events.Image!!)

            EventView.ArtistAll.setOnClickListener {
                val intent= Intent(context,
                    ArtistProfile::class.java)
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