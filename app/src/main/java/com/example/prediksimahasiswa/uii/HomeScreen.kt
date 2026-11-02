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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ================================================================
// WARNA
// ================================================================

private val PrimaryBlue = Color(0xFF2563EB)
private val DarkBlue = Color(0xFF1E3A8A)
private val LightBlue = Color(0xFFEFF6FF)
private val SuccessGreen = Color(0xFF16A34A)
private val TextDark = Color(0xFF1E293B)
private val TextGray = Color(0xFF64748B)


// ================================================================
// HOME SCREEN
// ================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartPrediction: () -> Unit
) {

    Scaffold(

        containerColor = Color(0xFFF8FAFC),

        topBar = {

            TopAppBar(

                title = {

                    Column {

                        Text(
                            text = "Prediksi Akademik",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Sistem Prediksi Mahasiswa",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = Color.White
                )
            )
        }


) { innerPadding ->

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // ====================================================
        // WELCOME CARD
        // ====================================================

        WelcomeCard()


        // ====================================================
        // BUTTON MULAI PREDIKSI
        // ====================================================

        Button(

            onClick = onStartPrediction,

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue
            ),

            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 3.dp
            )
        ) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column(
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "Mulai Prediksi",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Masukkan data mahasiswa",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null
            )
        }


        // ====================================================
        // INFORMASI MODEL
        // ====================================================

        Text(
            text = "Informasi Model",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )

        Text(
            text = "Ringkasan model machine learning yang digunakan",
            fontSize = 12.sp,
            color = TextGray
        )

        ModelInformationCard()


        // ====================================================
        // CARA KERJA SISTEM
        // ====================================================

        Text(
            text = "Cara Kerja Sistem",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark,
            modifier = Modifier.padding(top = 4.dp)
        )

        HowItWorksCard()


        // ====================================================
        // KATEGORI PREDIKSI
        // ====================================================

        Text(
            text = "Kategori Prediksi",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextDark,
            modifier = Modifier.padding(top = 4.dp)
        )

        PredictionCategoriesCard()


        // ====================================================
        // TENTANG SISTEM
        // ====================================================

        AboutSystemCard()

        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }
}
}


// ================================================================
// WELCOME CARD
// ================================================================

@Composable
fun WelcomeCard() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(

                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(
                            Color.White.copy(alpha = 0.16f)
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                Column {

                    Text(
                        text = "Selamat Datang 👋",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Sistem Prediksi Akademik",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Prediksi Risiko Dropout",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "dan Keberhasilan Akademik",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Sistem menggunakan algoritma Random Forest untuk menganalisis data mahasiswa dan memberikan prediksi status akademik.",
                color = Color.White.copy(alpha = 0.82f),
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
        }
    }
}


// ================================================================
// INFORMASI MODEL
// ================================================================

@Composable
fun ModelInformationCard() {

    Column(

        modifier = Modifier.fillMaxWidth(),

        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            ModelStatCard(

                modifier = Modifier.weight(1f),

                value = "RF",

                label = "Random Forest",

                icon = Icons.Default.Star,

                iconColor = PrimaryBlue
            )

            ModelStatCard(

                modifier = Modifier.weight(1f),

                value = "77,40%",

                label = "Akurasi",

                icon = Icons.Default.CheckCircle,

                iconColor = SuccessGreen
            )
        }


        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            ModelStatCard(

                modifier = Modifier.weight(1f),

                value = "36",

                label = "Fitur Input",

                icon = Icons.Default.Info,

                iconColor = PrimaryBlue
            )

            ModelStatCard(

                modifier = Modifier.weight(1f),

                value = "3",

                label = "Kelas Prediksi",

                icon = Icons.Default.Info,

                iconColor = DarkBlue
            )
        }
    }
}


// ================================================================
// MODEL STAT CARD
// ================================================================

@Composable
fun ModelStatCard(

    modifier: Modifier,

    value: String,

    label: String,

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    iconColor: Color

) {

    Card(

        modifier = modifier,

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Box(

                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                        iconColor.copy(alpha = 0.1f)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint = iconColor,

                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(

                text = value,

                fontSize = 19.sp,

                fontWeight = FontWeight.ExtraBold,

                color = TextDark
            )

            Text(

                text = label,

                fontSize = 11.sp,

                color = TextGray
            )
        }
    }
}


// ================================================================
// CARA KERJA SISTEM
// ================================================================

@Composable
fun HowItWorksCard() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            ProcessStep(

                number = "1",

                title = "Masukkan Data",

                description = "Isi data pribadi, pendaftaran, keluarga, akademik, dan indikator ekonomi."
            )

            ProcessLine()

            ProcessStep(

                number = "2",

                title = "Data Dikirim ke Server",

                description = "Aplikasi mengirimkan 36 fitur mahasiswa ke backend FastAPI."
            )

            ProcessLine()

            ProcessStep(

                number = "3",

                title = "Random Forest Menganalisis",

                description = "Model machine learning memproses data dan menentukan kelas prediksi."
            )

            ProcessLine()

            ProcessStep(

                number = "4",

                title = "Hasil Prediksi",

                description = "Sistem menampilkan Dropout, Enrolled, atau Graduate beserta probabilitasnya."
            )
        }
    }
}


// ================================================================
// PROCESS STEP
// ================================================================

@Composable
fun ProcessStep(

    number: String,

    title: String,

    description: String

) {

    Row(
        verticalAlignment = Alignment.Top
    ) {

        Box(

            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(PrimaryBlue),

            contentAlignment = Alignment.Center
        ) {

            Text(

                text = number,

                color = Color.White,

                fontSize = 14.sp,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(

                text = title,

                fontSize = 14.sp,

                fontWeight = FontWeight.Bold,

                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(

                text = description,

                fontSize = 11.sp,

                color = TextGray,

                lineHeight = 17.sp
            )
        }
    }
}


// ================================================================
// PROCESS LINE
// ================================================================

@Composable
fun ProcessLine() {

    Box(

        modifier = Modifier
            .padding(start = 17.dp)
            .width(2.dp)
            .height(18.dp)
            .background(
                Color(0xFFD6E4FF)
            )
    )
}


// ================================================================
// KATEGORI PREDIKSI
// ================================================================

@Composable
fun PredictionCategoriesCard() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(

            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            PredictionCategoryItem(

                title = "Dropout",

                description = "Mahasiswa diprediksi memiliki risiko tidak menyelesaikan pendidikan.",

                background = Color(0xFFFFF1F2),

                iconColor = Color(0xFFDC2626)
            )

            PredictionCategoryItem(

                title = "Enrolled",

                description = "Mahasiswa diprediksi masih berada dalam status terdaftar.",

                background = Color(0xFFFFFBEB),

                iconColor = Color(0xFFF59E0B)
            )

            PredictionCategoryItem(

                title = "Graduate",

                description = "Mahasiswa diprediksi memiliki kecenderungan menyelesaikan pendidikan.",

                background = Color(0xFFF0FDF4),

                iconColor = Color(0xFF16A34A)
            )
        }
    }
}


// ================================================================
// PREDICTION CATEGORY ITEM
// ================================================================

@Composable
fun PredictionCategoryItem(

    title: String,

    description: String,

    background: Color,

    iconColor: Color

) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(background)
            .padding(13.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(

            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(
                    iconColor.copy(alpha = 0.12f)
                ),

            contentAlignment = Alignment.Center
        ) {

            Icon(

                imageVector = Icons.Default.CheckCircle,

                contentDescription = null,

                tint = iconColor,

                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(11.dp)
        )

        Column {

            Text(

                text = title,

                fontSize = 14.sp,

                fontWeight = FontWeight.Bold,

                color = TextDark
            )

            Text(

                text = description,

                fontSize = 11.sp,

                color = TextGray,

                lineHeight = 16.sp
            )
        }
    }
}


// ================================================================
// TENTANG SISTEM
// ================================================================

@Composable
fun AboutSystemCard() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(

                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(

                        imageVector = Icons.Default.Info,

                        contentDescription = null,

                        tint = PrimaryBlue,

                        modifier = Modifier.size(21.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text = "Tentang Sistem",

                    fontSize = 16.sp,

                    fontWeight = FontWeight.Bold,

                    color = DarkBlue
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = "Aplikasi ini merupakan sistem prediksi akademik yang memanfaatkan algoritma Random Forest untuk mengklasifikasikan status mahasiswa ke dalam tiga kategori, yaitu Dropout, Enrolled, dan Graduate.",

                fontSize = 12.sp,

                color = TextGray,

                lineHeight = 18.sp
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = "Hasil prediksi digunakan sebagai informasi pendukung dan bukan sebagai keputusan akademik final.",

                fontSize = 11.sp,

                color = TextGray,

                lineHeight = 16.sp,

                fontWeight = FontWeight.Medium
            )
        }
    }
}