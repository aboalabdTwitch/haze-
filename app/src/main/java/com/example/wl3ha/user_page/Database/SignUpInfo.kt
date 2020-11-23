package com.example.wl3ha.user_page.Database

class SignUpInfo {
    var ID:String?=null
    var FirstName:String?=null
    var LastName:String?=null
    var Email:String? =null
    var Password:String ?=null
    var Time:MutableMap<String,String>?=null
    var username:String?=null
    var  PhoneNumber:String?=null
    var Birthday:String?= null
    var Gender:String?=null

    constructor(ID:String,FirstName:String,LastName:String,Password:String,Email:String,Time:MutableMap<String,String>,username:String,PhoneNumber:String,Birthday:String,Gender:String){
        this.FirstName=FirstName
        this.LastName=LastName
        this.Email=Email
        this.Password=Password
        this.Time=Time
        this.ID=ID
        this.username=username
        this.PhoneNumber=PhoneNumber
        this.Birthday=Birthday
        this.Gender=Gender
    }
}