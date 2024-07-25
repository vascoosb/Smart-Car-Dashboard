package com.example.smartcardashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer
import android.widget.Button
import kotlin.random.Random

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
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 2000L

    private var fuelLevel = 10
    private var speed = 0

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

        //Start data update
        startSimulatedDataUpdate()
    }

    private fun startSimulatedDataUpdate() {
        handler.post(object : Runnable {
            override fun run() {
                updateSimulatedData()
                handler.postDelayed(this, updateInterval)
            }
        })
    }

    private fun updateSimulatedData() {
        fuelLevel = if (fuelLevel > 0) fuelLevel - 1 else 100// fuel level decreases by 1
        speed = Random.nextInt(0, 201) // Random speed between 0 and 200 km/h
        val engineStatus = if (speed > 0) "On" else "Off" // Random engine status
        val navigation = listOf("North", "East", "South", "West").random() // Random navigation direction
        val weather = listOf("Sunny", "Cloudy", "Rainy", "Snowy").random() // Random weather condition

        updateUI("$fuelLevel%", "$speed", engineStatus, navigation, weather)
    }

    private fun updateUI(fuelLevel: String, speed: String, engineStatus: String, navigation: String, weather: String) {
        fuelLevelTextView.text = getString(R.string.fuel_level_label, fuelLevel)
        speedTextView.text = getString(R.string.speed_label, speed)
        engineStatusTextView.text = getString(R.string.engine_status_label, engineStatus)
        navigationTextView.text = getString(R.string.navigation_label, navigation)
        weatherTextView.text = getString(R.string.weather_label, weather)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release() // Release MediaPlayer resources
        }
        handler.removeCallbacksAndMessages(null) // Stop simulated data updates
    }
}