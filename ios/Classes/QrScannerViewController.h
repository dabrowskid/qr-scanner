//
//  QrScannerViewController.h
//  Pods-Runner
//
//  Created by Dawid Dąbrowski on 1/19/20.
//

#import <Foundation/Foundation.h>
#import <MTBBarcodeScanner/MTBBarcodeScanner.h>

#import "QrScannerViewControllerDelegate.h"
#import "ScannerOverlay.h"


@interface QrScannerViewController : UIViewController
@property(nonatomic, retain) UIView *previewView;
@property(nonatomic, retain) ScannerOverlay *scanRect;
@property(nonatomic, retain) MTBBarcodeScanner *scanner;
@property(nonatomic, weak) id<QrScannerViewControllerDelegate> delegate;

  
  -(id) initWithOptions:(NSDictionary *) options;
@end


