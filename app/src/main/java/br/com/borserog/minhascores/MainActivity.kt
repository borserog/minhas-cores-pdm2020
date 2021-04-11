package br.com.borserog.minhascores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

var ADD_COLOR_CONFIG = 1

class MainActivity : AppCompatActivity() {
    private lateinit var addColorBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.addColorBtn = findViewById(R.id.fabBtn)

        this.addColorBtn.setOnClickListener { onAddButtonTapped(it) }
    }

    private fun onAddButtonTapped(it: View?) {
        val intent = Intent(this, ColorForm::class.java)

        startActivityForResult(intent, ADD_COLOR_CONFIG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            RESULT_CANCELED -> Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }



    }
}