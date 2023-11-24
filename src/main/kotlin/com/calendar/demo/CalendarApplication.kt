package com.calendar.demo

import com.calendar.demo.domain.EventType
import com.calendar.demo.service.impl.EventServiceImpl
import com.calendar.demo.service.impl.UserServiceImpl
import java.time.LocalDateTime

fun main(args: Array<String>) {

    // Demonstrate user flow
    val userService = UserServiceImpl()

    // Add new user "Rama Shankar"
    userService.add(
        name = "Rama Shankar",
        email = "xyz@gmail.com",
        phoneNo = "0123456789",
        employeeCode = "RAM123",
    )
    userService.add(
        name = "Hari Mohan",
        email = "xyz@gmail.com",
        phoneNo = "0123456789",
        employeeCode = "HAR123",
    )

    // Get all users
    val users = userService.getUsers()
    println("All Users: \n$users")

    // Delete users
    val firstUser = users.first()
    val deletedUser = userService.delete(firstUser.id)
    println("Deleted User: \n$deletedUser")

    // Remaining Users
    val remainingUsers = userService.getUsers()
    println("Remaining Users: \n$remainingUsers")

    // update remaining user
    val updatedUser = remainingUsers.first()
        .let {
            userService.update(
                userId = it.id,
                name = "Ravi Mohan",
                email = it.email,
                phoneNo = it.phoneNo,
                employeeCode = it.employeeCode
            )
        }

    println("updated user: $updatedUser")

    val allUpdatedUsers = userService.getUsers()
    println("Final User: $allUpdatedUsers")

//    // Demonstrate Calendar flow
//    val eventService = EventServiceImpl()
//
//    val event = eventService.create(
//        startTime = LocalDateTime.now(),
//        endTime = LocalDateTime.now().plusHours(1),
//        eventType = EventType.BUSY,
//        location = "Thor room",
//        attendees = listOf(remainingUsers.first().id),
//        createdBy = remainingUsers.first().id
//    )
}