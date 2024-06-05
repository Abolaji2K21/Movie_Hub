package utils;

import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mavericktube.MaverickHub.Models.Category.ACTION;

public class TestUtil {

    public static final String Test_Video_Location = "C:\\Users\\DELL\\Desktop\\MaverickHub\\src\\main\\resources\\static\\FunnyVid.mp4";

    public static final String Test_Image_Location = "C:\\Users\\DELL\\Desktop\\MaverickHub\\src\\main\\resources\\static\\file.jpg";

    public static UploadMediaRequest buildUploadRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("Funny Ved",inputStream );
        request.setMediaFile(file);
        request.setCategory(ACTION);
        request.setUserId(201L);
        return request;
    }
}
