package com.davidluna.architectcoders2024.build_logic.libs

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

val VersionCatalog.composeBom: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeBom.name).get()

val VersionCatalog.composeUi: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUi.name).get()

val VersionCatalog.composeUiGraphics: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiGraphics.name).get()

val VersionCatalog.composeUiTooling: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiTooling.name).get()

val VersionCatalog.composeUiToolingPreview: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiToolingPreview.name).get()

val VersionCatalog.composeMaterial3: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeMaterial3.name).get()

val VersionCatalog.composeActivity: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeActivity.name).get()

val VersionCatalog.composeNavigation: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeNavigation.name).get()

val VersionCatalog.composeConstraintLayout: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeConstraintLayout.name).get()

val VersionCatalog.coilCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::coilCompose.name).get()

val VersionCatalog.iconsExtended: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::iconsExtended.name).get()

val VersionCatalog.kotlinCoroutinesCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinCoroutinesCore.name).get()

val VersionCatalog.retrofit: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::retrofit.name).get()

val VersionCatalog.kotlinConverter: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinConverter.name).get()

val VersionCatalog.protobufJavalite: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::protobufJavalite.name).get()

val VersionCatalog.protobufKotlinLite: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::protobufKotlinLite.name).get()

val VersionCatalog.okhttpClient: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::okhttpClient.name).get()

val VersionCatalog.okhttpLoggingInterceptor: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::okhttpLoggingInterceptor.name).get()

val VersionCatalog.arrowCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::arrowCore.name).get()

val VersionCatalog.pagingCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::pagingCompose.name).get()

val VersionCatalog.pagingRuntime: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::pagingRuntime.name).get()

val VersionCatalog.playServicesLocation: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::playServicesLocation.name).get()

val VersionCatalog.biometric: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::biometric.name).get()

val VersionCatalog.hiltAndroid: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltAndroid.name).get()

val VersionCatalog.hiltCompiler: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltCompiler.name).get()

val VersionCatalog.hiltNavigationCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltNavigationCompose.name).get()

val VersionCatalog.javaxInject: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::javaxInject.name).get()

val VersionCatalog.junit: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::junit.name).get()

val VersionCatalog.extJunit: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::extJunit.name).get()

val VersionCatalog.espressoCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::espressoCore.name).get()

val VersionCatalog.appcompat: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::appcompat.name).get()

val VersionCatalog.material: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::material.name).get()

val VersionCatalog.androidGradlePlugin: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::androidGradlePlugin.name).get()

val VersionCatalog.kotlinGradlePlugin: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinGradlePlugin.name).get()

val VersionCatalog.composeAndroidPermissions: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeAndroidPermissions.name).get()

val VersionCatalog.coreKtx: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::coreKtx.name).get()

val VersionCatalog.datastoreCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::datastoreCore.name).get()

val VersionCatalog.kotlinxSerializationJson: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinxSerializationJson.name).get()

val VersionCatalog.lifecycleRuntimeKtx: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::lifecycleRuntimeKtx.name).get()

