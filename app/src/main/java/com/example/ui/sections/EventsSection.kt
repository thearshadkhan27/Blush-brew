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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.CafeEvent
import com.example.ui.components.DecorativeDustCanvas
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextDarkSecondary

@Composable
fun EventsSection(
    onReserveEventClick: (CafeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val featuredEvent = CafeEvent(
        id = "evt_acoustic",
        title = "Acoustic Sunset Sessions",
        date = "Every Saturday",
        time = "7:00 PM",
        type = "Live Music & Coffee",
        description = "Not only can you get the best handcrafted brews in Amravati, you can gather with your favorite crowd while enjoying acoustic sessions and soulful ambience."
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BgNearBlack)
            .padding(horizontal = 20.dp, vertical = 36.dp)
            .testTag("events_section")
    ) {
        DecorativeDustCanvas(
            particleCount = 35,
            seed = 404,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            // Horizontal split style card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(16.dp, RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Left/Top: Event photography banner
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_cafe_interior),
                            contentDescription = "Acoustic Sunset Sessions",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.35f))
                        )
                    }

                    // Right/Bottom: Crisp White Panel
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(26.dp)
                    ) {
                        Text(
                            text = "Discover",
                            fontFamily = DancingScriptFontFamily,
                            fontSize = 26.sp,
                            color = AccentCopper,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Upcoming Events",
                            fontFamily = PlayfairFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = TextDark
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = featuredEvent.description,
                            fontSize = 13.5.sp,
                            lineHeight = 21.sp,
                            color = TextDarkSecondary
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Event badge line: Title / Date / Time / Type
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFF7F7F7), RoundedCornerShape(4.dp))
                                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(4.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = featuredEvent.title,
                                fontFamily = PlayfairFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${featuredEvent.date} | ${featuredEvent.time} | ${featuredEvent.type}",
                                fontSize = 11.5.sp,
                                color = AccentCopper,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.5.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { onReserveEventClick(featuredEvent) }
                                .padding(vertical = 4.dp)
                                .testTag("more_events_link")
                        ) {
                            Text(
                                text = "Reserve Table For Event →",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCopper,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }

                // Decorative wooden spoon / coffee icon badge overlapping corner
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-12).dp)
                        .background(Color(0xFF202020), RoundedCornerShape(12.dp))
                        .border(1.dp, AccentCopper.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🥄", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Curated Moments",
                            fontSize = 10.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
