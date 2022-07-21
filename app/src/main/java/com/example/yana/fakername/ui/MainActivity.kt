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

class MainActivity : BaseMainActivity(), FragmentCallBack {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override val graphId: List<Int>
        get() = listOf(
            R.navigation.nav_main,
            R.navigation.nav_about,
            R.navigation.nav_add,
            R.navigation.nav_about_work,
            R.navigation.nav_profile,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navSectionsStateKeeper.onCreate(savedInstanceState)
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
//        binding.bottomNav.selectedItemId = R.id.profile
//        binding.bottomNav.selectedItemId = R.id.editCommentFragment
    }

}


