package com.calendar.demo.dao

import com.calendar.demo.model.User

interface UserDao {

    fun add(user: User): User

    fun delete(id: Long): User

    fun getUsers(): List<User>

    fun getUserById(id: Long): User
}