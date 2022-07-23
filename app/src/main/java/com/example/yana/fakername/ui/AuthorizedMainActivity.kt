package com.example.yana.fakername.ui

import android.os.Bundle
import com.example.yana.fakername.R
import com.example.yana.fakername.databinding.ActivityAuthorizedMainBinding
import com.example.yana.fakername.fragmentsViewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizedMainActivity : BaseMainActivity(), FragmentCallBack {
    private lateinit var binding: ActivityAuthorizedMainBinding
    private val viewModel: MainViewModel by viewModel()

    override val graphId: List<Int>
        get() = listOf(
            R.navigation.nav_main,
            R.navigation.nav_about,
            R.navigation.nav_add_auth,
            R.navigation.nav_about_work,
            R.navigation.nav_profile_auth,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navSectionsStateKeeper.onCreate(savedInstanceState)
    }

    override fun openProfile() {
        binding.bottomNav.selectedItemId = R.id.nav_profile_auth
    }
}