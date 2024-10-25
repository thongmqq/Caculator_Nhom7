import 'package:flutter/material.dart';
import '../model/dog.dart';
import '../database/dog_provider.dart';

class DogAddScreen extends StatefulWidget {
  const DogAddScreen({super.key});

  @override
  State<StatefulWidget> createState() => DogAddScreenState();
}

class DogAddScreenState extends State<DogAddScreen> {
  @override
  Widget build(BuildContext context) {
    int id = 0;
    String name = "";
    int age = 0;
    return Scaffold(
        appBar: AppBar(
          title: Text('Nhập danh sách chó'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Padding(
                padding: EdgeInsets.all(10),
                child: TextField(
                  onChanged: (value) => id = int.parse(value),
                  decoration: const InputDecoration(
                    labelText: 'ID',
                  ),
                  keyboardType: TextInputType.number,
                ),
              ),
              Padding(
                padding: EdgeInsets.all(10),
                child: TextField(
                  onChanged: (value) => name = value,
                  decoration: const InputDecoration(
                    labelText: 'Tên chó',
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsets.all(10),
                child: TextField(
                  onChanged: (value) => age = int.parse(value),
                  decoration: const InputDecoration(
                    labelText: 'Tuổi',
                  ),
                  keyboardType: TextInputType.number,
                ),
              ),
              Padding(
                  padding: EdgeInsets.all(10),
                  child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Padding(
                          padding: EdgeInsets.all(10),
                          child: ElevatedButton(
                              onPressed: () async {
                                DogProvider dogProvider = DogProvider();
                                await dogProvider.open();
                                var dog = Dog(id: id, name: name, age: age);
                                await dogProvider.insertDog(dog);
                                print("Inserted dog: ");
                                await dogProvider.close();
                                Navigator.pop(context);
                              },
                              child: Text("Add")),
                        ),
                      ])),
            ],
          ),
        ));
  }
}
