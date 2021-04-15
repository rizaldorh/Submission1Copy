package com.dicoding.githubuserapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(a : FragmentActivity, val user : String) : FragmentStateAdapter(a) {

//    val isFollower = arrayOf(true, false)
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
//        val f = FragmentUser()
//        val arg = bundleOf("follow" to isFollower [position], "user" to user)
//        f.arguments = arg
//        return f
        var fragments: Fragment? = null
        when (position) {
            0 -> fragments = FragmentUser()
            1 -> fragments = FragmentUser2()
        }
        return fragments as Fragment
    }
}

//import android.content.Context
//import androidx.annotation.Nullable
//import androidx.annotation.StringRes
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import com.dicoding.githubuseruiux.R
//import com.dicoding.githubuseruiux.fragment.FollowersFragment
//import com.dicoding.githubuseruiux.fragment.FollowingFragment
//
//class SectionsPagerAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    companion object {
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//                R.string.followers,
//                R.string.following
//        )
//    }
////
//    override fun getItem(position: Int): Fragment {
//
//    }
//
//    @Nullable
//    override fun getPageTitle(position: Int): CharSequence {
//        return mContext.resources.getString(TAB_TITLES[position])
//    }
//
//    override fun getCount(): Int {
//        return 2
//    }
//}