package com.example.buyerapp.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurfaceTopToolBarBack(
    onOnclickBackButton: () -> Unit,
    title: String = "",
    onClickCommonButton: () -> Unit = {},
    onShowCommonButton: Boolean = false,
    pageContent: @Composable () -> Unit,
) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                pageContent()
            }
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(colorResource(id = R.color.back_button_background))
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onOnclickBackButton),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = "Arrow right",
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    title,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W700,
                    fontSize = 15.sp,
                    lineHeight = 30.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                if (onShowCommonButton) {
                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(colorResource(id = R.color.back_button_background))
                            .align(Alignment.TopEnd)
                            .clickable(onClick = onClickCommonButton),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.filter),
                            contentDescription = "Filter",
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            }
        },
        contentColor = Color.White,
        containerColor = Color.White
    )
}