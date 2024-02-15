package com.example.Bathroom.Model.Entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "bath_room")
open class BathRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "title", length = 45)
    open var title: String? = null

    @Column(name = "location", length = 45)
    open var location: String? = null

    @Column(name = "capacity")
    open var capacity: Int? = null

    @Column(name = "free")
    open var free: Boolean? = null

    @Column(name = "cost", precision = 10, scale = 2)
    open var cost: BigDecimal? = null

    @Column(name = "hours", length = 45)
    open var hours: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    @Column(name = "modified_at", nullable = false)
    open var modifiedAt: Instant? = null
}