package com.example.wl3ha.user_page

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.wl3ha.R
import com.example.wl3ha.user_page.Database.CreateEventsInfo
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_create_an_event.*
import java.lang.Exception
import java.util.*

class CreateAnEvent : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var Email:String?=null //to get the email from database and save it on this variable
    private var ID:String?=null

    private var database= FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("Events") // to access to root
    private var Privacy:String?=null
    private var mStorge: StorageReference?=null
    private lateinit var IDEvents:String
    private var TypeEvent:String?=null

    private lateinit var int:Intent

    var ImageEvent:String?=null
    var PlacePicker:Int=1

    var day=0
    var month=0
    var year=0
    var hour=0
    var min=0
    var savedDay=0
    var savedMonth=0
    var savedYear=0
    var savedHour=0
    var savedMin=0



    //TODO DEAl with artist
    //TODO check all fields (Validate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_an_event)

        IDEvents=mRef.push().key!!


        int = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)


        var intent: Intent =getIntent()
        Email=intent.getStringExtra("Email")
        ID=intent.getStringExtra("Id")

        pickdate()

        //textView14.findViewById<>()
        textView14.text



        mStorge= FirebaseStorage.getInstance().getReference("EventsPicture") //to access the root for Firebase storage



        TypeEventBTN.setOnClickListener{
            // Show the multiple choice list items on an alert dialog
            showDialog()
        }

    }


    fun CreateEventOnClick(view: View){


        var date=ETDateForEvent.text.toString()
        var time=ETTimeForEvent.text.toString()
        var Cap=ETCapacity.text.toString()
        var title=ETTitleForEvent.text.toString()
        var des=ETDescriptionForEvent.text.toString()
        var price=ETPriceForEvent.text.toString()
        var interesting:Int=0


        var NewEvent=CreateEventsInfo(Privacy!!,date,time,Cap,TypeEvent!!,title,ImageEvent!!,des,price,interesting)
        mRef.child(IDEvents!!).setValue(NewEvent)









    }


    fun ImageOnClick(view: View){
        int.type="image/*"
        startActivityForResult(int, 50)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // check if the number is equal and if i piked a photo
        if (requestCode == 50 && resultCode == Activity.RESULT_OK) {
            try {
                val e = data!!.data
                val FilePath=mStorge?.child("image"+e?.lastPathSegment)
                if (e!=null){
                    FilePath?.putFile(e)?.addOnSuccessListener {
                        FilePath.downloadUrl.addOnSuccessListener {
                            ImageEvent=it.toString()
                            //mRef.child(IDEvents).child("image").setValue(it.toString())
                            IVCreateEventImage.setImageURI(e)
                            Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_SHORT).show()
                        }
                    }?.addOnCanceledListener {
                        Toast.makeText(applicationContext, "Try Again", Toast.LENGTH_SHORT).show()

                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
            }


        }
    }



    // CHECK FOR RADIOBUTTON IF MALE OR FEMALE
    fun PrivacyOnClick(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.RadioPrivate ->
                    if (checked) {
                        Privacy="Private"
                    }
                R.id.RadioPublic ->
                    if (checked) {
                        Privacy="Public"
                    }
            }
        }
    }







    // Method to show an alert dialog with multiple choice list items
    private fun showDialog(){
        // Late initialize an alert dialog object
        lateinit var dialog:AlertDialog

        // Initialize an array of colors
        val ListType = arrayOf("Singing","Magic shows","Dancing","Dabkeh","DJs","Music band","Birthday team","Stand-up comedy","Rapping","Healthy","Sport","Poetic","Wedding")

        // Initialize a boolean array of checked items
        val arrayChecked = booleanArrayOf(false,false,false,false,false,false,false,false,false,false,false,false,false)


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        builder.setTitle("Type of your event")

        builder.setSingleChoiceItems(ListType,-1){dialog: DialogInterface?, which: Int ->
            TypeEvent=ListType[which]

            //dismiss dialog
            dialog!!.dismiss()
            
        }
        builder.setNeutralButton("Cancel"){dialog:DialogInterface,which:Int->
            dialog.cancel()

        }


        // Set the positive/yes button click listener
       /* builder.setPositiveButton("OK") { _, _ ->

            /*           // Do something when click positive button
            text_view.text = "Your preferred colors..... \n"
            for (i in 0 until arrayColors.size) {
                val checked = arrayChecked[i]
                if (checked) {
                    text_view.text = "${text_view.text}  ${arrayColors[i]} \n"*/

        }*/


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }

    private fun getDAteTimeCalender(){
        val cal= Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
        hour=cal.get(Calendar.HOUR)
        min=cal.get(Calendar.MINUTE)


    }

    private  fun pickdate(){
        ETDateForEvent.setOnClickListener {
            getDAteTimeCalender()
            DatePickerDialog(this,this,year,month,day ).show()

        }

    }



    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedDay=p3
        savedMonth=p2
        savedYear=p1

        getDAteTimeCalender()

        TimePickerDialog(this,this,hour,min,true).show()




    }


    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        savedHour=p1
        savedMin=p2
        ETDateForEvent.text=Editable.Factory.getInstance().newEditable("$savedDay-$savedMonth-$savedYear")
        ETTimeForEvent.text=Editable.Factory.getInstance().newEditable("$savedHour:$savedMin")

    }
}


















// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
