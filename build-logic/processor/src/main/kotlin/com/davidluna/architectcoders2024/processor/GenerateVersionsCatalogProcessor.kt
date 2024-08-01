package com.davidluna.architectcoders2024.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import java.io.File

class GenerateVersionsCatalogProcessor(
    private val logger: KSPLogger,
    private val libs: String,
    private val packageName: String,
    codeGenerator: CodeGenerator,
) : SymbolProcessor {
    private val libsGenerator = LibsGenerator(codeGenerator = codeGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing GenerateVersionsCatalogProcessor with libs: $libs")
        val file = File(libs)
        val libraries: Libraries = Libraries.parseFile(file)
        try {
            libsGenerator.generate(libraries, packageName, logger)
        } catch (e: FileAlreadyExistsException) {
            logger.info("File already exists")
        }
        return emptyList()
    }

}

