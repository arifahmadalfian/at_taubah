package com.zackyasgar.at_tauba

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zackyasgar.at_tauba.ui.masjid.MasjidFragment
import kotlinx.android.synthetic.main.fragment_masjid.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val FCM_SERVICE: String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        //komentar default dari aplikasi android studio saat membuat projek
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       // val appBarConfiguration = AppBarConfiguration(setOf(
          //      R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_masjid))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //untuk messeg popUp pada aplikasi ketika aplikasi sedang di buka
        //getMessageAlert()

    }
/*
    private fun getMessageAlert() {
        val message = intent.getStringExtra(FCM_SERVICE)

        if (!message.isNullOrEmpty()){
            AlertDialog.Builder(this)
                .setTitle("Notifikasi baru")
                .setMessage(message)
                .setPositiveButton("Ok", DialogInterface.OnClickListener{ _, _ ->  }).show()
        }
    }

 */
}