package pl.droidsonroids.gradle.aosp.aapt

import com.android.build.gradle.*
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AaptConfigPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.allprojects { Project subproject ->
            subproject.plugins.withType(AppPlugin) {
                applyAaptConfig(subproject.extensions.getByType(AppExtension))
            }
            subproject.plugins.withType(LibraryPlugin) {
                applyAaptConfig(subproject.extensions.getByType(LibraryExtension))
            }
        }
    }

    def applyAaptConfig(final BaseExtension android) {
        android.aaptOptions {
            def env = System.getenv()
            String resVersion = env.get('PLATFORM_SDK_VERSION')
            String preferredDensity = env.get('PRODUCT_AAPT_PREF_CONFIG')
            String productLocales = env.getOrDefault('PRODUCT_LOCALES', '').replace(' ', ',')
            String aaptConfigurations = env.getOrDefault('GRADLE_AAPT_INCLUDED_CONFIGURATIONS', '')

            def parameters = [] << '-c' << (aaptConfigurations + ',' + productLocales)
            if (resVersion)
                parameters << '--max-res-version' << resVersion
            if (preferredDensity)
                parameters << '--preferred-density' << preferredDensity
            additionalParameters parameters.toArray(new String[parameters.size()])
        }
    }
}