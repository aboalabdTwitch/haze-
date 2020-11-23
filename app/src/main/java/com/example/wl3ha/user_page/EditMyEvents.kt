package com.example.wl3ha.user_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.wl3ha.R
import kotlinx.android.synthetic.main.activity_create_an_event.*

class EditMyEvents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_events)

        TypeEventBTN.setOnClickListener{
            // Show the multiple choice list items on an alert dialog
            showDialog()
        }
    }

    // Method to show an alert dialog with multiple choice list items
    private fun showDialog(){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog

        // Initialize an array of colors
        val arrayColors = arrayOf("Singing","Magic shows","Dancing","Dabkeh","DJs","Music band","Birthday team","Stand-up comedy","Rapping","Healthy","Sport","Poetic","Wedding")

        // Initialize a boolean array of checked items
        val arrayChecked = booleanArrayOf(false,false,false,false,false,false,false,false,false,false,false,false,false)


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        builder.setTitle("Type of your event")


        // Define multiple choice items for alert dialog
        builder.setMultiChoiceItems(arrayColors, arrayChecked, {dialog,which,isChecked->
            // Update the clicked item checked status
            arrayChecked[which] = isChecked

            // Get the clicked item
            val color = arrayColors[which]

            // Display the clicked item text
            toast("$color clicked.")
        })


        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { _, _ ->

            /*           // Do something when click positive button
            text_view.text = "Your preferred colors..... \n"
            for (i in 0 until arrayColors.size) {
                val checked = arrayChecked[i]
                if (checked) {
                    text_view.text = "${text_view.text}  ${arrayColors[i]} \n"*/

        }


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}

// Extension function to show toast message
/*fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}*/
