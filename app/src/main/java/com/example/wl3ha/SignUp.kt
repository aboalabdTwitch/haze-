package com.example.wl3ha

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wl3ha.user_page.*
import com.example.wl3ha.user_page.Database.SignUpInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_an_event.*
import kotlinx.android.synthetic.main.activity_edit_personal_information_for_users.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import org.xml.sax.Parser
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.regex.Pattern


class SignUp : AppCompatActivity(), DatePickerDialog.OnDateSetListener {



    private var FirstName:String?=null
    private var LastName:String?=null
    private var Username:String?=null
    private var Password:String?=null
    private var Birthday:String?=null
    private var Email:String?=null
    private var PhoneNumber:String?=null
    private var Gender:String="Female"
    private var mAuth: FirebaseAuth?=null // for firebase
    private var Check:Boolean=false  //TO CHECK IF THERE IS SOME INFORMATION MISSING

    private var database=FirebaseDatabase.getInstance()//to connect to firebase
    private var mRef=database.getReference("NormalUsers") // to access to root

    var dy=0
    var mnth=0
    var yer=0
    var mdy=0
    var mmnth=0
    var myer=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth=FirebaseAuth.getInstance()




        TextEmailUser.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(TextEmailUser.text.toString()).matches())
                    BUSignUpForUser.isEnabled=true
                else {
                    TextEmailUser.setError("Inavalid Email")
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })



        TextPhoneNumberUser.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mobileValidate(TextPhoneNumberUser.text.toString()))
                    BUSignUpForUser.isEnabled=true
                else{
                    TextPhoneNumberUser.setError("Inavlid Mobile Number")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        TextPasswordUser.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(PasswordValidate(TextPasswordUser.text.toString()))
                    BUSignUpForUser.isEnabled=true
                else{
                    TextPasswordUser.setError("The Password should be at least 8 char. And contains at least one upper case and one number")
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })





        Pickdate()

    }

    private  fun Pickdate(){
        TextBirthdayUser.setOnClickListener {
            getDateTimeCalender()
            DatePickerDialog(this,this,yer,mnth,dy).show()

        }

    }
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        mdy=p3
        mmnth=p2
        myer=p1

        getDateTimeCalender()
        TextBirthdayUser.text=Editable.Factory.getInstance().newEditable("$mdy-$mmnth-$myer")



    }

    private fun getDateTimeCalender(){
        val c=Calendar.getInstance()
        dy=c.get(Calendar.DAY_OF_MONTH)
        mnth=c.get(Calendar.MONTH)
        yer=c.get(Calendar.YEAR)



    }




    private fun PasswordValidate(text: String?): Boolean {
        var p= Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
        var m=p.matcher(text)
        return m.matches()

    }

    private fun mobileValidate(text: String?): Boolean {
        var p= Pattern.compile("[0][7][7-9][0-9]{7}")
        var m=p.matcher(text)
        return m.matches()

    }









 //TODO valid all input
// to check all feilds if it empty or not
    fun CheckAllFeild(FirstName:String,LastName:String,
                      Username:String,Password:String,Birthday:String,Email:String,
                      PhoneNumber:String,view: View) {

     if (FirstName.length == 0 || LastName.length == 0 || Username.length == 0 || Password.length == 0 || Birthday.length == 0 || Email.length == 0 || PhoneNumber.length == 0) {
         Toast.makeText(this, "Missing some information", Toast.LENGTH_SHORT).show()
         Check = false
         progressSignUpUser.visibility = View.GONE
     } else {
         var rb: RadioButton
         var rbb: RadioButton
         rb = findViewById(R.id.RadioFemal)
         rbb = findViewById(R.id.RadioMale)
         val checked = rb.isChecked
         val checked2 = rbb.isChecked
         if (checked || checked2) {
             Check = true
         } else {
             Check = false
             progressSignUpUser.visibility = View.GONE
             Toast.makeText(this, "Missing some Information", Toast.LENGTH_SHORT).show()
         }
     }


 }

    //on click function when press signUp
    fun SignUpUser(view: View){
        progressSignUpUser.visibility=View.VISIBLE

        var FirstName=TextFirstNameUser.text.toString()
        var LastName=TextLastNameUser.text.toString()
        var Username=TextUsernameUser.text.toString()
        var Password=TextPasswordUser.text.toString()
        var Birthday=TextBirthdayUser.text.toString()
        var Email=TextEmailUser.text.toString()
        var PhoneNumber=TextPhoneNumberUser.text.toString()

        CheckAllFeild(FirstName,LastName,Username,Password,Birthday,Email,PhoneNumber,view)
        if(Check){
        SignUpFireBase(FirstName,LastName, Username, Password, Birthday, Email, PhoneNumber,Gender)
        FirebaseSignUp(Email, Password)
            }

    }











    //For all info
    fun SignUpFireBase(FirstName:String,LastName:String,
                       Username:String,Password:String,Birthday:String,Email:String,
                       PhoneNumber:String,Gender:String){
        var id=mRef.push().key
        var myInfo=SignUpInfo(id!!,FirstName,LastName,Password,Email,ServerValue.TIMESTAMP,Username,PhoneNumber,Birthday,Gender)
        mRef.child(id!!).setValue(myInfo)

        val intent=Intent(this,MainPageCategorey::class.java)
        intent.putExtra("Email",Email)
        intent.putExtra("ID",id)
        startActivity(intent)


    }


    //for firebase SignUP Password and Email
    fun FirebaseSignUp(Email:String,Password:String)
    {


            if (Email.isNotEmpty() && Password.isNotEmpty()) {
                mAuth?.createUserWithEmailAndPassword(Email, Password)
                    ?.addOnCompleteListener {// to see if it done correct
                        if (it.isSuccessful) {
                            Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                            progressSignUpUser.visibility=View.GONE

                        }
                        else{
                            Toast.makeText(applicationContext,it.exception.toString(),Toast.LENGTH_SHORT).show()
                            progressSignUpUser.visibility=View.GONE
                        }
                    }
            } else{
                Toast.makeText(applicationContext, "empty", Toast.LENGTH_SHORT).show()
                progressSignUpUser.visibility=View.GONE
            }

    }



    //to go on sign-up artist Page
    fun GoToArtistSignUp(view: View){
        val intent=Intent(this, SignUpForArtist::class.java)
        startActivity(intent)
    }

    // CHECK FOR RADIOBUTTON IF MALE OR FEMALE
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.RadioMale ->
                    if (checked) {
                        Gender="Male"
                    }
                R.id.RadioFemal ->
                    if (checked) {
                        Gender="Female"
                    }
            }
        }
    }





}