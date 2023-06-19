package com.example.buyerapp.presenter.onboarding.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.OnBoardingItem
import com.example.buyerapp.presenter.onboarding.OnBoardingViewState
import java.lang.Float

@Composable
fun OnBoardingContent(
    data: OnBoardingViewState,
    onSignIn: () -> Unit,
    imageLoader: ImageLoader
) {

    data.items?.let {
        ItemsPager(
            items = data.items,
            imageLoader = imageLoader,
            onClick = onSignIn
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsPager(
    items: List<OnBoardingItem>,
    imageLoader: ImageLoader,
    onClick: () -> Unit
) {
    val count = items.size;
    val state = rememberPagerState();

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            pageCount = count,
            state = state
        ) {

            val painter = rememberAsyncImagePainter(items[it].image, imageLoader)
            val imgState = painter.state

            val transition by animateFloatAsState(
                targetValue = if (imgState is AsyncImagePainter.State.Success) 1f else 0f
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "On boarding image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 50.dp, 0.dp, 0.dp)
                        .scale(.8f + (.2f * transition))
                        .graphicsLayer { rotationX = (1f - transition) * 5f }
                        .alpha(Float.min(1f, transition / .2f))
                )

                Text(
                    text = items[it].title.uppercase(),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 31.dp, 0.dp, 0.dp),
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W800,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    color = colorResource(id = R.color.black1),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = items[it].description,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 16.dp, 0.dp, 0.dp),
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    color = colorResource(id = R.color.black2),
                    textAlign = TextAlign.Center
                )
            }

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(count) { iteration ->
                val color =
                    if (state.currentPage == iteration) Color.Black else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color)
                        .size(height = 4.dp, width = 65.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .size(175.dp, 52.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(80.dp)

            ) {
                Text(text = "Войти")
            }
        }
    }
}
