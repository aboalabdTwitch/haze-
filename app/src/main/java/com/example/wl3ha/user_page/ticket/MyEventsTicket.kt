package com.example.wl3ha.user_page.ticket

class MyEventsTicket {
    var Title:String?=null
    var Type:String?=null
    var Date:String? =null
    var PeopleLeft:String ?=null
    var Image:Int?=null

    constructor(Title:String,Type:String,Date:String,PeopleLeft:String,Image:Int){
        this.Title=Title
        this.Type=Type
        this.Date=Date
        this.PeopleLeft=PeopleLeft
        this.Image=Image
    }

}