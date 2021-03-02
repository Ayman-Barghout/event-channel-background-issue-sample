import Flutter
import UIKit

public class SwiftEventChannelBackgroundIssuePlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "event_channel_background_issue", binaryMessenger: registrar.messenger())
    let instance = SwiftEventChannelBackgroundIssuePlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
