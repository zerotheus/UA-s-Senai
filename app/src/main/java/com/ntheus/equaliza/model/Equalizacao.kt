package com.ntheus.equaliza.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Equalizacoes")
class Equalizacao : Serializable {


    @PrimaryKey(autoGenerate = true)
    private var id: Int? = null
    private var usuarioId: Int
    private var sessentaHz: Short
    private var centoECinquentaHz: Short
    private var quatroCentosHz: Short
    private var umKiloHz: Short
    private var doisPontoQuatroKiloHz: Short
    private var quinzeKiloHz: Short

    constructor(
        usuarioId: Int,
        sessentaHz: Short,
        centoECinquentaHz: Short,
        quatroCentosHz: Short,
        umKiloHz: Short,
        doisPontoQuatroKiloHz: Short,
        quinzeKiloHz: Short
    ) {
        this.usuarioId = usuarioId
        this.sessentaHz = sessentaHz
        this.centoECinquentaHz = centoECinquentaHz
        this.quatroCentosHz = quatroCentosHz
        this.umKiloHz = umKiloHz
        this.doisPontoQuatroKiloHz = doisPontoQuatroKiloHz
        this.quinzeKiloHz = quinzeKiloHz
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        if (this.id == null) {
            return 0;
        }
        return this.id!!
    }

    fun getUsuarioId(): Int {
        return this.usuarioId
    }

    fun getSessentaHz(): Short {
        return this.sessentaHz
    }

    fun getCentoECinquentaHz(): Short {
        return this.centoECinquentaHz
    }

    fun getQuatroCentosHz(): Short {
        return this.quatroCentosHz
    }

    fun getUmKiloHz(): Short {
        return this.umKiloHz
    }

    fun getDoisPontoQuatroKiloHz(): Short {
        return this.doisPontoQuatroKiloHz
    }

    fun getQuinzeKiloHz(): Short {
        return this.umKiloHz
    }


}