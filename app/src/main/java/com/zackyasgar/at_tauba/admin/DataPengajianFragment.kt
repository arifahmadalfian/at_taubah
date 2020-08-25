package com.zackyasgar.at_tauba.admin

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import java.text.SimpleDateFormat
import java.util.*

class DataPengajianFragment : Fragment(), View.OnClickListener, TimePickerFragment.IDialogTimeListener{

    companion object {
        private const val TANGGAL_TAG = "Tanggal"
        private const val JAM_TAG = "Jam"
    }

    // untuk tanggal
    var builder = MaterialDatePicker.Builder.datePicker().setTitleText("Pilih Tanggal Pengajian")
    var materialDatePicker = builder.build()

    // untuk jam
    private var hour= 0
    private val minute = 0

    private val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private var clockFormat = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_pengajian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tanggal
        builder = MaterialDatePicker.Builder.datePicker()

        //jam


        btn_tgl.setOnClickListener(this)
        btn_jam.setOnClickListener(this)


        //object: MaterialPickerOnPositiveButtonClickListener<Long>
        materialDatePicker.addOnPositiveButtonClickListener {
            tv_tgl.text = materialDatePicker.headerText
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_tgl -> {
                materialDatePicker.show(childFragmentManager, TANGGAL_TAG)
            }
            R.id.btn_jam -> {
                val timePickerFragment = TimePickerFragment()
                val mFragmentManager = childFragmentManager
                timePickerFragment.show(mFragmentManager, JAM_TAG)
            }
        }

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        TODO("Not yet implemented")
    }

}


