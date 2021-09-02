package com.example.naviinterview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.Naviinterview.MainActivity

abstract class BaseFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        if(shouldShowBackButton()) {
            // change from black back icon to white
            mainActivity.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        } else {
            mainActivity.toolbar.setNavigationIcon(null)
        }
    }
    abstract fun shouldShowBackButton(): Boolean
}