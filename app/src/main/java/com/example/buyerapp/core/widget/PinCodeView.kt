package com.example.buyerapp.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R

@Composable
fun PinCodeView(
    length: Int,
    title: String,
    onInputComplete: (pin: String) -> Unit,
    onForgetPinClick: () -> Unit,
    onBiometricPrompt: () -> Unit,
) {

    val pinCodes = rememberMutableStateListOf<String>()

    val table: Array<Array<Int>> = Array(4, { Array(4, { 0 }) })
    table[0] = arrayOf(1, 2, 3)
    table[1] = arrayOf(4, 5, 6)
    table[2] = arrayOf(7, 8, 9)
    table[3] = arrayOf(10, 0, 11)

    Column(
        content = {

            Text(
                text = title,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                fontSize = 15.sp,
                lineHeight = 15.sp,
                color = colorResource(id = R.color.black2),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    21.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                for (i in 0 until length) {
                    if (i < pinCodes.size) {
                        Dot(Color.Black)
                    } else {
                        Dot(Color.LightGray)
                    }
                }
            }

            Digits(table, pinCodes, length, onInputComplete, onBiometricPrompt)

            Text(
                text = "Забыли код?",
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                fontSize = 15.sp,
                lineHeight = 15.sp,
                color = colorResource(id = R.color.black2),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
                    .clickable {
                        onForgetPinClick()
                    }
            )

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(Color.White)
            .padding(70.dp, 37.dp, 70.dp, 0.dp),
    )
}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                if (stateList.isNotEmpty()) {
                    val first = stateList.first()
                    if (!canBeSaved(first)) {
                        throw IllegalStateException("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                stateList.toList()
            },
            restore = { it.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}

@Composable
fun Digits(
    table: Array<Array<Int>>,
    pinCodes: SnapshotStateList<String>,
    length: Int,
    onInputComplete: (pin: String) -> Unit,
    onBiometricPrompt: () -> Unit
) {
    for (indexA in table) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (indexB in indexA) {

                if (indexB == 10) {
                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                onBiometricPrompt()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.faceid),
                            contentDescription = "Remove pin code",
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                if (indexB == 11) {
                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                if (pinCodes.size != 0) {
                                    pinCodes.remove(
                                        pinCodes.elementAt(
                                            pinCodes.lastIndex
                                        )
                                    )
                                }
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.remove_box),
                            contentDescription = "Remove pin code",
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                if (indexB != 10 && indexB != 11) {

                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                if (pinCodes.size < length) {
                                    pinCodes.add(indexB.toString())
                                    if (pinCodes.size == length) {
                                        onInputComplete(pinCodes.joinToString(""))
                                        pinCodes.clear()
                                    }
                                }
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = indexB.toString(),
                            fontFamily = FontFamily.Default,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W500,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            color = colorResource(id = R.color.black2),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Dot(
    color: Color,
) {
    Box(
        modifier = Modifier
            .requiredSize(
                size = 12.dp,
            )
            .background(
                color = color,
                shape = CircleShape,
            ),
    )
}