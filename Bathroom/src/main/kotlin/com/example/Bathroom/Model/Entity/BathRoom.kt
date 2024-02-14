package com.example.Bathroom.Model.Entity

import jakarta.persistence.*

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

    @Column(name = "cost")
    open var cost: Long? = null

    @Column(name = "hours", length = 45)
    open var hours: String? = null
}