package com.example.servlets

import com.example.dao.DiagnosisDao
import com.example.models.DiagnosisModel
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.sql.Connection
import java.sql.DriverManager

@WebServlet("/diagnoses")
class DiagnosesServlet : HttpServlet() {

    private lateinit var connection: Connection
    private lateinit var diagnosisDao: DiagnosisDao

    override fun init() {
        val jdbcUrl = "jdbc:your_database_url"
        val jdbcUser = "your_database_user"
        val jdbcPassword = "your_database_password"
        connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)
        diagnosisDao = DiagnosisDao(connection)
    }

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val diagnoses = diagnosisDao.getAllDiagnoses()
        resp.writer.write(diagnoses.toString())
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val diagnosis = DiagnosisModel(
            id = req.getParameter("id").toLong(),
            appointmentId = req.getParameter("appointmentId").toLong(),
            description = req.getParameter("description")
        )
        diagnosisDao.createDiagnosis(diagnosis)
        resp.status = HttpServletResponse.SC_CREATED
    }

    override fun doPut(req: HttpServletRequest, resp: HttpServletResponse) {
        val diagnosis = DiagnosisModel(
            id = req.getParameter("id").toLong(),
            appointmentId = req.getParameter("appointmentId").toLong(),
            description = req.getParameter("description")
        )
        diagnosisDao.updateDiagnosis(diagnosis.id, diagnosis)
        resp.status = HttpServletResponse.SC_OK
    }

    override fun doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
        val id = req.getParameter("id").toLong()
        diagnosisDao.deleteDiagnosis(id)
        resp.status = HttpServletResponse.SC_NO_CONTENT
    }

    override fun destroy() {
        connection.close()
    }
}
