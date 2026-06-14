package com.example.mvvm.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.data.local.FavoriteEntity
import androidx.compose.foundation.layout.statusBarsPadding

val DarkNavy = Color(0xFF050B14)
val DarkGray = Color(0xFF141414)
val Red = Color(0xFFE50914)
val SubtitleGray = Color(0xFF9AA7B8)

@Composable
fun DetailsScreen(
    question: String,
    correctAnswer: String,
    userAnswer: String,
    isCorrect: Boolean,
    category: String,
    onAddToFavorites: (FavoriteEntity) -> Unit,
    onNavigateBack: () -> Unit
) {
    var isFavorited by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavy)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onNavigateBack,
                colors = ButtonDefaults.buttonColors(containerColor = DarkGray),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Text("← Back", color = SubtitleGray, fontSize = 14.sp)
            }
            Text(
                text = category,
                color = SubtitleGray,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Question Details",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Question Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Question",
                    color = SubtitleGray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = question,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Correct Answer Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Correct Answer", color = SubtitleGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "✓ $correctAnswer", color = Color.Green, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // User Answer Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Your Answer", color = SubtitleGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${if (isCorrect) "✓" else "✗"} $userAnswer",
                    color = if (isCorrect) Color.Green else Red,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Favorite Button
        Button(
            onClick = {
                isFavorited = !isFavorited
                if (isFavorited) {
                    onAddToFavorites(
                        FavoriteEntity(
                            question = question,
                            correctAnswer = correctAnswer,
                            userAnswer = userAnswer,
                            isCorrect = isCorrect,
                            category = category
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFavorited) DarkGray else Red
            )
        ) {
            Text(
                text = if (isFavorited) "♥ Saved to Favorites" else "♡ Add to Favorites",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}