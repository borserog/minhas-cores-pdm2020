package br.com.borserog.minhascores

import android.app.Instrumentation
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.ColorRes

class ColorForm : AppCompatActivity(), OnSeekBarChangeListener {
    private lateinit var redSeek: SeekBar
    private lateinit var greenSeek: SeekBar
    private lateinit var blueSeek: SeekBar
    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button
    private lateinit var colorLabel: TextView
    private lateinit var colorDisplayButton: Button
    private lateinit var colorConfig: ColorConfig
    private lateinit var mode: String
    private var colorHexRepresentation = "#000000";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_form)

        if (intent.hasExtra("edit_color")) {
            this.mode = "EDIT"
            this.colorConfig = intent.getSerializableExtra("edit_color") as ColorConfig
        } else {
            this.mode = "NEW"
            this.colorConfig = ColorConfig("", 0);
        }

        init(this.colorConfig)
    }

    private fun onCancelClicked(it: View?) {
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun init(colorConfig: ColorConfig) {
        redSeek = findViewById(R.id.redAmount)
        greenSeek = findViewById(R.id.greenAmount)
        blueSeek = findViewById(R.id.blueAmount)
        cancelButton = findViewById(R.id.btnCancelar)
        saveButton = findViewById(R.id.btnSalvar)
        colorLabel = findViewById(R.id.colorLabel)
        colorDisplayButton = findViewById(R.id.colorDisplay)

        saveButton.setOnClickListener { onSaveClicked(it) }
        cancelButton.setOnClickListener { onCancelClicked(it) }

        redSeek.setOnSeekBarChangeListener(this)
        greenSeek.setOnSeekBarChangeListener(this)
        blueSeek.setOnSeekBarChangeListener(this)

        colorDisplayButton.text = colorHexRepresentation
        colorDisplayButton.setOnClickListener { onColorDisplayClicked(it) }

        populateForm(colorConfig)
    }

    private fun populateForm(colorConfig: ColorConfig) {
        colorLabel.text = colorConfig.nome
        redSeek.progress = Color.red(colorConfig.codigo)
        greenSeek.progress = Color.green(colorConfig.codigo)
        blueSeek.progress = Color.blue(colorConfig.codigo)
    }

    private fun onSaveClicked(it: View?) {
        this.colorConfig.nome = colorLabel.text.toString().trim()
        this.colorConfig.codigo = Color.rgb(redSeek.progress, greenSeek.progress, blueSeek.progress)
        val returnIntent = Intent()

        if (mode == "NEW") {
            returnIntent.putExtra("new_color", this.colorConfig)
            setResult(10, returnIntent)
            finish()
        }

        if (mode == "EDIT") {
            returnIntent.putExtra("edit_color", this.colorConfig)
            setResult(20, returnIntent)
            finish()
        }

    }

    private fun onColorDisplayClicked(it: View?) {
        val clipBoardService = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Hex Color", colorHexRepresentation)
        clipBoardService.setPrimaryClip(clip)

        Toast.makeText(this, "Cor copiada", Toast.LENGTH_SHORT).show()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        colorDisplayButton.setBackgroundColor(
            Color.rgb(
                redSeek.progress,
                greenSeek.progress,
                blueSeek.progress
            )
        )
        colorDisplayButton.text = String.format(
            "#%06X",
            (0xFFFFFF and Color.rgb(redSeek.progress, greenSeek.progress, blueSeek.progress))
        )
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

}