# Bazel 6 + Bzlmod Java Debug issue Example

Example project that illustrates a bug when debugging Java code the IntelliJ IDE running the Bazel plugin.

https://github.com/bazelbuild/intellij/issues/4841

## Description of the bug:

I am encountering an issue when attempting to debug a Bazel `java_binary` target in a Bazel 6 project with bzlmod enabled.
When attempting to invoke the target and attach the IntelliJ debugger via the UI, I received the following error:

```
ERROR: Unable to find package for @[unknown repo 'intellij_aspect' requested from @]//:java_classpath.bzl: The repository '@[unknown repo 'intellij_aspect' requested from @]' could not be resolved: No repository visible as '@intellij_aspect' from main repository.
```

The command that specifies the intellij_aspect uses a single `@` in the repo identifier.

```
Command: /usr/local/bin/bazelisk build --tool_tag=ijwb:IDEA:ultimate --curses=no --color=yes --progress_in_terminal_title=no --aspects=@intellij_aspect//:java_classpath.bzl%java_classpath_aspect --override_repository=intellij_aspect=/home/myuser/.local/share/JetBrains/Toolbox/apps/IDEA-U/ch-0/231.8770.65.plugins/ijwb/aspect --output_groups=runtime_classpath --build_event_binary_file=/tmp/intellij-bep-b4854b50-ce5a-42eb-b409-98aea4a40960 --nobuild_event_binary_file_path_conversion -- //src/hello_java:Main
```

A similar issue involving aspects required that the repo identifier be modified to include the `@@` prefix and override
the strict dependency checks.

https://github.com/bazelbuild/intellij/issues/4067

## What's the simplest, easiest way to reproduce this bug? Please provide a minimal example if possible.

- Open this project in IntelliJ with the Bazel plugin installed
- Sync Bazel project from dropdown menu (`Bazel > Sync > Sync Project with BUILD Files`)
- Open `./src/hello_java/java/com/github/jstuder_gh/examples/bzlmod/Main.java`
- Apply a breakpoint at line 9 of `Main.java`
- Right click the run arrow (in the left gutter) and select Debug

## Which Intellij IDE are you using? Please provide the specific version.

```
Intellij IDEA 2023.1.1 Ultimate edition

Build #IU-231.8770.65, built on April 27, 2023

Runtime version: 17.0.6+10-b829.9 amd64

VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.

```

## What programming languages and tools are you using? Please provide specific versions.

Java (Amazon Corretto 11.0.19). Although I don't think this issue is specific to a particular version

## What Bazel plugin version are you using?

`2023.04.18.0.1-api-version-231`

## Have you found anything relevant by searching the web?

This is similar to the issue found here:

https://github.com/bazelbuild/intellij/issues/4067

and addressed here:

https://github.com/bazelbuild/intellij/commit/48820e5fa4cc57f1ea607b845e5cba1854adf361
