package com.example.buyerapp.presenter.personal_info.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyerapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PersonalInfoContent(
    firstname: String,
    lastname: String,
    cellphone: String,
    onClickSave: (longname: String) -> Unit
) {
    val fullName = remember { mutableStateOf(firstname + " " + lastname) }
    val phone = remember { mutableStateOf(cellphone) }
    val editFullName = remember { mutableStateOf(false) }
    val editPhone = remember { mutableStateOf(false) }
    val (focusedFullName, focusedPhone) = remember { FocusRequester.createRefs() }

    val maxCharFullName = 30
    val maxCharPhone = 12

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.gray2),
                        shape = RoundedCornerShape(80.dp),
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = fullName.value,
                        onValueChange = { newText ->
                            if (newText.length <= maxCharFullName) fullName.value = newText
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Gray,
                            disabledTextColor = Color.Gray,
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                        ),
                        enabled = editFullName.value,
                        modifier = Modifier.focusRequester(focusedFullName)
                    )
                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                editFullName.value = true
                                focusedFullName.requestFocus()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Edit",
                            contentScale = ContentScale.Crop

                        )
                    }
                }
            }

            Text(
                text = "${fullName.value.length} / $maxCharFullName",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, end = 70.dp, bottom = 20.dp)
            )

            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.gray2),
                        shape = RoundedCornerShape(80.dp),
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    TextField(
                        value = phone.value,
                        onValueChange = { newText ->
                            if (newText.length <= maxCharPhone) phone.value = newText
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Gray,
                            disabledTextColor = Color.Gray,
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = true,
                            keyboardType = KeyboardType.Phone,
                        ),
                        enabled = editPhone.value,
                        modifier = Modifier.focusRequester(focusedPhone)
                    )
                    Row(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                editPhone.value = true
                                focusedPhone.requestFocus()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Edit",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Text(
                text = "${phone.value.length} / $maxCharPhone",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, end = 70.dp, bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { onClickSave(fullName.value) },
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
                        "Сохранить",
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