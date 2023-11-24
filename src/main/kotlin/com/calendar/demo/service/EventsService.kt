package com.calendar.demo.service

import com.calendar.demo.domain.EventType
import com.calendar.demo.model.Event
import java.time.LocalDateTime

interface EventsService {

    fun create(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        eventType: EventType,
        location: String,
        attendees: List<Long>,
        createdBy: Long
    ): Event

    fun delete(userId: Long, eventId: Long): String

    fun fetchBestCommonTime(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        userIds: List<Long>
    ): String
}