# Cars Sample App iOS

This is a simple iOS client which has been built to access the REST services the web app provides. This is built using Swift 4.0, iOS SDK 12.1 and XCode 10.1

## Security Settings

iOS requires that a app explicitly states URLs where it's permitted to make plain text HTTP connections. This is currently configured to allow connections to "localhost" which is fine if you are running in the Simulator to talk back to locally running instance of Cars_Sample_App.

To add additional hosts to this:

1. Open the project in XCode
1. Locate the Info.plist file in The Project Navigator
1. Navigate through the tree
	1. App Transport Secuirty Settings
	1. Exception Domains
1. Here you will see a entry for "localhost"
1. Copy and Paste this to create a new entry
1. You should now have "localhost - 2". Update this with the new domain
1. Rebuild the app

You can also just update the localhost entry alone if you don't want to have that any more
