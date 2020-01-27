# qr_scanner

A new flutter plugin project.

This package is solely created to read polish AZTEC codes in cars registration document. 
It uses

Android: 
https://github.com/yuriy-budiyev/code-scanner

https://github.com/dabrowskid/Z3SBarcodeScanner 

(above are compiled with changes locally and statically added as local libraries)

iOS: https://github.com/mikebuss/MTBBarcodeScanner

code inspired by: 
https://github.com/apptreesoftware/flutter_barcode_reader

## Getting Started

Import to your project  
```
dependencies:
  qr_scanner:
    git:
      url: git://github.com/dabrowskid/qr-scanner.git
```

Add permissions to camera 

AndroidManifest.xml
```
<uses-permission android:name="android.permission.CAMERA"></uses-permission>
```

Info.plist
```
	<key>NSCameraUsageDescription</key>
	<string>Scan Aztec Code</string>
```

And then : 

```
import 'package:qr_scanner/qr_scanner.dart';

String barcode = await QrScanner.scan();
```
