import 'dart:async';

import 'package:flutter/services.dart';

class QrScanner {
  static const MethodChannel _channel =
      const MethodChannel('pl.carbuddy.qr_scanner');

  static Future<String> scan() async => await _channel.invokeMethod("scanCode");
}
