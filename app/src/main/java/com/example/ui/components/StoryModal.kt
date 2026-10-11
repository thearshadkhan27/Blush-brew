package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun StoryModal(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .background(BgNearBlack, RoundedCornerShape(12.dp))
                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                .padding(24.dp)
                .testTag("story_modal")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Discover",
                            fontFamily = DancingScriptFontFamily,
                            fontSize = 24.sp,
                            color = AccentCopper
                        )
                        Text(
                            text = "The Story of Blush & Brew",
                            fontFamily = PlayfairFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = TextWhite
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("story_close_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Image(
                    painter = painterResource(id = R.drawable.img_cafe_interior),
                    contentDescription = "Blush & Brew Atmosphere",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "A Sanctuary for Coffee & Soul in Amravati",
                    fontFamily = PlayfairFontFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AccentCopper
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Situated on Pote College Road in Vijay Colony, near Orchid Bakery, Blush & Brew was imagined as an antidote to noisy, impersonal chain cafes. We sought to craft a space where deep black marble, warm amber lighting, and the fragrant steam of specialty Arabica unite into a soothing sanctuary.",
                    fontSize = 14.sp,
                    color = TextGrey,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Our name reflects our ethos: 'Blush' for delicate moments, warmth, floral notes, and blushing sweetness; 'Brew' for precise coffee extraction, artisanal roasting, and unhurried culinary love. Every bean is responsibly sourced from Chikmagalur estates, and our pastries are baked fresh in small batches every morning.",
                    fontSize = 14.sp,
                    color = TextGrey,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedCafeButton(
                    text = "Close",
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    testTag = "story_modal_done"
                )
            }
        }
    }
}
