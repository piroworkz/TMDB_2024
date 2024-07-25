package com.davidluna.architectcoders2024.build_logic.libs

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

internal val VersionCatalog.composeBom: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeBom.name).get()

internal val VersionCatalog.composeUi: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUi.name).get()

internal val VersionCatalog.composeUiGraphics: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiGraphics.name).get()

internal val VersionCatalog.composeUiTooling: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiTooling.name).get()

internal val VersionCatalog.composeUiToolingPreview: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeUiToolingPreview.name).get()

internal val VersionCatalog.composeMaterial3: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeMaterial3.name).get()

internal val VersionCatalog.composeActivity: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeActivity.name).get()

internal val VersionCatalog.composeNavigation: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeNavigation.name).get()

internal val VersionCatalog.composeConstraintLayout: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeConstraintLayout.name).get()

internal val VersionCatalog.coilCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::coilCompose.name).get()

internal val VersionCatalog.iconsExtended: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::iconsExtended.name).get()

internal val VersionCatalog.kotlinCoroutinesCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinCoroutinesCore.name).get()

internal val VersionCatalog.retrofit: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::retrofit.name).get()

internal val VersionCatalog.kotlinConverter: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinConverter.name).get()

internal val VersionCatalog.protobufJavalite: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::protobufJavalite.name).get()

internal val VersionCatalog.protobufKotlinLite: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::protobufKotlinLite.name).get()

internal val VersionCatalog.okhttpClient: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::okhttpClient.name).get()

internal val VersionCatalog.okhttpLoggingInterceptor: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::okhttpLoggingInterceptor.name).get()

internal val VersionCatalog.arrowCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::arrowCore.name).get()

internal val VersionCatalog.pagingCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::pagingCompose.name).get()

internal val VersionCatalog.pagingRuntime: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::pagingRuntime.name).get()

internal val VersionCatalog.playServicesLocation: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::playServicesLocation.name).get()

internal val VersionCatalog.biometric: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::biometric.name).get()

internal val VersionCatalog.hiltAndroid: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltAndroid.name).get()

internal val VersionCatalog.hiltCompiler: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltCompiler.name).get()

internal val VersionCatalog.hiltNavigationCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::hiltNavigationCompose.name).get()

internal val VersionCatalog.javaxInject: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::javaxInject.name).get()

internal val VersionCatalog.appcompat: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::appcompat.name).get()

internal val VersionCatalog.material: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::material.name).get()

internal val VersionCatalog.androidGradlePlugin: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::androidGradlePlugin.name).get()

internal val VersionCatalog.kotlinGradlePlugin: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinGradlePlugin.name).get()

internal val VersionCatalog.composeAndroidPermissions: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeAndroidPermissions.name).get()

internal val VersionCatalog.coreKtx: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::coreKtx.name).get()

internal val VersionCatalog.datastoreCore: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::datastoreCore.name).get()

internal val VersionCatalog.kotlinxSerializationJson: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::kotlinxSerializationJson.name).get()

internal val VersionCatalog.lifecycleRuntimeKtx: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::lifecycleRuntimeKtx.name).get()

internal val VersionCatalog.runtimeTracing: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::runtimeTracing.name).get()

internal val VersionCatalog.composeAnimation: Provider<MinimalExternalModuleDependency>
    get() = findLibrary(VersionCatalog::composeAnimation.name).get()

