import 'dart:io';
import 'package:excute_query/model/dog.dart';
import 'package:flutter/foundation.dart';
import 'package:path/path.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';

class DogProvider {
  late Database db;
  final String tableName = 'dogs';

  Future open() async {
    if (kIsWeb) {
      databaseFactory = databaseFactoryFfiWeb;
    } else if (Platform.isWindows || Platform.isLinux) {
      databaseFactory = databaseFactoryFfi;
    }

    db = await openDatabase(
      join(await getDatabasesPath(), 'dogs_database.db'),
      onCreate: (db, version) {
        return db.execute(
          '''CREATE TABLE dogs (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          name TEXT NOT NULL,
          breed TEXT NOT NULL,
          age INTEGER NOT NULL
          )''',
        );
      },
    );
  }

  Future<Dog> insertDog(Dog dog) async {
    await db.insert(tableName, dog.toMap());
    return dog;
  }

  Future<Dog?> getDog(Dog dog) async {
    List<Map> maps = await db.query(
      tableName,
      where: 'id =?',
      whereArgs: [dog.id],
    );

    if (maps.isNotEmpty) {
      var data = maps.first;
      return Dog(id: data['id'], name: data['name'], age: data['age']);
    }
    return null;
  }

  Future<int> deleteDog(Dog dog) async {
    return await db.delete(tableName, where: 'id =?', whereArgs: [dog.id]);
  }

  Future<int> updateDog(Dog dog) async {
    return await db
        .update(tableName, dog.toMap(), where: 'id =?', whereArgs: [dog.id]);
  }

  Future close() => db.close();
}
