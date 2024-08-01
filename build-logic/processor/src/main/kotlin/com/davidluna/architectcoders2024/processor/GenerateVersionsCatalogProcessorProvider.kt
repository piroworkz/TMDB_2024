package com.davidluna.architectcoders2024.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class GenerateVersionsCatalogProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val libs = environment.options["libsPath"]
        val packageName = environment.options["packageName"]
        return GenerateVersionsCatalogProcessor(
            logger = environment.logger,
            codeGenerator = environment.codeGenerator,
            libs = libs ?: "",
            packageName = packageName ?: ""
        )
    }
}