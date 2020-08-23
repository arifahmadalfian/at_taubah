package com.zackyasgar.at_tauba.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import java.text.SimpleDateFormat
import java.util.*

class DataPengajianFragment : Fragment(), View.OnClickListener, DatePickerFragment.IDialogDateListener, TimePickerFragment.IDialogTimeListener {

    companion object {
        private const val TANGGAL_TAG = "Tanggal"
        private const val JAM_TAG = "Jam"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_pengajian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_tgl.setOnClickListener(this)
        btn_jam.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_tgl -> {
                val datePickerFragment = DatePickerFragment()
                val mFragmentManager = childFragmentManager
                datePickerFragment.show(mFragmentManager, TANGGAL_TAG)
            }
            R.id.btn_jam -> {
                val timePickerFragment = TimePickerFragment()
                val mFragmentManager = childFragmentManager
                timePickerFragment.show(mFragmentManager, JAM_TAG)
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        tv_tgl.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        tv_jam.text = dateFormat.format(calendar.time)
    }

}