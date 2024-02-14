package com.example.Bathroom.Repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.Bathroom.Model.Entity.BathRoom
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BathroomRepository : JpaRepository<BathRoom, Long> {
    @Query("SELECT * FROM android.bath_room", nativeQuery = true)
    fun findAllBathrooms(): List<BathRoom>
}