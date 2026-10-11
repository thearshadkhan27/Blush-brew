package com.example.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgDeep
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun FooterSection(
    onDirectionsClick: () -> Unit,
    onCallClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BgDeep)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.08f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(24.dp)
            .navigationBarsPadding()
            .testTag("footer_section")
    ) {
        // 3-Column / Sections Layout

        // Column 1: Location
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Location",
                fontFamily = DancingScriptFontFamily,
                fontSize = 24.sp,
                color = AccentCopper,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "XQ94+6CF, Vijay Colony, Pote College Road\nNear Orchid Bakery, Amravati\nMaharashtra 444604",
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = TextGrey
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onDirectionsClick() }
                    .padding(vertical = 4.dp)
                    .testTag("footer_directions_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = AccentCopper,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Open In Maps →",
                    fontSize = 12.sp,
                    color = AccentCopper,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.08f))
        Spacer(modifier = Modifier.height(20.dp))

        // Column 2: Working Hours
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Working Hours",
                fontFamily = DancingScriptFontFamily,
                fontSize = 24.sp,
                color = AccentCopper,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Monday – Thursday", fontSize = 13.sp, color = TextWhite)
                Text(text = "9:00 am – 11:00 pm", fontSize = 13.sp, color = TextGrey)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Friday", fontSize = 13.sp, color = TextWhite)
                Text(text = "9:00 am – 11:30 pm", fontSize = 13.sp, color = TextGrey)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Saturday – Sunday", fontSize = 13.sp, color = TextWhite)
                Text(text = "9:00 am – 11:30 pm", fontSize = 13.sp, color = TextGrey)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.08f))
        Spacer(modifier = Modifier.height(20.dp))

        // Column 3: Brand & Contact with Official Logo
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_bnb_official_logo),
                    contentDescription = "Blush & Brew Official Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, AccentCopper, CircleShape)
                )

                Column {
                    Text(
                        text = "Blush & Brew",
                        fontFamily = DancingScriptFontFamily,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = "Sip the blush. Brew the love ☕",
                        fontSize = 12.sp,
                        color = AccentCopper,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Phone
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onCallClick() }
                    .padding(vertical = 4.dp)
                    .testTag("footer_phone_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = null,
                    tint = TextGrey,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "+91 7387312529",
                    fontSize = 13.sp,
                    color = TextWhite
                )
            }

            // Instagram & Email
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = TextGrey,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "blushandbrew.cafe@gmail.com",
                    fontSize = 13.sp,
                    color = TextWhite
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onShareClick() }
                    .padding(vertical = 4.dp)
                    .testTag("footer_share_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = null,
                    tint = AccentCopper,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Share Cafe With Friends",
                    fontSize = 12.sp,
                    color = AccentCopper,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom Copyright
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "© 2026 Blush & Brew Café. All rights reserved.",
                fontSize = 11.sp,
                color = TextMuted
            )
        }
    }
}
