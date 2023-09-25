package com.example.dicegambler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/**
 * Rolls two dice, compares the two, and displays them with score
 * @version 09.24.23
 * @author Alex Ru
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finds UI elements
        val dice1button: Button = findViewById(R.id.left_button)
        val dice2button: Button = findViewById(R.id.right_button)
        val scoreText: TextView = findViewById(R.id.score)

        // Updates score and rolls dice based on user choice
        var score = 0
        dice1button.setOnClickListener {
            if (rollDice() == 0) {
                score += 1
            }
            scoreText.text = score.toString()
        }
        dice2button.setOnClickListener {
            if (rollDice() == 2) {
                score += 1
            }
            scoreText.text = score.toString()
        }
    }

    private fun rollDice(): Int {
        // Creates Dice objects
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        // Rolls dice
        val roll1 = dice1.roll()
        val roll2 = dice2.roll()

        // Updates dice display
        val dice1Text: TextView = findViewById(R.id.dice1_num)
        val dice2Text: TextView = findViewById(R.id.dice2_num)
        dice1Text.text = roll1.toString()
        dice2Text.text = roll2.toString()

        // Compares and returns value, 0 is 1, 1 is tie, 2 is 2
        return if (roll1 == roll2) 1
        else if (roll1 > roll2) 0
        else 2
    }
}

/**
 * Dice class that takes in sides and has roll method
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}