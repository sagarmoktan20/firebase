    package com.example.firebasetut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

    class welcomeactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomeactivity)
        val uname=intent.getStringExtra(signin.key2)
        val emil=intent.getStringExtra(signin.key1)

        val welcomeText=findViewById<TextView>(R.id.welcometv)
        val mail=findViewById<TextView>(R.id.mail)
        val username=findViewById<TextView>(R.id.username)
        mail.text="email $emil"
        welcomeText.text="Welcome $uname"
        username.text="Username = $uname"


    }
}