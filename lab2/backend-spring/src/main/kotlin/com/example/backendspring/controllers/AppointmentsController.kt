package com.example.backendspring.controllers

import com.example.backendspring.models.AppointmentModel
import com.example.backendspring.services.AppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/appointments")
class AppointmentsController @Autowired constructor(private val appointmentService: AppointmentService) {

    @GetMapping
    fun getAllAppointments(): ResponseEntity<List<AppointmentModel>> {
        return ResponseEntity.ok(appointmentService.getAllAppointments())
    }

    @PostMapping
    fun createAppointment(@RequestBody appointment: AppointmentModel): ResponseEntity<AppointmentModel> {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointment))
    }

    @GetMapping("/{id}")
    fun getAppointmentById(@PathVariable id: Long): ResponseEntity<AppointmentModel> {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id))
    }

    @PutMapping("/{id}")
    fun updateAppointment(@PathVariable id: Long, @RequestBody appointment: AppointmentModel): ResponseEntity<AppointmentModel> {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment))
    }

    @DeleteMapping("/{id}")
    fun deleteAppointment(@PathVariable id: Long): ResponseEntity<Void> {
        appointmentService.deleteAppointment(id)
        return ResponseEntity.noContent().build()
    }
}
