package com.example.buyerapp.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.buyerapp.R

@Composable
fun BackButton(action: () -> Unit) {
    Row(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorResource(id = R.color.back_button_background))
            .clickable(onClick = action),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.arrow_right),
            contentDescription = "Arrow right",
            contentScale = ContentScale.Crop
        )
    }
}