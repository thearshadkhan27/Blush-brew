package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ui.CafeViewModel
import com.example.ui.components.CafeNavbar
import com.example.ui.components.FullMenuModal
import com.example.ui.components.IngredientDetailDialog
import com.example.ui.components.OutlinedCafeButton
import com.example.ui.components.ReservationDialog
import com.example.ui.components.StoryModal
import com.example.ui.sections.EventsSection
import com.example.ui.sections.FooterSection
import com.example.ui.sections.HeroSection
import com.example.ui.sections.IngredientsSection
import com.example.ui.sections.OurMenuSection
import com.example.ui.sections.OurStorySection
import com.example.ui.sections.ReservationBannerSection
import com.example.ui.sections.ReviewsSection
import com.example.ui.theme.BgNearBlack
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: CafeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                BlushAndBrewApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun BlushAndBrewApp(viewModel: CafeViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Section Y positions for smooth scrolling
    val sectionPositions = remember { mutableMapOf<Int, Float>() }

    fun scrollToSection(index: Int) {
        viewModel.setActiveNav(index)
        coroutineScope.launch {
            val targetY = sectionPositions[index]?.toInt() ?: 0
            scrollState.animateScrollTo(targetY)
        }
    }

    fun openMaps() {
        val query = Uri.encode("Blush & brew Vijay Colony Amravati Maharashtra 444604")
        val gmmIntentUri = Uri.parse("geo:0,0?q=$query")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        try {
            context.startActivity(mapIntent)
        } catch (_: Exception) {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$query")
            )
            context.startActivity(webIntent)
        }
    }

    fun callCafe() {
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:7387312529"))
        try {
            context.startActivity(dialIntent)
        } catch (_: Exception) {
            Toast.makeText(context, "Call: +91 7387312529", Toast.LENGTH_SHORT).show()
        }
    }

    fun shareCafe() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Check out Blush & Brew Café in Amravati! Aesthetic coffee vibes, artisanal desserts & moments ☕✨\nLocation: Vijay Colony, Pote College Road, Amravati\nCall: +91 7387312529"
            )
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Blush & Brew"))
    }

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearToast()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = BgNearBlack,
        topBar = {
            CafeNavbar(
                activeSectionIndex = uiState.activeNavIndex,
                onSectionSelected = { index -> scrollToSection(index) },
                onOpenReservation = { viewModel.openReservationDialog() },
                onCallClick = { callCafe() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BgNearBlack)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Section 0: HERO
                HeroSection(
                    onBookTableClick = { viewModel.openReservationDialog() },
                    onExploreMenuClick = { viewModel.openFullMenuModal() },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[0] = coordinates.positionInParent().y
                    }
                )

                // Section 1: OUR STORY
                OurStorySection(
                    onMoreAboutUsClick = { viewModel.openStoryModal() },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[1] = coordinates.positionInParent().y
                    }
                )

                // Section 2: OUR MENU
                OurMenuSection(
                    onExploreFullMenuClick = { viewModel.openFullMenuModal() },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[2] = coordinates.positionInParent().y
                    }
                )

                // Section 3: UPCOMING EVENTS
                EventsSection(
                    onReserveEventClick = { event ->
                        viewModel.openReservationDialog(selectedEvent = event)
                    },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[3] = coordinates.positionInParent().y
                    }
                )

                // Section 4: THE BEST INGREDIENTS
                IngredientsSection(
                    onIngredientClick = { ingredient ->
                        viewModel.openIngredientDetails(ingredient)
                    },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[4] = coordinates.positionInParent().y
                    }
                )

                // Section 5: BOOK YOUR TABLE (RESERVATION BANNER)
                ReservationBannerSection(
                    onOnlineBookingClick = { viewModel.openReservationDialog() },
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        sectionPositions[5] = coordinates.positionInParent().y
                    }
                )

                // Section 6: GUEST REVIEWS & VIBES
                ReviewsSection()

                // Section 7: FOOTER
                FooterSection(
                    onDirectionsClick = { openMaps() },
                    onCallClick = { callCafe() },
                    onShareClick = { shareCafe() }
                )
            }
        }
    }

    // Modal Overlays
    if (uiState.isReservationDialogOpen) {
        ReservationDialog(
            selectedEvent = uiState.selectedEvent,
            onDismiss = { viewModel.closeReservationDialog() },
            onSubmit = { name, phone, date, time, guests, area, note ->
                viewModel.submitReservation(name, phone, date, time, guests, area, note)
            }
        )
    }

    if (uiState.isFullMenuModalOpen) {
        FullMenuModal(
            selectedCategory = uiState.selectedCategoryFilter,
            onCategoryChange = { cat -> viewModel.setCategoryFilter(cat) },
            onSelectItem = { item ->
                // Option to reserve table with item in mind
                viewModel.closeFullMenuModal()
                viewModel.openReservationDialog()
            },
            onDismiss = { viewModel.closeFullMenuModal() }
        )
    }

    if (uiState.isAboutStoryModalOpen) {
        StoryModal(onDismiss = { viewModel.closeStoryModal() })
    }

    uiState.selectedIngredient?.let { ingredient ->
        IngredientDetailDialog(
            ingredient = ingredient,
            onDismiss = { viewModel.closeIngredientDetails() }
        )
    }
}
