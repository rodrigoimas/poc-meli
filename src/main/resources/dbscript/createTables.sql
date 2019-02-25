-- Table: solar.pronostico

-- DROP TABLE solar.pronostico;

CREATE TABLE solar.pronostico
(
    id integer NOT NULL DEFAULT nextval('solar."Pronostico_id_seq"'::regclass),
    dia bigint NOT NULL,
    clima text COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE solar.pronostico
    OWNER to postgres;