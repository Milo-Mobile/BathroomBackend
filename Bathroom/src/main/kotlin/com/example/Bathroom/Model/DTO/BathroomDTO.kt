package com.example.Bathroom.Model.DTO

import java.math.BigDecimal
import java.time.Instant

data class BathroomDTO(
    var id: Long?,
    var title: String?,
    var location: String?,
    var capacity: Int?,
    var free: Boolean?,
    var cost: BigDecimal?,
    var hours: String?,
    var createdAt: Instant?,
    var modifiedAt: Instant?
)