package com.ntheus.equaliza.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.ntheus.equaliza.R
import com.ntheus.equaliza.builders.EqualizerBuilder
import com.ntheus.equaliza.controller.EqualizerController
import com.ntheus.equaliza.model.Equalizacao
import com.ntheus.equaliza.model.Usuario
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Equalizer : AppCompatActivity() {

    private lateinit var botaoVoltar: ImageButton
    private lateinit var botaoSalvar: Button
    private lateinit var botaoCarregar: Button
    private val equalizerBuilder = EqualizerBuilder()
    private var sliders: MutableList<SeekBar> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        val equalizerController = EqualizerController(this.applicationContext, this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_equalizer)
        try {
            supportActionBar!!.show()

        } catch (e: Exception) {
            Log.i("Error", e.toString())
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sliders.add(findViewById(R.id.seekBar6hz))
        sliders.add(findViewById(R.id.seekBar150hz))
        sliders.add(findViewById(R.id.seekBar400hz))
        sliders.add(findViewById(R.id.seekBar1Khz))
        sliders.add(findViewById(R.id.seekBar24Khz))
        sliders.add(findViewById(R.id.seekBar15KHZ))
        botaoVoltar = findViewById(R.id.goBackButton)
        botaoVoltar.setOnClickListener({
            finish()
        })
        botaoSalvar = findViewById(R.id.Salvar)
        botaoSalvar.setOnClickListener({
            equalizerBuilder.sessentaHz(sliders.get(0).progress.toShort())
            equalizerBuilder.centoECinquentaHz(sliders.get(1).progress.toShort())
            equalizerBuilder.qutroCentosHz(sliders.get(2).progress.toShort())
            equalizerBuilder.umKiloHz(sliders.get(3).progress.toShort())
            equalizerBuilder.doisPontoQuatroHz(sliders.get(4).progress.toShort())
            equalizerBuilder.quinzeKiloHz(sliders.get(5).progress.toShort())
            var usuario: Usuario = intent.getSerializableExtra("usuario") as Usuario
            equalizerController.save(equalizerBuilder.build(usuario))
        })
        val handler = Handler(Looper.getMainLooper())
        botaoCarregar = findViewById(R.id.buttonCarregar)
        botaoCarregar.setOnClickListener({
            var usuario: Usuario = intent.getSerializableExtra("usuario") as Usuario
            var equalizada: Equalizacao?
            runBlocking {
                launch {
                    equalizada = equalizerController.load(usuario.getUid())
                    if (equalizada == null) {
                        return@launch
                    }
                    handler.post {
                        sliders.get(0).progress = equalizada!!.getSessentaHz().toInt()
                        sliders.get(1).progress = equalizada!!.getCentoECinquentaHz().toInt()
                        sliders.get(2).progress = equalizada!!.getQuatroCentosHz().toInt()
                        sliders.get(3).progress = equalizada!!.getUmKiloHz().toInt()
                        sliders.get(4).progress = equalizada!!.getDoisPontoQuatroKiloHz().toInt()
                        sliders.get(5).progress = equalizada!!.getQuinzeKiloHz().toInt()
                    }
                }
            }
        })
    }
}