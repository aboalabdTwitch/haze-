package com.example.wl3ha.user_page.ticket

import java.util.*

class EventTicket {

    var Title:String?=null
    var Type:String?=null
    var Date:String? =null
    var PeopleLeft:String ?=null
    var Image:String?=null

    constructor(Title:String,Type:String,Date:String,PeopleLeft:String,Image:String){
        this.Title=Title
        this.Type=Type
        this.Date=Date
        this.PeopleLeft=PeopleLeft
        this.Image=Image
    }


}