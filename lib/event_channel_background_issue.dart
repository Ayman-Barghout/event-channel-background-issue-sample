import 'package:flutter/services.dart';

class EventChannelBackgroundIssue {
  static const EventChannel _eventChannelOne =
      const EventChannel('event_channel_one');

  static Stream<dynamic> get getData {
    return _eventChannelOne.receiveBroadcastStream();
  }
}
