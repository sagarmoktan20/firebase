package com.example.firebasetut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signinbtn = findViewById<Button>(R.id.btnsignup)
        val etusernam = findViewById<EditText>(R.id.username)
        val etemail = findViewById<EditText>(R.id.email)
        val etpssword = findViewById<EditText>(R.id.password)

        signinbtn.setOnClickListener {

            val username = etusernam.text.toString()
            val email = etemail.text.toString()
            val password = etpssword.text.toString()
            val user = Users(username, email, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {
                etusernam.text?.clear()
                etemail.text?.clear()
                etpssword.text?.clear()
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, " failed try again", Toast.LENGTH_SHORT).show()
            }
        }

    }
}