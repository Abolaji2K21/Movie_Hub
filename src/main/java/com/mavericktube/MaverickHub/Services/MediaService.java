package com.mavericktube.MaverickHub.Services;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.dtos.requests.UpdateMediaRequest;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import com.mavericktube.MaverickHub.dtos.responds.MediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UpdateMediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UploadMediaResponse;
import com.mavericktube.MaverickHub.exceptions.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    UploadMediaResponse upload(UploadMediaRequest request);

    Media getMediaBy(Long id);

    UpdateMediaResponse updateMedia(Long mediaId, JsonPatch updateMediaRequest);

    List<MediaResponse> getMediaFor(Long userId) throws UserNotFoundException;
}
