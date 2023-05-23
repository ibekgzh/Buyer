package com.example.buyerapp.presenter.confirm_otp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.buyerapp.R

@Composable
fun ConfirmOtpView(
    modifier: Modifier = Modifier,
    pinCount: Int = 4,
    onInputPinCompleted: (pin: String) -> Unit
) {

    var pinValue by rememberSaveable { mutableStateOf("") }
    var isCompleted by rememberSaveable { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
        value = TextFieldValue(pinValue, selection = TextRange(pinValue.length)),
        onValueChange = {
            if (it.text.length <= pinCount) {
                pinValue = it.text
                if (it.text.length == pinCount && !isCompleted) {
                    isCompleted = true
                    onInputPinCompleted(it.text)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                repeat(pinCount) { index ->
                    CharView(
                        index = index,
                        text = pinValue
                    )
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(75.dp)
            .height(52.dp)
            .border(
                1.dp, when {
                    isFocused -> colorResource(id = R.color.edt_phone)
                    else -> colorResource(id = R.color.pin_code_background)
                }, RoundedCornerShape(80.dp)
            )
            .background(
                if (isFocused) {
                    Color.White
                } else {
                    colorResource(id = R.color.pin_code_background)
                }, shape = RoundedCornerShape(80.dp)
            )
            .padding(0.dp, 15.dp),
        text = char,
        color = if (isFocused) {
            Color.LightGray
        } else {
            Color.Gray
        },
        textAlign = TextAlign.Center
    )
}