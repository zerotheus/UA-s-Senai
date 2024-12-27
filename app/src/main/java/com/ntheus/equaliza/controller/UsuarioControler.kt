package com.ntheus.equaliza.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ntheus.equaliza.MainActivity
import com.ntheus.equaliza.model.Usuario
import com.ntheus.equaliza.repository.EqualizaDatabase
import com.ntheus.equaliza.repository.UsuarioRepository
import com.ntheus.equaliza.singleton.AppDatabase
import com.ntheus.equaliza.view.Equalizer
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UsuarioControler {

    private var context: Context
    private val database:EqualizaDatabase
    private var usuarioRepository:UsuarioRepository
    private var activity:AppCompatActivity;

    constructor(context: Context, activity:AppCompatActivity) {
        this.context = context
        this.activity = activity
        database= Room.databaseBuilder(context,EqualizaDatabase::class.java,"EqualizaDB").build()
        usuarioRepository = database.usuarioDao()
        AppDatabase.database = database
    }

    fun login(usuario: String,password: String) {
        runBlocking {
            launch {
                val usuarioModel:Usuario? = usuarioRepository.getByUsername(usuario)
                if (usuarioModel == null){
                    return@launch;
                }
                if (!usuarioModel.getPassword().equals(password)){
                    return@launch
                }
                val intent = Intent(context,Equalizer::class.java)
                intent.putExtra("usuario",usuarioModel)
                activity.startActivity(intent)
            }
        }
    }

    fun cadastrar(usuario: String, password: String) {
        val usuarioModel = Usuario(usuario, password)
        runBlocking {
            launch {
                if (verificarSehaUsuarioCadastrado(usuario)){
                    Toast.makeText(context,"Ja há usuario cadastrado",Toast.LENGTH_LONG).show()
                    return@launch
                }
                usuarioRepository.save(usuarioModel)
                Toast.makeText(context,"cadastro feito com sucesso",Toast.LENGTH_LONG).show()
            }
        }
    }

    suspend fun verificarSehaUsuarioCadastrado(usuario: String): Boolean {
        return usuarioRepository.getByUsername(usuario) != null;
    }
}