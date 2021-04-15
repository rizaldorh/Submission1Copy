package com.dicoding.githubuserapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import org.json.JSONArray

class FragmentUser2 : Fragment() {

    private lateinit var adapter: UserAdapter2
    private lateinit var username: String
//    var isFollower = false
    var job : Job?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user2, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        isFollower = arguments?.getBoolean("follow", false) ?: false

        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view2)

        adapter = UserAdapter2(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        username = arguments?.getString("user") ?: "default"
        getFollowing(username)
    }

    fun getFollowing(username : String) {
        val pb = view!!.findViewById<ProgressBar>(R.id.loading2)
        pb.visibility = View.VISIBLE
//        val end = if(isFollower) "followers" else "following"
        val url = "https://api.github.com/users/$username/following"
        job?.cancel()
        job = util.download(requireContext(),url) {
            try {
                val list = arrayListOf<UserItem>()
                val jsonArray = JSONArray(it)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val username: String = jsonObject.getString("login")
                    val avatar: String = jsonObject.getString("avatar_url")
                    list.add(UserItem(username, avatar))
                }
                adapter.users = list
                pb.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        }
    }
}