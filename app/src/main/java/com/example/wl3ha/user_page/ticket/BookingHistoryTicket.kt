package com.example.wl3ha.user_page.ticket

class BookingHistoryTicket
{
    var Title:String?=null
    var Type:String?=null
    var Date:String? =null
    var Price:String ?=null
    var Image:Int?=null
    var Time:String?=null

    constructor(Title:String,Type:String,Date:String,Price:String,Image:Int,Time:String){
        this.Title=Title
        this.Type=Type
        this.Date=Date
        this.Price=Price
        this.Image=Image
        this.Time=Time
    }
}