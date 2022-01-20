package com.bignerdranch.android.geoqiuznormalversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var prevButton: ImageButton


    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?)called")
        setContentView(R.layout.activity_main)

//        val provider: ViewModelProvider = ViewModelProviders.of(this)// здесь должно быть с на конце s.of(this)
//        val quizViewModel = provider.get(QuizViewModel::class.java)
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex



        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        prevButton = findViewById(R.id.prev_button)

        trueButton.setOnClickListener { view: View ->

            checkAnswer(true)

        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
                quizViewModel.moveToBack()
                updateQuestion()
            }
        updateQuestion()
}



    override fun onStart() {// функция обратного вызова
        super.onStart()//обязательная строчка, которая должна идти второй по счету
        Log.d(TAG, "OnStart called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause called")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop called")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy called")
    }





    private fun updateQuestion() {
        val questionTextRezId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextRezId)
    }


    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast}
        else{
            R.string.incorrect_toast
        }
            Toast.makeText(
                this,
                messageResId,
                Toast.LENGTH_SHORT
            ).show()

    }





    }
