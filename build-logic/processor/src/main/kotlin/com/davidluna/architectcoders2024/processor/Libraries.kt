package com.davidluna.architectcoders2024.processor

import java.io.File

data class Libraries(
    val versions: List<String>,
    val libraries: List<String>,
    val plugins: List<String>,
    val bundles: List<String>,
) {
    companion object {
        fun parseFile(file: File): Libraries {
            val fileContent = file.readText()

            val versions = mutableListOf<String>()
            val libraries = mutableListOf<String>()
            val plugins = mutableListOf<String>()
            val bundles = mutableListOf<String>()

            val sectionRegex = Regex("""^\[(\w+)]""", RegexOption.MULTILINE)
            val keyValueRegex = Regex("""^(\w+) =""", RegexOption.MULTILINE)

            val sections = sectionRegex.findAll(fileContent)
                .map { it.groupValues[1] }
                .toList()

            val sectionContents = sectionRegex.split(fileContent).drop(1)

            sections.forEachIndexed { index, section ->
                val sectionContent = sectionContents[index]
                val keys = keyValueRegex.findAll(sectionContent).map { it.groupValues[1] }.toList()

                when (section) {
                    "versions" -> versions.addAll(keys)
                    "libraries" -> libraries.addAll(keys)
                    "plugins" -> plugins.addAll(keys)
                    "bundles" -> bundles.addAll(keys)
                }
            }
            return Libraries(versions, libraries, plugins, bundles)
        }
    }
}