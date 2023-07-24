package com.orbitalsonic.chatgpt.ui.activities

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.orbitalsonic.chatgpt.R
import com.orbitalsonic.chatgpt.databinding.ActivityMainBinding
import com.orbitalsonic.chatgpt.helpers.extensions.Extensions.sonicBackPress
import com.orbitalsonic.chatgpt.ui.activities.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    /**
     *  No need to setContentView()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarMain)
        initNavController()
        registerBackPressDispatcher()
    }


    private fun initNavController() {
        navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment).navController
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.fragmentHome))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun homeBackPressed() {
        onBack()
    }

    private fun registerBackPressDispatcher() {
        sonicBackPress {
            onBack()
        }
    }

    private fun onBack() {
        finishAndRemoveTask()
    }
}