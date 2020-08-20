package com.zackyasgar.at_tauba.ui.masjid

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.admin.AdminActivity
import kotlinx.android.synthetic.main.fragment_masjid.*
import android.content.Intent.getIntent as intentGetIntent


class MasjidFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_masjid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_fab_maps.setOnClickListener(this)
        btn_admin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_fab_maps -> getGoogleMaps()
            R.id.btn_admin -> getHalamanAdmin()
        }
    }

    // berpindah ke google maps
    private fun getGoogleMaps() {
        val gDestination = "-6.937810,107.655653"
        try {
            val uri = Uri.parse("https://www.google.co.in/maps/dir/?api=1&destination=$gDestination")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: ActivityNotFoundException){
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            val intent = Intent(ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    // pindah ke activity baru
    private fun getHalamanAdmin() {
        val intent = Intent(activity, AdminActivity::class.java)
        startActivity(intent)
    }

}