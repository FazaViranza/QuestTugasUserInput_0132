package com.example.p5_tugas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


import androidx.compose.material3.Checkbox

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.AlertDialog

import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay


@Composable
fun FormBiodata() {

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var tanggalLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var experienceExpanded by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()


    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiUx by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }

    var showSummary by remember { mutableStateOf(false) }

    val isFormValid =
        namaDepan.isNotBlank() &&
                namaBelakang.isNotBlank() &&
                email.isNotBlank() &&
                tanggalLahir.isNotBlank() &&
                experience.isNotBlank() &&
                (skillAndroid || skillUiUx || skillWeb)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Scaffold sebagai dasar halaman
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorResource(R.color.form_white)
        ) { innerPadding ->
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentScale = ContentScale.Crop
            )
        }

        // Panel form mengambang di atas Scaffold
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = dimensionResource(
                            R.dimen.form_corner_radius
                        ),
                        topEnd = dimensionResource(
                            R.dimen.form_corner_radius
                        )
                    )
                )
                .background(colorResource(R.color.form_white))
                .padding(
                    horizontal = dimensionResource(
                        R.dimen.form_horizontal_padding
                    ),
                    vertical = dimensionResource(
                        R.dimen.form_vertical_padding
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.form_title),
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(R.color.form_purple)
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_title_spacing)
                )
            )

            Text(
                text = stringResource(R.string.form_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.form_hint)
            )


            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_field_spacing)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.form_input_spacing)
                )
            ) {
                BiodataTextField(
                    value = namaDepan,
                    onValueChange = { namaDepan = it },
                    placeholder = stringResource(R.string.first_name),
                    modifier = Modifier.weight(1f)
                )

                BiodataTextField(
                    value = namaBelakang,
                    onValueChange = { namaBelakang = it },
                    placeholder = stringResource(R.string.last_name),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_field_spacing)
                )
            )

            BiodataTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_field_spacing)
                )
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.form_input_spacing)
                )
            ) {
                // Tanggal lahir
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { showDatePicker = true }
                ) {
                    OutlinedTextField(
                        value = tanggalLahir,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(R.dimen.form_input_height)),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.birth_date),
                                color = colorResource(R.color.form_hint)
                            )
                        },
                        trailingIcon = {
                            Text(
                                text = stringResource(R.string.calendar_icon),
                                color = colorResource(R.color.form_hint)
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.form_input_corner)
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = colorResource(R.color.form_border),
                            disabledContainerColor = colorResource(R.color.form_white),
                            disabledTextColor = colorResource(R.color.form_text),
                            disabledPlaceholderColor = colorResource(R.color.form_hint),
                            disabledTrailingIconColor = colorResource(R.color.form_hint)
                        )
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        datePickerState.selectedDateMillis?.let { millis ->
                                            val formatter = SimpleDateFormat(
                                                "dd/MM/yyyy",
                                                Locale.getDefault()
                                            )
                                            formatter.timeZone = TimeZone.getTimeZone("UTC")
                                            tanggalLahir = formatter.format(Date(millis))
                                        }
                                        showDatePicker = false
                                    }
                                ) {
                                    Text(stringResource(R.string.date_confirm))
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = { showDatePicker = false }
                                ) {
                                    Text(stringResource(R.string.date_cancel))
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }
                }

                // Experience
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { experienceExpanded = true }
                ) {
                    OutlinedTextField(
                        value = experience,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(R.dimen.form_input_height)),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.experience),
                                color = colorResource(R.color.form_hint)
                            )
                        },
                        trailingIcon = {
                            Text(
                                text = stringResource(R.string.dropdown_arrow),
                                color = colorResource(R.color.form_hint)
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.form_input_corner)
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = colorResource(R.color.form_border),
                            disabledContainerColor = colorResource(R.color.form_white),
                            disabledTextColor = colorResource(R.color.form_text),
                            disabledPlaceholderColor = colorResource(R.color.form_hint),
                            disabledTrailingIconColor = colorResource(R.color.form_hint)
                        )
                    )

                    DropdownMenu(
                        expanded = experienceExpanded,
                        onDismissRequest = { experienceExpanded = false }
                    ) {
                        listOf(
                            stringResource(R.string.experience_beginner),
                            stringResource(R.string.experience_intermediate),
                            stringResource(R.string.experience_advanced)
                        ).forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    experience = option
                                    experienceExpanded = false
                                }
                            )
                        }
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_field_spacing)
                )
            )

            Text(
                text = stringResource(R.string.skills_title),
                color = colorResource(R.color.form_text),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Android
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = skillAndroid,
                        onCheckedChange = { skillAndroid = it }
                    )
                    Text(
                        text = stringResource(R.string.skill_android),
                        color = colorResource(R.color.form_text),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // UI/UX
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = skillUiUx,
                        onCheckedChange = { skillUiUx = it }
                    )
                    Text(
                        text = stringResource(R.string.skill_uiux),
                        color = colorResource(R.color.form_text),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Web
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = skillWeb,
                        onCheckedChange = { skillWeb = it }
                    )
                    Text(
                        text = stringResource(R.string.skill_web),
                        color = colorResource(R.color.form_text),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }








            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.form_field_spacing)
                )
            )

            Button(
                onClick = {
                    // Aksi setelah tombol ditekan
                    showSummary = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(R.dimen.form_button_height)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        if (isFormValid) {
                            R.color.form_purple
                        } else {
                            R.color.form_disabled
                        }
                    )
                ),
                enabled = isFormValid
            ) {
                Text(
                    text = stringResource(R.string.proceed),
                    color = colorResource(R.color.form_white)
                )
            }


            if (showSummary) {
                val selectedSkills = buildList {
                    if (skillAndroid) add("Android")
                    if (skillUiUx) add("UI/UX")
                    if (skillWeb) add("Web")
                }.joinToString(", ")

                LaunchedEffect(showSummary) {
                    delay(5000)
                    showSummary = false
                }

                AlertDialog(
                    onDismissRequest = {
                        showSummary = false
                    },
                    title = {
                        Text("Ringkasan Biodata")
                    },
                    text = {
                        Column {
                            Text("Nama: $namaDepan $namaBelakang")
                            Text("Email: $email")
                            Text("Tanggal Lahir: $tanggalLahir")
                            Text("Experience: $experience")
                            Text("Skill: $selectedSkills")
                        }
                    },
                    confirmButton = {}
                )
            }


        }
            }


        }






@Composable
fun BiodataTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(
            dimensionResource(R.dimen.form_input_height)
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(R.color.form_hint)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.form_input_corner)
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.form_border),
            unfocusedBorderColor = colorResource(R.color.form_border),
            focusedContainerColor = colorResource(R.color.form_white),
            unfocusedContainerColor = colorResource(R.color.form_white)
        )
    )
}

