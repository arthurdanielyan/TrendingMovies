package com.example.core.presentation.extensions

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment

fun Fragment.setSupportActionBarTitle(title: String) {
    (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.showSupportActionBarBackButton(onBackClick: () -> Unit) {
    val activity = (requireActivity() as? AppCompatActivity) ?: return
    val supportActionBar = activity.supportActionBar

    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowHomeEnabled(true)
    }

    activity.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}
        override fun onMenuItemSelected(item: MenuItem): Boolean {
            return if (item.itemId == android.R.id.home) {
                onBackClick()
                true
            } else false
        }
    }, viewLifecycleOwner)
}

fun Fragment.hideSupportActionBarBackButton() {
    (requireActivity() as? AppCompatActivity)?.supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(false)
        setDisplayShowHomeEnabled(false)
    }
}