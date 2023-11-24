package com.calendar.demo.dao.repository

import com.calendar.demo.model.User

/**
 * A repository class that stores and exposes methods to access user data.
 */
object UserRepository {
    private val users = mutableMapOf<Long, User>()

    fun fetchById(id: Long): User? {
        return users[id]
    }

    fun add(user: User): User {
        users[user.id] = user
        return user
    }

    fun fetchAll(): List<User> {
        return users.values.toList()
    }

    fun update(user: User): User {
        val oldUser = users[user.id] ?: error("User not found with the id: ${user.id}")
        val newUser = oldUser.copy(
            name = user.name,
            email = user.email,
            phoneNo = user.phoneNo,
            employeeCode = user.employeeCode
        )

        users[user.id] = newUser
        return newUser
    }

    fun delete(id: Long): User {
        val oldUser = users[id] ?: error("User not found with id: $id")
        users.remove(id)

        return oldUser
    }
}