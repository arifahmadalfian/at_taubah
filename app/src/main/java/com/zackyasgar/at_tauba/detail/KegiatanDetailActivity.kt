package com.zackyasgar.at_tauba.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.activity_kegiatan_detail.*

class KegiatanDetailActivity : AppCompatActivity() {

    companion object {
        const val KEGIATAN_JUDUL = "kegiatan_judul"
        const val KEGIATAN_TANGGAL = "kegiatan_tanggal"
        const val KEGIATAN_JAM = "kegiatan_jam"
        const val KEGIATAN_ISI = "kegiatan_isi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan_detail)

        val judul = intent.getStringExtra(KEGIATAN_JUDUL)
        val tanggal = intent.getStringExtra(KEGIATAN_TANGGAL)
        val jam = intent.getStringExtra(KEGIATAN_JAM)
        val isi = intent.getStringExtra(KEGIATAN_ISI)

        tv_detail_kegiatan_judul.text = judul
        tv_detail_kegiatan_tanggal.text = tanggal
        tv_detail_kegiatan_jam.text = jam
        tv_detail_kegiatan_isi.text = isi
    }
}