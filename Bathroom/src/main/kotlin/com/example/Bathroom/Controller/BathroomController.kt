package com.example.Bathroom.Controller

import com.example.Bathroom.Enum.ReturnCode
import com.example.Bathroom.Model.DTO.BathroomDTO
import com.example.Bathroom.Service.BathroomService
import com.example.Bathroom.Util.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/bathroom-info")
class BathroomController(
    private val bathroomService: BathroomService

) {

    private val logger: Logger = LoggerFactory.getLogger(BathroomController::class.java)

    @GetMapping("/bathrooms")
    fun GetAllBathRoomInfo(): ResponseEntity<Any> {
        val apiResponse: ApiResponse<Any>
        val bathRoomVOs = bathroomService.GetAllBathrooms()
        apiResponse = if (bathRoomVOs.isEmpty()) {
            ApiResponse.error(ReturnCode.RC404.code, "No bathrooms found")

        } else {
            ApiResponse.success(bathRoomVOs)

        }
        return ResponseEntity.status(apiResponse.code).body(apiResponse)
    }

    @GetMapping("/bathroom")
    fun GetBathRoomInfoById(@RequestParam("number") id: Long): ResponseEntity<Any> {
        val apiResponse: ApiResponse<Any>
        val bathroomVO = bathroomService.GetBathRoomById(id)
        apiResponse = if (bathroomVO == null) {
            ApiResponse.error(ReturnCode.RC404.code, "No bathroom found")
        } else {
            ApiResponse.success(bathroomVO)
        }
        return ResponseEntity.status(apiResponse.code).body(apiResponse)
    }

    @PostMapping("/add")
    fun AddBathRoomInfo(@RequestBody bathroomDTO: BathroomDTO): ResponseEntity<Any> {
        val apiResponse: ApiResponse<Any>
        bathroomDTO.createdAt = Instant.now()
        bathroomDTO.modifiedAt = Instant.now()
        val savedBathroom = bathroomService.AddBathRoom(bathroomDTO)
        apiResponse = if (savedBathroom == null) {
            ApiResponse.error(ReturnCode.RC400.code, "Failed to save bathroom")
        } else {
            ApiResponse.success(savedBathroom)
        }
        return ResponseEntity.status(apiResponse.code).body(apiResponse)
    }

    @PostMapping("/update")
    fun UpdateBathRoomInfo(@RequestBody bathroomDTO: BathroomDTO): ResponseEntity<Any> {
        val apiResponse: ApiResponse<Any>
        bathroomDTO.modifiedAt = Instant.now()
        val updatedBathroom = bathroomService.UpdateBathRoom(bathroomDTO)
        apiResponse = if (updatedBathroom == null) {
            ApiResponse.error(ReturnCode.RC400.code, "Failed to edit bathroom")
        } else {
            ApiResponse.success(updatedBathroom)
        }
        return ResponseEntity.status(apiResponse.code).body(apiResponse)
    }

    @DeleteMapping("/delete")
    fun DeleteBathRoomInfo(@RequestParam("number") id: Long): ResponseEntity<Any> {
        val apiResponse: ApiResponse<Any>

        val isDeleted = bathroomService.DeleteBathRoom(id)
        // 0: No bathroom found to delete
        // 1: Bathroom deleted successfully
        // 2: Failed to delete bathroom
        apiResponse = if (isDeleted == 0) {
            ApiResponse.error(ReturnCode.RC404.code, "No bathroom found to delete")
        } else if (isDeleted == 1) {
            ApiResponse.success("Bathroom deleted successfully")
        } else {
            ApiResponse.error(ReturnCode.RC400.code, "Failed to delete bathroom")
        }
        return ResponseEntity.status(apiResponse.code).body(apiResponse)
    }
}