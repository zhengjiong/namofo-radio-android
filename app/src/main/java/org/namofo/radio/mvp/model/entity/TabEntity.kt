package com.meimeiyoupin.mvp.model.entity

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by zhengjiong
 * date: 2018/4/15 11:12
 */

class TabEntity(val title: String, val selectedIcon: Int = 0, val unSelectedIcon: Int = 0) : CustomTabEntity {


    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon;
    }

    override fun getTabTitle(): String {
        return title
    }

}