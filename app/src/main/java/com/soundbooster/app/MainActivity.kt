package com.soundbooster.app

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var boostText: TextView
    private lateinit var boostSeekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        boostText = findViewById(R.id.boostText)
        boostSeekBar = findViewById(R.id.boostSeekBar)

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        boostSeekBar.max = 100
        boostSeekBar.progress = 0

        boostSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                boostText.text = "Boost: $progress%"
                val newVolume = (progress / 100.0 * maxVolume).toInt()
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
