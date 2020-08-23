package com.zackyasgar.at_tauba.admin

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.util.*


class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var mListener: IDialogTimeListener? = null

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        mListener = childFragment as IDialogTimeListener
    }

    override fun onDetach() {
        super.onDetach()
        if (mListener != null) {
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val formatHour24 = true
        return TimePickerDialog(activity as Context, this, hour, minute, formatHour24)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mListener?.onDialogTimeSet(tag,hourOfDay,minute)
    }

    interface IDialogTimeListener {
        fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int)
    }
}