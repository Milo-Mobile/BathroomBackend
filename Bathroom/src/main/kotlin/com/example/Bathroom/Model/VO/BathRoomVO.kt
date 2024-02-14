package com.example.Bathroom.Model.VO

data class BathRoomVO(
    var id: Long?,
    var title: String?,
    var location: String?,
    var capacity: Int?,
    var isFree: Boolean?,
    var cost: Long?,
    var hours: String?
){
    constructor() : this(null, null, null, null, null, null, null)
}
