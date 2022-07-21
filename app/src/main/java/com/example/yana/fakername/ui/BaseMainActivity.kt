package com.example.yana.fakername.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yana.fakername.R
import com.example.yana.fakername.utils.NavigationBottomBarSectionsStateKeeperWorkaround

abstract class BaseMainActivity : AppCompatActivity() {

    abstract val graphId: List<Int>

    protected val navSectionsStateKeeper by lazy {
        NavigationBottomBarSectionsStateKeeperWorkaround(
            activity = this,
            navHostContainerID = R.id.container,
            navGraphIds = graphId,
            bottomNavigationViewID = R.id.bottomNav
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navSectionsStateKeeper.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp() =
        navSectionsStateKeeper.onSupportNavigateUp()

    override fun onBackPressed() {
        if (!navSectionsStateKeeper.onSupportNavigateUp())
            super.onBackPressed()
    }
}