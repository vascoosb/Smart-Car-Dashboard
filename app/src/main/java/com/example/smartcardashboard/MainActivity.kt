package com.example.smartcardashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var fuelLevelTextView: TextView
    private lateinit var speedTextView: TextView
    private lateinit var engineStatusTextView: TextView
    private lateinit var navigationTextView: TextView
    private lateinit var weatherTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        fuelLevelTextView = findViewById(R.id.fuelLevelTextView)
        speedTextView = findViewById(R.id.speedTextView)
        engineStatusTextView = findViewById(R.id.engineStatusTextView)
        navigationTextView = findViewById(R.id.navigationTextView)
        weatherTextView = findViewById(R.id.weatherTextView)

        // Example data update - Replace these with real-time data
        updateUI("75%", "120", "On", "North", "Sunny")
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