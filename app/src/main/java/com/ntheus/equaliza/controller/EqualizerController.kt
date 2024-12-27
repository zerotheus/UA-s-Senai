package com.ntheus.equaliza.controller

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ntheus.equaliza.model.Equalizacao
import com.ntheus.equaliza.repository.EqualizaDatabase
import com.ntheus.equaliza.repository.EqualizacaoRepository
import com.ntheus.equaliza.singleton.AppDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EqualizerController {

    private var context: Context
    private val database: EqualizaDatabase
    private val equalizacaoRepository:EqualizacaoRepository
    private var activity: AppCompatActivity

    constructor(context: Context,activity: AppCompatActivity){
        this.context=context
        this.activity =activity
        this.database = AppDatabase.database!!
        this.equalizacaoRepository = database.equalizacaoDao()
    }

    fun save(model: Equalizacao) :Equalizacao{
        runBlocking {
            launch {
                //Isso de fato limita o app, mas é apenas uma maneira de testar as funcionalidades do room
                val equalizacao = load(model.getUsuarioId())
                if (equalizacao != null){
                    equalizacaoRepository.delete(equalizacao)
                    Toast.makeText(context,"Equalizada Salva, Anterior foi Sobrescrita", Toast.LENGTH_LONG).show()
                }
                equalizacaoRepository.save(model);
                Toast.makeText(context,"Equalizada Salva", Toast.LENGTH_LONG).show()
            }
        }
        return model;
    }

    suspend fun load(userId:Int):Equalizacao? {
        return equalizacaoRepository.getOneById(userId)
    }

}