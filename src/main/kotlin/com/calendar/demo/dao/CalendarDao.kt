package com.calendar.demo.dao

import com.calendar.demo.model.Calendar
import java.time.LocalDate

interface CalendarDao {

    fun addEvent(userId: Long, calendar: Calendar): Calendar

    fun deleteEvent(userId: Long, date: LocalDate, eventId: Long): Calendar

    fun fetchCalendar(userId: Long, date: LocalDate): Calendar

    fun fetchTotalEvents(): Long

    fun fetchAllUsersCalendars(): Long
}