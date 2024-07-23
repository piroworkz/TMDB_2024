import com.davidluna.architectcoders2024.build_logic.utils.jacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoConventionPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {

        pluginManager.apply {
            findPlugin("jacoco") ?: apply("jacoco")
            findPlugin("jacoco-report-aggregation") ?: apply("jacoco-report-aggregation")
            findPlugin("test-report-aggregation") ?: apply("test-report-aggregation")
        }

        jacoco {
            toolVersion = "0.8.8"
        }

        taskJacocoReport()
    }


    private fun Project.taskJacocoReport() {
        task<JacocoReport>("jacocoReport") {
            reports {
                csv.required.set(false)
                xml.required.set(false)
                html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
            }
        }
    }

}