-- =========================================================================
-- 1. ENUM TYPES
-- =========================================================================
CREATE TYPE user_role AS ENUM (
    'employee',
    'manager'
    );

-- Use 'deactivated' instead of 'deleted' to allow admin reactivation.
CREATE TYPE user_status AS ENUM (
    'active',
    'deactivated'
    );

CREATE TYPE chat_type AS ENUM (
    'dm',
    'group_open',
    'group_closed'
    );

CREATE TYPE message_type AS ENUM (
    'text',
    'image',
    'video'
    );

-- 'superseded' tracks historical edits; 'deleted' is a terminal soft-delete.
CREATE TYPE message_status AS ENUM (
    'active',
    'superseded',
    'deleted'
    );

-- =========================================================================
-- 2. USERS
-- =========================================================================
CREATE TABLE users (
                       id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                       email       varchar NOT NULL,
                       first_name  varchar NOT NULL,
                       last_name   varchar NOT NULL,
                       role        user_role NOT NULL,
                       status      user_status NOT NULL DEFAULT 'active',
                       created_at  timestamptz NOT NULL DEFAULT now()
);

-- Policy 1: Email addresses are globally unique and never reused,
-- even after a user is deactivated.
CREATE UNIQUE INDEX users_email_lower_uidx ON users (lower(email));

-- =========================================================================
-- 3. CHATS
-- =========================================================================
CREATE TABLE chats (
                       id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                       owner       uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
                       type        chat_type NOT NULL,
                       created_at  timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX chats_owner_idx ON chats (owner);

-- =========================================================================
-- 4. MESSAGE VERSIONING (Root Identity + Immutable History)
-- =========================================================================
-- 4a. Roots: Immutable identity of a message (never changes on edit).
CREATE TABLE message_roots (
                               id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                               chat        uuid NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
                               sender      uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
                               created_at  timestamptz NOT NULL DEFAULT now() -- Original send time
);

CREATE INDEX message_roots_chat_created_idx
    ON message_roots (chat, created_at DESC);
CREATE INDEX message_roots_sender_idx ON message_roots (sender);

-- 4b. Versions: Immutable content history (one row per edit).
CREATE TABLE message_versions (
                                  id           uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                  message_root uuid NOT NULL REFERENCES message_roots(id) ON DELETE CASCADE,
                                  type         message_type NOT NULL,
                                  content      varchar NOT NULL,
                                  status       message_status NOT NULL DEFAULT 'active',
                                  created_at   timestamptz NOT NULL DEFAULT now() -- When this version was authored
);

-- Enforce exactly one active version per message root.
CREATE UNIQUE INDEX message_versions_one_active_per_root
    ON message_versions (message_root)
    WHERE status = 'active';

CREATE INDEX message_versions_root_created_idx
    ON message_versions (message_root, created_at DESC);

-- =========================================================================
-- 5. CHAT MEMBERS
-- =========================================================================
CREATE TABLE chat_members (
                              chat              uuid NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
                              "user"            uuid NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    -- Points to the root, not a version, so edits don't shift readers' cursors.
                              last_read_message uuid REFERENCES message_roots(id) ON DELETE SET NULL,
                              joined_at         timestamptz NOT NULL DEFAULT now(),
                              PRIMARY KEY (chat, "user")
);

CREATE INDEX chat_members_user_idx      ON chat_members ("user");
CREATE INDEX chat_members_last_read_idx ON chat_members (last_read_message);