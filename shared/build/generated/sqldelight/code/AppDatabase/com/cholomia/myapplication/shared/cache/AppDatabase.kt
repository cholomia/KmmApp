package com.cholomia.myapplication.shared.cache

import com.cholomia.myapplication.shared.cache.shared.newInstance
import com.cholomia.myapplication.shared.cache.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

interface AppDatabase : Transacter {
  val appDatabaseQueries: AppDatabaseQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    operator fun invoke(driver: SqlDriver): AppDatabase = AppDatabase::class.newInstance(driver)}
}
