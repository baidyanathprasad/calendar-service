package com.calendar.demo.service

import com.calendar.demo.model.User

interface UserService {

    fun add(name: String, email: String, phoneNo: String, employeeCode: String): User

    fun update(userId: Long, name: String, email: String, phoneNo: String, employeeCode: String): User

    fun delete(id: Long): User

    fun getUsers(): List<User>
}