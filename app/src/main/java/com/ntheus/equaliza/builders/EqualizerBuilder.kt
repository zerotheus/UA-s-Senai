package com.ntheus.equaliza.builders

import com.ntheus.equaliza.model.Equalizacao
import com.ntheus.equaliza.model.Usuario

class EqualizerBuilder {

    private var sessentaHz:Short=0
    private var centoECinquentaHz:Short=0
    private var quatroCentosHz:Short=0
    private var umKiloHz:Short=0
    private var doisPontoQuatroKiloHz:Short=0
    private var quinzeKiloHz:Short=0

    fun sessentaHz(decibeis:Short): EqualizerBuilder {
        this.sessentaHz = decibeis;
        return this;
    }

    fun centoECinquentaHz(decibeis:Short): EqualizerBuilder {
        this.centoECinquentaHz = decibeis;
        return this;
    }

    fun qutroCentosHz(decibeis:Short): EqualizerBuilder {
        this.quatroCentosHz = decibeis;
        return this;
    }

    fun umKiloHz(decibeis:Short): EqualizerBuilder {
        this.umKiloHz = decibeis;
        return this;
    }

    fun doisPontoQuatroHz(decibeis:Short): EqualizerBuilder {
        this.doisPontoQuatroKiloHz = decibeis;
        return this;
    }

    fun quinzeKiloHz(decibeis:Short): EqualizerBuilder {
        this.quinzeKiloHz = decibeis;
        return this;
    }

    fun build(usuario: Usuario): Equalizacao {
        var equalizacao = Equalizacao(usuario.getUid(),sessentaHz,centoECinquentaHz,quatroCentosHz,umKiloHz,doisPontoQuatroKiloHz,quinzeKiloHz)
        setToDefault()
        return equalizacao
    }

    private fun setToDefault(){
        this.sessentaHz=0
        this.centoECinquentaHz=0
        this.quatroCentosHz=0
        this.umKiloHz=0
        this.doisPontoQuatroKiloHz=0
        this.quinzeKiloHz=0
    }





}