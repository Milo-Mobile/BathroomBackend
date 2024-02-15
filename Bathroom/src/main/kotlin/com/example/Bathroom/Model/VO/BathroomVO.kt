package com.example.Bathroom.Model.VO

import java.math.BigDecimal

data class BathroomVO(
    var id: Long?,
    var title: String?,
    var location: String?,
    var capacity: Int?,
    var free: Boolean?,
    var cost: BigDecimal?,
    var hours: String?,
    var createdAt: String?,
    var modifiedAt: String?
){
    constructor() : this(null, null, null, null, null, null, null, null, null)
}
