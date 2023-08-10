package com.abdulqohar.qmovieaddict.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.abdulqohar.qmovieaddict.QMovieAddictApplication

class HiltTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, QMovieAddictApplication::class.java.name, context)
    }
}