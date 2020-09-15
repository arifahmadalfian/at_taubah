package com.zackyasgar.at_tauba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_jumat_detail.*

class JumatDetailActivity : AppCompatActivity() {

    companion object {
        const val JUMATAN_KHOTIB = "jumatan_khotib"
        const val JUMATAN_MUADZIN = "jumatan_muadzin"
        const val JUMATAN_TANGGAL = "jumatan_tanggal"
        const val JUMATAN_JAM = "jumatan_jam"
        const val JUMATAN_ISI = "jumatan_isi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jumat_detail)

        val khotib = intent.getStringExtra(JUMATAN_KHOTIB)
        val muadzin = intent.getStringExtra(JUMATAN_MUADZIN)
        val tanggal = intent.getStringExtra(JUMATAN_TANGGAL)
        val jam = intent.getStringExtra(JUMATAN_JAM)
        val isi = intent.getStringExtra(JUMATAN_ISI)

        tv_detail_jumatan_khotib.text = khotib
        tv_detail_jumatan_muadzin.text = muadzin
        tv_detail_jumatan_tanggal.text = tanggal
        tv_detail_jumatan_jam.text = jam
        tv_detail_jumatan_isi.text = isi
    }
}