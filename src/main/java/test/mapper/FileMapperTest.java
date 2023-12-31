package test.mapper;

import com.denis_adidas.cloudstorage.CloudStorageApplication;
import com.denis_adidas.cloudstorage.mapper.FileMapper;
import com.denis_adidas.cloudstorage.mapper.UserMapper;
import com.denis_adidas.cloudstorage.model.File;
import com.denis_adidas.cloudstorage.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CloudStorageApplication.class)
@ExtendWith(SpringExtension.class)
class FileMapperTest {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    private static User testUser;

    private File testFile;

    @BeforeAll
    static void beforeAll() {
        testUser = new User(null, "johnnydoe", "someSalt", "password", "John", "Doe");
    }

    @BeforeEach
    void setUp() {
        userMapper.createUser(testUser);
        testUser = userMapper.getUser(testUser.getUsername());
        byte[] fileData = new byte[20];
        testFile = new File(null, "somefile.png", "image/png", "32", fileData, testUser.getUserId(), 0);
        fileMapper.insertFile(testFile);
    }

    @Test
    void getFileById() {
        File fileById = fileMapper.getFileById(1);
        assertThat(fileById.getFilename()).isEqualTo(testFile.getFilename());
        assertThat(fileById.getFileData()).isEqualTo(testFile.getFileData());
    }


    @Test
    void insertFile() {
        byte[] fileData = new byte[20];
        int created = fileMapper.insertFile(new File(null, "somefile.png", "image/png", "32", fileData, testUser.getUserId(), 0));
        assertThat(created).isGreaterThan(0);
    }

    @Test
    void deleteFile() {
        int deleted = fileMapper.deleteFile(1);
        assertThat(deleted).isGreaterThan(0);
    }

    @Test
    void getAllFiles() {
        List<File> allFiles = fileMapper.getAllFiles();
        assertThat(allFiles).isNotEmpty();

        File file = allFiles.get(0);
        assertThat(file.getContentType()).isEqualTo(testFile.getContentType());
        assertThat(file.getFileSize()).isEqualTo(testFile.getFileSize());
    }

    @Test
    void getFilesByUser() {
        fileMapper.insertFile(testFile);
        List<File> filesByUser = fileMapper.getFilesByUser(1);
        assertThat(filesByUser).isNotEmpty();
        assertThat(filesByUser.size()).isGreaterThan(1);

        File file = filesByUser.get(0);
        assertThat(file).isNotNull();
        assertThat(file.getFilename()).isEqualTo(testFile.getFilename());
        assertThat(file.getFileData()).isEqualTo(testFile.getFileData());
    }
}