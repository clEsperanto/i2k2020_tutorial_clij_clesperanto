## Count_blobs
by Robert Haase
Tue Nov 17 10:48:10 CET 2020

Counts blobs in grey value images

In order to build this plugin, you need to install and Java Development kit, version 8 or higher, e.g. [OpenJDK](https://openjdk.java.net/).
Furthermore, you need [maven](https://maven.apache.org/). To make it work easily, it is recommended to add mavens `/bin/` 
directory to the PATH variable of your environment (How to: 
[Windows](https://answers.microsoft.com/en-us/windows/forum/windows_10-other_settings/adding-path-variable/97300613-20cb-4d85-8d0e-cc9d3549ba23)
[Linux](https://opensource.com/article/17/6/set-path-linux)
[MacOS](https://support.apple.com/guide/terminal/use-environment-variables-apd382cc5fa-4f58-4449-b20a-41c53c006f8f/mac)
).

You can then build it by running
```
mvn package
```

This will generate a file called `clijx-assistant-count_blobs_-0.1.0.0.jar`. 
Copy it to your Fijis `/plugins/` directory and restart Fiji to run the plugin. 
You will find it in the menu `Plugins > ImageJ on GPU (CLIJx-Assistant) > Custom > Count_blobs`.

