Title : Sample Article App
Brief : The Sample Article App is to display list of NY Most Popular Articles available. The app simply hit the https(GET) api to collect the list of article items.
Author : Vikas Grover, 13 December 2019


Build & Run The App :
1. Simply open the project named 'article app' : File -> Open
2. Run the app :  Run -> Run App
3. App can be run on emulators & physical Devices.
4. To run the app on real device , be sure to enable the USB debugging under Developer Options : Settings -> Additional Settings -> Developer Options -> USB Debugging
5. Android Packaging Kit (APK) can also be generated : Build -> Build Bundles/APK.
5. To Run apk directly to physical device, be sure to enable Unknown Source Option : Device & Privacy -> Unknown Resource


Run Test Cases & Calculate Coverage :
1. To run a single test, open the Project window, and then right-click a test and click Run.
2. To test all methods in a class, right-click a class or method in the test file and click Run.
3. To run all tests in a directory, right-click on the directory and select Run tests.
4. Define a run/debug configuration under Android JUnit to specify your package and select a few include/exclude options.
5. 'jacoco' plug need to be enabled to generate Coverage Report, apply plugin build.gradle(article app).
6. To generate reports execute the command './gradlew jacocoTestReportDebug'.



