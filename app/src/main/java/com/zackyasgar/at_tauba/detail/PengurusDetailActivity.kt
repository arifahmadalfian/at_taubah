package com.zackyasgar.at_tauba.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.activity_pengurus_detail.*

class PengurusDetailActivity : AppCompatActivity() {

    companion object {
        const val PENGURUS_NAMA = "pengurus_nama"
        const val PENGURUS_JABATAN = "pengurus_jabatan"
        const val PENGURUS_UMUR = "pengurus_umur"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengurus_detail)

        val nama = intent.getStringExtra(PENGURUS_NAMA)
        val jabatan = intent.getStringExtra(PENGURUS_JABATAN)
        val umur = intent.getStringExtra(PENGURUS_UMUR)

        tv_detail_pengurus_nama.text = nama
        tv_detail_pengurus_jabatan.text = jabatan
        tv_detail_pengurus_umur.text = umur
    }
}