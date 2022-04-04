package com.contentside.tech.test

import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import net.masterthought.cucumber.presentation.PresentationMode
import net.masterthought.cucumber.sorting.SortingMethod
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors.toList

class Reporting {
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            generateReports()
        }
    }
}

private fun generateReports() {
    val reportPaths = Files.walk(Paths.get(""))
            .filter { Files.isRegularFile(it) }
            .filter { it.endsWith("acceptance-report.json") }
            .map { it.toString() }
            .collect(toList())
    ReportBuilder(
            reportPaths,
            Configuration(File("target/cucumber"), "Microservices")
                    .apply {
                        this.sortingMethod = SortingMethod.NATURAL
                        this.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS)
                        this.addPresentationModes(PresentationMode.PARALLEL_TESTING)
                    }
    ).generateReports()
}
