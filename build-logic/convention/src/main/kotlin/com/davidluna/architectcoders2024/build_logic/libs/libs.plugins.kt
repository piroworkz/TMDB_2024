package com.davidluna.architectcoders2024.build_logic.libs

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

val VersionCatalog.androidApplication: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::androidApplication.name).get()

val VersionCatalog.kotlinAndroid: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinAndroid.name).get()

val VersionCatalog.kotlinJvm: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinJvm.name).get()

val VersionCatalog.kotlinSerialization: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kotlinSerialization.name).get()

val VersionCatalog.protobufPlugin: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::protobufPlugin.name).get()

val VersionCatalog.hiltPlugin: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::hiltPlugin.name).get()

val VersionCatalog.kapt: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::kapt.name).get()

val VersionCatalog.androidLibrary: Provider<PluginDependency>
    get() = findPlugin(VersionCatalog::androidLibrary.name).get()


