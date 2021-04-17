package br.com.borserog.minhascores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

var ADD_COLOR_CONFIG = 1

class MainActivity : AppCompatActivity() {
    private lateinit var addColorBtn: FloatingActionButton
    private lateinit var colorListRef: ListView
    private lateinit var colorsList: ColorsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.colorsList = ColorsList()

        this.addColorBtn = findViewById(R.id.fabBtn)
        this.colorListRef = findViewById(R.id.colorList)

        this.colorListRef.adapter = ColorConfigAdapter(this, colorsList);
        this.colorListRef.onItemLongClickListener = LongItemClick()

        this.addColorBtn.setOnClickListener { onAddButtonTapped(it) }
    }

    private fun onAddButtonTapped(it: View?) {
        val intent = Intent(this, ColorForm::class.java)

        startActivityForResult(intent, ADD_COLOR_CONFIG)
    }

    private fun addNewColor(colorConfig: ColorConfig) {
        colorsList.add(colorConfig)
        (colorListRef.adapter as ColorConfigAdapter).notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            RESULT_OK -> {
                val newColor = data?.getSerializableExtra("new_color") as ColorConfig?

                Log.i("DEV", newColor.toString())

                if (newColor != null) {
                    addNewColor(newColor)
                    Toast.makeText(this, "Cor cadastrada", Toast.LENGTH_SHORT).show()
                }
            }
            RESULT_CANCELED -> Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }

    }

    inner class LongItemClick: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
            val color = this@MainActivity.colorsList[position]
            Toast.makeText(this@MainActivity, "Cor ${color.nome} removida", Toast.LENGTH_SHORT).show()
            colorsList.remove(color);
            (colorListRef.adapter as ColorConfigAdapter).notifyDataSetChanged()
            return true
        }
    }
}