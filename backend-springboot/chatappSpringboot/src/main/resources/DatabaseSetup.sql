-- Create the database and user with proper permissions
CREATE DATABASE chatapp;

-- Create user if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_user WHERE usename = 'admin') THEN
        CREATE USER admin WITH PASSWORD 'chat';
    END IF;
END
$$;

-- Grant all privileges on the database
GRANT ALL PRIVILEGES ON DATABASE chatapp TO admin;

-- Connect to the chatapp database
\c chatapp;

-- Grant schema permissions
GRANT ALL ON SCHEMA public TO admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO admin;

-- Make admin the owner of the public schema
ALTER SCHEMA public OWNER TO admin;

-- Grant future permissions (for tables/sequences created later)
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO admin;
