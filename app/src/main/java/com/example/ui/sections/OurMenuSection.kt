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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.DecorativeDustCanvas
import com.example.ui.components.OutlinedCafeButton
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun OurMenuSection(
    onExploreFullMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "menu_plates_float")
    val float1 by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "p1_float"
    )
    val float2 by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = -4f,
        animationSpec = infiniteRepeatable(
            animation = tween(3400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "p2_float"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BgNearBlack)
            .padding(horizontal = 20.dp, vertical = 36.dp)
            .testTag("our_menu_section")
    ) {
        DecorativeDustCanvas(
            particleCount = 50,
            seed = 303,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            // Header: Discover / Our Menu / Description
            Text(
                text = "Discover",
                fontFamily = DancingScriptFontFamily,
                fontSize = 28.sp,
                color = AccentCopper,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Our Menu",
                fontFamily = PlayfairFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Few things come close to the joy of freshly roasted beans and warm oven delicacies — prepared simply with tender, loving care. Explore our signature handcrafted dishes below.",
                fontSize = 13.5.sp,
                lineHeight = 21.sp,
                color = TextGrey,
                modifier = Modifier.fillMaxWidth(0.95f)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Plate 1: Circular cut-out Plate on the Left, Text on the Right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("menu_plate_1"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Cutout Plate
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .offset(y = float1.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_savory_toast),
                        contentDescription = "Appetizer & Toast",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(1.dp, Color.White.copy(alpha = 0.18f), CircleShape)
                            .shadow(20.dp, CircleShape, spotColor = Color.Black)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text Description
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Appetizer",
                        fontFamily = DancingScriptFontFamily,
                        fontSize = 22.sp,
                        color = AccentCopper,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Gourmet Avocado Toast",
                        fontFamily = PlayfairFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Start with our artisan sourdough bread with egg, Haas avocado, and sea salt.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = TextGrey
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "₹260",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCopper
                    )
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            // Plate 2: Zig-zag (Text on Left, Plate on Right)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("menu_plate_2"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Text Description
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Artisanal Brew",
                        fontFamily = DancingScriptFontFamily,
                        fontSize = 22.sp,
                        color = AccentCopper,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Signature Rose Latte",
                        fontFamily = PlayfairFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Our velvety double-shot espresso pulled fresh to satisfy your coffee palate.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = TextGrey,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "₹220",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCopper,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Cutout Plate
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .offset(y = float2.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_dish),
                        contentDescription = "Signature Rose Latte",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(1.dp, Color.White.copy(alpha = 0.18f), CircleShape)
                            .shadow(20.dp, CircleShape, spotColor = Color.Black)
                    )
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            // Plate 3: Plate on Left, Text on Right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("menu_plate_3"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Cutout Plate
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .offset(y = float1.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_dessert_plate),
                        contentDescription = "Lotus Biscoff Cheesecake",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(1.dp, Color.White.copy(alpha = 0.18f), CircleShape)
                            .shadow(20.dp, CircleShape, spotColor = Color.Black)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text Description
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Dessert",
                        fontFamily = DancingScriptFontFamily,
                        fontSize = 22.sp,
                        color = AccentCopper,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Biscoff Burnt Cheesecake",
                        fontFamily = PlayfairFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Finish your cafe experience with our oven-baked Basque cheesecake drizzled in caramel.",
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = TextGrey
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "₹280",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCopper
                    )
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            // Outlined CTA Button to Explore Full Menu
            OutlinedCafeButton(
                text = "Explore Full Menu",
                onClick = onExploreFullMenuClick,
                isAccent = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                testTag = "menu_explore_full_button"
            )
        }
    }
}
