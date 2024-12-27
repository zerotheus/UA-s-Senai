package com.ntheus.equaliza.repository

import kotlinx.coroutines.flow.Flow

interface Persistencia<T> {

    suspend fun save(model:T)

    fun edit(model:T)

    fun getById(id:Int):Flow<T>

    fun delete(id: T)

}