package pl.droidsonroids.gradle.aosp.aapt

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

public class AaptConfigPluginTest {

    @Test
    public void testApplicationPlugin() throws Exception {
        def androidPluginName = 'com.android.application'
        testApplyAaptConfigWithPlugin(androidPluginName, AppExtension)
    }

    @Test
    public void testLibraryPlugin() throws Exception {
        def androidPluginName = 'com.android.library'
        testApplyAaptConfigWithPlugin(androidPluginName, LibraryExtension)
    }

    private static void testApplyAaptConfigWithPlugin(String androidPluginName, Class<? extends BaseExtension> androidExtensionClass) {
        def project = ProjectBuilder.builder().build()
        project.pluginManager.apply androidPluginName
        project.pluginManager.apply AaptConfigPlugin.class
        File manifest = project.file('src/main/AndroidManifest.xml')
        manifest.getParentFile().mkdirs()
        manifest << '<?xml version="1.0" encoding="utf-8"?>\n' +
                '<manifest package="pl.droidsonroids.zentesting"/>'
        def android = project.extensions.getByType(androidExtensionClass)
        if (androidExtensionClass == AppExtension)
            android.defaultConfig.setApplicationId 'pl.droidsonroids.testapplication'
        android.defaultConfig.setMinSdkVersion(1)
        android.buildToolsVersion '23.0.2'
        android.compileSdkVersion 23
        project.evaluate()
        assert android.aaptOptions.additionalParameters == ['-c', 'sw600dp,land,en_US,pl,es_ES', '--max-res-version', '15', '--preferred-density', 'xhdpi']
    }
}