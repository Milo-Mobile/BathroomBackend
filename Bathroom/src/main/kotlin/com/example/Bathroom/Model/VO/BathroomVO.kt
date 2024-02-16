package com.example.Bathroom.Model.VO

import java.math.BigDecimal

data class BathroomVO(
    val id: Long?,
    val title: String?,
    val location: String?,
    val capacity: Int?,
    val free: Boolean?,
    val cost: BigDecimal?,
    val hours: String?,
    val createdAt: String?,
    val modifiedAt: String?
)
