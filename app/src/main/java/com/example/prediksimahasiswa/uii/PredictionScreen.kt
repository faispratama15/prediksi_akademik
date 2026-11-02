package com.example.prediksimahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prediksimahasiswa.data.api.RetrofitClient
import com.example.prediksimahasiswa.data.model.PredictionResponse
import com.example.prediksimahasiswa.data.model.StudentRequest
import kotlinx.coroutines.launch
import java.util.Locale

private val PrimaryBlue = Color(0xFF2563EB)
private val DarkBlue = Color(0xFF1E3A8A)
private val LightBlue = Color(0xFFEFF6FF)
private val SuccessGreen = Color(0xFF16A34A)
private val WarningOrange = Color(0xFFF59E0B)
private val DangerRed = Color(0xFFDC2626)
private val TextDark = Color(0xFF1E293B)
private val TextGray = Color(0xFF64748B)
private val BorderGray = Color(0xFFE2E8F0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionScreen(
    onBack: () -> Unit = {}
) {

    var currentStep by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    var predictionResult by remember { mutableStateOf<PredictionResponse?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // DATA PRIBADI
    var maritalStatus by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var ageAtEnrollment by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var displaced by remember { mutableStateOf("") }
    var educationalSpecialNeeds by remember { mutableStateOf("") }
    var international by remember { mutableStateOf("") }

    // DATA PENDAFTARAN
    var applicationMode by remember { mutableStateOf("") }
    var applicationOrder by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var daytimeEveningAttendance by remember { mutableStateOf("") }
    var previousQualification by remember { mutableStateOf("") }
    var previousQualificationGrade by remember { mutableStateOf("") }
    var admissionGrade by remember { mutableStateOf("") }

    // DATA KELUARGA
    var mothersQualification by remember { mutableStateOf("") }
    var fathersQualification by remember { mutableStateOf("") }
    var mothersOccupation by remember { mutableStateOf("") }
    var fathersOccupation by remember { mutableStateOf("") }

    // DATA KEUANGAN
    var debtor by remember { mutableStateOf("") }
    var tuitionFeesUpToDate by remember { mutableStateOf("") }
    var scholarshipHolder by remember { mutableStateOf("") }

    // SEMESTER 1
    var sem1Credited by remember { mutableStateOf("") }
    var sem1Enrolled by remember { mutableStateOf("") }
    var sem1Evaluations by remember { mutableStateOf("") }
    var sem1Approved by remember { mutableStateOf("") }
    var sem1Grade by remember { mutableStateOf("") }
    var sem1WithoutEvaluations by remember { mutableStateOf("") }

    // SEMESTER 2
    var sem2Credited by remember { mutableStateOf("") }
    var sem2Enrolled by remember { mutableStateOf("") }
    var sem2Evaluations by remember { mutableStateOf("") }
    var sem2Approved by remember { mutableStateOf("") }
    var sem2Grade by remember { mutableStateOf("") }
    var sem2WithoutEvaluations by remember { mutableStateOf("") }

    // INDIKATOR EKONOMI
    var unemploymentRate by remember { mutableStateOf("") }
    var inflationRate by remember { mutableStateOf("") }
    var gdp by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    fun validateCurrentStep(): Boolean {
        errorMessage = null

        return when (currentStep) {
            1 -> {
                if (maritalStatus.isBlank() || gender.isBlank() || ageAtEnrollment.isBlank() ||
                    nationality.isBlank() || displaced.isBlank() ||
                    educationalSpecialNeeds.isBlank() || international.isBlank()
                ) {
                    errorMessage = "Mohon lengkapi semua data pribadi."
                    false
                } else true
            }

            2 -> {
                if (applicationMode.isBlank() || applicationOrder.isBlank() || course.isBlank() ||
                    daytimeEveningAttendance.isBlank() || previousQualification.isBlank() ||
                    previousQualificationGrade.isBlank() || admissionGrade.isBlank()
                ) {
                    errorMessage = "Mohon lengkapi semua data pendaftaran."
                    false
                } else true
            }

            3 -> {
                if (mothersQualification.isBlank() || fathersQualification.isBlank() ||
                    mothersOccupation.isBlank() || fathersOccupation.isBlank() || debtor.isBlank() ||
                    tuitionFeesUpToDate.isBlank() || scholarshipHolder.isBlank()
                ) {
                    errorMessage = "Mohon lengkapi semua data keluarga dan keuangan."
                    false
                } else true
            }

            4 -> {
                if (sem1Credited.isBlank() || sem1Enrolled.isBlank() || sem1Evaluations.isBlank() ||
                    sem1Approved.isBlank() || sem1Grade.isBlank() || sem1WithoutEvaluations.isBlank()
                ) {
                    errorMessage = "Mohon lengkapi seluruh data semester 1."
                    false
                } else true
            }

            5 -> {
                if (sem2Credited.isBlank() || sem2Enrolled.isBlank() || sem2Evaluations.isBlank() ||
                    sem2Approved.isBlank() || sem2Grade.isBlank() || sem2WithoutEvaluations.isBlank() ||
                    unemploymentRate.isBlank() || inflationRate.isBlank() || gdp.isBlank()
                ) {
                    errorMessage = "Mohon lengkapi seluruh data semester 2 dan indikator ekonomi."
                    false
                } else true
            }

            else -> true
        }
    }

    fun performPrediction() {
        if (!validateCurrentStep()) return

        isLoading = true
        errorMessage = null

        scope.launch {
            try {
                val request = StudentRequest(
                    marital_status = maritalStatus.substringBefore(" ").toInt(),
                    application_mode = applicationMode.substringBefore(" ").toInt(),
                    application_order = applicationOrder.substringBefore(" ").toInt(),
                    course = course.substringBefore(" ").toInt(),
                    daytime_evening_attendance = daytimeEveningAttendance.substringBefore(" ").toInt(),
                    previous_qualification = previousQualification.substringBefore(" ").toInt(),
                    previous_qualification_grade = previousQualificationGrade.toDouble(),
                    nationality = nationality.substringBefore(" ").toInt(),
                    mothers_qualification = mothersQualification.substringBefore(" ").toInt(),
                    fathers_qualification = fathersQualification.substringBefore(" ").toInt(),
                    mothers_occupation = mothersOccupation.substringBefore(" ").toInt(),
                    fathers_occupation = fathersOccupation.substringBefore(" ").toInt(),
                    admission_grade = admissionGrade.toDouble(),
                    displaced = displaced.substringBefore(" ").toInt(),
                    educational_special_needs = educationalSpecialNeeds.substringBefore(" ").toInt(),
                    debtor = debtor.substringBefore(" ").toInt(),
                    tuition_fees_up_to_date = tuitionFeesUpToDate.substringBefore(" ").toInt(),
                    gender = gender.substringBefore(" ").toInt(),
                    scholarship_holder = scholarshipHolder.substringBefore(" ").toInt(),
                    age_at_enrollment = ageAtEnrollment.toInt(),
                    international = international.substringBefore(" ").toInt(),
                    curricular_units_1st_sem_credited = sem1Credited.toInt(),
                    curricular_units_1st_sem_enrolled = sem1Enrolled.toInt(),
                    curricular_units_1st_sem_evaluations = sem1Evaluations.toInt(),
                    curricular_units_1st_sem_approved = sem1Approved.toInt(),
                    curricular_units_1st_sem_grade = sem1Grade.toDouble(),
                    curricular_units_1st_sem_without_evaluations = sem1WithoutEvaluations.toInt(),
                    curricular_units_2nd_sem_credited = sem2Credited.toInt(),
                    curricular_units_2nd_sem_enrolled = sem2Enrolled.toInt(),
                    curricular_units_2nd_sem_evaluations = sem2Evaluations.toInt(),
                    curricular_units_2nd_sem_approved = sem2Approved.toInt(),
                    curricular_units_2nd_sem_grade = sem2Grade.toDouble(),
                    curricular_units_2nd_sem_without_evaluations = sem2WithoutEvaluations.toInt(),
                    unemployment_rate = unemploymentRate.toDouble(),
                    inflation_rate = inflationRate.toDouble(),
                    gdp = gdp.toDouble()
                )

                predictionResult = RetrofitClient.api.predict(request)
            } catch (e: Exception) {
                errorMessage = "Gagal melakukan prediksi.\n${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        containerColor = Color(0xFFF8FAFC),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Prediksi Akademik",
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )
                        Text(
                            text = "Analisis risiko mahasiswa",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onBack) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                HeaderCard()
            }

            item { ProgressCard(currentStep = currentStep) }

            item {
                when (currentStep) {
                    1 -> {
                        SectionHeader("Data Pribadi", "Informasi dasar mahasiswa")
                        Spacer(modifier = Modifier.height(10.dp))
                        DropdownField("Status Pernikahan", maritalStatus, maritalStatusOptions()) { maritalStatus = it }
                        DropdownField("Jenis Kelamin", gender, genderOptions()) { gender = it }
                        NumberField("Usia Saat Mendaftar", ageAtEnrollment, { ageAtEnrollment = it }, "Contoh: 19")
                        DropdownField("Kewarganegaraan", nationality, nationalityOptions()) { nationality = it }
                        DropdownField("Pindahan / Displaced", displaced, yesNoOptions()) { displaced = it }
                        DropdownField("Kebutuhan Pendidikan Khusus", educationalSpecialNeeds, yesNoOptions()) { educationalSpecialNeeds = it }
                        DropdownField("Mahasiswa Internasional", international, yesNoOptions()) { international = it }
                    }

                    2 -> {
                        SectionHeader("Data Pendaftaran", "Informasi saat mahasiswa mendaftar")
                        Spacer(modifier = Modifier.height(10.dp))
                        DropdownField("Mode Pendaftaran", applicationMode, applicationModeOptions()) { applicationMode = it }
                        DropdownField("Urutan Pilihan", applicationOrder, applicationOrderOptions()) { applicationOrder = it }
                        DropdownField("Program Studi", course, courseOptions()) { course = it }
                        DropdownField("Waktu Perkuliahan", daytimeEveningAttendance, attendanceOptions()) { daytimeEveningAttendance = it }
                        DropdownField("Pendidikan Sebelumnya", previousQualification, previousQualificationOptions()) { previousQualification = it }
                        NumberField("Nilai Pendidikan Sebelumnya", previousQualificationGrade, { previousQualificationGrade = it }, "Contoh: 140")
                        NumberField("Nilai Penerimaan", admissionGrade, { admissionGrade = it }, "Contoh: 130.5")
                    }

                    3 -> {
                        SectionHeader("Keluarga & Keuangan", "Latar belakang keluarga dan kondisi finansial")
                        Spacer(modifier = Modifier.height(10.dp))
                        DropdownField("Pendidikan Ibu", mothersQualification, motherQualificationOptions()) { mothersQualification = it }
                        DropdownField("Pendidikan Ayah", fathersQualification, fatherQualificationOptions()) { fathersQualification = it }
                        DropdownField("Pekerjaan Ibu", mothersOccupation, occupationOptions()) { mothersOccupation = it }
                        DropdownField("Pekerjaan Ayah", fathersOccupation, occupationOptions()) { fathersOccupation = it }
                        DropdownField("Memiliki Tunggakan", debtor, yesNoOptions()) { debtor = it }
                        DropdownField("Biaya Kuliah Lancar", tuitionFeesUpToDate, yesNoOptions()) { tuitionFeesUpToDate = it }
                        DropdownField("Penerima Beasiswa", scholarshipHolder, yesNoOptions()) { scholarshipHolder = it }
                    }

                    4 -> {
                        SectionHeader("Akademik Semester 1", "Performa akademik pada semester pertama")
                        Spacer(modifier = Modifier.height(10.dp))
                        NumberField("Mata Kuliah Diakui", sem1Credited, { sem1Credited = it }, "Contoh: 0")
                        NumberField("Mata Kuliah Diambil", sem1Enrolled, { sem1Enrolled = it }, "Contoh: 6")
                        NumberField("Mata Kuliah Dievaluasi", sem1Evaluations, { sem1Evaluations = it }, "Contoh: 6")
                        NumberField("Mata Kuliah Lulus", sem1Approved, { sem1Approved = it }, "Contoh: 5")
                        NumberField("Nilai Rata-rata Semester 1", sem1Grade, { sem1Grade = it }, "Contoh: 13.5")
                        NumberField("Tanpa Evaluasi", sem1WithoutEvaluations, { sem1WithoutEvaluations = it }, "Contoh: 0")
                    }

                    5 -> {
                        SectionHeader("Semester 2 & Ekonomi", "Performa semester kedua dan indikator ekonomi")
                        Spacer(modifier = Modifier.height(10.dp))
                        NumberField("Mata Kuliah Diakui", sem2Credited, { sem2Credited = it }, "Contoh: 0")
                        NumberField("Mata Kuliah Diambil", sem2Enrolled, { sem2Enrolled = it }, "Contoh: 6")
                        NumberField("Mata Kuliah Dievaluasi", sem2Evaluations, { sem2Evaluations = it }, "Contoh: 6")
                        NumberField("Mata Kuliah Lulus", sem2Approved, { sem2Approved = it }, "Contoh: 5")
                        NumberField("Nilai Rata-rata Semester 2", sem2Grade, { sem2Grade = it }, "Contoh: 13.5")
                        NumberField("Tanpa Evaluasi", sem2WithoutEvaluations, { sem2WithoutEvaluations = it }, "Contoh: 0")

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Indikator Ekonomi", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Data ekonomi makro yang digunakan oleh model.", fontSize = 12.sp, color = TextGray)
                        Spacer(modifier = Modifier.height(8.dp))
                        NumberField("Tingkat Pengangguran", unemploymentRate, { unemploymentRate = it }, "Contoh: 10.8")
                        NumberField("Tingkat Inflasi", inflationRate, { inflationRate = it }, "Contoh: 1.4")
                        NumberField("GDP", gdp, { gdp = it }, "Contoh: 1.74")
                    }
                }
            }

            errorMessage?.let { message ->
                item { ErrorCard(message = message) }
            }

            item {
                NavigationButtons(
                    currentStep = currentStep,
                    isLoading = isLoading,
                    onPrevious = {
                        if (currentStep > 1) {
                            currentStep--
                            errorMessage = null
                        }
                    },
                    onNext = {
                        if (validateCurrentStep()) {
                            if (currentStep < 5) currentStep++
                        }
                    },
                    onPredict = { performPrediction() }
                )
            }

            predictionResult?.let { result ->
                item {
                    PredictionResultCard(
                        result = result,
                        onReset = {
                            predictionResult = null
                            currentStep = 1
                            errorMessage = null
                        }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun HeaderCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text("Sistem Prediksi Akademik", color = Color.White.copy(alpha = 0.85f), fontSize = 13.sp)
                    Text("Random Forest", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text("Prediksi Risiko Dropout", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
            Text("dan Keberhasilan Akademik Mahasiswa", color = Color.White.copy(alpha = 0.9f), fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Masukkan data mahasiswa untuk mendapatkan hasil prediksi berdasarkan model machine learning.",
                color = Color.White.copy(alpha = 0.82f),
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
        }
    }
}

@Composable
fun ProgressCard(currentStep: Int) {
    val progress = currentStep / 5f
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Progress Pengisian", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    Text("Lengkapi data untuk melakukan prediksi", fontSize = 12.sp, color = TextGray)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(LightBlue)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("$currentStep / 5", color = PrimaryBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(7.dp).clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StepIndicator(1, "Pribadi", currentStep >= 1)
                StepIndicator(2, "Daftar", currentStep >= 2)
                StepIndicator(3, "Keluarga", currentStep >= 3)
                StepIndicator(4, "Sem 1", currentStep >= 4)
                StepIndicator(5, "Sem 2", currentStep >= 5)
            }
        }
    }
}

@Composable
fun StepIndicator(number: Int, label: String, active: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(if (active) PrimaryBlue else Color(0xFFE2E8F0)),
            contentAlignment = Alignment.Center
        ) {
            if (active) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(number.toString(), fontSize = 11.sp, color = TextGray, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 9.sp, color = if (active) PrimaryBlue else TextGray, fontWeight = if (active) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightBlue)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(42.dp).clip(CircleShape).background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("✓", color = PrimaryBlue, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = DarkBlue)
                Text(subtitle, fontSize = 12.sp, color = TextGray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            modifier = Modifier.padding(start = 4.dp, bottom = 5.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Pilih $label", color = TextGray) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(text = option, fontSize = 13.sp)
                        },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun NumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            modifier = Modifier.padding(start = 4.dp, bottom = 5.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                    onValueChange(newValue)
                }
            },
            placeholder = { Text(placeholder, color = TextGray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ErrorCard(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F2))
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = DangerRed
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(message, color = Color(0xFF991B1B), fontSize = 13.sp, lineHeight = 18.sp)
        }
    }
}

@Composable
fun NavigationButtons(
    currentStep: Int,
    isLoading: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onPredict: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (currentStep > 1) {
            OutlinedButton(
                onClick = onPrevious,
                modifier = Modifier.weight(1f).height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Kembali", fontWeight = FontWeight.SemiBold)
            }
        }

        if (currentStep < 5) {
            Button(
                onClick = onNext,
                modifier = Modifier.weight(1f).height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text("Lanjut", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        } else {
            Button(
                onClick = onPredict,
                enabled = !isLoading,
                modifier = Modifier.weight(1f).height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                if (isLoading) {
                    androidx.compose.material3.CircularProgressIndicator(
                        modifier = Modifier.size(21.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Menganalisis...", fontWeight = FontWeight.Bold)
                } else {
                    Text("Prediksi Sekarang", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun PredictionResultCard(result: PredictionResponse, onReset: () -> Unit) {
    val prediction = result.prediction
    val confidence = result.probabilities[prediction] ?: 0.0

    val interpretation = when (prediction) {
        "Dropout" -> "Model memprediksi mahasiswa memiliki risiko untuk tidak menyelesaikan pendidikan."
        "Enrolled" -> "Model memprediksi mahasiswa masih berada dalam status terdaftar atau proses pendidikan."
        "Graduate" -> "Model memprediksi mahasiswa memiliki kecenderungan berhasil menyelesaikan pendidikan."
        else -> "Model memberikan hasil prediksi yang tidak dikenal."
    }

    val recommendation = when (prediction) {
        "Dropout" -> "Perlu perhatian lebih terhadap performa akademik, keaktifan perkuliahan, dan kondisi finansial mahasiswa."
        "Enrolled" -> "Disarankan melakukan pemantauan akademik secara berkala agar mahasiswa dapat menyelesaikan studinya."
        "Graduate" -> "Pertahankan performa akademik dan terus lakukan monitoring sampai mahasiswa menyelesaikan studinya."
        else -> "Lakukan evaluasi lebih lanjut terhadap data mahasiswa."
    }

    val resultColor = when (prediction) {
        "Dropout" -> DangerRed
        "Enrolled" -> WarningOrange
        "Graduate" -> SuccessGreen
        else -> PrimaryBlue
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Hasil Prediksi", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = resultColor.copy(alpha = 0.09f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(48.dp).clip(CircleShape).background(resultColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (prediction == "Dropout") {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = resultColor,
                                    modifier = Modifier.size(25.dp)
                                )
                            } else {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = resultColor,
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text("Prediksi", fontSize = 12.sp, color = TextGray)
                            Text(prediction, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = resultColor)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Confidence Model", fontSize = 12.sp, color = TextGray)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(formatProbability(confidence), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextDark)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            InfoResultSection("Interpretasi", interpretation)
            Spacer(modifier = Modifier.height(10.dp))
            InfoResultSection("Rekomendasi", recommendation)
            Spacer(modifier = Modifier.height(18.dp))

            Text("Probabilitas Setiap Kelas", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Spacer(modifier = Modifier.height(12.dp))
            ProbabilityItem("Dropout", result.probabilities["Dropout"] ?: 0.0, DangerRed)
            ProbabilityItem("Enrolled", result.probabilities["Enrolled"] ?: 0.0, WarningOrange)
            ProbabilityItem("Graduate", result.probabilities["Graduate"] ?: 0.0, SuccessGreen)

            Spacer(modifier = Modifier.height(18.dp))
            androidx.compose.material3.HorizontalDivider(color = BorderGray)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Informasi Model", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Spacer(modifier = Modifier.height(10.dp))
            ModelInfoRow("Algoritma", "Random Forest")
            ModelInfoRow("Jumlah fitur", "36 fitur")
            ModelInfoRow("Jumlah kelas", "3 kelas")
            ModelInfoRow("Akurasi pengujian", "77,40%")

            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Text(
                        "Probabilitas merupakan keluaran model berdasarkan data yang dimasukkan dan bukan jaminan hasil akademik mahasiswa.",
                        fontSize = 11.sp,
                        color = TextGray,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
            OutlinedButton(
                onClick = onReset,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text("Prediksi Ulang", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun InfoResultSection(title: String, text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextDark)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text, fontSize = 12.sp, color = TextGray, lineHeight = 18.sp)
        }
    }
}

@Composable
fun ProbabilityItem(label: String, value: Double, color: Color) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextDark)
            Text(formatProbability(value), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = { value.toFloat() },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun ModelInfoRow(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, fontSize = 12.sp, color = TextGray)
        Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextDark)
    }
}

fun formatProbability(value: Double): String = String.format(Locale.US, "%.2f%%", value * 100)

fun yesNoOptions() = listOf("0 Tidak", "1 Ya")

fun genderOptions() = listOf("0 Perempuan", "1 Laki-laki")

fun maritalStatusOptions() = listOf(
    "1 Belum menikah",
    "2 Menikah",
    "3 Duda/Janda",
    "4 Cerai",
    "5 Hubungan facto",
    "6 Pisah secara hukum"
)

fun applicationModeOptions() = listOf(
    "1 Fase pertama - Umum",
    "2 Fase pertama - Kuota khusus",
    "5 Fase kedua - Umum",
    "7 Fase kedua - Kuota khusus",
    "10 Fase ketiga - Umum",
    "15 Transfer",
    "16 Transfer + ujian",
    "17 Fase khusus",
    "18 Re-entry",
    "26 Jalur khusus",
    "27 Jalur internasional",
    "39 Jalur alternatif",
    "42 Jalur lain",
    "43 Jalur umum",
    "44 Jalur khusus lainnya",
    "51 Jalur tambahan",
    "53 Jalur lainnya",
    "57 Jalur re-entry"
)

fun applicationOrderOptions() = listOf(
    "0 Pilihan pertama",
    "1 Pilihan kedua",
    "2 Pilihan ketiga",
    "3 Pilihan keempat",
    "4 Pilihan kelima",
    "5 Pilihan keenam",
    "6 Pilihan ketujuh",
    "7 Pilihan kedelapan",
    "8 Pilihan kesembilan",
    "9 Pilihan kesepuluh"
)

fun courseOptions() = listOf(
    "33 Biofuel Production Technologies",
    "171 Animation and Multimedia Design",
    "8014 Social Service",
    "9003 Agronomy",
    "9070 Communication Design",
    "9085 Veterinary Nursing",
    "9119 Informatics Engineering",
    "9130 Equinculture",
    "9147 Management",
    "9238 Social Service",
    "9254 Tourism",
    "9500 Nursing",
    "9556 Oral Hygiene",
    "9670 Advertising and Marketing",
    "9773 Journalism",
    "9853 Basic Education",
    "9991 Management"
)

fun attendanceOptions() = listOf("0 Malam", "1 Siang")

fun previousQualificationOptions() = listOf(
    "1 Pendidikan menengah",
    "2 Pendidikan dasar",
    "3 Pendidikan dasar",
    "4 Pendidikan menengah",
    "5 Pendidikan tinggi",
    "6 Pendidikan lainnya",
    "9 Pendidikan lainnya",
    "10 Pendidikan menengah",
    "12 Pendidikan tinggi",
    "14 Pendidikan lainnya",
    "15 Pendidikan tinggi",
    "19 Pendidikan lainnya",
    "38 Pendidikan lainnya",
    "39 Pendidikan lainnya",
    "40 Pendidikan lainnya",
    "42 Pendidikan tinggi",
    "43 Pendidikan tinggi"
)

fun motherQualificationOptions() = listOf(
    "1 Pendidikan dasar",
    "2 Pendidikan menengah",
    "3 Pendidikan tinggi",
    "4 Pendidikan tinggi",
    "5 Pendidikan tinggi",
    "6 Pendidikan lainnya",
    "9 Pendidikan lainnya",
    "10 Pendidikan menengah",
    "11 Pendidikan tinggi",
    "12 Pendidikan tinggi",
    "14 Pendidikan tinggi",
    "18 Pendidikan tinggi",
    "19 Pendidikan lainnya",
    "22 Pendidikan tinggi",
    "26 Pendidikan tinggi",
    "27 Pendidikan tinggi",
    "29 Pendidikan lainnya",
    "30 Pendidikan tinggi",
    "34 Pendidikan tinggi",
    "35 Pendidikan tinggi",
    "36 Pendidikan tinggi",
    "37 Pendidikan tinggi",
    "38 Pendidikan tinggi",
    "39 Pendidikan tinggi",
    "40 Pendidikan tinggi",
    "41 Pendidikan tinggi",
    "42 Pendidikan tinggi",
    "43 Pendidikan tinggi",
    "44 Pendidikan tinggi"
)

fun fatherQualificationOptions() = listOf(
    "1 Pendidikan dasar",
    "2 Pendidikan menengah",
    "3 Pendidikan tinggi",
    "4 Pendidikan tinggi",
    "5 Pendidikan tinggi",
    "6 Pendidikan lainnya",
    "9 Pendidikan lainnya",
    "10 Pendidikan menengah",
    "11 Pendidikan tinggi",
    "12 Pendidikan tinggi",
    "13 Pendidikan tinggi",
    "14 Pendidikan tinggi",
    "18 Pendidikan tinggi",
    "19 Pendidikan lainnya",
    "20 Pendidikan tinggi",
    "22 Pendidikan tinggi",
    "25 Pendidikan tinggi",
    "26 Pendidikan tinggi",
    "27 Pendidikan tinggi",
    "29 Pendidikan lainnya",
    "30 Pendidikan tinggi",
    "31 Pendidikan tinggi",
    "33 Pendidikan tinggi",
    "34 Pendidikan tinggi",
    "35 Pendidikan tinggi",
    "36 Pendidikan tinggi",
    "37 Pendidikan tinggi",
    "38 Pendidikan tinggi",
    "39 Pendidikan tinggi",
    "40 Pendidikan tinggi",
    "41 Pendidikan tinggi",
    "42 Pendidikan tinggi",
    "43 Pendidikan tinggi",
    "44 Pendidikan tinggi"
)

fun occupationOptions() = listOf(
    "0 Mahasiswa",
    "1 Manajer / Direktur",
    "2 Spesialis",
    "3 Teknisi / Profesi menengah",
    "4 Staf administrasi",
    "5 Jasa / Keamanan / Penjualan",
    "6 Pertanian / Perikanan / Kehutanan",
    "7 Industri / Konstruksi / Kerajinan",
    "8 Operator mesin / Perakit",
    "9 Pekerja tidak terampil",
    "10 Angkatan bersenjata",
    "90 Situasi lainnya",
    "99 Tidak diketahui / kosong"
)

fun nationalityOptions() = listOf(
    "1 Portugis",
    "2 Jerman",
    "6 Spanyol",
    "11 Italia",
    "13 Belanda",
    "14 Inggris",
    "17 Lithuania",
    "21 Angola",
    "22 Cape Verde",
    "24 Guinea",
    "25 Mozambik",
    "26 Sao Tome dan Principe",
    "32 Turki",
    "41 Brazil",
    "62 Rumania",
    "100 Moldova",
    "101 Meksiko",
    "103 Ukraina",
    "105 Rusia",
    "108 Kuba",
    "109 Kolombia"
)
