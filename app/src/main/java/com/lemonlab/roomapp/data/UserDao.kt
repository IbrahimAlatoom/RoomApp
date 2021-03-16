package com.lemonlab.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//contain all method for access database

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

}