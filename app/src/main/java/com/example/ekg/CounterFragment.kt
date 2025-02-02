package com.example.ekg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var counter: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCounter = view.findViewById<TextView>(R.id.tv_counter)
        val btnPlus = view.findViewById<Button>(R.id.btn_plus)
        val btnMinus = view.findViewById<Button>(R.id.btn_minus)

        tvCounter.text = counter.toString()

        btnPlus.setOnClickListener {
            counter++
            tvCounter.text = counter.toString()
        }
        btnMinus.setOnClickListener {
            counter--
            tvCounter.text = counter.toString()
        }
    }

}
