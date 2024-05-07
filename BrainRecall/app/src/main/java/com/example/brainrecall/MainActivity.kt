package com.example.brainrecall
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answerInput: TextView
    private lateinit var submitButton: Button
    private lateinit var scoreText: TextView
    private lateinit var timerText: TextView
    private lateinit var nextButton: Button
    private lateinit var resultView: TextView

    private var score: Int = 0
    private var number: Int=2
    private var timeRemaining: Long = 60000 // 1 minute
    private lateinit var timer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.textViewQuestion)
        answerInput = findViewById(R.id.editTextAnswer)
        submitButton = findViewById(R.id.buttonSubmit)
        scoreText = findViewById(R.id.textViewScore)
        timerText = findViewById(R.id.textViewTimer)
        nextButton = findViewById(R.id.buttonNext)
        resultView = findViewById(R.id.textViewResult)

        submitButton.setOnClickListener {
            checkAnswer()
            generateQuestion()
        }
        nextButton.setOnClickListener {
            startGame()
        }

        startGame()
    }

    private fun startGame() {
        answerInput.isEnabled = true
        score = 0
        timeRemaining = 60000
        updateScoreText()
        updateTimerText()

        generateQuestion()

        timer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                endGame()
            }
        }
        timer.start()
    }

    private fun generateQuestion() {
        val randomNumber1 = Random.nextInt(1, 100)
        val randomNumber2 = Random.nextInt(1, 100)

        val operator = when (Random.nextInt(3)) {
            0 -> "+"
            1 -> "-"
            else -> "*"
        }

        val question = "$randomNumber1 $operator $randomNumber2"
        questionText.text = question
    }

    private fun checkAnswer() {
        val userAnswer = answerInput.text.toString().toIntOrNull()
        val questionParts = questionText.text.split(" ")

        if (userAnswer != null && questionParts.size == 3) {
            val operand1 = questionParts[0].toInt()
            val operator = questionParts[1]
            val operand2 = questionParts[2].toInt()
            var correctAnswer = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                else -> 0
            }
            if (correctAnswer < 0) {
                correctAnswer -= correctAnswer * 2
            }
            if (userAnswer == correctAnswer) {
                score++
                updateScoreText()
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }
            answerInput.text = null
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateScoreText() {
        scoreText.text = "Score: $score"
    }

    @SuppressLint("SetTextI18n")
    private fun updateTimerText() {
        timerText.text = "Time: ${timeRemaining / 1000} sec"
    }

    @SuppressLint("SetTextI18n")
    private fun endGame() {

        timer.cancel()
        answerInput.text = null
        answerInput.isEnabled = false
        if(score >= number){
            resultView.text = "Your score is $score You are Win"
            number++
        }else{
            resultView.text = "Game Over! Your score is $score You lose the game"
        }

        //startGame()
    }
}
