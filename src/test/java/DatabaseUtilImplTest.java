import com.example.firsttomcat.model.impl.DatabaseUtilImpl;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DatabaseUtilImplTest {
    private static final String PATH ="src/main/webapp/images/error.png";

    DatabaseUtilImpl databaseUtil = new DatabaseUtilImpl();

    @Test
    public void testSaveFile() throws FileNotFoundException {
        File file = new File(PATH);
        InputStream inputStream = new FileInputStream(file);
        databaseUtil.uploadFile(inputStream, "error.png");
    }
}
