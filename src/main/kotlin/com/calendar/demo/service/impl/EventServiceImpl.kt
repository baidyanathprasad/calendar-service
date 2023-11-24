package com.calendar.demo.service.impl

import com.calendar.demo.dao.impl.CalendarDaoImpl
import com.calendar.demo.dao.impl.UsersDaoImpl
import com.calendar.demo.dao.repository.CalendarRepository
import com.calendar.demo.domain.EventType
import com.calendar.demo.model.Calendar
import com.calendar.demo.model.Event
import com.calendar.demo.service.EventsService
import java.time.LocalDate
import java.time.LocalDateTime

class EventServiceImpl: EventsService {

    private val usersDao = UsersDaoImpl()
    private val calendarDao = CalendarDaoImpl()

    override fun create(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        eventType: EventType,
        location: String,
        attendees: List<Long>,
        createdBy: Long
    ): Event {
        val user = usersDao.getUserById(createdBy)
        val attendeesList = attendees.map { usersDao.getUserById(it) }
        val id = generateId(calendarDao.fetchCalendar(user.id, LocalDate.now()).events)

        val event = Event(
            id = id,
            eventTye = eventType,
            location = location,
            startTime = startTime,
            endTime = endTime,
            attendees = attendeesList,
            createdBy = user
        )

        try {
            val allUsersCalendar = attendeesList.plus(user)
                .map {
                    try {
                        val oldCalendar = CalendarRepository.fetchById(it.id) ?: error("Calendar not found for userId: ${user.id}")
                        val updatedCalendar = oldCalendar.copy(date = LocalDate.now(), events = oldCalendar.events + event)
                        it to updatedCalendar
                    } catch (e: Exception) {
                        val newCalendar = Calendar(id = calendarDao.fetchAllUsersCalendars() + 1, date = LocalDate.now(), events = listOf(event))
                        it to newCalendar
                    }
                }
                .map { (user, calendar) ->
                    val updatedCalendar = calendarDao.addEvent(userId = user.id, calendar = calendar)
                    println("updated calendar: $updatedCalendar")
                    return event
                }
            allUsersCalendar.forEach { _ ->
                println("Event: $event added for user's calendar: ${user.id}")
            }
        } catch (e: Exception) {
            error(e.localizedMessage)
        }

        return event
    }

    override fun delete(
        userId: Long,
        eventId: Long
    ): String {
        val user = usersDao.getUserById(userId)
        val calendar = calendarDao.deleteEvent(userId = userId, date = LocalDate.now(), eventId = eventId)

        return "Event with id: $eventId deleted from calendar: ${calendar.id} for user: ${user.id}"
    }

    override fun fetchBestCommonTime(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        userIds: List<Long>
    ): String {
        // validate all users
        val users = userIds.map {
            val user = usersDao.getUserById(it)
            user
        }
        println("All users fetched successfully: $users")

        val userEvents = userIds.map {
            it to calendarDao.fetchCalendar(userId = it, date = LocalDate.now()).events
        }
        userEvents.forEach { (userId, events) ->
            println("UserId: $userId, events: $events")
        }

        return "user events fetched successfully!"
    }

    private fun generateId(events: List<Event>): Long {
        return events.size.toLong() + 1
    }
}