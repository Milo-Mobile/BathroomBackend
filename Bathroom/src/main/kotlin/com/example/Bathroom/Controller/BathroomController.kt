package com.example.Bathroom.Controller

import com.example.Bathroom.Enum.ReturnCode
import com.example.Bathroom.Model.DTO.BathroomDTO
import com.example.Bathroom.Model.VO.BathroomVO
import com.example.Bathroom.Util.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.example.Bathroom.Service.BathroomService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import com.example.Bathroom.Util.DateTimeConverter
import java.time.Instant

@RestController
@RequestMapping("/bathroom-info")
class BathroomController(
    private val bathroomService: BathroomService

) {

    private val logger: Logger = LoggerFactory.getLogger(BathroomController::class.java)

    @GetMapping("/bathrooms")
    fun GetAllBathRoomInfo(): ResponseEntity<Any> {
        val bathRoomVOs = bathroomService.GetAllBathrooms()
        return if (bathRoomVOs.isEmpty()) {
            val apiResponse: ApiResponse<List<BathroomVO>> = ApiResponse.error(ReturnCode.RC404.code, "No bathrooms found")
            ResponseEntity(apiResponse, HttpStatus.NOT_FOUND)
        } else {
            val apiResponse: ApiResponse<List<BathroomVO>> = ApiResponse.success(bathRoomVOs)
            ResponseEntity(apiResponse, HttpStatus.OK)
        }
    }
    @GetMapping("/bathroom")
    fun GetBathRoomInfoById(@RequestParam("number") id: Long): ResponseEntity<Any> {
        val bathroomVO = bathroomService.GetBathRoomById(id)
        return if (bathroomVO == null) {
            val apiResponse: ApiResponse<Any> = ApiResponse.error(ReturnCode.RC404.code, "No bathroom found")
            ResponseEntity(apiResponse, HttpStatus.NOT_FOUND)
        } else {
            val apiResponse = ApiResponse.success(bathroomVO)
            ResponseEntity(apiResponse, HttpStatus.OK)
        }
    }
    @PostMapping("/add")
    fun AddBathRoomInfo(@RequestBody bathroomDTO: BathroomDTO): ResponseEntity<Any> {
        bathroomDTO.createdAt = Instant.now()
        bathroomDTO.modifiedAt = Instant.now()
        val savedBathroom = bathroomService.AddBathRoom(bathroomDTO)
        return if (savedBathroom == null) {
            val apiResponse: ApiResponse<Any> = ApiResponse.error(ReturnCode.RC400.code, "Failed to save bathroom")
            ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST)
        } else {
            val apiResponse = ApiResponse.success(savedBathroom)
            ResponseEntity(apiResponse, HttpStatus.OK)
        }
    }
    @PostMapping("/update")
    fun UpdateBathRoomInfo(@RequestBody bathroomDTO: BathroomDTO): ResponseEntity<Any> {
        bathroomDTO.modifiedAt = Instant.now()
        val updatedBathroom = bathroomService.UpdateBathRoom(bathroomDTO)
        return if (updatedBathroom == null) {
            val apiResponse: ApiResponse<Any> = ApiResponse.error(ReturnCode.RC400.code, "Failed to edit bathroom")
            ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST)
        } else {
            val apiResponse = ApiResponse.success(updatedBathroom)
            ResponseEntity(apiResponse, HttpStatus.OK)
        }
    }
}