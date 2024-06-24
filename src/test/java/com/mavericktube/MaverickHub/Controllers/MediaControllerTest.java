package com.mavericktube.MaverickHub.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.TestUtil.Test_Video_Location;
import static utils.TestUtil.buildUploadRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts ={"/db/data.sql"})
class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMediaController() throws Exception {
        try(InputStream inputStream = Files.newInputStream(Paths.get(Test_Video_Location))){
            MultipartFile file = new MockMultipartFile("mediaFile", inputStream);
            mockMvc.perform(multipart("/api/v1/media")
                            .file(file.getName(), file.getBytes())
                            .part(new MockPart("userId", "200".getBytes()))
                            .part(new MockPart("description", "test description".getBytes()))
                            .part(new MockPart("category", "HORROR".getBytes()))
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                            .andExpect(status().isCreated())
                    .andDo(print());
        } catch (Exception exception) {
            throw  exception;
}
}

    @Test
    public void testGetMediaUser(){
        try{
            mockMvc.perform(get("/api/v1/media?userId=200")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception exception) {
            assertThat(exception).isNull();
    }
}


    @Test
    public void testGetMediaForUserShouldFailForInvalidUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/media?userId=20000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());


    }
}