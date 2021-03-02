#import "EventChannelBackgroundIssuePlugin.h"
#if __has_include(<event_channel_background_issue/event_channel_background_issue-Swift.h>)
#import <event_channel_background_issue/event_channel_background_issue-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "event_channel_background_issue-Swift.h"
#endif

@implementation EventChannelBackgroundIssuePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftEventChannelBackgroundIssuePlugin registerWithRegistrar:registrar];
}
@end
