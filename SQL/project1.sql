DROP TABLE IF EXISTS ers_user_roles CASCADE;
DROP TABLE IF EXISTS ers_reimbursemnet_type CASCADE;
DROP TABLE IF EXISTS ers_reimbursement_status CASCADE;
DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE IF EXISTS ers_user_roles CASCADE;


CREATE TABLE ers_user_roles(
	ers_user_role_id INTEGER PRIMARY KEY NOT NULL, 
	user_role VARCHAR(10) NOT NULL
)

CREATE TABLE ers_reimbursement_type(
	reimb_type_id INTEGER PRIMARY KEY NOT NULL,
	reimb_type VARCHAR(10) NOT NULL
)

CREATE TABLE ers_reimbursement_status(
	reimb_status_id INTEGER PRIMARY KEY NOT NULL,
	reimb_status VARCHAR(10) NOT NULL
)

CREATE TABLE ers_users(
	ers_users_id INTEGER PRIMARY KEY NOT NULL,
	ers_username VARCHAR(50) UNIQUE NOT NULL,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL, 
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) UNIQUE NOT NULL,
	user_role_id INTEGER NOT NULL REFERENCES ers_user_roles(ers_user_role_id)
)

CREATE TABLE ers_reimbursement(
	reimb_id INTEGER PRIMARY KEY NOT NULL,
	reimb_amount INTEGER NOT NULL,
	reimb_submitted TIMESTAMP NOT NULL,
	reimb_resolved TIMESTAMP, 
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,
	reimb_author INTEGER NOT NULL REFERENCES ers_users(ers_users_id),
	reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id INTEGER NOT NULL REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id INTEGER NOT NULL REFERENCES ers_reimbursement_type(reimb_type_id)
)











