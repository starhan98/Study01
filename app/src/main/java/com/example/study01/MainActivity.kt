package com.example.study01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sunnyButton.setOnClickListener {
            imageView.setImageResource(R.drawable.sunny01)
            textView.text = "맑은 날"
        }

        cloudyButton.setOnClickListener {
            imageView.setImageResource(R.drawable.cloudy01)
            textView.text = "흐린 날"
        }

        rainyButton.setOnClickListener {
            imageView.setImageResource(R.drawable.rainy01)
            textView.text = "비 오는 날"
        }
    }
}