android-gradle-aosp-aapt-plugin
==================================
## Overview
This Gradle plugin sets [android.aaptOptions.additionalParameters](http://google.github.io/android-gradle-dsl/current/com.android.build.gradle.internal.dsl.AaptOptions.html#com.android.build.gradle.internal.dsl.AaptOptions:additionalParameters)
based on environment variables defined by AOSP build system.

Supported variables:

| Environment variable     | aapt parameter      |
| --------------------     |:-------------------:|
| PLATFORM_SDK_VERSION     | --max-res-version   |
| PRODUCT_AAPT_PREF_CONFIG | --preferred-density |
| PRODUCT_LOCALES | -c |
| GRADLE_AAPT_INCLUDED_CONFIGURATIONS | -c |

`GRADLE_AAPT_INCLUDED_CONFIGURATIONS` is additional variable which can contain comma separated configurations e.g.: `sw600dp,land`.

## Usage

Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:
```
plugins {
  id "pl.droidsonroids.aosp.aapt" version "0.0.1"
}
```

Build script snippet for use in all Gradle versions:
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.pl.droidsonroids.gradle.aosp.aapt:android-gradle-aosp-plugin:0.0.1"
  }
}

apply plugin: "pl.droidsonroids.aosp.aapt"
```

See also [Gradle Plugin Portal](https://plugins.gradle.org/plugin/pl.droidsonroids.aosp.aapt).

## Environment

Gradle-based AOSP modules are needed. See [Android.mk examples](https://github.com/DroidsOnRoids/android-gradle-aosp-aapt-plugin/issues/1#issuecomment-205082352).

## License

MIT License<br>
See [LICENSE](LICENSE) file.
