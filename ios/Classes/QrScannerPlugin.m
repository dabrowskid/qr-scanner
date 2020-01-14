#import "QrScannerPlugin.h"
#if __has_include(<qr_scanner/qr_scanner-Swift.h>)
#import <qr_scanner/qr_scanner-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "qr_scanner-Swift.h"
#endif

@implementation QrScannerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftQrScannerPlugin registerWithRegistrar:registrar];
}
@end
