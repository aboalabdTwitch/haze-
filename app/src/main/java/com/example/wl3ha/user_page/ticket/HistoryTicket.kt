package com.example.wl3ha.user_page.ticket

class HistoryTicket
{
    var Title:String?=null
    var Type:String?=null
    var Date:String? =null
    var Image:Int?=null

    constructor(Title:String,Type:String,Date:String,Image:Int){
        this.Title=Title
        this.Type=Type
        this.Date=Date
        this.Image=Image
    }
}