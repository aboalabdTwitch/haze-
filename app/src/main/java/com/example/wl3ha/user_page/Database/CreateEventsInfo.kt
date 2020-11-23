package com.example.wl3ha.user_page.Database

class CreateEventsInfo  {

    var Priveciy:String?=null
    var date:String?=null
    var Time:String?=null
    var Capacity:String?=null
    var Type:String?=null
    var Title:String?=null
    var img:String?=null
    var description:String?=null
    var price:String?=null
    var interesting:Int?=null

    constructor(
        Priveciy:String,
        Date:String,
        Time:String,
        Capacity:String,
        Type:String,
        Title:String,
        img:String,
        des:String,
        price:String,
        interesting: Int
    ){
        this.Priveciy=Priveciy
        this.date=Date
        this.Time=Time
        this.Capacity=Capacity
        this.Type=Type
        this.Title=Title
        this.img=img
        this.description=des
        this.price=price
        this.interesting=interesting


    }





}