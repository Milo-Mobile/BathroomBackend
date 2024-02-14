package com.example.Bathroom.Controller

import com.example.Bathroom.Util.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.Bathroom.Service.BathroomService

@RestController
@RequestMapping("/bathroom-info")
class BathroomController(
    private val bathroomService: BathroomService

) {

    private val logger: Logger = LoggerFactory.getLogger(BathroomController::class.java)

    @GetMapping("/")
    fun GetBathroomInfo(): ResponseEntity<ApiResponse<String>> {
        val BathRoomVOs = bathroomService.getAllBathrooms()
        if (BathRoomVOs.isEmpty()) {
            val apiResponse: ApiResponse<String> = ApiResponse.error(404, "No bathrooms found")
            return ResponseEntity(apiResponse, HttpStatus.NOT_FOUND)
        } else {
            val apiResponse: ApiResponse<String> = ApiResponse.success(BathRoomVOs.toString())
            return ResponseEntity(apiResponse, HttpStatus.OK)
        }
    }
}