package com.example.firebasetut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    companion object{
        const val key1="com.example.firebasetut.mail.key"
        const val key2="com.example.firebasetut.uname.key"

    }
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signinbtn = findViewById<Button>(R.id.btnsignin)
        val username = findViewById<EditText>(R.id.signinusername)

        signinbtn.setOnClickListener {
            //take refrence till the node user
            val usernamestring = username.text.toString()
            if (usernamestring.isNotEmpty()) {
                readData(usernamestring)

            } else {
                Toast.makeText(this, "please enter your username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(usernamestring: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(usernamestring).get().addOnSuccessListener {
            if(it.exists()){
                //welcome user to next page
                val email=it.child("email").value
                val password=it.child("password").value
                val username=it.child("username").value

                val intentwelcome=Intent(this,welcomeactivity::class.java)
                intentwelcome.putExtra(key1,email.toString())
                intentwelcome.putExtra(key2,username.toString())
                startActivity(intentwelcome)
            }else{
                Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show()
            }


        }.addOnFailureListener {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
    }
}