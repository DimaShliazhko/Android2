package com.dshliazhko.android.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private var count :Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.viewText)



    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
        myToast.show();
    }
    fun countMe (view: View){
        val countString  = textView.text.toString();
         count = Integer.parseInt(countString)
        count++
        textView.text = count.toString()
    }

    fun randomMe(view: View) {
      //  var random : Int = Random(0).nextInt(10)
      //  textView.text = random.toString();

        val intent = Intent(this,Activity_2::class.java)
        intent.putExtra("Sent",count)
        startActivity(intent)

    }
}