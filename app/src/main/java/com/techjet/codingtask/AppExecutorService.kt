package com.techjet.codingtask

import java.util.concurrent.Executors

class AppExecutorService private constructor() {

    init {
        throw IllegalArgumentException(AppExecutorService::class.java.name)
    }

    companion object {
        private const val TOTAL_THREAD_SIZE = 25
        val SERVICE = Executors.newFixedThreadPool(TOTAL_THREAD_SIZE)
        val singleThreadExecutor = Executors.newSingleThreadExecutor()
    }
}