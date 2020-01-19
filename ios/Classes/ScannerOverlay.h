//
//  ScannerOverlay.h
//  Pods-Runner
//
//  Created by Dawid Dąbrowski on 1/19/20.
//

#import <UIKit/UIKit.h>

@interface ScannerOverlay : UIView
@property(nonatomic) CGRect scanLineRect;

- (void) startAnimating;
- (void) stopAnimating;
@end

