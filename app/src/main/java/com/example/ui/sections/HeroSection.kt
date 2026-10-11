package com.example.ui.sections

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.DecorativeDustCanvas
import com.example.ui.components.OutlinedCafeButton
import com.example.ui.theme.AccentBlush
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun HeroSection(
    onBookTableClick: () -> Unit,
    onExploreMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Subtle floating loop for hero dish
    val infiniteTransition = rememberInfiniteTransition(label = "hero_float")
    val floatY by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "hero_y_offset"
    )

    val rotateBean by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "hero_bean_rot"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BgNearBlack)
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .testTag("hero_section")
    ) {
        // Background Decorative Dust Canvas
        DecorativeDustCanvas(
            particleCount = 55,
            seed = 101,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Brand Logo & Rating Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_bnb_official_logo),
                    contentDescription = "B&B Official Monogram",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, AccentCopper, CircleShape)
                        .shadow(8.dp, CircleShape)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(20.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = AccentCopper,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "4.4 ★ (Amravati)",
                        fontSize = 11.sp,
                        color = TextWhite,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "• ₹200–400 per person",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Elegant Script Tagline
            Text(
                text = "Sip the blush, brew the love",
                fontFamily = DancingScriptFontFamily,
                fontSize = 28.sp,
                color = AccentCopper,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Big Bold Serif Headline (3 lines)
            Text(
                text = "A Premium\nAnd Aesthetic\nCoffee Haven",
                fontFamily = PlayfairFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                lineHeight = 46.sp,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "Crafted specialty coffees, warm skillet delicacies, and decadent desserts curated for mindful pauses and soulful conversations.",
                fontSize = 14.sp,
                lineHeight = 22.sp,
                color = TextGrey,
                modifier = Modifier.fillMaxWidth(0.92f)
            )

            Spacer(modifier = Modifier.height(26.dp))

            // CTA Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedCafeButton(
                    text = "Book A Table",
                    onClick = onBookTableClick,
                    isAccent = false,
                    testTag = "hero_book_table_button"
                )

                OutlinedCafeButton(
                    text = "Explore Menu",
                    onClick = onExploreMenuClick,
                    isAccent = true,
                    testTag = "hero_explore_menu_button"
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Right/Center Visual: Large circular dish cutout with floating spices & coffee beans
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                contentAlignment = Alignment.Center
            ) {
                // Soft glow radial backdrop
                Box(
                    modifier = Modifier
                        .size(260.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    AccentCopper.copy(alpha = 0.22f),
                                    Color(0x05DE9B85),
                                    Color.Transparent
                                )
                            ),
                            CircleShape
                        )
                )

                // Large Main Dish Cutout (Circular Plate/Skillet with Coffee)
                Image(
                    painter = painterResource(id = R.drawable.img_hero_dish),
                    contentDescription = "Signature Skillet & Specialty Brew",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(270.dp)
                        .offset(y = floatY.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                        .shadow(28.dp, CircleShape, spotColor = Color.Black)
                        .testTag("hero_dish_image")
                )

                // Floating Decorative Cutout 1: Top-Right Roasted Coffee Bean / Rose Petal badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-16).dp, y = (10 + floatY * 0.5f).dp)
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(16.dp))
                        .border(1.dp, AccentCopper.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "☕", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Chikmagalur Roast",
                            fontSize = 10.sp,
                            color = AccentBlush,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Floating Decorative Cutout 2: Bottom-Left Rose Crema Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 10.dp, y = (-20 - floatY * 0.7f).dp)
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🌸", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Damask Rose Infusion",
                            fontSize = 10.sp,
                            color = TextWhite,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Floating Decorative Cutout 3: Sugar & Cinnamon Dust badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-10).dp, y = (-10 + floatY).dp)
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(16.dp))
                        .border(1.dp, AccentCopper.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "✨", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Ceylon Spice Dust",
                            fontSize = 10.sp,
                            color = AccentCopper,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
