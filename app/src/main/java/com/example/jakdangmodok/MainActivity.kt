package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.jakdangmodok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val fragments: List<Fragment> by lazy {
        listOf(HomeFragment(), SubscribeFragment(), BookFragment(), GroupFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setBottomNavigationView()

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.menu_home
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.menu_notification -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragments[4]).commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragments[0]).commit()
                }

                R.id.menu_subscribe -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragments[1]).commit()
                }

                R.id.menu_add -> {
                    val intent = Intent(this, AddActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_book -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragments[2]).commit()
                }

                R.id.menu_group -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container,fragments[3]).commit()
                }
            }

            true
        }
    }
}