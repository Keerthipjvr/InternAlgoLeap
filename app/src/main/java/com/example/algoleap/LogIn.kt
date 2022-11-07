package com.example.algoleap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var submit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)


        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        submit = findViewById(R.id.submit)


        submit.setOnClickListener {

            val email = email.text.toString()
            val pass = password.text.toString()

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Please enter Email Id", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
                }else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else{

            val intent = Intent(this, EmpList::class.java)
            startActivity(intent)
            finish()

}
            }


    }
}