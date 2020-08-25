package com.zackyasgar.at_tauba.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_kegiatan.*
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import kotlinx.android.synthetic.main.fragment_data_pengajian.tv_pengajian_tgl
import java.util.*

class DataKegiatanFragment : Fragment(), View.OnClickListener {

    // untuk jam dan tanggal
    var timePickerDialog: TimePickerDialog? = null
    var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_kegiatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_kegiatan_tgl.setOnClickListener(this)
        btn_kegiatan_jam.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_kegiatan_tgl -> {
                val calendar = Calendar.getInstance()
                val c_year = calendar.get(Calendar.YEAR)
                val c_month = calendar.get(Calendar.MONTH)
                val c_day = calendar.get(Calendar.DAY_OF_MONTH)

                datePickerDialog = DatePickerDialog(
                    context as Context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        tv_kegiatan_tgl.text = ("$dayOfMonth-${month + 1}-$year")  //ditambah satu karena index MOUNT di mulai dari nol (0-11)
                    }, c_year, c_month, c_day
                )

                datePickerDialog?.show()
            }

            R.id.btn_kegiatan_jam -> {
                val calendar = Calendar.getInstance()

                timePickerDialog = TimePickerDialog(
                    context as Context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        tv_kegiatan_jam.text = ("$hourOfDay:$minute")
                    },
                    // calender pertamakali di buka
                    calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE],
                    // cek format 24 jam
                    DateFormat.is24HourFormat(context as Context)
                )

                timePickerDialog?.show()
            }
        }
    }

}