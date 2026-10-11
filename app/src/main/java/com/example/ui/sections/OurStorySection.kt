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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import com.example.ui.components.DecorativeDustCanvas
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextDarkSecondary

@Composable
fun OurStorySection(
    onMoreAboutUsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BgNearBlack)
            .padding(horizontal = 20.dp, vertical = 36.dp)
            .testTag("our_story_section")
    ) {
        // Decorative dust behind the card
        DecorativeDustCanvas(
            particleCount = 35,
            seed = 202,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            // Horizontal split style card: photo on top/left, white panel on bottom/right
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(16.dp, RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Photo portion: Cafe interior & atmosphere
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_cafe_interior),
                            contentDescription = "Our Story Cafe Ambiance",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        // Dark gradient overlay on photo
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.25f))
                        )
                    }

                    // Crisp White Panel (Matching the video exact design)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(26.dp)
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
                                    fontSize = 26.sp,
                                    color = AccentCopper,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Our Story",
                                    fontFamily = PlayfairFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp,
                                    color = TextDark
                                )
                            }

                            Image(
                                painter = painterResource(id = R.drawable.img_bnb_official_logo),
                                contentDescription = "B&B Official Seal",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, AccentCopper, CircleShape)
                                    .shadow(6.dp, CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Get the true coffee and dessert experience at Blush & Brew. Whether you're joining us for a romantic coffee date, a peaceful work session, or catching up with your favorite crowd, our baristas craft superior artisanal brews and warm pastries for an unforgettable cafe experience in Amravati.",
                            fontSize = 13.5.sp,
                            lineHeight = 22.sp,
                            color = TextDarkSecondary
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { onMoreAboutUsClick() }
                                .padding(vertical = 4.dp)
                                .testTag("more_about_us_link")
                        ) {
                            Text(
                                text = "More About Us →",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCopper,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }

                // Decorative sprig badge overlapping corner
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-12).dp)
                        .background(Color(0xFF202020), RoundedCornerShape(12.dp))
                        .border(1.dp, AccentCopper.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🌿", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Est. 2024 • Amravati",
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
