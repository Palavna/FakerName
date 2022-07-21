package com.example.yana.fakername.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.ActivityMainBinding
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.utils.NavigationBottomBarSectionsStateKeeperWorkaround
import com.example.yana.fakername.utils.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FragmentCallBack {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private val navSectionsStateKeeper by lazy {
        NavigationBottomBarSectionsStateKeeperWorkaround(
            activity = this,
            navHostContainerID = R.id.container,
            navGraphIds = listOf(
                R.navigation.nav_main,
                R.navigation.nav_about,
                R.navigation.nav_add,
                R.navigation.nav_about_work,
                R.navigation.nav_profile
            ),
            bottomNavigationViewID = R.id.bottomNav
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navSectionsStateKeeper.onCreate(savedInstanceState)
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }


//    private fun setupFrameLayout(savedInstanceState: Bundle?) {
//        if (SharedPreferenceFaker.token.isNotEmpty())
//            binding.bottomNav.inflateMenu(R.menu.menu_bottom_auth)
//        else binding.bottomNav.inflateMenu(R.menu.menu_bottom)
//
//        if (savedInstanceState == null) {
//            val navGraphIds = listOf(
//                R.navigation.nav_main,
//                R.navigation.nav_about,
//                R.navigation.nav_add,
//                R.navigation.nav_about_work,
//                R.navigation.nav_profile
//            )
//            binding.bottomNav.setupWithNavController(
//                navGraphIds = navGraphIds,
//                fragmentManager = supportFragmentManager,
//                containerId = R.id.container,
//                intent = intent
//            ).observe(this) {
//                viewModel.navController = it
//                (viewModel.navController as NavHostController).enableOnBackPressed(true)
//                viewModel.navController.addOnDestinationChangedListener { _, destination, _ ->
////                if (flag) {
////                    binding.navView.findViewById<View>(R.id.navigation_new_requests).performClick()
////                    flag = false
////                } else {
////                    if (destination.label == getString(R.string.main))
////                        finish()
////                }
//                }
////            savedInstanceState?.getInt("tab", R.id.nav_main)?.let {
////                binding.bottomNav.selectedItemId = it
////            }
//            }
//        }
//
//
////        binding.bottomNav.selectedItemId = R.id.nav_main
////        binding.bottomNav.setOnItemSelectedListener {
////            when (it.itemId) {
////                R.id.main -> changeFragment(MainFragment())
////                R.id.about -> changeFragment(AboutFragment())
////                R.id.works -> changeFragment(WorksFragment())
////                R.id.addText -> {
////                    if (SharedPreferenceFaker.token.isNotEmpty())
////                        changeFragment(DataAddFragment())
////                    else changeFragment(RegistrationFragment())
////                }
//////                R.id.registration -> changeFragment(RegistrationFragment())
////                R.id.profile -> changeFragment(PrivateCabinetFragment())
////            }
////            true
////        }
////        binding.bottomNav.selectedItemId = R.id.main
//    }

    fun changeFragment(fragment: Fragment, isNeedBackStack: Boolean = false) {
        val manager = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
        if (isNeedBackStack)
            manager.addToBackStack(fragment.javaClass.name)
        manager.commit()
    }

    override fun openProfile() {
        binding.bottomNav.selectedItemId = R.id.profile
        binding.bottomNav.selectedItemId = R.id.editCommentFragment
    }

}


