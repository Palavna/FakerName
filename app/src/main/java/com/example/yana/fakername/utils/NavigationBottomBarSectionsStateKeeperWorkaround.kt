package com.example.yana.fakername.utils

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.yana.fakername.R
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference


class NavigationBottomBarSectionsStateKeeperWorkaround(
    activity: AppCompatActivity,
    private val navHostContainerID: Int,
    private val navGraphIds: List<Int>,
    private val bottomNavigationViewID: Int
) {

    private var currentNavController: LiveData<NavController>? = null
    private val activityRef = WeakReference(activity)

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val activity = activityRef.get() ?: return

        val bottomNavigationView =
            activity.findViewById<BottomNavigationView>(bottomNavigationViewID)

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = activity.supportFragmentManager,
            containerId = navHostContainerID,
            intent = activity.intent
        )

        currentNavController = controller
    }

    fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false
}
