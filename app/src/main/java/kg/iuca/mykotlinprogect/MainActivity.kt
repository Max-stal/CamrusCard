package kg.iuca.mykotlinprogect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CampusCardScreen()
                }
            }
        }
    }
}

// Exercise 5
@Composable
fun ProfileHeader(name: String, status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = status, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Exercise 3, 4, 5
@Composable
fun ScheduleRow(
    time: String,
    title: String,
    room: String,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically // Для Упражнения 3
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Увеличенный размер шрифта для времени
        Text(
            text = time,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        // Exercise 4: Modifier.weight(1f) позволяет названию занимать всё свободное место
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = room,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CampusCardScreen() {
    // Exercise 2
    var useSpaceBetween by remember { mutableStateOf(true) }

    // Exercise 6:
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        // Верхний блок с заголовком и расписанием
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            // Exercise 1: Центрированный блок заголовка
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Campus Card", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Student ID: 2284", style = MaterialTheme.typography.labelLarge)
            }

            HorizontalDivider()

            // Exercise 5:
            ProfileHeader(name = "Stalmakov Maksim", status = "In class · Building B")

            Text(
                text = "Today",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Список занятий
            Column {
                // Exercise 3 и 4
                ScheduleRow(
                    time = "09:30",
                    title = "CS101 Intro to Computer Science & Software Engineering Fundamentals",
                    room = "B 305",
                    verticalAlignment = Alignment.CenterVertically
                )
                ScheduleRow(time = "11:05", title = "MATH210 Linear Algebra", room = "B 204")
                ScheduleRow(time = "14:45", title = "Soft Skills & Leadership", room = "B 001")
            }
        }

        // Exercise 2: Нижний блок кнопок с тестированием расположения
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кнопка переключения режимов для теста
            TextButton(onClick = { useSpaceBetween = !useSpaceBetween }) {
                Text("Toggle Layout: ${if (useSpaceBetween) "SpaceBetween" else "Center"}")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (useSpaceBetween) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.Center
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (useSpaceBetween) {
                    Button(onClick = { }) { Text("Map") }
                    OutlinedButton(onClick = { }) { Text("Show QR") }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(onClick = { }) { Text("Map") }
                        OutlinedButton(onClick = { }) { Text("Show QR") }
                    }
                }
            }
        }
    }
}