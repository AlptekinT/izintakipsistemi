create table IZINTAKIP_MATERIALS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PERMISSION varchar(255),
    ROLE varchar(255),
    START_DATE date,
    FINISH_DATE date,
    STATUS varchar(255),
    --
    primary key (ID)
);