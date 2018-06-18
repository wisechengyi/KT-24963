# Minimal repro for https://youtrack.jetbrains.com/issue/KT-24963

## 1. Sanity check
```
./gradlew lib:squabble:kaptKotlin
```
should work. Note that all build artifacts are under `build/projects/<project path>/`

## 2. Now, symlink build/ to somewhere else. E.g.
```
rm -rf build/ && \
mkdir -p /tmp/build && \
ln -s /tmp/build build
```

### Kapt
```
$ ./gradlew lib:squabble:kaptKotlin
w: Classpath entry points to a non-existent location: /Users/yic/workspace/dummy_android_2/lib/squabble/src/main/kotlin
e: /private/tmp/build/projects/lib/squabble/tmp/kapt3/stubs/main/error/NonExistentClass.java:3: error: duplicate class: error.NonExistentClass
public final class NonExistentClass {
             ^
e: /private/tmp/build/projects/lib/squabble/tmp/kapt3/stubs/main/example/SquabbleProcessor.java:8: error: duplicate class: example.SquabbleProcessor
public final class SquabbleProcessor extends javax.annotation.processing.AbstractProcessor {
             ^

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':lib:squabble:kaptKotlin'.
> Compilation error. See log for more details

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 1s
4 actionable tasks: 4 executed
```

### Rerun works, which shoud not?
```
$ ./gradlew lib:squabble:kaptKotlin

BUILD SUCCESSFUL in 0s
4 actionable tasks: 4 up-to-date
```

## Step 2 is repeatable

## Revert to 1.2.41
Apply the diff:
```
diff --git a/lib/squabble/build.gradle b/lib/squabble/build.gradle
index ee0849c..05802a4 100644
--- a/lib/squabble/build.gradle
+++ b/lib/squabble/build.gradle
@@ -1,9 +1,9 @@

-ext.kotlin_version = '1.2.50'
+ext.kotlin_version = '1.2.41'

 buildscript {

-    ext.kotlin_version = '1.2.50'
+    ext.kotlin_version = '1.2.41'

     repositories {
         google()
```
Do step 2 again, and there is no error.