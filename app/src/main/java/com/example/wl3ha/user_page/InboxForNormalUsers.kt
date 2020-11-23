package com.example.wl3ha.user_page

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wl3ha.R
import com.example.wl3ha.user_page.ticket.InboxTecket
import kotlinx.android.synthetic.main.activity_inbox_for_normal_users.*
import kotlinx.android.synthetic.main.inboxnormaluserstecket.view.*

class InboxForNormalUsers : AppCompatActivity() {

    var ListOfInbox=ArrayList<InboxTecket>()
    var Dapter: InboxAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox_for_normal_users)



        LoadInbox()

        Dapter=InboxAdapter(ListOfInbox,this)
        LVInbox.adapter=Dapter
    }

    fun LoadInbox(){
        ListOfInbox.add(
            InboxTecket(
                "Name",
                "Message",
                R.drawable.man,
                "1 hour"
            )
        )
    }


    inner class InboxAdapter: BaseAdapter {

        var ListOfInboxLocal=ArrayList<InboxTecket>()
        var context: Context?=null
        constructor(ListOfInbox:ArrayList<InboxTecket>, context: Context){
            ListOfInboxLocal=ListOfInbox
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var Events=ListOfInboxLocal[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var EventView=inflater.inflate(R.layout.inboxnormaluserstecket,null)
            EventView.TVName.text=Events.Name!!
            EventView.TVMessage.text=Events.Message!!
            EventView.IVChatImage.setImageResource(Events.Image!!)
            EventView.TVTime.text=Events.Time!!


            return EventView
        }

        override fun getItem(p0: Int): Any {
            return ListOfInboxLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return ListOfInboxLocal.size
        }

    }
}