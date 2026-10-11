package com.example.ui.sections

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CafeSampleData
import com.example.model.Ingredient
import com.example.ui.components.DecorativeDustCanvas
import com.example.ui.theme.AccentCopper
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.DancingScriptFontFamily
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextGrey
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun IngredientsSection(
    onIngredientClick: (Ingredient) -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ingredients_float")
    val floatAnim by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ing_float"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BgNearBlack)
            .padding(horizontal = 20.dp, vertical = 36.dp)
            .testTag("ingredients_section")
    ) {
        DecorativeDustCanvas(
            particleCount = 45,
            seed = 505,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Centered Script Label
            Text(
                text = "Discover",
                fontFamily = DancingScriptFontFamily,
                fontSize = 28.sp,
                color = AccentCopper,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Centered Bold Serif Title
            Text(
                text = "The Best Ingredients",
                fontFamily = PlayfairFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = TextWhite,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Paragraph
            Text(
                text = "We take an enormous amount of pride in sourcing our ingredients carefully to ensure that the flavors of our beverages and bakery items are as delicious and authentic as possible. We're only able to achieve this level of excellence by putting an extra level of care into each element.",
                fontSize = 13.5.sp,
                lineHeight = 22.sp,
                color = TextGrey,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.92f)
            )

            Spacer(modifier = Modifier.height(34.dp))

            // Flat-lay row of raw ingredient cut-outs with subtle floating animation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CafeSampleData.ingredients.forEachIndexed { index, ingredient ->
                    val isCenter = index == 0
                    val currentOffset = if (index % 2 == 0) floatAnim else -floatAnim

                    Column(
                        modifier = Modifier
                            .width(130.dp)
                            .offset(y = currentOffset.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceCard)
                            .border(
                                width = 1.dp,
                                color = if (isCenter) AccentCopper else Color.White.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { onIngredientClick(ingredient) }
                            .padding(14.dp)
                            .testTag("ingredient_card_${ingredient.name}"),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Emoji/Icon circular medallion
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF252525))
                                .border(1.dp, AccentCopper.copy(alpha = 0.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ingredient.iconEmoji,
                                fontSize = 26.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = ingredient.name,
                            fontFamily = PlayfairFontFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = ingredient.origin,
                            fontSize = 10.sp,
                            color = AccentCopper,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = ingredient.note,
                            fontSize = 10.5.sp,
                            color = TextGrey,
                            textAlign = TextAlign.Center,
                            lineHeight = 14.sp,
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}
