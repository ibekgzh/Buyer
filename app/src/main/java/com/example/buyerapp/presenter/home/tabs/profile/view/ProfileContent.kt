package com.example.buyerapp.presenter.home.tabs.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.domain.model.longname
import com.example.buyerapp.presenter.home.tabs.profile.ProfileViewState

@Composable
fun ProfileContent(
    data: ProfileViewState,
    onClickPersonalInfo: () -> Unit,
    onClickPasswordSafety: () -> Unit,
    onClickLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            "Профиль",
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W800,
                            fontSize = 32.sp,
                            lineHeight = 30.sp,
                        )

                        Text(
                            data.userInfo.longname(),
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            modifier = Modifier
                                .padding(0.dp, 7.dp, 0.dp, 0.dp),
                            color = colorResource(
                                id = R.color.client_fio_color
                            )
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.profile_black),
                        contentDescription = "Profile image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }

                Divider(
                    color = colorResource(id = R.color.gray2),
                    thickness = 1.dp,
                    modifier = Modifier.padding(0.dp, 48.dp, 0.dp, 20.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .clickable {
                            onClickPersonalInfo()
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Личная информация",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = colorResource(
                            id = R.color.black2
                        )
                    )

                    Image(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = "Arrow left",
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 35.dp, 0.dp, 0.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .clickable {
                            onClickPasswordSafety()
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Пароль и безопасность",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        color = colorResource(
                            id = R.color.black2
                        )
                    )

                    Image(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = "Arrow left",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    onClick = { onClickLogout() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(80.dp)
                ) {
                    Text(
                        "Выйти",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 14.sp,
                        lineHeight = 30.sp,
                    )
                }
            }
        }
    }
}