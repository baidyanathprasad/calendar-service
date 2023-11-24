package com.calendar.demo.dao.impl

import com.calendar.demo.dao.CalendarDao
import com.calendar.demo.dao.repository.CalendarRepository
import com.calendar.demo.model.Calendar
import java.time.LocalDate

class CalendarDaoImpl: CalendarDao {

    override fun addEvent(userId: Long, calendar: Calendar): Calendar {
        try {
            return CalendarRepository.add(userId, calendar)
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    override fun deleteEvent(userId: Long, date: LocalDate, eventId: Long): Calendar {
        try {
            val calendar = CalendarRepository.fetchById(userId) ?: throw Exception("Calendar not found")
            val events = calendar.events.filter { it.id != eventId }
            val updatedCalendar = calendar.copy(date = date, events = events).apply {
                CalendarRepository.update(userId, calendar)
            }

            return updatedCalendar
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    override fun fetchCalendar(userId: Long, date: LocalDate): Calendar {
        try {
            return CalendarRepository.fetchById(userId) ?: error("No calendar is associated with this user: $userId")
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    override fun fetchTotalEvents(): Long {
        return CalendarRepository.fetchAll().map { it.value }.flatMap { it.events }.size.toLong()
    }

    override fun fetchAllUsersCalendars(): Long {
        val usersCalendar = CalendarRepository.fetchAll()
        return usersCalendar.size.toLong()
    }
}
