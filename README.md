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

`GRADLE_AAPT_INCLUDED_CONFIGURATIONS` is additional variable which can contain comma separated configurations eg: `sw600dp,land`.

## License

MIT License<br>
See [LICENSE](LICENSE) file.
