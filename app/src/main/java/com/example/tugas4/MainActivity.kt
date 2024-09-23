package com.example.tugas4


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.tugas4.model.UserData
import com.example.tugas4.view.UserAdapter


        class MainActivity : AppCompatActivity() {
            private lateinit var btn: FloatingActionButton
            private lateinit var recv: RecyclerView
            private lateinit var userList: ArrayList<UserData>
            private lateinit var userAdapter: UserAdapter
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                setContentView(R.layout.activity_main)
                userList = ArrayList()
                btn = findViewById(R.id.btn)
                recv = findViewById(R.id.recy)
                userAdapter = UserAdapter(this, userList)
                recv.layoutManager = LinearLayoutManager(this)
                recv.adapter = userAdapter
                btn.setOnClickListener { addInfo() }
            }

            private fun addInfo() {
                val inflter = LayoutInflater.from(this)
                val v = inflter.inflate(R.layout.add_item, null)
                val userName = v.findViewById<EditText>(R.id.userName)
                val userNo = v.findViewById<EditText>(R.id.userNo)

                val addDialog = AlertDialog.Builder(this)

                addDialog.setView(v)
                addDialog.setPositiveButton("Ok") { dialog, _ ->
                    val names = userName.text.toString()
                    val number = userNo.text.toString()
                    userList.add(UserData("Nama: $names", "No. : $number"))
                    userAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Informasi berhasil ditambahkan", Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }
                addDialog.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "Dibatalkan", Toast.LENGTH_SHORT).show()

                }
                addDialog.create()
                addDialog.show()
            }
        }