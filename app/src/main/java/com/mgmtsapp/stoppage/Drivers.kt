package com.mgmtsapp.stoppage

class Drivers {
    var dMail:String = ""
    var dName :String = ""
    var dID :String = ""
    var dAssignedBusID : String = ""

    constructor(dmail:String, dName:String, dID:String, dBusId:String){
        this.dMail = dMail
        this.dName = dName
        this.dID = dID
        this.dAssignedBusID = dBusId
    }
}