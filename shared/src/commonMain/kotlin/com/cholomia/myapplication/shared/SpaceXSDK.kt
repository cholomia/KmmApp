package com.cholomia.myapplication.shared

import com.cholomia.myapplication.shared.cache.Database
import com.cholomia.myapplication.shared.cache.DatabaseDriverFactory
import com.cholomia.myapplication.shared.entity.RocketLaunch
import com.cholomia.myapplication.shared.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }

}