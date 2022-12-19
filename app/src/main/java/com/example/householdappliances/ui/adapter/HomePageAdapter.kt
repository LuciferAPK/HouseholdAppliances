package com.example.householdappliances.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePageAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    private val fragmentList: MutableList<Fragment> = ArrayList()


    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

//    fun getFragmentTitleList(): Array<String> {
//        val titles : ArrayList<String> = arrayListOf()
//        for(fragment in fragmentList){
//            when(fragment){
//                is WallpaperForYouFragment-> titles.add(fragment.title)
//                is LiveFragment -> titles.add(fragment.title)
//                is DownloadedFragment -> titles.add(fragment.title)
//                is FavouriteFragment -> titles.add(fragment.title)
//                is HashtagHistoryFragment -> {
//                    val  myFragment = fragment as HashtagHistoryFragment
//                    fragment.getDataFromBundle()
//                    titles.add(fragment.title)
//                }
//                is HashtagDynamicFragment -> {
//                    val  myFragment = fragment as HashtagDynamicFragment
//                    fragment.getDataFromBundle()
//                    titles.add(fragment.title)
//                }
//            }
//        }
//        return titles.toTypedArray()
//    }

    fun addFragments(
        fragments: List<Fragment>
    ) {
        fragmentList.clear()
        fragmentList.addAll(fragments)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}


