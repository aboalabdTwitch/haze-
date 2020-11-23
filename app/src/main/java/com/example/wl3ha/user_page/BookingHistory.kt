package com.example.wl3ha.user_page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wl3ha.R
import com.example.wl3ha.user_page.ticket.BookingHistoryTicket
import kotlinx.android.synthetic.main.activity_booking_history.*
import kotlinx.android.synthetic.main.booking_history_tecket.view.*


class BookingHistory : AppCompatActivity() {

    var ListOfBookingHistory=ArrayList<BookingHistoryTicket>()
    var Dapter: HistoryTicketAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)


        LoadBookingHistory()

        Dapter=HistoryTicketAdapter(ListOfBookingHistory,this)
        LVCurrentBooking.adapter=Dapter
    }


    fun LoadBookingHistory(){
        ListOfBookingHistory.add(
            BookingHistoryTicket(
                "Cool Of Summer",
                "Dance",
                "11/12/2020",
                "50$",
                R.drawable.event3,
                "9 PM"
            )
        )
    }


    inner class HistoryTicketAdapter: BaseAdapter {

        var ListOfBookingHistoryLocal=ArrayList<BookingHistoryTicket>()
        var context: Context?=null
        constructor(ListOfBookingHistory:ArrayList<BookingHistoryTicket>, context: Context){
            ListOfBookingHistoryLocal=ListOfBookingHistory
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfBookingHistoryLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.booking_history_tecket,null)
            EventView.TVTitle.text=Events.Title!!
            EventView.TVType.text=Events.Type!!
            EventView.TVDate.text=Events.Date!!
            EventView.IVEvent.setImageResource(Events.Image!!)
            EventView.TVPrice.text=Events.Price!!
            EventView.TVTime.text=Events.Time!!


            return EventView
        }

        override fun getItem(p0: Int): Any {
            return ListOfBookingHistoryLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return ListOfBookingHistoryLocal.size
        }

    }
}