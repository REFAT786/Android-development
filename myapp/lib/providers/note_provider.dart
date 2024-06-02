// lib/providers/note_provider.dart
import 'package:flutter/material.dart';
import 'package:uuid/uuid.dart';  // Import uuid package

import '../models/note.dart';

class NoteProvider with ChangeNotifier {
  final List<Note> _notes = [];

  List<Note> get notes => _notes;

  void addNote(String title, String content) {
    _notes.add(Note(
      id: Uuid().v4(),  // Generate unique id
      title: title,
      content: content,
    ));
    notifyListeners();
  }

  void deleteNote(String id) {
    _notes.removeWhere((note) => note.id == id);
    notifyListeners();
  }
}
