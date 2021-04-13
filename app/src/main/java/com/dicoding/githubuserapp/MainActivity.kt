package com.dicoding.githubuserapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var searchBar : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_user)
        searchBar = findViewById(R.id.search_bar)
        searchBar.isSingleLine = true
        searchBar.addTextChangedListener {
            searchUser(it.toString())
        }

        adapter = UserAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        initUser()

    }

    fun searchUser(username : String) {
        val pb = findViewById<ProgressBar>(R.id.loading)
        pb.visibility = View.VISIBLE
        val url = "https://api.github.com/search/users?q=$username"
        util.download(this,url) {
            try {
                val list = arrayListOf<UserItem>()
                val jsonObject = JSONObject(it)
                val jsonArray = jsonObject.getJSONArray("items")
                for (i in 0 until jsonArray.length()) {
                    val _jsonObject = jsonArray.getJSONObject(i)
                    val username: String = _jsonObject.getString("login")
                    val avatar: String = _jsonObject.getString("avatar_url")
                    list.add(UserItem(username, avatar))
                }
                adapter.users = list
                pb.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        }
    }

    fun initUser() {
        val pb = findViewById<ProgressBar>(R.id.loading)
        pb.visibility = View.VISIBLE
        val url = "https://api.github.com/users"
        util.download(this,url) {
            try {
                val list = arrayListOf<UserItem>()
                val jsonArray = JSONArray(it)
                for (i in 0 until jsonArray.length()) {
                    val _jsonObject = jsonArray.getJSONObject(i)
                    val username: String = _jsonObject.getString("login")
                    val avatar: String = _jsonObject.getString("avatar_url")
                    list.add(UserItem(username, avatar))
                }
                adapter.users = list
                pb.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        }
    }

}

