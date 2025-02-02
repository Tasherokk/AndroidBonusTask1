package com.example.ekg

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class EcgFragment : Fragment(R.layout.fragment_ecg) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStartEcg = view.findViewById<Button>(R.id.btn_start_ecg)
        btnStartEcg.setOnClickListener {
            // TODO: Реализация записи ЭКГ в будущем
        }
    }
}
