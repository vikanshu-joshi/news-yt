package com.vikanshu.newsyt

object Constants {
    const val DATABASE_NAME = "Articles_Database"
    const val DEFAULT_FROM_DATE= "1990/01/01"
    val CATEGORIES =
        mapOf(
            "Breaking News" to "breaking-news",
            "World" to "world",
            "Nation" to "nation",
            "Business" to "business",
            "Technology" to "technology",
            "Entertainment" to "entertainment",
            "Sports" to "sports",
            "Science" to "science",
            "Heath" to "health"
        )
    val SORTBY =
        mapOf(
            "Relevance" to "relevance",
            "Published At" to "publishedAt"
        )
    val LANGUAGES = mapOf(
        "Arabic" to "ar",
        "German" to "de",
        "Greek" to "el",
        "English" to "en",
        "Spanish" to "es",
        "French" to "fr",
        "Hebrew" to "he",
        "Hindi" to "hi",
        "Italian" to "it",
        "Japanese" to "ja",
        "Malayalam" to "ml",
        "Marathi" to "mr",
        "Dutch" to "nl",
        "Norwegian" to "no",
        "Portuguese" to "pt",
        "Romanian" to "ro",
        "Russian" to "ru",
        "Swedish" to "sv",
        "Tamil" to "ta",
        "Telugu" to "te",
        "Ukrainian" to "uk",
        "Chinese" to "zh"
    )
    val COUNTRIES = mapOf(
        "Australia" to "au",
        "Brazil" to "br",
        "Canada" to "ca",
        "Switzerland" to "ch",
        "China" to "cn",
        "Germany" to "de",
        "Egypt" to "eg",
        "Spain" to "es",
        "France" to "fr",
        "United Kingdom" to "gb",
        "Greece" to "gr",
        "Hong Kong" to "hk",
        "Ireland" to "ie",
        "Israel" to "il",
        "India" to "in",
        "Italy" to "it",
        "Japan" to "jp",
        "Netherlands" to "nl",
        "Norway" to "no",
        "Peru" to "pe",
        "Philippines" to "ph",
        "Pakistan" to "pk",
        "Portugal" to "pt",
        "Romania" to "ro",
        "Russian Federation" to "ru",
        "Sweden" to "se",
        "Singapore" to "sg",
        "Taiwan, Province of China" to "tw",
        "Ukraine" to "ua",
        "United States" to "us"
    )
}