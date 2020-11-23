package com.example.wl3ha.user_page.ticket

class InboxTecket
{
    var Name:String?=null
    var Time:String?=null
    var Message:String? =null
    var Image:Int?=null

    constructor(Name:String,Message:String,Image:Int,Time:String){
        this.Name=Name
        this.Message=Message
        this.Image=Image
        this.Time=Time
    }


}