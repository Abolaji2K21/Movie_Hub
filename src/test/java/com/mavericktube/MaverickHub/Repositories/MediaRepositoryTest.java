package com.mavericktube.MaverickHub.Repositories;

import com.mavericktube.MaverickHub.Models.Media;
import com.mavericktube.MaverickHub.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
@Slf4j
class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void findAllMediaFor() throws UserNotFoundException {
        List<Media> media = mediaRepository.findAllMediaFor(200L);
        log.info("items-->{}", media);

        assertThat(media).hasSize(3);
}

}