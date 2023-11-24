package com.calendar.demo.model

import java.time.LocalDate

data class Calendar(
    val id: Long,
    val date: LocalDate,
    val events: List<Event>
)
