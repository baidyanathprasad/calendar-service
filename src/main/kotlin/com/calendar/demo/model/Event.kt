package com.calendar.demo.model

import com.calendar.demo.domain.EventType
import java.time.LocalDateTime

data class Event(
    val id: Long,
    val eventTye: EventType,
    val location: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val attendees: List<User>,
    val createdBy: User
)
