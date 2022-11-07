package com.example.algoleap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Details : AppCompatActivity() {
    private lateinit var back2: Button
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initView()
        setValuesToViews()

        back2.setOnClickListener{
            val intent = Intent(this, EmpList::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        tvEmpName = findViewById(R.id.tv_ename)
        tvEmpId = findViewById(R.id.tv_eid)
        tvEmpEmail = findViewById(R.id.tv_eemail)
        back2 = findViewById(R.id.back2)


    }

    private fun setValuesToViews() {
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpEmail.text = intent.getStringExtra("empEmail")

    }
}