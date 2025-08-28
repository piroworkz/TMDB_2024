package com.davidluna.tmdb.core_framework.di.qualifiers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.BINARY

@Qualifier
@Retention(BINARY)
annotation class PermissionStatus()