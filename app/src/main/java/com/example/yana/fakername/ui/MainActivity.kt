package com.example.yana.fakername.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.ActivityMainBinding
import com.example.yana.fakername.fragments.*
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.utils.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FragmentCallBack {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFrameLayout()
    }


    private fun setupFrameLayout() {

        if (SharedPreferenceFaker.token.isNotEmpty())
            binding.bottomNav.inflateMenu(R.menu.menu_bottom_auth)
        else binding.bottomNav.inflateMenu(R.menu.menu_bottom)


        val navGraphIds = listOf(
            R.navigation.nav_main,
            R.navigation.nav_about,
            R.navigation.nav_add,
            R.navigation.nav_about_work,
            R.navigation.nav_profile
        )

        binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.container,
            intent = intent
        ).observe(this) {
            navController = it
            (navController as NavHostController).enableOnBackPressed(true)
            navController.addOnDestinationChangedListener { _, destination, _ ->
//                if (flag) {
//                    binding.navView.findViewById<View>(R.id.navigation_new_requests).performClick()
//                    flag = false
//                } else {
//                    if (destination.label == getString(R.string.main))
//                        finish()
//                }
            }
        }
//        binding.bottomNav.selectedItemId = R.id.nav_main
//        binding.bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.main -> changeFragment(MainFragment())
//                R.id.about -> changeFragment(AboutFragment())
//                R.id.works -> changeFragment(WorksFragment())
//                R.id.addText -> {
//                    if (SharedPreferenceFaker.token.isNotEmpty())
//                        changeFragment(DataAddFragment())
//                    else changeFragment(RegistrationFragment())
//                }
////                R.id.registration -> changeFragment(RegistrationFragment())
//                R.id.profile -> changeFragment(PrivateCabinetFragment())
//            }
//            true
//        }
        binding.bottomNav.selectedItemId = R.id.main
    }

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


