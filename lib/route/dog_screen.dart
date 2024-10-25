import 'package:flutter/material.dart';
import '../model/dog.dart';
import '../database/dog_provider.dart';

class DogListScreen extends StatefulWidget {
  const DogListScreen({super.key});

  @override
  State<StatefulWidget> createState() => DogListScreenState();
}

class DogListScreenState extends State<DogListScreen> {
  @override
  Widget build(BuildContext context) {
    int id;
    String name;
    int age;
    return Scaffold(
        appBar: AppBar(
          title: Text('Nhập danh sách chó'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  ElevatedButton(
                      onPressed: () {
                        Navigator.pushNamed(context, "/second");
                      },
                      child: const Text("Thêm chó")),
                  ElevatedButton(
                    onPressed: () async {
                      DogProvider dogProvider = DogProvider();
                      await dogProvider.open();
                      Dog? dog = await dogProvider.getDog(1 as Dog);
                      await dogProvider.close();
                      print(dog.toString());
                    },
                    child: const Text("Get Dog"),
                  )
                ],
              )
            ],
          ),
        ));
  }
}
