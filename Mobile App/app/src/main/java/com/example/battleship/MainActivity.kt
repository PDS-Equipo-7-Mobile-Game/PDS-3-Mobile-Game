package com.example.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.battleship.databinding.ActivityMainBinding
import com.example.battleship.ui.view.users.FriendListFragment
import com.example.battleship.ui.view.users.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bottomNavigationView.visibility = View.INVISIBLE

        val profileFragment = ProfileFragment()
        val friendListFragment = FriendListFragment()

        binding.bottomNavigationView.setOnNavigationItemReselectedListener {

            when(it.itemId){

                R.id.friendsBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, friendListFragment).commit()
                R.id.profileNavBar -> supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment ).commit()
                R.id.playBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment )
            }
        }

        setContentView(binding.root)
    }


}

