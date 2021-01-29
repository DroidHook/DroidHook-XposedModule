# DroidHook-XposedModule

**DroidHook** is a tool for dynamic analysis of Android applications. The goal of DroidHook is to provide a lightweight, compatible, easily expandable tool for Android security researchers and practitioners to monitor API calls of sample APKs. This repository hosts the Xposed module of DroidHook, which can monitor the specified Android application package and record the specified API calls with their parameters. The whole module is written in Kotlin, and strives for simplicity and ease of expansion.

## Build and Install

You can build the project with Android Studio, Gradle or any tools, but Android Studio is recommended.

This DroidHook module should work on any Android device with any implementation of Xposed framework. We have tested the [original Xposed](https://github.com/rovo89/Xposed) by rovo89 and [EdXposed](https://github.com/ElderDrivers/EdXposed).

When the module is installed and working properly, it reads the `/sdcard/PackageName` file and monitor the Android application with the package name.

## Expand

There is already some code in `io.github.droidhook.hook` to monitor several API calls such as `android.telephony.SmsManager.sendTextMessage`. It is easy to extend the monitor list and please refer to the sample code. The key steps are:

1. Create an `object` and implement `HookInterface` in `io.github.droidhook.module.hook`.
2. Use `XposedBridge.hookMethod` to hook the desired API. We have encapsulated `FindMethodUtil` and `LogUtil` to make it easier to find API calls and generate logs.
3. Start your `object` in `io.github.droidhook.module.xposed`.

## Collect Reports

The reports are written directly to the Xposed log. Please refer to the tool in [DroidHook-Host](https://github.com/DroidHook/DroidHook-Host) to extract and convert them to json files for further analysis.

## Publication

We are working on a paper about the details of DroidHook. We will update the information once it is ready for publication.
