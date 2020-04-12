package com.pairdev.github

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pairdev.github.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }

    private fun setupView() {
        binding.resultTextView.text = "loading..."

        DataRepository().getLatestTrendingRepositoriesInLastWeek {

            val handler = Handler(Looper.getMainLooper())
            handler.post {
                val sb = StringBuilder()

                for (edge in it.first ?: listOf()) {
                    sb.append(edge?.node?.asRepository?.name)
                    sb.appendln()
                    sb.append(edge?.node?.asRepository?.description)

                    sb.appendln()
                    sb.appendln()
                }
                binding.resultTextView.text = sb.toString()
            }
        }
    }
}
