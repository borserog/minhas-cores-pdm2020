package br.com.borserog.minhascores

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class ColorForm : AppCompatActivity(), OnSeekBarChangeListener {
    private lateinit var redSeek: SeekBar
    private lateinit var greenSeek: SeekBar
    private lateinit var blueSeek: SeekBar
    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button
    private lateinit var colorLabel: TextView
    private lateinit var colorDisplayButton: Button
    private lateinit var colorConfig: ColorConfig
    private var redAmount = 0;
    private var greenAmount = 0;
    private var blueAmount = 0;
    private var colorHexRepresentation = "#000000";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_form)

        this.colorConfig = ColorConfig("", 0);

        init()
    }

    private fun onCancelTapped(it: View?) {
        setResult(RESULT_CANCELED)
        finish()
    }

    private fun init() {
        redSeek = findViewById(R.id.redAmount)
        greenSeek = findViewById(R.id.greenAmount)
        blueSeek = findViewById(R.id.blueAmount)
        cancelButton = findViewById(R.id.btnCancelar)
        saveButton = findViewById(R.id.btnSalvar)
        colorLabel = findViewById(R.id.colorLabel)
        colorDisplayButton = findViewById(R.id.colorDisplay)

        cancelButton.setOnClickListener { onCancelTapped(it) }

        redSeek.setOnSeekBarChangeListener(this)
        greenSeek.setOnSeekBarChangeListener(this)
        blueSeek.setOnSeekBarChangeListener(this)

        colorDisplayButton.setOnClickListener { onColorDisplayClicked(it) }
    }

    private fun onColorDisplayClicked(it: View?) {
        val clipBoardService = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Hex Color", colorHexRepresentation)
        clipBoardService.setPrimaryClip(clip)
    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        Log.i("DEV", "RED: ${redAmount shl 16}\nGREEN: ${greenAmount shl 8}\nBLUE: $blueAmount\nSUM: ${String.format("#%06X", (0xFFFFFF and (redAmount shl 16) + (greenAmount shl 8) + (blueAmount)))}")
        when (seekBar?.tag) {
            "RED" -> {
                redAmount = progress
            }
            "GREEN" -> {
                greenAmount = progress
            }
            "BLUE" -> {
                blueAmount = progress
            }
        }

        val colorValue = ((redAmount and 0xFF) shl 16) + ((greenAmount and 0xFF) shl 8) + (blueAmount)
        colorHexRepresentation = String.format("#%06X", (0xFFFFFF and colorValue))

        colorDisplayButton.setBackgroundColor(Color.rgb(redAmount, greenAmount, blueAmount))
        colorDisplayButton.text = colorHexRepresentation

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}