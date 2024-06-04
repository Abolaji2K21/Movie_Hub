truncate table  users cascade;
truncate table media cascade;


insert into users(id, email,password, time_created)values
(200, 'john@email.com','password', '2024-06-04T15:03:03.792009700'),
(201, 'jor@email.com','password', '2024-06-04T15:03:03.792009700'),
(202, 'jon@email.com','password', '2024-06-04T15:03:03.792009700'),
(203, 'ohn@email.com','password', '2024-06-04T15:03:03.792009700'),
(204, 'peter@email.com','password','2024-06-04T15:03:03.792009700');



insert into  media(id, category, description, url, time_created) values
    (100, 'DRAMA' ,'media 1', 'https://www.cloudinary.com/media1','2024-06-04T15:03:03.792009700' ),
    (101, 'ROMANCE' ,'media 2', 'https://www.cloudinary.com/media2','2024-06-04T15:03:03.792009700' ),
    (102, 'SCI_FI','media 3', 'https://www.cloudinary.com/media3','2024-06-04T15:03:03.792009700' ),
    (103, 'COMEDY' ,'media 4', 'https://www.cloudinary.com/media4','2024-06-04T15:03:03.792009700' );