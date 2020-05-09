#import "AdsunityPlugin.h"
#if __has_include(<adsunity/adsunity-Swift.h>)
#import <adsunity/adsunity-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "adsunity-Swift.h"
#endif

@implementation AdsunityPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAdsunityPlugin registerWithRegistrar:registrar];
}
@end
