package test.mapper;

import com.denis_adidas.cloudstorage.CloudStorageApplication;
import com.denis_adidas.cloudstorage.mapper.NoteMapper;
import com.denis_adidas.cloudstorage.mapper.UserMapper;
import com.denis_adidas.cloudstorage.model.Note;
import com.denis_adidas.cloudstorage.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = CloudStorageApplication.class)
@ExtendWith(SpringExtension.class)
public class NoteMapperTest {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    private Note testNote;
    private static User testUser;

    @BeforeAll
    static void beforeAll() {
        //Create user
        testUser = new User(null, "johnnydoe", "someSalt", "password", "John", "Doe");
    }

    @BeforeEach
    void setUp() {
        userMapper.createUser(testUser);
        testUser = userMapper.getUser(testUser.getUsername());
        testNote = new Note(null, "About me", "Hey there, I build great software", testUser.getUserId());
        noteMapper.createNote(testNote);
    }

    @Test
    void getNoteById() {
        Note noteById = noteMapper.getNoteById(1);

        System.out.println(noteById.getNoteDescription());

//        assertThat(noteById).isNotNull();
//        assertThat(noteById.getUserId()).isEqualTo(testUser.getUserId());
//        assertThat(noteById.getNoteTitle()).isEqualTo(testNote.getNoteTitle());
    }

    @Test
    void getNoteByTitle() {
        Note noteByTitle = noteMapper.getNoteByTitle(testNote.getNoteTitle());

        assertThat(noteByTitle).isNotNull();
        assertThat(noteByTitle.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(noteByTitle.getNoteTitle()).isEqualTo(testNote.getNoteTitle());
        assertThat(noteByTitle.getNoteDescription()).isEqualTo(testNote.getNoteDescription());
    }

    @Test
    void createNote() {
        int updated = noteMapper.createNote(new Note(null, "Monday task", "Send email to John", testUser.getUserId()));
        assertThat(updated).isGreaterThan(0);
    }

    @Test
    void updateNote() {
        Note newNote = new Note(1, "More About me", "I build great software and various computer systems.", testUser.getUserId());
        int updated = noteMapper.updateNote(newNote);
        assertThat(updated).isGreaterThan(0);

        Note noteById = noteMapper.getNoteById(1);
        assertThat(noteById.getNoteTitle()).isEqualTo(newNote.getNoteTitle());
        assertThat(noteById.getNoteDescription()).isEqualTo(newNote.getNoteDescription());
    }


    @Test
    void deleteNoteById() {
        int deleted = noteMapper.deleteNoteById(1);
        assertThat(deleted).isGreaterThan(0);
    }

    @Test
    void getAllNotes() {
        //retrieve note from database
        List<Note> notes = noteMapper.getAllNotes();
        assertThat(notes).isNotEmpty();

        Note note = notes.get(0);
        assertThat(note.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(note.getNoteTitle()).isEqualTo(testNote.getNoteTitle());
    }

    @Test
    void getNotesByUser() {
        List<Note> notesByUser = noteMapper.getNotesByUser(testUser.getUserId());
        assertThat(notesByUser).isNotEmpty();

        Note note = notesByUser.get(0);
        assertThat(note.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(note.getNoteTitle()).isEqualTo(testNote.getNoteTitle());
    }
}