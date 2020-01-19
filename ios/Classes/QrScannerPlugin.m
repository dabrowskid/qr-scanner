#import "QrScannerPlugin.h"
#import "QrScannerViewController.h"


@implementation QrScannerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel *channel = [FlutterMethodChannel methodChannelWithName:@"pl.carbuddy.qr_scanner" binaryMessenger:registrar.messenger];
    
    QrScannerPlugin *instance = [QrScannerPlugin new];
    instance.hostViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall *)call result:(FlutterResult)result {
    if ([@"scanCode" isEqualToString:call.method]) {
        self.result = result;
        [self showBarcodeView];
    } else {
        result(FlutterMethodNotImplemented);
    }
}

- (void)showBarcodeView {
    QrScannerViewController *scannerViewController = [[QrScannerViewController alloc] init];
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:scannerViewController];
    scannerViewController.delegate = self;
    [self.hostViewController presentViewController:navigationController animated:NO completion:nil];
}


- (void)barcodeScannerViewController:(QrScannerViewController *)controller didScanBarcodeWithResult:(NSString *)result {
    if (self.result) {
        self.result(result);
    }
}

- (void)barcodeScannerViewController:(QrScannerViewController *)controller didFailWithErrorCode:(NSString *)errorCode {
    if (self.result){
        self.result([FlutterError errorWithCode:errorCode
                                        message:nil
                                        details:nil]);
    }
}
@end
