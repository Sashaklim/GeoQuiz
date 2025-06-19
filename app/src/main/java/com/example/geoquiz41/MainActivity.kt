package com.example.geoquiz41

import com.example.a41.Question



import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private var currentIndex = 0
    private var score = 0
    private lateinit var answeredQuestions: BooleanArray

    private val questionBank = listOf(
        Question(R.string.question_san, true),
        Question(R.string.question_russia, true),
        Question(R.string.question_ping, false),
        Question(R.string.question_atom, false),
        Question(R.string.question_germany, true)
    )

    companion object {
        private const val KEY_INDEX = "index"
        private const val KEY_SCORE = "score"
        private const val KEY_ANSWERED = "answered"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        score = savedInstanceState?.getInt(KEY_SCORE) ?: 0
        answeredQuestions = savedInstanceState?.getBooleanArray(KEY_ANSWERED) ?: BooleanArray(questionBank.size)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            if (currentIndex < questionBank.size - 1) {
                currentIndex++
                updateQuestion()
            }
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionResId)

        val alreadyAnswered = answeredQuestions[currentIndex]
        trueButton.isEnabled = !alreadyAnswered
        falseButton.isEnabled = !alreadyAnswered
        trueButton.visibility = if (alreadyAnswered) Button.INVISIBLE else Button.VISIBLE
        falseButton.visibility = if (alreadyAnswered) Button.INVISIBLE else Button.VISIBLE

        if (currentIndex == questionBank.lastIndex) {
            nextButton.isEnabled = false
            nextButton.visibility = Button.INVISIBLE
        } else {
            nextButton.isEnabled = true
            nextButton.visibility = Button.VISIBLE
        }
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userPressedTrue == correctAnswer) {
            score++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        trueButton.isEnabled = false
        falseButton.isEnabled = false
        trueButton.visibility = Button.INVISIBLE
        falseButton.visibility = Button.INVISIBLE

        answeredQuestions[currentIndex] = true

        if (currentIndex == questionBank.lastIndex) {
            Toast.makeText(this, "Результат: $score из ${questionBank.size}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, currentIndex)
        outState.putInt(KEY_SCORE, score)
        outState.putBooleanArray(KEY_ANSWERED, answeredQuestions)
    }
}