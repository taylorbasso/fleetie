CREATE TABLE IF NOT EXISTS company
(
    id         BINARY(16)   NOT NULL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user
(
    id            BINARY(16)             NOT NULL PRIMARY KEY,
    company_id    BINARY(16)             NOT NULL,
    username      VARCHAR(255)           NOT NULL UNIQUE,
    password_hash VARCHAR(255)           NOT NULL,
    role          ENUM ('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    created_at    TIMESTAMP                       DEFAULT CURRENT_TIMESTAMP,
    account_non_expired BOOLEAN DEFAULT 1,
    enabled BOOLEAN DEFAULT 1,
    account_non_locked BOOLEAN DEFAULT 1,
    credentials_non_expired BOOLEAN DEFAULT 1,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS vehicle
(
    id         BINARY(16)   NOT NULL PRIMARY KEY,
    company_id BINARY(16)   NOT NULL,
    make       VARCHAR(100) NOT NULL,
    model      VARCHAR(100) NOT NULL,
    year       INT,
    identifier VARCHAR(100),
    type       VARCHAR(50),
    vin        VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS service_log
(
    id                        BINARY(16)   NOT NULL PRIMARY KEY,
    vehicle_id                BINARY(16)   NOT NULL,
    service_date              DATE         NOT NULL,
    service_type              VARCHAR(100) NOT NULL,
    odometer                  INT,
    notes                     VARCHAR(255),
    cost                      DECIMAL(10, 2),
    created_at                TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_service_log_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);

