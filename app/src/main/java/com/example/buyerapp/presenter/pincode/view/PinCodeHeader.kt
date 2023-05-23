package com.example.buyerapp.presenter.pincode.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun PinCodeHeader(fullName: String?, onLogout: () -> Unit, onClose: () -> Unit) {

    Box(Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {
                onClose()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black
            ),
        ) {
            Icon(Icons.Filled.Close, contentDescription = "ArrowBack")
        }

        fullName?.let {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 150.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(start = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile_black),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(end = 18.dp)
                    )
                    Text(
                        text = fullName,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        color = colorResource(
                            id = R.color.black2
                        )
                    )
                }

                Button(
                    onClick = {
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        painterResource(id = R.drawable.logout),
                        contentDescription = "Выйти"
                    )
                }
            }
        }
    }
}