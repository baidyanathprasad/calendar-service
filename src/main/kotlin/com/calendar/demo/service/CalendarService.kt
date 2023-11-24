package com.calendar.demo.service

import com.calendar.demo.model.Calendar
import java.time.LocalDate
import java.time.LocalDateTime

interface CalendarService {

    fun createEvent(userId: Long): Calendar

    fun fetchEvents(self: Long, users: List<Long>): Calendar

    fun fetchSlots(users: List<Long>, startDateTime: LocalDateTime, endDateTime: LocalDateTime): Calendar

    fun fetchConflictsSlots(users: List<Long>, date: LocalDate): Calendar
}