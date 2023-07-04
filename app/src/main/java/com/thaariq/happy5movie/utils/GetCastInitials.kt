package com.thaariq.happy5movie.utils

fun getCastInitials(names : String?) : String {
    val words = names?.split(" ")
    val initials = words?.mapNotNull { it.firstOrNull() }?.joinToString("")
    return initials ?: ""
}