CREATE TABLE ${schemaname}.OLGH17837ENTITY (ID NUMERIC(38) NOT NULL, INTVAL1 INTEGER, INTVAL2 INTEGER, STRVAL1 VARCHAR(255), STRVAL2 VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE ${schemaname}.COLTABLE1 (ent_id NUMERIC(38), COLVAL1 VARCHAR(255));
ALTER TABLE ${schemaname}.COLTABLE1 ADD CONSTRAINT COLTABLE1_ent_id FOREIGN KEY (ent_id) REFERENCES ${schemaname}.OLGH17837ENTITY (ID);