package com.thies.geoquiz

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.thies.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
  //  private lateinit var trueButton: Button
  //  private lateinit var falseButton: Button

    // Adding list
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // Variable to keep track of index as working through strings
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // trueButton = findViewById(R.id.true_button)
       // falseButton = findViewById(R.id.false_button)


        binding.trueButton.setOnClickListener{
            var snackBar = Snackbar.make(
                it,
                "Correct",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
        binding.falseButton.setOnClickListener{
            var snackBar = Snackbar.make(
                it,
                "Incorrect",
                Snackbar.LENGTH_LONG
            )
            snackBar.setTextColor(Color.BLACK)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.show()
        }
        binding.questionTextview.setOnClickListener{
            currentIndex = (currentIndex +1) % questionBank.size
            updateQuestion()
        }
        binding.previousButton.setOnClickListener{
            if(currentIndex > 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
            else {
                currentIndex = 5 % questionBank.size
                updateQuestion()
            }
        }
        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextview.setText(questionTextResId)
            updateQuestion()
        }

        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextview.setText(questionTextResId)
    }
}