create table genres
(
    id    bigint auto_increment primary key,
    genre varchar(255) null
);

create table roles
(
    id   bigint auto_increment primary key,
    role varchar(255) null
);

create table platforms
(
    id       bigint auto_increment primary key,
    platform varchar(255) null
);

create table users
(
    id       bigint auto_increment primary key,
    password varchar(255) not null,
    username varchar(20)  not null,
    constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)
);

create table users_roles
(
    user_entity_id bigint not null,
    roles_id       bigint not null,
    constraint FK7v417qhe0i2m9h8njggvciv00 foreign key (user_entity_id) references users (id),
    constraint FKa62j07k5mhgifpp955h37ponj foreign key (roles_id) references roles (id)
);

create table movies
(
    id                 bigint auto_increment primary key,
    bulgarian_language bit            null,
    description        varchar(255)   null,
    duration           int            not null,
    imdb_url           varchar(255)   not null,
    poster_url         varchar(255)   not null,
    rating             decimal(19, 2) null,
    title1             varchar(50)    not null,
    title2             varchar(50)    null,
    trailer_url        varchar(255)   not null,
    year               int            not null,
    user_id            bigint         null,
    constraint FKtfuo09vaplh15krjjrakmvva7 foreign key (user_id) references users (id)
);

create table movies_genres
(
    movie_entity_id bigint not null,
    genres_id       bigint not null,
    constraint FKabwobqnegu888274nercpwc9p foreign key (genres_id) references genres (id),
    constraint FKck8j62p5bpfo04dah1dm2h0ul foreign key (movie_entity_id) references movies (id)
);

create table movies_platforms
(
    movie_entity_id bigint not null,
    platforms_id    bigint not null,
    constraint FKqck4gmmk37vakjv8wrrnvxa62 foreign key (movie_entity_id) references movies (id),
    constraint FKqj3wts32bd9fokd5suuqyxlc9 foreign key (platforms_id) references platforms (id)
);