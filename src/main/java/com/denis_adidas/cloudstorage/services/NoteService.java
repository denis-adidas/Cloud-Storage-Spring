package com.denis_adidas.cloudstorage.services;

import com.denis_adidas.cloudstorage.mapper.NoteMapper;
import com.denis_adidas.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public boolean addNote(Note note) {
        return noteMapper.createNote(note) > 0;
    }

    public List<Note> getNoteByUser(int userId) {
        return noteMapper.getNotesByUser(userId);
    }

    public boolean updateNote(Note note) {
        Note optionalNote = noteMapper.getNoteById(note.getNoteId());

        if(optionalNote != null) {
            int updated = noteMapper.updateNote(note);
            return updated > 0;
        }
        return false;
    }

    public boolean deleteNote(int noteId) {
        Note optionalNote = noteMapper.getNoteById(noteId);

        if(optionalNote != null) {
            int deleted = noteMapper.deleteNoteById(noteId);
            return deleted > 0;
        }
        return false;
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }
}
