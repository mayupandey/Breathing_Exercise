package com.gamingfella.breathingexcerciese

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Hiding the Status bar
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}
