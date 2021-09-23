DO
$$
    BEGIN

        -- Entities
        RAISE INFO 'Creating entities %', NOW();

        CREATE TABLE IF NOT EXISTS push_campaign
        (
                    "id"                UUID            NOT NULL,
                    "title"             VARCHAR(60)     NOT NULL,
                    "description"       VARCHAR(150)    NOT NULL,
                    "segment"           VARCHAR         NOT NULL,
                    "action"            VARCHAR         NOT NULL,
                    "initial_date"      TIMESTAMP       NOT NULL,
                    "end_date"          TIMESTAMP       NOT NULL,
                    "release_hour"      TIME            NOT NULL,
                    "period"            VARCHAR,
                    "frequency"         INTEGER,
                    "created_on"        TIMESTAMP       NOT NULL,
                    "updated_on"        TIMESTAMP,
                    "deleted_on"        TIMESTAMP,

                    CONSTRAINT push_campaign_pk
                        PRIMARY KEY ("id")

        );
        CREATE INDEX IF NOT EXISTS id_deleted_on_idx ON push_campaign (id, deleted_on);

    END
$$