CREATE TABLE public.mailinglist
(
    mailinglist_id bigserial,
    datetime_start timestamp with time zone NOT NULL DEFAULT now(),
    message text NOT NULL,
    filter text NOT NULL,
    datetime_end timestamp with time zone NOT NULL DEFAULT null,
    PRIMARY KEY (mailinglist_id)
);

ALTER TABLE public.mailinglist
    OWNER to postgres;

CREATE TABLE public.client
(
    client_id bigserial NOT NULL,
    phone_number character varying(11) NOT NULL,
    operators_code CHAR(3) GENERATED ALWAYS AS ( SUBSTRING(phone_number, 2, 3) ) STORED,
    tag text NOT NULL,
    time_zone character varying(100) NOT NULL,
    PRIMARY KEY (client_id)
);

ALTER TABLE public.client
    OWNER to postgres;

CREATE TABLE public.message
(
    message_id bigserial NOT NULL,
    datetime_sending timestamp with time zone NOT NULL DEFAULT now(),
    status character varying(100) NOT NULL,
    mailinglist_id bigserial NOT NULL,
    client_id bigserial NOT NULL,
    PRIMARY KEY (message_id),
    CONSTRAINT mailinglist_id_fk FOREIGN KEY (mailinglist_id)
        REFERENCES public.mailinglist (mailinglist_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT client_id_fk FOREIGN KEY (client_id)
        REFERENCES public.client (client_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE public.message
    OWNER to postgres;