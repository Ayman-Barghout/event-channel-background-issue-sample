import 'package:event_channel_background_issue/event_channel_background_issue.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  final List<String> _data = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('EventChannel Background Issue Sample'),
      ),
      body: Center(
        child: SingleChildScrollView(
          child: StreamBuilder(
            stream: EventChannelBackgroundIssue.getData,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                print('Data Received: ${snapshot.data}'
                    'Total Data in List: ${_data.length}');
                _data.add(snapshot.data);
                return Text('Latest Data: ${snapshot.data}');
              }
              return Text('No Data');
            },
          ),
        ),
      ),
    );
  }
}
