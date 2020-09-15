package com.zackyasgar.at_tauba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pengajian_detail.*

class PengajianDetailActivity : AppCompatActivity() {

    companion object {
        const val PENGAJIAN_TEMA = "pengajian_tema"
        const val PENGAJIAN_JUDUL = "pengajian_judul"
        const val PENGAJIAN_TANGGAL = "pengajian_tanggal"
        const val PENGAJIAN_JAM = "pengajian_jam"
        const val PENGAJIAN_ISI = "pengajian_isi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajian_detail)

        val tema = intent.getStringExtra(PENGAJIAN_TEMA)
        val judul = intent.getStringExtra(PENGAJIAN_JUDUL)
        val tanggal = intent.getStringExtra(PENGAJIAN_TANGGAL)
        val jam = intent.getStringExtra(PENGAJIAN_JAM)
        val isi = intent.getStringExtra(PENGAJIAN_ISI)

        tv_detail_pengajian_tema.text = tema
        tv_detail_pengajian_judul.text = judul
        tv_detail_pengajian_tanggal.text = tanggal
        tv_detail_pengajian_jam.text = jam
        tv_detail_pengajian_isi.text = isi
    }
}