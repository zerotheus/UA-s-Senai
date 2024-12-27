package com.ntheus.equaliza.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ntheus.equaliza.model.Equalizacao
import com.ntheus.equaliza.model.Usuario

@Database(entities = [Usuario::class,Equalizacao::class], version = 1)
abstract class EqualizaDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioRepository

    abstract fun equalizacaoDao():EqualizacaoRepository

}