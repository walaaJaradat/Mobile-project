package com.example.mvvm.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.data.local.FavoriteEntity
import com.example.mvvm.ui.details.DarkGray
import com.example.mvvm.ui.details.DarkNavy
import com.example.mvvm.ui.details.Red
import com.example.mvvm.ui.details.SubtitleGray

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onNavigateBack: () -> Unit
) {
    val favorites by viewModel.favorites.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavy)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onNavigateBack) {
                Text("← Back", color = SubtitleGray)
            }
            Text(
                text = "${favorites.size} saved",
                color = SubtitleGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "My Favorites",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            // Empty state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "♡", fontSize = 48.sp, color = SubtitleGray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No favorites yet",
                        color = SubtitleGray,
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(favorites) { favorite ->
                    FavoriteCard(
                        favorite = favorite,
                        onRemove = { viewModel.removeFromFavorites(favorite) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(
    favorite: FavoriteEntity,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = favorite.question,
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "✓ ${favorite.correctAnswer}",
                    color = Color.Green,
                    fontSize = 12.sp
                )
                Text(
                    text = favorite.category,
                    color = SubtitleGray,
                    fontSize = 11.sp
                )
            }
            IconButton(onClick = onRemove) {
                Text(text = "♥", color = Red, fontSize = 20.sp)
            }
        }
    }
}