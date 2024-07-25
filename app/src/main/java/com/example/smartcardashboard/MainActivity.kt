package com.example.smartcardashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var fuelLevelTextView: TextView
    private lateinit var speedTextView: TextView
    private lateinit var engineStatusTextView: TextView
    private lateinit var navigationTextView: TextView
    private lateinit var weatherTextView: TextView

    private lateinit var mediaStatusTextView: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        fuelLevelTextView = findViewById(R.id.fuelLevelTextView)
        speedTextView = findViewById(R.id.speedTextView)
        engineStatusTextView = findViewById(R.id.engineStatusTextView)
        navigationTextView = findViewById(R.id.navigationTextView)
        weatherTextView = findViewById(R.id.weatherTextView)

        // Initialize Media Player UI
        mediaStatusTextView = findViewById(R.id.mediaStatusTextView)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        stopButton = findViewById(R.id.stopButton)

        // Set up Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio) // Play sample_audio.mp3 in res/raw

        playButton.setOnClickListener {
            mediaPlayer.start()
            mediaStatusTextView.text = "Playing"
        }

        pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mediaStatusTextView.text = "Paused"
            }
        }

        stopButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare() // Prepare media player for next play
                mediaStatusTextView.text = "Stopped"
            }
        }

        // Example data update - Replace these with real-time data
        updateUI("75%", "120", "On", "North", "Sunny")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }

    // Method to update UI with real-time data
    private fun updateUI(fuelLevel: String, speed: String, engineStatus: String, navigation: String, weather: String) {
        fuelLevelTextView.text = getString(R.string.fuel_level_label, fuelLevel)
        speedTextView.text = getString(R.string.speed_label, speed)
        engineStatusTextView.text = getString(R.string.engine_status_label, engineStatus)
        navigationTextView.text = getString(R.string.navigation_label, navigation)
        weatherTextView.text = getString(R.string.weather_label, weather)
    }
}