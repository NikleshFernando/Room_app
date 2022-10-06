package com.example.room_app.Repository

import androidx.lifecycle.LiveData
import com.example.room_app.Model.User
import com.example.room_app.Model.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User)=userDao.insertUser(user)

    val readusers:LiveData<List<User>> = userDao.readusers()

    suspend fun updateUser(user: User)=userDao.updateUser(user)

    suspend fun deleteUser(user: User)=userDao.deleteUser(user)

    suspend fun deleteAllUsers()=userDao.deleteAllUsers()
}