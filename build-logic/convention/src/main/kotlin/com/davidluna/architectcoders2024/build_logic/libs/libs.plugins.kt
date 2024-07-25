package com.davidluna.architectcoders2024.build_logic.libs

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

internal val VersionCatalog.androidApplication: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::androidApplication.name).get()

internal val VersionCatalog.kotlinAndroid: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinAndroid.name).get()

internal val VersionCatalog.kotlinJvm: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinJvm.name).get()

internal val VersionCatalog.kotlinSerialization: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinSerialization.name).get()

internal val VersionCatalog.protobufPlugin: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::protobufPlugin.name).get()

internal val VersionCatalog.hiltPlugin: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::hiltPlugin.name).get()

internal val VersionCatalog.ksp: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::ksp.name).get()

internal val VersionCatalog.androidLibrary: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::androidLibrary.name).get()

internal val VersionCatalog.composeCompiler: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::composeCompiler.name).get()
