package com.orteney.playground.ui.chips

import android.os.Bundle
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import android.view.View
import android.widget.ArrayAdapter
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_chips.*

class ChipsFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_chips

    private val PEOPLE = arrayOf("John Smith", "Kate Eckhart", "Emily Sun", "Frodo Baggins")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line, PEOPLE
        )

        autoCompleteTextView.setAdapter<ArrayAdapter<String>>(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, arg1, position, arg3 ->
            autoCompleteTextView.text = null
            val selected = parent.getItemAtPosition(position) as String
            addChipToGroup(selected, chipGroup2)
        }
    }

    private fun addChipToGroup(person: String, chipGroup: ChipGroup) {
        val chip = Chip(context)
        chip.text = person
        chip.isCloseIconEnabled = true

        // necessary to get single selection working
        chip.isClickable = true
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
    }
}