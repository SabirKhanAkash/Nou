package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuBox(heading: String, leadingIcon: Int, items: Int) {
    var isMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedMenuItem by rememberSaveable { mutableStateOf("") }
    val menuItems = stringArrayResource(id = items)
    ExposedDropdownMenuBox(expanded = isMenuExpanded,
        onExpandedChange = { isMenuExpanded = !isMenuExpanded }) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedMenuItem,
            onValueChange = { selectedMenuItem = it },
            readOnly = true,
            label = {
                Text(
                    text = heading,
                    fontFamily = Constant().balooda2font,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isMenuExpanded
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "item_type_icon"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        if (menuItems.isNotEmpty()) {
            ExposedDropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Constant().small_light_gray),
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                menuItems.forEach { option ->
                    DropdownMenuItem(enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Constant().small_light_gray),
                        text = {
                            Text(
                                option,
                                fontFamily = Constant().balooda2font,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            selectedMenuItem = option
                            isMenuExpanded = false
                        })
                }
            }
        }
    }
}