package com.example.firebasetut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupbtn = findViewById<Button>(R.id.btnsignup)
        val etusernam = findViewById<EditText>(R.id.username)
        val etemail = findViewById<EditText>(R.id.email)
        val etpssword = findViewById<EditText>(R.id.password)


        signupbtn.setOnClickListener {

            val username = etusernam.text.toString()
            val email = etemail.text.toString()
            val password = etpssword.text.toString()
            val user = Users(username, email, password)
            if (username.toString().isNotEmpty()&&email.toString().isNotEmpty() &&password.toString().isNotEmpty()){
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {
                etusernam.text?.clear()
                etemail.text?.clear()
                etpssword.text?.clear()
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, " failed try again", Toast.LENGTH_SHORT).show()
            }}
                else{
                Toast.makeText(this, "Please enter the above credentials", Toast.LENGTH_SHORT).show()

                }
        }
        val signintext=findViewById<TextView>(R.id.tvsignin)
        signintext.setOnClickListener {
            val opensignInactivity=Intent(this,signin::class.java)
            startActivity(opensignInactivity)
        }

    }
}