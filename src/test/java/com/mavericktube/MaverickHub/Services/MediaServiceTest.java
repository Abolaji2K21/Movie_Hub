package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.Models.Category;
import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.dtos.requests.UpdateMediaRequest;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import com.mavericktube.MaverickHub.dtos.responds.UpdateMediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UploadMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mavericktube.MaverickHub.Models.Category.HORROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtil.Test_Image_Location;
import static utils.TestUtil.Test_Video_Location;

@SpringBootTest
@Slf4j
public class MediaServiceTest {

    @Autowired
    private MediaService mediaService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void uploadMediaTest() throws IOException {
        new UploadMediaRequest();
        Path path = Paths.get(Test_Image_Location);
        var inputStream= Files.newInputStream(path);
        UploadMediaRequest request = buildUploadRequest(inputStream);
        UploadMediaResponse response = mediaService.upload(request);
        log.info("response ->{}",response);
       assertThat(response).isNotNull();
        assertThat(response.getUrl()).isNotNull();


    }


    @Test
    public void uploadVideoMediaTest() throws IOException {
        Path path = Paths.get(Test_Video_Location);
        var inputStream= Files.newInputStream(path);

        UploadMediaRequest request = buildUploadRequest(inputStream);
        UploadMediaResponse response = mediaService.uploadVed(request);
        assertThat(response).isNotNull();
        assertThat(response.getUrl()).isNotNull();

    }

    @Test
    @DisplayName("test that media can be retrieved by id ")
    @Sql(scripts = {"/db/data.sql"})
    public void getMedia(){
         Media media = mediaService.getById(100L);
         assertThat(media).isNotNull();
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void uploadImageTest() throws IOException {
        UpdateMediaRequest request = new UpdateMediaRequest();
        request.setId(100L);
        request.setCategory(HORROR);
        request.setDescription("Sweet terror");
        UpdateMediaResponse response = mediaService.update(request);
        Media media = mediaService.getById(response.getId());
        assertThat(media).isNotNull();
        assertThat(media.getId()).isNotNull();
        assertEquals("Sweet terror", media.getDescription());
}



    private static UploadMediaRequest buildUploadRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("Funny Ved",inputStream );
        request.setMediaFile(file);
        request.setCategory(Category.ACTION);
        request.setUserId(201L);
        return request;
    }
}
