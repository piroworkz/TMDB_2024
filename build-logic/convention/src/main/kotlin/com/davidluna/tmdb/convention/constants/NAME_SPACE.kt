package com.davidluna.tmdb.convention.constants

import org.gradle.api.Project

internal val Project.NAME_SPACE: String
    get() = Constants.NAMESPACE.plus(".$name")