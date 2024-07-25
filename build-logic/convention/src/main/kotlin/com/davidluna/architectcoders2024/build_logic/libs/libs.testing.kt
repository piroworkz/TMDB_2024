package com.davidluna.architectcoders2024.build_logic.libs

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

// unit testing
internal val VersionCatalog.junit: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::junit.name).get()

internal val VersionCatalog.coroutinesTest: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::coroutinesTest.name).get()

internal val VersionCatalog.mockitoKotlin: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::mockitoKotlin.name).get()

internal val VersionCatalog.mockitoInline: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::mockitoInline.name).get()

internal val VersionCatalog.truth: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::truth.name).get()

internal val VersionCatalog.turbine: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::turbine.name).get()

// ui testing
internal val VersionCatalog.uiTestManifest: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::uiTestManifest.name).get()

internal val VersionCatalog.uiTestJunit4: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::uiTestJunit4.name).get()

internal val VersionCatalog.hiltTest: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltTest.name).get()

internal val VersionCatalog.mockWebServer: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::mockWebServer.name).get()

internal val VersionCatalog.navigationTesting: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::navigationTesting.name).get()

internal val VersionCatalog.androidRunner: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::androidRunner.name).get()
