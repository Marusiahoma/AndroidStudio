package com.example.rumireapinashina

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Flowers
import com.example.rumireapinashina.databinding.ActivityFlowersBinding
import com.example.rumireapinashina.presentation.FlowerViewModel
import java.text.SimpleDateFormat
import java.util.*

class ImpressionActivity : BaseActivity() {

    private lateinit var binding: ActivityFlowersBinding
    private val flowerViewModel: FlowerViewModel by viewModels()
    private lateinit var flowerAdapter: FlowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        setupNavigationDrawer()

        flowerAdapter = FlowerAdapter(mutableListOf())
        binding.rvFlowers.layoutManager = LinearLayoutManager(this)
        binding.rvFlowers.adapter = flowerAdapter

        flowerViewModel.flowers.observe(this, Observer { flowers ->
            flowerAdapter.updateFlowers(flowers)
        })

        flowerViewModel.loadFlower()

        binding.btnAddFlower.setOnClickListener {
            val title = binding.etFlowerTitle.text.toString()
            val description = binding.etFlowerDescription.text.toString()
            val ratingText = binding.etFlowerAll.text.toString()
            val rating = ratingText.toFloatOrNull() ?: 0f

            if (title.isNotBlank()) {
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val flowers = Flowers(title = title, description = description, date = currentDate, rating = rating)
                flowerViewModel.addFlower(flowers)

                binding.etFlowerTitle.text.clear()
                binding.etFlowerDescription.text.clear()
                binding.etFlowerAll.text.clear()
            }
        }
    }
}
