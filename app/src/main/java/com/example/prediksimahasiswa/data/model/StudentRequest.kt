package com.example.prediksimahasiswa.data.model

data class StudentRequest(
    val marital_status: Int,
    val application_mode: Int,
    val application_order: Int,
    val course: Int,
    val daytime_evening_attendance: Int,
    val previous_qualification: Int,
    val previous_qualification_grade: Double,
    val nationality: Int,
    val mothers_qualification: Int,
    val fathers_qualification: Int,
    val mothers_occupation: Int,
    val fathers_occupation: Int,
    val admission_grade: Double,
    val displaced: Int,
    val educational_special_needs: Int,
    val debtor: Int,
    val tuition_fees_up_to_date: Int,
    val gender: Int,
    val scholarship_holder: Int,
    val age_at_enrollment: Int,
    val international: Int,

    val curricular_units_1st_sem_credited: Int,
    val curricular_units_1st_sem_enrolled: Int,
    val curricular_units_1st_sem_evaluations: Int,
    val curricular_units_1st_sem_approved: Int,
    val curricular_units_1st_sem_grade: Double,
    val curricular_units_1st_sem_without_evaluations: Int,

    val curricular_units_2nd_sem_credited: Int,
    val curricular_units_2nd_sem_enrolled: Int,
    val curricular_units_2nd_sem_evaluations: Int,
    val curricular_units_2nd_sem_approved: Int,
    val curricular_units_2nd_sem_grade: Double,
    val curricular_units_2nd_sem_without_evaluations: Int,

    val unemployment_rate: Double,
    val inflation_rate: Double,
    val gdp: Double
)