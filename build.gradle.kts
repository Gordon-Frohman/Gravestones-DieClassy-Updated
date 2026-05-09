import org.gradle.plugins.ide.eclipse.model.Library
import org.gradle.plugins.ide.eclipse.model.internal.FileReferenceFactory

plugins {
    id("com.gtnewhorizons.gtnhconvention")
    eclipse
}

eclipse {
    classpath {
        file {
            whenMerged {
                val cp = this as org.gradle.plugins.ide.eclipse.model.Classpath
                val factory = FileReferenceFactory()
                
                cp.entries.forEach { entry ->
                    if (entry is Library && entry.path.contains("libs/")) {
                        val jarFile = file(entry.path)
                        
                        val javadocFile = jarFile.parentFile.resolve(jarFile.name.replace(".jar", "-javadoc.jar"))
                        if (javadocFile.exists()) {
                            entry.javadocPath = factory.fromPath(javadocFile.absolutePath)
                        }

                        val sourcesFile = jarFile.parentFile.resolve(jarFile.name.replace(".jar", "-sources.jar"))
                        if (sourcesFile.exists()) {
                            entry.sourcePath = factory.fromPath(sourcesFile.absolutePath)
                        }
                    }
                }
            }
        }
    }
}
