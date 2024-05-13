package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("2+2=?", "3+3=?", "4+4=?")

    private val option = arrayOf(
        arrayOf("4", "6", "8"),
        arrayOf("6", "8", "10"),
        arrayOf("8", "10", "12")
    )

    private val correctAnswer = arrayOf(0, 0, 0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }

    private fun resetButtonColor() {
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showCorrectButtonColor(correctAnswerIndex: Int) {
        when (correctAnswerIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun showIncorrectButtonColor(selectedAnswerIndex: Int) {
        when (selectedAnswerIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun displayQuestion() {
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = option[currentQuestionIndex][0]
        binding.option2Button.text = option[currentQuestionIndex][1]
        binding.option3Button.text = option[currentQuestionIndex][2]
        resetButtonColor()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            showCorrectButtonColor(correctAnswerIndex)
        } else {
            showIncorrectButtonColor(selectedAnswerIndex)
            showCorrectButtonColor(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 1000)
        } else {
            showResults()
        }
    }

    private fun showResults() {
        Toast.makeText(
            this,
            "Your score: $score out of ${questions.size}",
            Toast.LENGTH_LONG
        ).show()
        binding.restartButton.isEnabled = true
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
        resetButtonColor()
    }
}
