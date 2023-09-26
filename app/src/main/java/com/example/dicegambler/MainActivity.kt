package com.example.dicegambler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * Rolls two dice, compares the two, and displays them with score
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finds UI elements
        val dice1Button: Button = findViewById(R.id.left_button)
        val dice2Button: Button = findViewById(R.id.right_button)
        val scoreText: TextView = findViewById(R.id.score)

        // Updates score and rolls dice based on user choice
        var score = 0
        dice1Button.setOnClickListener {
            if (rollDice() == 0) {
                score += 1
            }
            scoreText.text = score.toString()
        }
        dice2Button.setOnClickListener {
            if (rollDice() == 2) {
                score += 1
            }
            scoreText.text = score.toString()
        }

        rollDice()
    }

    private fun rollDice(): Int {
        // Creates Dice objects
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        // Rolls dice
        val roll1 = dice1.roll()
        val roll2 = dice2.roll()

        // Updates dice display
        val dice1Img: ImageView = findViewById(R.id.dice1)
        val dice2Img: ImageView = findViewById(R.id.dice2)
        val drawableResource1 = when (roll1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else-> R.drawable.dice_6
        }
        val drawableResource2 = when (roll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else-> R.drawable.dice_6
        }
        dice1Img.setImageResource(drawableResource1)
        dice2Img.setImageResource(drawableResource2)

        dice1Img.contentDescription = "dice1img$roll1"
        dice2Img.contentDescription = "dice2img$roll2"

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