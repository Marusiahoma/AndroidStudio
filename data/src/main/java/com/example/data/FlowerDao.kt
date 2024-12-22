package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.FlowerEntity
import com.example.domain.model.Flowers

@Dao
interface FlowerDao {
    @Insert
    suspend fun insertFlower(flowerEntity: FlowerEntity)

    @Query("SELECT * FROM flowers")
    suspend fun getAllFlowers(): List<FlowerEntity>
}
