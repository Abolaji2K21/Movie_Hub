package com.mavericktube.MaverickHub.Services;


import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.mavericktube.MaverickHub.Models.Category;
import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.dtos.requests.UpdateMediaRequest;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import com.mavericktube.MaverickHub.dtos.responds.MediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UpdateMediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UploadMediaResponse;
import lombok.Data;
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
import java.util.List;

import static com.mavericktube.MaverickHub.Models.Category.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtil.*;

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
    public void updateImageTest() throws IOException {
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

    @Test
    @DisplayName("Test that media can be retrieved")
    @Sql(scripts = {"/db/data.sql"})
    public void updateMediaTest() throws IOException, JsonPointerException, JsonPatchException {
        Category category = mediaService.getById(100L).getCategory();
        assertThat(category).isEqualTo(DRAMA);

        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),new TextNode(ROMANCE.name()))
        );
        JsonPatch updateMediaRequest = new JsonPatch(operations);
        UpdateMediaResponse response = mediaService.updateOne(100L, updateMediaRequest);
        System.out.println(response);
        assertThat(response).isNotNull();
        assertThat(category).isNotNull();
        category = mediaService.getById(100L).getCategory();
        assertThat(category).isEqualTo(ROMANCE);

    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void getMediaForUserTest(){
        Long userId = 200L;
        List<MediaResponse>  media = mediaService.getMediaFor(userId);
        assertThat(media).hasSize(3);
    }


}
