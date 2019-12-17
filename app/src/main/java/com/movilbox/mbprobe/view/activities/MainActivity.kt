package com.movilbox.mbprobe.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.movilbox.mbprobe.R
import com.movilbox.mbprobe.utils.Functions.closeSession
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.main_navHost_fragment)

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        //Toolbar configuration
        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.dashboardFragment -> {
                    supportActionBar?.title = "Dashboard"
                }
                R.id.prospectsListFragment -> {
                    supportActionBar?.title = "Lista de prospectos"
                }
                R.id.prospectEditionFragment -> {
                    supportActionBar?.title = "Edición de prospecto"
                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                main_drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.closeSession -> {
                closeSession(this, sharedPreferences)
            }
            R.id.dashboard -> {
                navController.navigate(R.id.dashboardFragment)
            }
            R.id.prospects -> {
                navController.navigate(R.id.prospectsListFragment)
            }
        }

        main_drawerLayout.closeDrawers()

        return true
    }

}
