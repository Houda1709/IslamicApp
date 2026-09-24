package com.example.islamicapp.data

data class Zikr(
    val texteArabe: String,
    val transliteration: String,
    val repetitions: Int
)

data class AzkarCategory(
    val id: Int,
    val titre: String,
    val icon: String,
    val azkar: List<Zikr>
)

object AzkarData {
    val categories = listOf(
        AzkarCategory(
            id = 0,
            titre = "Azkar du matin",
            icon = "🌅",
            azkar = listOf(
                Zikr("أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ", "Asbahna wa asbahal mulku lillah", 1),
                Zikr("اللَّهُمَّ بِكَ أَصْبَحْنَا", "Allahumma bika asbahna", 1),
                Zikr("سُبْحَانَ اللَّهِ وَبِحَمْدِهِ", "Subhanallahi wa bihamdih", 100)
            )
        ),
        AzkarCategory(
            id = 1,
            titre = "Azkar du soir",
            icon = "🌙",
            azkar = listOf(
                Zikr("أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ", "Amsayna wa amsal mulku lillah", 1),
                Zikr("اللَّهُمَّ بِكَ أَمْسَيْنَا", "Allahumma bika amsayna", 1),
                Zikr("أَسْتَغْفِرُ اللَّهَ", "Astaghfirullah", 100)
            )
        ),
        AzkarCategory(
            id = 2,
            titre = "Après la prière",
            icon = "🤲",
            azkar = listOf(
                Zikr("سُبْحَانَ اللَّهِ", "Subhanallah", 33),
                Zikr("الْحَمْدُ لِلَّهِ", "Alhamdulillah", 33),
                Zikr("اللَّهُ أَكْبَرُ", "Allahu Akbar", 33)
            )
        )
    )
}