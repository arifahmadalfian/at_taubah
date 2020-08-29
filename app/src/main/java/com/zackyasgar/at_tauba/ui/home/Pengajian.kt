package com.zackyasgar.at_tauba.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pengajian(
    var temaPengajian: String = "",
    var judulPengajian: String = "",
    var tanggalPengajian: String ="",
    var jamPengajian: String = "",
    var isiPengajian: String = ""
): Parcelable {

}