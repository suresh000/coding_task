package com.techjet.codingtask

import android.app.Activity
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.navOptions

object AppNavigation {

    fun navigate(activity: Activity, navDirections: NavDirections) {
        val navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
        navController.navigate(navDirections, navOptions {
            anim {
                enter = R.anim.slide_in_from_right
                exit = R.anim.slide_out_to_left
            }
            launchSingleTop = true
        })
    }

    fun popBackStack(activity: Activity) {
        val navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
        navController.popBackStack()
    }

    fun popBackStack(view: View) {
        val navController = Navigation.findNavController(view)
        navController.popBackStack()
    }

    fun navigateUp(view: View) {
        Navigation.findNavController(view).navigateUp()
    }

    fun navigateUp(activity: Activity) {
        Navigation.findNavController(activity, R.id.nav_host_fragment).navigateUp()
    }

    fun navigateUp(navController: NavController) {
        navController.navigateUp()
    }

}