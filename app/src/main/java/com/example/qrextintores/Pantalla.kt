package com.example.qrextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qrextintores.databinding.ActivityPantallaBinding
import com.google.zxing.integration.android.IntentIntegrator

class Pantalla : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener {
            iniciarScanner()
        }
    }

    private fun iniciarScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setBeepEnabled(true)
        integrator.setPrompt("Escanee codigo QR")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Valores: ${result.contents}", Toast.LENGTH_LONG).show()
                binding.tvescaneado.text = result.contents//pasamos lo escaneado al TextView
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}