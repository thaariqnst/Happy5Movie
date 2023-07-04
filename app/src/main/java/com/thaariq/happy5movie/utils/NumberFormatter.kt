package com.thaariq.happy5movie.utils

fun voteCountNumberFormatter(number: Int): String {
    return when (number) {
        in 1000..999999 -> {
            val roundedNumber = (number / 1000.0).toInt()
            "(${roundedNumber}k reviews)"
        }
        else -> number.toString()
    }
}