package com.example.wl3ha.user_page.ticket

class RequestedFriendsTicket
{
    var Name:String?=null
    var Image:Int?=null
    var ID:Int?=null
    var Accepted:Int?=null
    var Rejected:Int?=null

    constructor(Name:String,ID:Int,Image:Int,Accepted:Int,Rejected:Int){
        this.Name=Name
        this.Image=Image
        this.ID=ID
        this.Accepted=Accepted
        this.Rejected=Rejected
    }
}