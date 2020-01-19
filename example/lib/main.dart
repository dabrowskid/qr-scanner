import 'dart:async';

import 'package:flutter/material.dart';
import 'package:qr_scanner/qr_scanner.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
   String _barcodeResult = 'Unknown';

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_barcodeResult'),
        ),
        floatingActionButton: FloatingActionButton(

          child: Icon(Icons.center_focus_weak),
          onPressed:scan,
        ),
      ),
    );
  }

  Future scan() async {
    try {
      String barcode = await QrScanner.scan();
      setState(() {
        _barcodeResult = barcode;
      });
      print("RESULT from flutter: ");
      print(barcode);
    } catch (e) {
      print(e);
    }
  }
}
