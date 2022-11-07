package com.example.algoleap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){
    private var list = mutableListOf<EmpEnt>()
    private var actionUpdate: ((EmpEnt) -> Unit)? = null
    private var actionDelete: ((EmpEnt)->Unit)? = null
    var onItemClick : ((EmpEnt) -> Unit)?=null


    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): RecyclerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
            return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val empEnt = list[position]
        holder.tvName.text = empEnt.employeeName
        holder.tvId.text = empEnt.employeeId
        holder.tvEmail.text = empEnt.employeeEmail

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(empEnt)
        }

        holder.actionUpdate.setOnClickListener { actionUpdate?.invoke(empEnt) }
        holder.actionDelete.setOnClickListener { actionDelete?.invoke(empEnt) }


    }


    override fun getItemCount() = list.size

    fun setData(data: List<EmpEnt>) {
        list.apply{
            clear()
            addAll(data)
        }
    }

    fun setOnActionUpdateListener(callback: (EmpEnt) -> Unit) {
        this.actionUpdate = callback
    }

    fun setOnActionDeleteListener(callback: (EmpEnt) -> Unit) {
        this.actionDelete = callback
    }



    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tv_title)
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_description)
        val actionUpdate: ImageView = itemView.findViewById(R.id.action_update)
        val actionDelete: ImageView = itemView.findViewById(R.id.action_delete)
        }
    }




