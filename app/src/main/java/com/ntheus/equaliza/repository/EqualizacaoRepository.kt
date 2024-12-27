package com.ntheus.equaliza.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ntheus.equaliza.model.Equalizacao
import kotlinx.coroutines.flow.Flow

@Dao
interface EqualizacaoRepository :Persistencia<Equalizacao>{

    @Insert
    override suspend fun save(model: Equalizacao)

    @Delete
    override suspend fun delete(id: Equalizacao)

    @Update
    override fun edit(model: Equalizacao)

    @Query(value = "Select * from Equalizacoes where id = :id")
    override fun getById(id: Int): Flow<Equalizacao>

    @Query(value = "Select * from Equalizacoes where usuarioId = :id")
    suspend fun getOneById(id: Int): Equalizacao?

    @Query(value = "Select * from Equalizacoes where usuarioId = :userId")
    fun getAllByUserId(userId:Int):List<Equalizacao>
}