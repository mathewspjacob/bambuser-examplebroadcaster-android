<div>
  <br/><br />
  <p align="center">
    <a href="https://irisplatform.io" target="_blank" align="center">
        <img src="https://irisplatform.io/static/images/company/iris-by-bambuser-black-horisontal.png" width="280">
    </a>
  </p>
  <br /><br />
  <h1>Iris broadcast SDK for Android sample code</h1>
</div>

Sample code for broadcasting using the Iris broadcast SDK for Android.

The sample code in this project contains the bare minimum required to start broadcasting.


## Getting started

1. Install Android Studio.
2. Clone the sample code from this repository, so you have a local directory named i.e. `ExampleBroadcaster`.
3. Sign up for an account on https://irisplatform.io/ if you don't already have one.
4. Download the Android SDK zip from https://dashboard.irisplatform.io/developer and open the zip file.
5. Copy the `libbambuser-x.x.x.aar` file to the `ExampleBroadcaster/libbambuser` folder.
6. Import the `ExampleBroadcaster` project in Android Studio.
7. Edit `ExampleBroadcaster/libbambuser/build.gradle` and point it to the version of the `.aar` file.
8. Generate an `applicationId` at https://dashboard.irisplatform.io/developer
9. Open the `BroadcasterActivity.java` file in Android Studio and insert the `applicationId` generated above.
10. Run the sample code on your device and start a broadcast. The broadcast should show up on https://dashboard.irisplatform.io/content


The Iris platform by Bambuser can be found at https://irisplatform.io/,
and technical documentation can be found at https://irisplatform.io/docs/
