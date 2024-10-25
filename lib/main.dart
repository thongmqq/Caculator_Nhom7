import 'package:excute_query/route/dog_screen_add.dart';
import 'package:flutter/material.dart';
import 'route/dog_screen.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/': (context) => DogListScreen(),
        '/second': (context) => DogAddScreen(),
      },
    );
  }
}
