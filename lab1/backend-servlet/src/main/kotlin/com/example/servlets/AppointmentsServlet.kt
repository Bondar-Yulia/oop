package com.example.servlets

import com.example.dao.AppointmentDao
import com.example.models.AppointmentModel
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.sql.Connection
import java.sql.DriverManager

@WebServlet("/appointments")
class AppointmentsServlet : HttpServlet() {

    private lateinit var connection: Connection
    private lateinit var appointmentDao: AppointmentDao

    override fun init() {
        val jdbcUrl = "jdbc:your_database_url"
        val jdbcUser = "your_database_user"
        val jdbcPassword = "your_database_password"
        connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)
        appointmentDao = AppointmentDao(connection)
    }

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val appointments = appointmentDao.getAllAppointments()
        resp.writer.write(appointments.toString())
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val appointment = AppointmentModel(
            id = req.getParameter("id").toLong(),
            patientId = req.getParameter("patientId").toLong(),
            doctorId = req.getParameter("doctorId").toLong(),
            date = req.getParameter("date"),
            diagnosis = req.getParameter("diagnosis"),
            prescription = req.getParameter("prescription")
        )
        appointmentDao.createAppointment(appointment)
        resp.status = HttpServletResponse.SC_CREATED
    }

    override fun doPut(req: HttpServletRequest, resp: HttpServletResponse) {
        val appointment = AppointmentModel(
            id = req.getParameter("id").toLong(),
            patientId = req.getParameter("patientId").toLong(),
            doctorId = req.getParameter("doctorId").toLong(),
            date = req.getParameter("date"),
            diagnosis = req.getParameter("diagnosis"),
            prescription = req.getParameter("prescription")
        )
        appointmentDao.updateAppointment(appointment.id, appointment)
        resp.status = HttpServletResponse.SC_OK
    }

    override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        val id = req.getParameter("id").toLong()
        appointmentDao.deleteAppointment(id)
        resp.status = HttpServletResponse.SC_NO_CONTENT
    }

    override fun destroy() {
        connection.close()
    }
}
