package com.qtgm.expand.md

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.qtgm.expand.R
import com.qtgm.expand.entity.AnimalEntity
import com.qtgm.expand.entity.getAnimals
import kotlinx.android.synthetic.main.activity_md.*
import kotlinx.android.synthetic.main.adapter_md_layout.view.*

class MdActivity : AppCompatActivity() {

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_md)
        mContext = this
        toolBar.title = "MdActivity"
        toolBar.setBackgroundColor(getColor(R.color.teal_200))
        setSupportActionBar(toolBar)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_md2, menu)
        return true
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter =
            AnimalsAdapter(getAnimals())

        fab.setOnClickListener {
            Snackbar.make(fab, "data delete", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(mContext, "data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    class AnimalsAdapter(var list: List<AnimalEntity>) :
        RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_md_layout, parent, false)
            return ViewHolder(
                itemView
            )
        }

        override fun getItemCount(): Int = if (list.isNullOrEmpty()) 0 else list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.ivShow.setImageResource(list[position].icon)
            holder.itemView.tvShow.text = list[position].name
            holder.itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, AnimalActivity::class.java))
            }
        }


        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }
}