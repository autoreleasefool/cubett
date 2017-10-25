# Cubett

Cubett, or Cube Terminal Timer, is a command-line based application for timing speedcube solves.

Built because the iOS app I use to time solves hasn't been updated in years and had no built in importing/exporting functionality. This application will give me complete control over my solve data, on my local system, and options to export to CSV so I can always switch to a new app in the future.

I'm mostly building this app as a stepping stone to building an Android/iOS speedcube timing application. Additionally, Kotlin may not have been the best tool for the job, but it'll get the job done and gives me an opportunity to learn the language before I dive into a bigger project that I'm hoping for more use out of. This will serve mainly my own purposes, but others are welcome to use it.

## How to run

When the first version releases, this should be updated with a link to the build

For now, you can build the app yourself, but it won't do much. You'll need to have Kotlin and Java installed on your system.

```
git clone https://github.com/josephroque/cubett.git
cd cubett
kotlinc com/cubett/ -include-runtime -d Cubett.jar
java -jar Cubett.jar
```

Or, alternatively, use the build/run script, `run.sh`, to build and run the app.
