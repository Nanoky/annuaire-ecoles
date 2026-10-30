CREATE TABLE villes (
    id UUID PRIMARY KEY,
    name VARCHAR(300) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE ecoles (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sigle VARCHAR(255) NOT NULL UNIQUE,
    ville_id UUID REFERENCES villes (id),
    statut INTEGER NOT NULL,
    filieres VARCHAR(255)[] NOT NULL,
    diplomes VARCHAR(255)[] NOT NULL,
    description VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    website VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE INDEX idx_ecoles_ville_id ON ecoles (ville_id);
