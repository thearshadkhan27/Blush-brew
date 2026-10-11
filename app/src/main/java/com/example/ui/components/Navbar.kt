package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextWhite

@Composable
fun CafeNavbar(
    activeSectionIndex: Int,
    onSectionSelected: (Int) -> Unit,
    onOpenReservation: () -> Unit,
    onCallClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navItems = listOf(
        "Home",
        "About Us",
        "Menu",
        "Events",
        "Ingredients",
        "Reservations"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xF0141414),
                        Color(0xD9141414),
                        Color(0x00141414)
                    )
                )
            )
            .statusBarsPadding()
            .padding(top = 10.dp, bottom = 6.dp)
            .testTag("cafe_navbar")
    ) {
        // Top Brand Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Official B&B Logo + Script Brand Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onSectionSelected(0) }
                    .testTag("nav_logo")
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_bnb_official_logo),
                    contentDescription = "Blush & Brew Official Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, AccentCopper.copy(alpha = 0.7f), CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Blush & Brew",
                    fontFamily = DancingScriptFontFamily,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .background(AccentCopper.copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "EN • ₹",
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                        color = AccentCopper,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Quick Actions: Direct Call & Quick Reserve
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onCallClick,
                    modifier = Modifier.testTag("nav_call_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Call Cafe",
                        tint = AccentCopper
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedCafeButton(
                    text = "Book",
                    onClick = onOpenReservation,
                    isAccent = true,
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    ),
                    testTag = "nav_book_button"
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontal Navigation Links with active underline
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEachIndexed { index, title ->
                val isActive = activeSectionIndex == index
                Column(
                    modifier = Modifier
                        .clickable { onSectionSelected(index) }
                        .padding(vertical = 4.dp)
                        .testTag("nav_item_$index"),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        letterSpacing = 1.2.sp,
                        color = if (isActive) TextWhite else TextGrey,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .height(1.5.dp)
                            .width(if (isActive) 24.dp else 0.dp)
                            .background(if (isActive) AccentCopper else Color.Transparent)
                    )
                }
            }
        }
    }
}
