package com.mavericktube.MaverickHub.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.Repositories.MediaRespository;
import com.mavericktube.MaverickHub.dtos.requests.UpdateMediaRequest;
import com.mavericktube.MaverickHub.dtos.requests.UploadMediaRequest;
import com.mavericktube.MaverickHub.dtos.responds.UpdateMediaResponse;
import com.mavericktube.MaverickHub.dtos.responds.UploadMediaResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class MediaServiceImpl implements MediaService{


    private final MediaRespository mediaRespository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;


//    @Autowired
//    public MediaServiceImpl(MediaRespository mediaRespository, Cloudinary cloudinary, ModelMapper modelMapper) {
//        this.mediaRespository = mediaRespository;
//        this.cloudinary = cloudinary;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) throws IOException {
        User user= userService.getById(request.getUserId());
        Map <?,?> response = cloudinary.uploader().upload(request.getMediaFile().getBytes(), null);
        log.info("cloudinary upload response :: {}", response);
        String Url = response.get("url").toString();
//
        Media media = modelMapper.map(request,Media.class);
        media.setUrl(Url);
        media.setUploader(user);
        media = mediaRespository.save(media);

       return modelMapper.map(media, UploadMediaResponse.class);

//        mediaResponse.setMediaUrl(media.getUrl());
//        mediaResponse.setId(media.getId());
//        mediaResponse.setDescriptions(media.getDescription());
//
//        mediaRespository.save(media);
//        return mediaResponse;
    }


    @Override
    public UploadMediaResponse uploadVed(UploadMediaRequest request) {
        try {
            var response = cloudinary.uploader().upload(request.getMediaFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            media = mediaRespository.save(media);
            return modelMapper.map(media, UploadMediaResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
}
}

    @Override
    public UpdateMediaResponse update(UpdateMediaRequest request) throws IOException {
        Media existingMedia = getById(request.getId());
        existingMedia.setDescription(request.getDescription());
        existingMedia.setCategory(request.getCategory());

        Media updatedMedia = mediaRespository.save(existingMedia);
        return modelMapper.map(updatedMedia, UpdateMediaResponse.class);
    }

    @Override
    public UpdateMediaResponse updateOne(Long mediaId, JsonPatch UpdateMediaRequest) throws IOException, JsonPatchException {

        Media media = getById(mediaId);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mediaNode =  objectMapper.convertValue(media, JsonNode.class);
        mediaNode = UpdateMediaRequest.apply(mediaNode);
        media = objectMapper.convertValue(mediaNode, Media.class);
        media = mediaRespository.save(media);
        return modelMapper.map(media,UpdateMediaResponse.class);
    }



    @Override
    public Media getById(Long id) {
        return mediaRespository.findById(id).orElseThrow();
    }
}
