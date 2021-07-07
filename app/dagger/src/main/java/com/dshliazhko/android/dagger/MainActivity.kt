package com.dshliazhko.android.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var computer: Computer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       appComponetn.inject(this)
    }

}