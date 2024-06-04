package com.mavericktube.MaverickHub.Services;

import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.dtos.requests.UpdateMediaRequest;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import com.mavericktube.MaverickHub.dtos.responds.UpdateMediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UploadMediaResponse;

import java.io.IOException;

public interface MediaService {

    UploadMediaResponse upload (UploadMediaRequest request) throws IOException;
    UploadMediaResponse uploadVed (UploadMediaRequest request) throws IOException;


    UpdateMediaResponse update (UpdateMediaRequest request) throws IOException;

    Media getById(Long userId);



}
