package com.jefisu.dictionary.feature.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jefisu.dictionary.R
import com.jefisu.dictionary.core.util.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {
    var isLoading by remember { mutableStateOf(false) }
    val animation by animateFloatAsState(targetValue = if (isLoading) 1f else 0f)

    LaunchedEffect(key1 = true) {
        isLoading = true
        delay(200L)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF7C2D8D),
                        Color.Red
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_dictionary),
            contentDescription = "Dictionary icon",
            modifier = Modifier
                .size(150.dp)
                .alpha(animation)
        )
    }
}