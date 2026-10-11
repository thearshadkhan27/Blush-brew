package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.CafeEvent
import com.example.model.TableReservation
import com.example.ui.theme.AccentBlush
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardElevated
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReservationDialog(
    selectedEvent: CafeEvent? = null,
    onDismiss: () -> Unit,
    onSubmit: (name: String, phone: String, date: String, time: String, guests: Int, area: String, note: String) -> TableReservation
) {
    var guestName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(selectedEvent?.date ?: "Today") }
    var selectedTime by remember { mutableStateOf(selectedEvent?.time ?: "7:00 PM") }
    var guestCount by remember { mutableIntStateOf(2) }
    var seatingArea by remember { mutableStateOf("Window Aesthetic Table") }
    var specialNote by remember { mutableStateOf("") }
    var confirmedReservation by remember { mutableStateOf<TableReservation?>(null) }
    var errorText by remember { mutableStateOf<String?>(null) }

    val dateOptions = listOf("Today", "Tomorrow", "Friday", "Saturday", "Sunday")
    val timeOptions = listOf("11:30 AM", "1:30 PM", "4:30 PM", "6:00 PM", "7:30 PM", "9:00 PM")
    val seatingOptions = listOf(
        "Window Aesthetic Table",
        "Velvet Lounge Corner",
        "Open Bar Stool",
        "Outdoor Garden Patio"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .background(BgNearBlack, RoundedCornerShape(12.dp))
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                .padding(24.dp)
                .testTag("reservation_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header with close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Reservation",
                            fontFamily = DancingScriptFontFamily,
                            fontSize = 24.sp,
                            color = AccentCopper
                        )
                        Text(
                            text = if (selectedEvent != null) "Reserve for Event" else "Book Your Table",
                            fontFamily = PlayfairFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = TextWhite
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("dialog_close_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextMuted
                        )
                    }
                }

                if (selectedEvent != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AccentCopper.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Event: ${selectedEvent.title} (${selectedEvent.date})",
                            fontSize = 13.sp,
                            color = AccentCopper,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (confirmedReservation != null) {
                    // Confirmed Success State
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Image(
                                painter = painterResource(id = R.drawable.img_bnb_official_logo),
                                contentDescription = "Blush & Brew Emblem",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, AccentCopper, CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .offset(x = 4.dp, y = 4.dp)
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(AccentCopper),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Confirmed",
                                    tint = BgNearBlack,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "Table Reserved!",
                            fontFamily = PlayfairFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Booking Reference: ${confirmedReservation?.id}",
                            fontSize = 13.sp,
                            color = AccentCopper,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Card(
                            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Guest: ${confirmedReservation?.guestName}",
                                    color = TextWhite,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Date & Time: ${confirmedReservation?.date} at ${confirmedReservation?.timeSlot}",
                                    color = TextGrey,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Party Size: ${confirmedReservation?.guestCount} Guests (${confirmedReservation?.seatingArea})",
                                    color = TextGrey,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Location: Vijay Colony, Amravati",
                                    color = TextMuted,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedCafeButton(
                            text = "Done",
                            onClick = onDismiss,
                            modifier = Modifier.fillMaxWidth(),
                            testTag = "booking_done_btn"
                        )
                    }
                } else {
                    // Reservation Form Fields
                    Text(
                        text = "1. SELECT DATE",
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        dateOptions.forEach { date ->
                            val isSelected = selectedDate == date
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) AccentCopper else Color.White.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .background(
                                        if (isSelected) AccentCopper.copy(alpha = 0.2f) else Color.Transparent
                                    )
                                    .clickable { selectedDate = date }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = date,
                                    color = if (isSelected) AccentCopper else TextWhite,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "2. TIME SLOT",
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        timeOptions.forEach { time ->
                            val isSelected = selectedTime == time
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) AccentCopper else Color.White.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .background(
                                        if (isSelected) AccentCopper.copy(alpha = 0.2f) else Color.Transparent
                                    )
                                    .clickable { selectedTime = time }
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = time,
                                    color = if (isSelected) AccentCopper else TextWhite,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "3. NUMBER OF GUESTS",
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        (1..6).forEach { count ->
                            val isSelected = guestCount == count
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) AccentCopper else Color.White.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .background(
                                        if (isSelected) AccentCopper.copy(alpha = 0.2f) else Color.Transparent
                                    )
                                    .clickable { guestCount = count }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "$count",
                                    color = if (isSelected) AccentCopper else TextWhite,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "4. SEATING PREFERENCE",
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        seatingOptions.forEach { area ->
                            val isSelected = seatingArea == area
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp,
                                        if (isSelected) AccentCopper else Color.White.copy(alpha = 0.15f),
                                        RoundedCornerShape(4.dp)
                                    )
                                    .background(
                                        if (isSelected) AccentCopper.copy(alpha = 0.12f) else Color.Transparent
                                    )
                                    .clickable { seatingArea = area }
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = area,
                                    color = if (isSelected) AccentCopper else TextWhite,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "5. CONTACT DETAILS",
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = guestName,
                        onValueChange = { guestName = it },
                        label = { Text("Your Name") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_guest_name"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            focusedBorderColor = AccentCopper,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                            focusedLabelColor = AccentCopper,
                            unfocusedLabelColor = TextMuted
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Phone Number") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_phone_number"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            focusedBorderColor = AccentCopper,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                            focusedLabelColor = AccentCopper,
                            unfocusedLabelColor = TextMuted
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = specialNote,
                        onValueChange = { specialNote = it },
                        label = { Text("Special Request (e.g. date night, quiet spot)") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_special_request"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            focusedBorderColor = AccentCopper,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                            focusedLabelColor = AccentCopper,
                            unfocusedLabelColor = TextMuted
                        )
                    )

                    if (errorText != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorText ?: "",
                            color = AccentBlush,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedCafeButton(
                        text = "Confirm Reservation",
                        onClick = {
                            if (guestName.isBlank()) {
                                errorText = "Please enter your name"
                            } else if (phoneNumber.isBlank() || phoneNumber.length < 6) {
                                errorText = "Please enter a valid phone number"
                            } else {
                                errorText = null
                                confirmedReservation = onSubmit(
                                    guestName,
                                    phoneNumber,
                                    selectedDate,
                                    selectedTime,
                                    guestCount,
                                    seatingArea,
                                    specialNote
                                )
                            }
                        },
                        isAccent = true,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "submit_reservation_btn"
                    )
                }
            }
        }
    }
}
