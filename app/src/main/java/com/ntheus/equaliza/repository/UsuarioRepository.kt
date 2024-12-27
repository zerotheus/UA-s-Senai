package com.ntheus.equaliza.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ntheus.equaliza.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioRepository :Persistencia<Usuario>{

    @Insert
    override suspend fun save(model: Usuario)

    @Query(value = "Select * from usuarios where uid = :id")
    override fun getById(id: Int):Flow<Usuario>

    @Query(value = "Select * from usuarios where username = :username")
    suspend fun getByUsername(username: String):Usuario?

    @Query(value = "Select * from usuarios")
    fun getAll():List<Usuario>;

    @Update
    override fun edit(model: Usuario)

    @Delete
    override suspend fun delete(id: Usuario)

}