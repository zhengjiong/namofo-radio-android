package org.namofo.radio.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.namofo.radio.mvp.ui.fragment.RadioFragment
import org.namofo.radio.mvp.ui.fragment.RecommendFragment
import java.lang.IllegalArgumentException

/**
 *
 * Copyright:Copyright(c) 2018
 * CreateTime:18/5/2$  14:21$
 * @author 郑炯
 * @version 1.0
 */

class MainFragmentPagerAdapter(private val titles: Array<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RecommendFragment.newInstance()
            }
            1 -> {
                RadioFragment.newInstance()
            }
            2 -> {
                RecommendFragment.newInstance()
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}