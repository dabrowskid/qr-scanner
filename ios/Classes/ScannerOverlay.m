//
//  ScannerOverlay.m
//  Pods-Runner
//
//  Created by Dawid Dąbrowski on 1/19/20.
//

#import "ScannerOverlay.h"

@interface ScannerOverlay()
  @property(nonatomic, retain) UIView *line;
@end

@implementation ScannerOverlay
  
  - (instancetype)initWithFrame:(CGRect)frame
  {
    self = [super initWithFrame:frame];
    if (self) {
      _line = [[UIView alloc] init];
      _line.backgroundColor = UIColor.redColor;
      _line.translatesAutoresizingMaskIntoConstraints = NO;
      [self addSubview:_line];
    }
    return self;
  }

- (void)drawRect:(CGRect)rect {
  CGContextRef context = UIGraphicsGetCurrentContext();
  
  UIColor * overlayColor = [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.55];
  
  CGContextSetFillColorWithColor(context, overlayColor.CGColor);
  CGContextFillRect(context, self.bounds);
  
  // make a hole for the scanner
  CGRect holeRect = [self scanRect];
  CGRect holeRectIntersection = CGRectIntersection( holeRect, rect );
  [[UIColor clearColor] setFill];
  UIRectFill(holeRectIntersection);
  
  // draw a horizontal line over the middle
  CGRect lineRect = [self scanLineRect];
  _line.frame = lineRect;

  
}
  
  - (void)startAnimating {
    CABasicAnimation *flash = [CABasicAnimation animationWithKeyPath:@"opacity"];
    flash.fromValue = [NSNumber numberWithFloat:0.0];
    flash.toValue = [NSNumber numberWithFloat:1.0];
    flash.duration = 0.25;
    flash.autoreverses = YES;
    flash.repeatCount = HUGE_VALF;
    [_line.layer addAnimation:flash forKey:@"flashAnimation"];
  }
  
  - (void)stopAnimating {
    [self.layer removeAnimationForKey:@"flashAnimation"];
  }
  
  - (CGRect)scanRect {
    CGRect rect = self.frame;
    CGFloat scanRectWidth = rect.size.width * 0.8f;
    CGFloat scanRectHeight = scanRectWidth;
    CGFloat scanRectOriginX = (rect.size.width / 2) - (scanRectWidth / 2);
    CGFloat scanRectOriginY = (rect.size.height / 2) - (scanRectHeight / 2);
    return CGRectMake(scanRectOriginX, scanRectOriginY, scanRectWidth, scanRectHeight);
  }
  
  - (CGRect)scanLineRect {
    CGRect scanRect = [self scanRect];
    CGRect rect = self.frame;
    return CGRectMake(scanRect.origin.x, rect.size.height / 2, scanRect.size.width, 1);
  }

@end
