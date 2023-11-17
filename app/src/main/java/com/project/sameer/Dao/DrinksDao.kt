package com.project.sameer.Dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.sameer.DataClass.RoomData

@Dao
interface DrinksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertDrink(drink: RoomData)

    @Query("SELECT * FROM drinks")
     fun getAllDrinks(): List<RoomData>

    @Query("SELECT * FROM drinks WHERE itemid = :drinkid")
     fun getDrinkById(drinkid: String): RoomData?

}