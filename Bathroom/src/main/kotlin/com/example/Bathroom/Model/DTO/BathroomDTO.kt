package com.example.Bathroom.Model.DTO

import java.math.BigDecimal
import java.time.Instant

data class BathroomDTO(
    val id: Long?,
    val title: String?,
    val location: String?,
    val capacity: Int?,
    val free: Boolean?,
    val cost: BigDecimal?,
    val hours: String?,
    val createdAt: Instant?,
    val modifiedAt: Instant?
)