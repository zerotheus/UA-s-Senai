package com.ntheus.equaliza.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("Usuarios")
class Usuario:Serializable {

    @PrimaryKey(true)
    private var uid: Int?;
    private var username: String
    private var password: String

    constructor(uid: Int, username: String, password: String) {
        this.uid = uid
        this.username = username;
        this.password = password;
    }

    @Ignore
    constructor(username: String, password: String) {
        this.username = username;
        this.password = password;
        this.uid = null
    }

    fun setUid(uid: Int) {
        this.uid = uid;
    }

    fun getUid(): Int {
        if (uid == null) {
            return 0;
        }
        return this.uid!!;
    }

    fun getUsername(): String {
        return this.username
    }

    fun getPassword(): String {
        return this.password
    }


}