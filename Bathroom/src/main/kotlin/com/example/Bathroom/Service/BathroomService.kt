package com.example.Bathroom.Service

import com.example.Bathroom.Model.DTO.BathroomDTO
import com.example.Bathroom.Model.Entity.BathRoom
import com.example.Bathroom.Model.VO.BathroomVO
import com.example.Bathroom.Repository.BathroomRepository
import com.example.Bathroom.Util.DateTimeConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BathroomService(

    private val bathroomRepository: BathroomRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(BathroomService::class.java)

    fun GetAllBathrooms(): List<BathroomVO> {
        logger.info("Getting all bathrooms")
        val bathRoomList: List<BathRoom> = bathroomRepository.findAllBathrooms()
        return TransferToVO(bathRoomList)
    }
    fun GetBathRoomById(id: Long): BathroomVO? {
        logger.info("Getting bathroom by ID")
        val bathRoom: BathRoom? = bathroomRepository.findById(id).orElse(null)
        return if (bathRoom == null) {
            null
        } else {
            val bathroomVO = BathroomVO(
                id = bathRoom.id,
                title = bathRoom.title,
                location = bathRoom.location,
                capacity = bathRoom.capacity,
                free = bathRoom.free,
                cost = bathRoom.cost,
                hours = bathRoom.hours,
                createdAt = DateTimeConverter().dateTimeConvertFromInstant(bathRoom.createdAt),
                modifiedAt = DateTimeConverter().dateTimeConvertFromInstant(bathRoom.modifiedAt)
            )
            bathroomVO
        }
    }
    fun AddBathRoom(bathroomDTO: BathroomDTO) : BathRoom?{
        logger.info("Adding a bathroom")
        try {
            val bathRoom = BathRoom().apply {
                title = bathroomDTO.title
                location = bathroomDTO.location
                capacity = bathroomDTO.capacity ?: 0
                free = bathroomDTO.free ?: false
                cost = bathroomDTO.cost ?: BigDecimal.ZERO
                hours = bathroomDTO.hours
                createdAt = bathroomDTO.createdAt
                modifiedAt = bathroomDTO.modifiedAt
            }
            return bathroomRepository.save(bathRoom)

        } catch (e: Exception) {
            logger.error("Failed to add bathroom info: {}", e.message, e)
        }
        return null
    }
    fun UpdateBathRoom(bathroomDTO: BathroomDTO) : BathRoom?{
        logger.info("Updating a bathroom")
        try {
            val id = bathroomDTO.id
            if (id == null) {
                logger.error("Bathroom ID is null")
                return null
            }
            val originalBathroom = bathroomRepository.findById(id).orElse(null)
            originalBathroom?.apply {
                title = if (bathroomDTO.title.isNullOrEmpty()) title else bathroomDTO.title
                location = if (bathroomDTO.location.isNullOrEmpty()) location else bathroomDTO.location
                capacity = bathroomDTO.capacity ?: capacity
                free = bathroomDTO.free ?: free
                cost = bathroomDTO.cost ?: cost
                hours = bathroomDTO.hours ?: hours
                createdAt = createdAt
                modifiedAt = bathroomDTO.modifiedAt
            }
            return bathroomRepository.save(originalBathroom)
        } catch (e: Exception) {
            logger.error("Failed to update bathroom info: {}", e.message, e)
        }
        return null
    }
    fun TransferToVO(bathRoomList: List<BathRoom>): List<BathroomVO> {
        logger.info("Transferring to VO")
        val bathroomVOList: List<BathroomVO> = bathRoomList.map { bathRoom ->
            BathroomVO(
                id = bathRoom.id,
                title = bathRoom.title,
                location = bathRoom.location,
                capacity = bathRoom.capacity,
                free = bathRoom.free,
                cost = bathRoom.cost,
                hours = bathRoom.hours,
                createdAt = DateTimeConverter().dateTimeConvertFromInstant(bathRoom.createdAt),
                modifiedAt = DateTimeConverter().dateTimeConvertFromInstant(bathRoom.modifiedAt)
            )
        }
        return bathroomVOList
    }
}
