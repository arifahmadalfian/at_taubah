package com.zackyasgar.at_tauba.ui.masjid

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_masjid.*
import android.content.Intent.getIntent as intentGetIntent


class MasjidFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_masjid, container, false)


    }

}