package com.calendar.demo.dao.repository

import com.calendar.demo.model.Calendar

object CalendarRepository {
    private val calendars = mutableMapOf<Long, Calendar>() // <userId, Calendar>

    fun fetchById(id: Long) : Calendar? {
        return calendars[id]
    }

    fun add(userId: Long, calendar: Calendar): Calendar {
        calendars[userId] = calendar
        return calendar
    }

    fun fetchAll(): Map<Long, Calendar> {
        return calendars
    }

    fun update(userId: Long, calendar: Calendar): Calendar {
        val oldCalendar = calendars[userId] ?: error("Calendar not found for userId: $userId")
        val newCalendar = oldCalendar.copy(
            date = calendar.date,
            events = calendar.events
        )

        calendars[userId] = newCalendar
        return newCalendar
    }

    fun delete(userId: Long): Calendar {
        val oldCalendar = calendars[userId] ?: error("Calendar not found for userId: $userId")
        calendars.remove(userId)

        return oldCalendar
    }
}