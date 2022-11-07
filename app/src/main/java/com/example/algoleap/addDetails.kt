package com.example.algoleap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.algoleap.databinding.ActivityAddDetailsBinding


class AddDetails : AppCompatActivity() {
    private lateinit var binding: ActivityAddDetailsBinding
    private var empEnt: EmpEnt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)
        binding = ActivityAddDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (empEnt == null) binding.saveOrUpdate.text = "Save or Update "
        else {
            binding.saveOrUpdate.text = "Update"
            binding.EName.setText(empEnt?.employeeName.toString())
            binding.EId.setText(empEnt?.employeeId.toString())
            binding.EEmail.setText(empEnt?.employeeEmail.toString())
        }
        binding.saveOrUpdate.setOnClickListener {
            addEmp()
        }

    }

    private fun addEmp() {
        val employeeName = binding.EName.text.toString()
        val employeeId = binding.EId.text.toString()
        val employeeEmail = binding.EEmail.text.toString()

        lifecycleScope.launch {
            if (empEnt == null) {
                val empEnt = EmpEnt(
                    employeeName = employeeName,
                    employeeId = employeeId,
                    employeeEmail = employeeEmail
                )

                AppDb(this@AddDetails).getEmpDao().insertEmpEnt(empEnt)
                finish()
            } else {
                val em = EmpEnt(employeeName,employeeId,employeeEmail)
                em.id = empEnt?.id?: 0
                AppDb(this@AddDetails).getEmpDao().updateEmpEnt(em)
                finish()
            }
        }
    }
}