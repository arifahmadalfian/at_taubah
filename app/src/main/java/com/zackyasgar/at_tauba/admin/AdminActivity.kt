package com.zackyasgar.at_tauba.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zackyasgar.at_tauba.R

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // id dari acitvity_admin.xml pada bottom navigation view
        val navViewAdmin: BottomNavigationView = findViewById(R.id.nav_view_admin)

        // id dari fragment yang menjadi Navigasi Host atau fragment kontainer di activity admin
        val navControlerAdmin = findNavController(R.id.nav_host_admin_fragment)

        //mengubah title saat fragment berpindah
        val appBarConfigurationTitle = AppBarConfiguration(setOf(
            R.id.navigation_admin_pengajian, R.id.navigation_admin_jumat, R.id.navigation_admin_kegiatan, R.id.navigation_admin_notifikasi))
        setupActionBarWithNavController(navControlerAdmin,appBarConfigurationTitle)

        //ini berfungsi ketika icon dari BottomNavigationView di klik maka fragment secara otomatis berpindah
        navViewAdmin.setupWithNavController(navControlerAdmin)

    }
}