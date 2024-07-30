package com.akash.nou.view.feature.homepage.composable

import Constant
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akash.nou.dto.DropDownMenuDTO
import com.akash.nou.viewmodel.TicketViewModel
import com.akash.nou.viewmodelfactory.TicketViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    dropDownType: String,
    dropDownMenuDTO: DropDownMenuDTO,
    ticketViewModel: TicketViewModel = viewModel(factory = TicketViewModelFactory())
) {
    val _selectedSeatType by ticketViewModel.selectedSeatType.observeAsState(initial = "")
    val _selectedSource by ticketViewModel.selectedSource.observeAsState(initial = "")
    val _selectedDestination by ticketViewModel.selectedDestination.observeAsState(initial = "")
    var isMenuExpanded by rememberSaveable { mutableStateOf(false) }
    val menuItems = stringArrayResource(id = dropDownMenuDTO.items)
    ExposedDropdownMenuBox(modifier = Modifier.fillMaxWidth(),
        expanded = isMenuExpanded,
        onExpandedChange = { isMenuExpanded = !isMenuExpanded }) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = when (dropDownType) {
                "seatType" -> {
                    _selectedSeatType
                }

                "source" -> {
                    _selectedSource
                }

                "destination" -> {
                    _selectedDestination
                }

                else -> {
                    _selectedSeatType
                }
            },
            onValueChange = { ticketViewModel.setDropDownItem(it, dropDownType) },
            readOnly = true,
            label = {
                Text(
                    text = dropDownMenuDTO.heading,
                    fontFamily = Constant().balooda2font,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isMenuExpanded,
                )
            },
            leadingIcon = {
                Icon(
                    tint = Constant().app_theme_color,
                    painter = painterResource(id = dropDownMenuDTO.leadingIcon),
                    contentDescription = "item_type_icon"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(12.dp)
        )

        if (menuItems.isNotEmpty()) {
            ExposedDropdownMenu(modifier = Modifier
                .fillMaxWidth(1.0f)
                .background(Constant().small_light_gray),
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }) {
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
                            ticketViewModel.setDropDownItem(option, dropDownType)
                            isMenuExpanded = false
                        })
                }
            }
        }
    }
}