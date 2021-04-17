package br.com.borserog.minhascores

import android.content.ContentValues
import android.content.Context

class ColorConfigDAO {
    private lateinit var db: DBHelper

    constructor(context: Context){
        this.db = DBHelper(context)
    }

    fun insert(colorConfig: ColorConfig){
        val cv = ContentValues()
        cv.put("nome", colorConfig.nome)
        cv.put("codigo", colorConfig.codigo)
        this.db.writableDatabase.insert("colors", null, cv)
    }

    fun select(): ArrayList<ColorConfig>{
        val lista = ArrayList<ColorConfig>()
        val colunas = arrayOf("id", "nome", "codigo")

        val cursor = this.db.readableDatabase.query("colors", colunas, null, null, null, null, "nome")
        cursor.moveToFirst()

        for (i in 1..cursor.count){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val codigo = cursor.getInt(cursor.getColumnIndex("codigo"))
            lista.add(ColorConfig(id, nome, codigo))
            cursor.moveToNext()
        }

        return lista
    }

    fun count(): Int{
        val sql = "select count(id) from colors"
        val cursor = this.db.readableDatabase.rawQuery(sql, null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    fun find(id: Int): ColorConfig?{
        val columns = arrayOf("id", "nome", "codigo")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())

        val cursor = this.db.readableDatabase.query("colors", columns, where, pWhere, null, null, null)
        cursor.moveToFirst()

        if (cursor.count == 1){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val codigo = cursor.getInt(cursor.getColumnIndex("codigo"))
            return ColorConfig(id, nome, codigo)
        }

        return null
    }

    fun update(colorConfig: ColorConfig){
        val where = "id = ?"
        val pWhere = arrayOf(colorConfig.id.toString())
        val cv = ContentValues()
        cv.put("nome", colorConfig.nome)
        cv.put("codigo", colorConfig.codigo)
        this.db.writableDatabase.update("colors", cv, where, pWhere)
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.db.writableDatabase.delete("colors", where, pWhere)
    }
}