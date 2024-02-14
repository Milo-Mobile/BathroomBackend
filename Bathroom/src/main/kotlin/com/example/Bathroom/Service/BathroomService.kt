package com.example.Bathroom.Service

import com.example.Bathroom.Model.Entity.BathRoom
import com.example.Bathroom.Model.VO.BathRoomVO
import com.example.Bathroom.Repository.BathroomRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BathroomService(

    private val bathroomRepository: BathroomRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(BathroomService::class.java)

    fun getAllBathrooms(): List<BathRoomVO> {
        logger.info("Getting all bathrooms")
        val bathRooms: List<BathRoom> = bathroomRepository.findAllBathrooms()
        val bathRoomVOs: List<BathRoomVO> = bathRooms.map { bathRoom ->
            BathRoomVO(
                id = bathRoom.id,
                title = bathRoom.title,
                location = bathRoom.location,
                capacity = bathRoom.capacity,
                isFree = bathRoom.free,
                cost = bathRoom.cost,
                hours = bathRoom.hours
            )
        }
        return bathRoomVOs
    }
}