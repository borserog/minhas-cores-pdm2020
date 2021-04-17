package br.com.borserog.minhascores

import java.io.Serializable

class ColorConfig : Serializable {

    var id: Int
    lateinit var nome: String
    var codigo: Int

    constructor(nome: String, codigo: Int) {
        this.id = -1
        this.nome = nome
        this.codigo = codigo
    }

    constructor(id: Int, nome: String, codigo: Int) {
        this.id = id
        this.nome = nome
        this.codigo = codigo
    }

    fun toHex(): String {
        return String.format("#%06X", (0xFFFFFF and codigo))
    }

    override fun toString(): String {
        return "ColorConfig(id=$id, nome='$nome', codigo=$codigo)"
    }


}