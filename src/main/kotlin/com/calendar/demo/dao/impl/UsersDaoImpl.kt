package com.calendar.demo.dao.impl

import com.calendar.demo.dao.UserDao
import com.calendar.demo.dao.repository.UserRepository
import com.calendar.demo.model.User

class UsersDaoImpl : UserDao {

    override fun add(user: User): User {
        return try {
            val existingUser = UserRepository.fetchById(user.id)
            if (existingUser == null) {
                val updatedUser = UserRepository.add(user)
                updatedUser
            } else {
                val updatedUser = UserRepository.update(user)
                updatedUser
            }
        } catch (e: Exception) {
            error("Error while adding user")
        }
    }

    override fun delete(id: Long): User {
        try {
            return UserRepository.delete(id)
        } catch (e: Exception) {
            error("Error while deleting user")
        }
    }

    override fun getUsers(): List<User> {
        return UserRepository.fetchAll()
    }

    override fun getUserById(id: Long): User {
        return UserRepository.fetchById(id) ?: error("User not found")
    }
}
