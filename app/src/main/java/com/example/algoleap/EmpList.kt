package com.example.algoleap


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.algoleap.databinding.ActivityEmpListBinding
import kotlinx.coroutines.launch


class EmpList : AppCompatActivity() {

    private lateinit var binding: ActivityEmpListBinding
    private var mAdapter: RecyclerAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_list)
        binding = ActivityEmpListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolBar))

        binding.add.setOnClickListener {
            val intent = Intent(this@EmpList, AddDetails::class.java)
            startActivity(intent)
        }

        binding.logout.setOnClickListener {
            val intent = Intent(
                this@EmpList, Login::class.java)
            startActivity(intent)

        }

        mAdapter?.onItemClick = {
            val intent = Intent(this, Details::class.java)
            intent.putExtra("emp", it)
            startActivity(intent)
        }
        


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)




    }




    private fun setAdapter(list:List<EmpEnt>){
        mAdapter?.setData(list)

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val empList = AppDb(this@EmpList).getEmpDao().getAllEmpEnt()

            mAdapter = RecyclerAdapter()
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@EmpList)
                adapter = mAdapter
                setAdapter(empList)

                    mAdapter?.setOnActionUpdateListener {
                        val intent = Intent(this@EmpList, AddDetails::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }

                    mAdapter?.setOnActionDeleteListener {
                        val builder = AlertDialog.Builder(this@EmpList)
                        builder.setMessage("Are you sure, You want to delete?")
                        builder.setPositiveButton("Yes")
                        { p0, p1 ->
                            lifecycleScope.launch {
                                AppDb(this@EmpList).getEmpDao().deleteEmpEnt(it)
                                val emList = AppDb(this@EmpList).getEmpDao().getAllEmpEnt()
                                setAdapter(emList)
                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("No") { p0, p1 ->
                            p0.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }
                }
            }
        }
    }
