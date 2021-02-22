package com.threedots.projectx.util

import android.util.Patterns
import java.util.regex.Pattern

fun validEmail(email : String) : Boolean {
    var pattern = Patterns.EMAIL_ADDRESS;
    return pattern.matcher(email).matches()
}

