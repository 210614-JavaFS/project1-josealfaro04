DROP TABLE IF EXISTS ers_user_roles CASCADE;
DROP TABLE IF EXISTS ers_reimbursement_type CASCADE;
DROP TABLE IF EXISTS ers_reimbursement_status CASCADE;
DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE IF EXISTS ers_reimbursement CASCADE;


CREATE TABLE ers_user_roles(
	ers_user_role_id INTEGER PRIMARY KEY, 
	user_role VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_type(
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_status(
	reimb_status_id INTEGER PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE ers_users(
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) UNIQUE NOT NULL,
	ers_password VARCHAR(100) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL, 
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) UNIQUE NOT NULL,
	user_role_id INTEGER NOT NULL REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement(
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount NUMERIC(19,2) NOT NULL,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP, 
	reimb_description VARCHAR(250),
	--reimb_receipt BYTEA,
	reimb_author INTEGER NOT NULL REFERENCES ers_users(ers_users_id),
	reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id INTEGER NOT NULL REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id INTEGER NOT NULL REFERENCES ers_reimbursement_type(reimb_type_id)
);

INSERT INTO ers_user_roles(ers_user_role_id, user_role)
VALUES (1, 'employee'), (2, 'manager');

INSERT INTO ers_reimbursement_type (reimb_type)
VALUES ('food'), ('lodging'), ('travel'), ('other');

INSERT INTO ers_reimbursement_status(reimb_status_id, reimb_status)
VALUES (0, 'pending'), (1, 'accepted'), (2, 'denied');

INSERT INTO ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
VALUES ('jose123', '$2a$12$m87RKj1TZTsLRqfqr07aQOoYxDAXQVX9Vdcuiul7ZvOheprN0VKRi', 'jose', 'alfaro', 'jose@gmail.com', 1), ('joe321', '$2a$12$jdGv.I4cdOeOnJWX44qMNeZTNraULGtc289VHmGy3rQPxoUilRcom', 'joe', 'smith', 'joe@gmail.com', 2);

INSERT INTO ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id)
VALUES (300, 'testing purpose', 2, 0, 1);







