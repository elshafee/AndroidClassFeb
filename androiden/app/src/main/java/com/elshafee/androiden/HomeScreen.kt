package com.elshafee.androiden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.elshafee.androiden.auth.todlistapp.TodoListActivity
import com.elshafee.androiden.courotine.KotlinCourotineExample
import com.elshafee.androiden.notifications.NotificationExample
import com.elshafee.androiden.shoppingitemlist.ui.ShoppingItemListActivity
import com.elshafee.androiden.todolistapi.ui.TodoListApiActivity
import com.elshafee.androiden.ui.MyEmail
import com.elshafee.androiden.ui.OurEvents
import com.elshafee.androiden.ui.SplashScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val emailFragmet = MyEmail()
        val todofragment = TodoListActivity()
        val ourEvent = OurEvents()
        val sharedPrefrence = getSharedPreferences("authData", 0)
        val editor = sharedPrefrence.edit()

        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        setCurrentFragment(todofragment)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.TodoListApp -> {
                    setCurrentFragment(todofragment)
                }
                R.id.myEmail -> {
                    val intent = Intent(this, NotificationExample::class.java)
                    startActivity(intent)
                }
                R.id.ourEvent -> {
                    setCurrentFragment(ourEvent)
                }
                R.id.wodgets ->{
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.shoppingItemsApp ->{
                    val intent = Intent(this,ShoppingItemListActivity::class.java)
                    startActivity(intent)
                }
                R.id.todoListApi ->{
                    val intent = Intent(this,TodoListApiActivity::class.java)
                    startActivity(intent)
                }
                R.id.courotineExaple -> {
                    val intent = Intent(this, KotlinCourotineExample::class.java)
                    startActivity(intent)
                }
                R.id.logout -> {
                    editor.putBoolean("issigned", false)
                    editor.apply()
                    val intent = Intent(this, SplashScreen::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        bottomnav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.TodoListApp -> setCurrentFragment(todofragment)
                R.id.myEmail -> setCurrentFragment(emailFragmet)
                R.id.ourEvent -> setCurrentFragment(ourEvent)

                R.id.logout -> {
                    editor.putBoolean("issigned", false)
                    editor.apply()
                    val intent = Intent(this, SplashScreen::class.java)
                    startActivity(intent)
                }
            }

            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}