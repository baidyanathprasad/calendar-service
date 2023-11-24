package com.calendar.demo.service.impl

import com.calendar.demo.dao.impl.UsersDaoImpl
import com.calendar.demo.dao.repository.UserRepository
import com.calendar.demo.model.User
import com.calendar.demo.service.UserService

class UserServiceImpl: UserService {
    private val userDao = UsersDaoImpl()

    override fun add(
        name: String,
        email: String,
        phoneNo: String,
        employeeCode: String
    ): User {
        val user = User(generateId(), name, email, phoneNo, employeeCode)

        try {
            return userDao.add(user)
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    override fun update(
        userId: Long,
        name: String,
        email: String,
        phoneNo: String,
        employeeCode: String
    ): User {
        val oldUser = UserRepository.fetchById(userId) ?: error("User not found")
        val updatedUser = oldUser.copy(
            name = name,
            email = email,
            phoneNo = phoneNo,
            employeeCode = employeeCode
        )

        return try {
            UserRepository.update(updatedUser)
        } catch (e: Exception) {
            error("Issue occurred while updating the user: ${updatedUser.id}")
        }
    }

    override fun delete(id: Long): User {
        try {
            return userDao.delete(id)
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    override fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    private fun generateId(): Long {
        return getUsers().size.toLong() + 1
    }
}