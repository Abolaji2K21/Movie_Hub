package com.mavericktube.MaverickHub.Services;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
    UpdateMediaResponse updateOne (Long mediaId, JsonPatch UpdateMediaRequest ) throws IOException, JsonPatchException;


    Media getById(Long userId);



}
