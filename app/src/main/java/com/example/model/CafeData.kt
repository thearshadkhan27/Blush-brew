package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

data class MenuItem(
    val id: String,
    val name: String,
    val scriptCategory: String, // e.g. "Appetizer", "Artisanal Brew", "Dessert"
    val category: String, // "Coffee", "Desserts", "Savory Bites", "Coolers"
    val description: String,
    val price: Int, // In INR ₹
    @DrawableRes val imageRes: Int,
    val isPopular: Boolean = false,
    val isVeg: Boolean = true,
    val tastingNotes: String = ""
)

data class CafeEvent(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val type: String,
    val description: String
)

data class Ingredient(
    val name: String,
    val origin: String,
    val note: String,
    val iconEmoji: String
)

data class Review(
    val id: String,
    val author: String,
    val rating: Float,
    val relativeTime: String,
    val comment: String,
    val dishMentioned: String
)

data class TableReservation(
    val id: String,
    val guestName: String,
    val phoneNumber: String,
    val date: String,
    val timeSlot: String,
    val guestCount: Int,
    val seatingArea: String,
    val specialRequest: String = ""
)

object CafeSampleData {
    val menuItems = listOf(
        MenuItem(
            id = "menu_1",
            name = "Signature Rose Velvet Latte",
            scriptCategory = "Artisanal Brew",
            category = "Specialty Coffee",
            description = "Double-shot Chikmagalur espresso pulled over steamed micro-foam, kissed with organic Damask rose essence and edible petals.",
            price = 220,
            imageRes = R.drawable.img_hero_dish,
            isPopular = true,
            tastingNotes = "Floral sweetness, silky cocoa, subtle cardamom finish"
        ),
        MenuItem(
            id = "menu_2",
            name = "Gourmet Avocado Sourdough Toast",
            scriptCategory = "Appetizer",
            category = "Savory Bites",
            description = "Toasted artisanal sourdough crowned with smashed Haas avocado, organic cherry tomatoes, poached egg, and chili sea salt flakes.",
            price = 260,
            imageRes = R.drawable.img_savory_toast,
            isPopular = true,
            tastingNotes = "Creamy, zesty lime, crusty fermented sourdough"
        ),
        MenuItem(
            id = "menu_3",
            name = "Lotus Biscoff Burnt Cheesecake",
            scriptCategory = "Dessert",
            category = "Desserts",
            description = "Basque burnt cheesecake with a spiced speculoos biscuit crust, drizzled with warm caramel crunch sauce.",
            price = 280,
            imageRes = R.drawable.img_dessert_plate,
            isPopular = true,
            tastingNotes = "Rich cream cheese, roasted cinnamon sugar, melted caramel"
        ),
        MenuItem(
            id = "menu_4",
            name = "Spanish Cortado & Dulce Crema",
            scriptCategory = "Artisanal Brew",
            category = "Specialty Coffee",
            description = "Equal parts bold ristretto and condensed milk micro-foam, dusted with cinnamon bark.",
            price = 210,
            imageRes = R.drawable.img_hero_dish,
            tastingNotes = "Velvety sweetness, nutty roast, warm spice"
        ),
        MenuItem(
            id = "menu_5",
            name = "Truffle Mushroom Grilled Brioche",
            scriptCategory = "Savory Bite",
            category = "Savory Bites",
            description = "Wild forest mushrooms sautéed in garlic thyme butter and melted aged cheddar inside toasted milk brioche.",
            price = 290,
            imageRes = R.drawable.img_savory_toast,
            tastingNotes = "Earthy black truffle, melted cheddar, buttery crunch"
        ),
        MenuItem(
            id = "menu_6",
            name = "Warm Cast Iron Skillet Cookie",
            scriptCategory = "Dessert",
            category = "Desserts",
            description = "Oven-baked Belgian dark chocolate chip dough in mini skillet with artisanal vanilla bean gelato.",
            price = 290,
            imageRes = R.drawable.img_hero_dish,
            isPopular = true,
            tastingNotes = "Gooey chocolate center, crispy edges, chilled vanilla"
        ),
        MenuItem(
            id = "menu_7",
            name = "Hibiscus & Berry Sparkling Cold Brew",
            scriptCategory = "Artisan Cooler",
            category = "Coolers",
            description = "18-hour cold brew steeped with Egyptian hibiscus petals, wild berry puree, and sparkling tonic.",
            price = 240,
            imageRes = R.drawable.img_hero_dish,
            tastingNotes = "Refreshing tart berry, effervescent, crisp coffee finish"
        ),
        MenuItem(
            id = "menu_8",
            name = "Belgian Chocolate Ganache Croffle",
            scriptCategory = "Dessert",
            category = "Desserts",
            description = "Buttery croissant pressed into waffle iron, glazed in 70% dark Belgian ganache and roasted almonds.",
            price = 250,
            imageRes = R.drawable.img_dessert_plate,
            tastingNotes = "Crisp buttery layers, intense dark cocoa, toasted nuts"
        )
    )

    val events = listOf(
        CafeEvent(
            id = "evt_1",
            title = "Acoustic Sunset Sessions",
            date = "Every Saturday",
            time = "7:00 PM – 9:30 PM",
            type = "Live Music & Coffee",
            description = "Unwind to soothing acoustic indie melodies, candlelight ambience, and special evening brew pairings."
        ),
        CafeEvent(
            id = "evt_2",
            title = "Manual Brew & Pour-Over Masterclass",
            date = "Sunday, Oct 18",
            time = "4:00 PM – 6:00 PM",
            type = "Workshop",
            description = "Learn the science of V60, Aeropress, and French Press brewing hands-on with our head barista."
        ),
        CafeEvent(
            id = "evt_3",
            title = "Poetry & Warm Brew Open-Mic",
            date = "Friday, Oct 23",
            time = "6:30 PM – 8:30 PM",
            type = "Cultural Evening",
            description = "An intimate gathering for spoken word, stories, and warm comforting beverages in our lounge."
        )
    )

    val ingredients = listOf(
        Ingredient(
            name = "Single-Origin Arabica",
            origin = "Chikmagalur, Karnataka",
            note = "High-altitude shade-grown beans with natural notes of roasted hazelnut and dark berry.",
            iconEmoji = "☕"
        ),
        Ingredient(
            name = "Ceylon Cinnamon",
            origin = "Sri Lanka",
            note = "Hand-rolled sweet true cinnamon quills infusing our signature spiced beverages.",
            iconEmoji = "🌿"
        ),
        Ingredient(
            name = "Bourbon Vanilla",
            origin = "Madagascar",
            note = "Whole aromatic vanilla pods scraped into house-made syrups and gelato.",
            iconEmoji = "✨"
        ),
        Ingredient(
            name = "Belgian Dark Cocoa",
            origin = "Callebaut, Belgium",
            note = "70% single origin cocoa for our molten cookies, ganaches, and mochas.",
            iconEmoji = "🍫"
        ),
        Ingredient(
            name = "Organic Rose Essence",
            origin = "Kannauj, India",
            note = "Pure hydro-distilled Damask rose water lending our signature blush hue and aroma.",
            iconEmoji = "🌸"
        ),
        Ingredient(
            name = "Farmstead Dairy",
            origin = "Local Maharashtra Farms",
            note = "Fresh grass-fed milk with high natural sweetness steamed into silky micro-foam.",
            iconEmoji = "🥛"
        )
    )

    val reviews = listOf(
        Review(
            id = "rev_1",
            author = "Tanvi K.",
            rating = 5.0f,
            relativeTime = "3 days ago",
            comment = "Absolute gem in Amravati! The dark aesthetic ambiance is so calming and luxurious. The Signature Rose Latte and Biscoff Cheesecake are out of this world.",
            dishMentioned = "Signature Rose Velvet Latte"
        ),
        Review(
            id = "rev_2",
            author = "Aditya Deshmukh",
            rating = 4.5f,
            relativeTime = "1 week ago",
            comment = "Best coffee spot near Pote College road. Super aesthetic interiors, great crowd, and the avocado sourdough toast is fresh and delicious. Highly recommend!",
            dishMentioned = "Gourmet Avocado Toast"
        ),
        Review(
            id = "rev_3",
            author = "Pooja W.",
            rating = 5.0f,
            relativeTime = "2 weeks ago",
            comment = "Sip the blush, brew the love is truly their motto! Amazing desserts and warm hospitable staff. Perfect spot for quiet work or evening dates.",
            dishMentioned = "Lotus Biscoff Cheesecake"
        ),
        Review(
            id = "rev_4",
            author = "Rohit Joshi",
            rating = 4.0f,
            relativeTime = "3 weeks ago",
            comment = "Really love the modern vibe and music playlist. Coffee was prepared to perfection. Great addition to Amravati's cafe culture.",
            dishMentioned = "Spanish Cortado"
        )
    )
}
