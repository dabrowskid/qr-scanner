//
//  QrScannerViewControllerDelegate.h
//  Pods
//
//  Created by Dawid Dąbrowski on 1/19/20.
//

#import <Foundation/Foundation.h>

@class QrScannerViewController;

@protocol QrScannerViewControllerDelegate <NSObject>

- (void)barcodeScannerViewController:(QrScannerViewController *)controller didScanBarcodeWithResult:(NSString *)result;

- (void)barcodeScannerViewController:(QrScannerViewController *)controller didFailWithErrorCode:(NSString *)errorCode;

@end
