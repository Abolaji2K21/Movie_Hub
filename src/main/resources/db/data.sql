truncate table  users cascade;
truncate table media cascade;


insert into users(id, email,password, time_created)values
(200, 'john@email.com','$2a$10$o1mApuoFN76VGiUxcHJJYeaaR2O70GDg2rXp3SWWPe4NXRojyJbRu', '2024-06-04T15:03:03.792009700'),
(201, 'jor@email.com','$2a$10$o1mApuoFN76VGiUxcHJJYeaaR2O70GDg2rXp3SWWPe4NXRojyJbRu', '2024-06-04T15:03:03.792009700'),
(202, 'jon@email.com','$2a$10$o1mApuoFN76VGiUxcHJJYeaaR2O70GDg2rXp3SWWPe4NXRojyJbRu', '2024-06-04T15:03:03.792009700'),
(203, 'ohn@email.com','$2a$10$o1mApuoFN76VGiUxcHJJYeaaR2O70GDg2rXp3SWWPe4NXRojyJbRu', '2024-06-04T15:03:03.792009700'),
(204, 'peter@email.com','$2a$10$o1mApuoFN76VGiUxcHJJYeaaR2O70GDg2rXp3SWWPe4NXRojyJbRu','2024-06-04T15:03:03.792009700');


-- insert into playlist(media[], )



insert into  media(id, category, description, url, time_created, uploader_id) values
    (100, 'DRAMA' ,'media 1', 'https://www.cloudinary.com/media1','2024-06-04T15:03:03.792009700' ,200),
    (101, 'ROMANCE' ,'media 2', 'https://www.cloudinary.com/media2','2024-06-04T15:03:03.792009700',201 ),
    (102, 'SCI_FI','media 3', 'https://www.cloudinary.com/media3','2024-06-04T15:03:03.792009700', 200 ),
    (103, 'COMEDY' ,'media 4', 'https://www.cloudinary.com/media4','2024-06-04T15:03:03.792009700' ,200);