package br.com.rodorush.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rodorush.R
import br.com.rodorush.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.mainTb.apply { title =
            context.getString(R.string.app_name) })
    }
}