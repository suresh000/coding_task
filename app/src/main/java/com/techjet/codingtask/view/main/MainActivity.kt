package com.techjet.codingtask.view.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.techjet.codingtask.AppNavigation
import com.techjet.codingtask.R
import com.techjet.codingtask.base.BaseActivity
import com.techjet.codingtask.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mVm: MainViewModel
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mVm = ViewModelProvider(this)[MainViewModel::class.java]
        mBinding.vm = mVm

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(navDestinationListener)
    }

    private val navDestinationListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.listFragment -> {
                    updateUI(
                        resources.getString(R.string.app_name),
                        isBack = false
                    )
                }
                else -> {

                }
            }
        }

    private fun updateUI(
        title: String,
        isBack: Boolean = false
    ) {
        mVm.title.set(title)

        if (isBack) {
            mBinding.toolbar.navigationIcon =
                ContextCompat.getDrawable(this, R.drawable.ic_close)
            mBinding.toolbar.setNavigationOnClickListener { AppNavigation.navigateUp(navController) }
        } else {
            mBinding.toolbar.navigationIcon = null
        }
    }

}