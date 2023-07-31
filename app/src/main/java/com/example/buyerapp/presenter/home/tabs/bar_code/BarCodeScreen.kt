package com.example.buyerapp.presenter.home.tabs.bar_code

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.application.navigation.graph.HomeNavGraph
import com.example.buyerapp.core.widget.barcode.BarCodePreview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@HomeNavGraph
@Destination
@Composable
fun BarCodeScreen(navigator: NavigationProvider) {

    val context = LocalContext.current

    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    var isBarCode by rememberSaveable { mutableStateOf(false) }
    var isNfc by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (isBarCode) {
            BarCodePreview(onDetectedBarCode = {
                navigator.openProductInfo(it)
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                isBarCode = false
                isNfc = false
            })
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Button(
                    onClick = {
                        cameraPermissionState.launchPermissionRequest()
                        isBarCode = true
                        isNfc = false
                    },
                    modifier = Modifier
                        .height(52.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = if (isBarCode) Color.White else Color.Black,
                        containerColor = if (isBarCode) Color.Black else Color.LightGray
                    ),
                    shape = RoundedCornerShape(80.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.barcode_botton_nav),
                        contentDescription = "Bar code",
                        colorFilter = if (isBarCode) ColorFilter.tint(Color.White) else ColorFilter.tint(
                            Color.Black
                        ),
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    Text(
                        "Bar code",
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.weight(0.1f))

                Button(
                    onClick = {
                        isBarCode = false
                        isNfc = true
                    },
                    modifier = Modifier
                        .height(52.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.LightGray.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.nfc_btn),
                        contentDescription = "NFC",
                        colorFilter = ColorFilter.tint(
                            Color.Black
                        ),
                        modifier = Modifier.padding(end = 27.dp)
                    )

                    Text(
                        "NFC",
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