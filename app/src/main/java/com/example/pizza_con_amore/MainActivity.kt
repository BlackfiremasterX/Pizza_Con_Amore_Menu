package com.example.pizza_con_amore

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pizza_con_amore.databinding.ActivityMainBinding
import com.example.pizza_con_amore.firebase.FirebaseDataStructure.*
import com.example.pizza_con_amore.firebase.adapter.CategoryAdapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,CategoryAdapter.c_Listener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var pca_base: DatabaseReference
    private fun setTextViewDrawableColor(textView: TextView, color: Int) {
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter =
                    PorterDuffColorFilter(
                        ContextCompat.getColor(textView.context, color),
                        PorterDuff.Mode.SRC_IN
                    )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.mainDrawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)


        navView.itemIconTintList = null

        ///*Открыть Drawer на старте*/ drawerLayout.openDrawer(navView)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_restraunt_menu, R.id.nav_active_category
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(category: CategoryData) {


      }
    }