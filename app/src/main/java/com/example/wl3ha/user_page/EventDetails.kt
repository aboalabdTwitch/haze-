package com.example.wl3ha.user_page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wl3ha.R
import com.example.wl3ha.user_page.ticket.ComentForArtistTicket
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.coment_for_artist_ticket.view.*

class EventDetails : AppCompatActivity() {
    var ListOfComment=ArrayList<ComentForArtistTicket>()
    var Dapter: EventTicketAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        var bundel=intent.extras
        val image=bundel!!.getInt("Image")
        EventImage.setImageResource(image)


        LoadEvents()

        Dapter=EventTicketAdapter(ListOfComment,this)
        LVComment.adapter=Dapter
    }

    fun LoadEvents(){
        ListOfComment.add(
            ComentForArtistTicket(
                "Hazem Bodair",
                " sngorg song s;ogns gowng w gopwg wo[ gw ojg owjig wglw gow[g w; gwo gw gowbg wouw gowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agnsowigbw gwog w gongwl gangig o[agns",
                R.drawable.man
            )
        )
        ListOfComment.add(
            ComentForArtistTicket(
                "Yousof ",
                "$ sngorg song s;ogns gowng w gopwg wo[ gw ojg owjig wglw gow[g w; gwo gw gowbg wouw gowigbw gwog w gongwl gangig o[agns",
                R.drawable.man
            )
        )
        ListOfComment.add(
            ComentForArtistTicket(
                "Yousof ",
                "$ sngorg song s;ogns gowng w gopwg wo[ gw ojg owjig wglw gow[g w; gwo gw gowbg wouw gowigbw gwog w gongwl gangig o[agns",
                R.drawable.man
            )
        )
    }


    inner class EventTicketAdapter: BaseAdapter {

        var ListOfEventsLocal=ArrayList<ComentForArtistTicket>()
        var context: Context?=null
        constructor(
            ListOfEvents: ArrayList<ComentForArtistTicket>,
            context: Context
        ){
            ListOfEventsLocal=ListOfEvents
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfEventsLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.coment_for_artist_ticket,null)
            EventView.TVArtistName.text=Events.Name!!
            EventView.TVCommentForArtist.text=Events.Comment!!
            EventView.IVArtistImage.setImageResource(Events.Image!!)

            /* EventView.ArtistAll.setOnClickListener {
                 val intent= Intent(context,ArtistProfile::class.java)
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