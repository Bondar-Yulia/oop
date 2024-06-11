package com.example.servlets

import com.example.dao.PatientDao
import com.example.models.PatientModel
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.sql.Connection
import java.sql.DriverManager

@WebServlet("/patients")
class PatientsServlet : HttpServlet() {

    private lateinit var connection: Connection
    private lateinit var patientDao: PatientDao

    override fun init() {
        val jdbcUrl = "jdbc:your_database_url"
        val jdbcUser = "your_database_user"
        val jdbcPassword = "your_database_password"
        connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)
        patientDao = PatientDao(connection)
    }

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val patients = patientDao.getAllPatients()
        resp.writer.write(patients.toString())
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val patient = PatientModel(
            id = req.getParameter("id").toLong(),
            name = req.getParameter("name"),
            birthdate = req.getParameter("birthdate")
        )
        patientDao.createPatient(patient)
        resp.status = HttpServletResponse.SC_CREATED
    }

    override fun doPut(req: HttpServletRequest, resp: HttpServletResponse) {
        val patient = PatientModel(
            id = req.getParameter("id").toLong(),
            name = req.getParameter("name"),
            birthdate = req.getParameter("birthdate")
        )
        patientDao.updatePatient(patient.id, patient)
        resp.status = HttpServletResponse.SC_OK
    }

    override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        val id = req.getParameter("id").toLong()
        patientDao.deletePatient(id)
        resp.status = HttpServletResponse.SC_NO_CONTENT
    }

    override fun destroy() {
        connection.close()
    }
}
