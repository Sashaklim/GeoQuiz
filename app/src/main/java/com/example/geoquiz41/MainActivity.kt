package com.example.geoquiz41

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
        }

private val questionBank = listOf(
    Question(R.string.question_san, true),
    Question(R.string.question_russia, true),
    Question(R.string.question_ping, false),
    Question(R.string.question_atom, false),
    Question(R.string.question_germany, true)
)