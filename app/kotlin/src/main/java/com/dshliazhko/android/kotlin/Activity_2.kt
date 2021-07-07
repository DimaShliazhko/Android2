package com.dshliazhko.android.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Activity_2 : AppCompatActivity() {
    private lateinit var textActivity_2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        textActivity_2 = findViewById(R.id.viewText2Random)
        var count: Int = intent.getIntExtra("Save", 0)
        var random: Int = Random(0).nextInt(count)
        textActivity_2.text = random.toString();
    }
}