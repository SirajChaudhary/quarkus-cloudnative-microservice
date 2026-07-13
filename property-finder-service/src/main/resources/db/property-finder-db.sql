-- ============================================================================
-- Property Finder Service Database Script
-- ============================================================================
-- PostgreSQL
--
-- 1. Execute CREATE DATABASE while connected to the postgres database.
-- 2. Connect to propertyfinderdb.
-- 3. Execute the remaining statements.
-- ============================================================================

CREATE DATABASE propertyfinderdb;

-- Connect to propertyfinderdb before executing the statements below.
-- Example (psql):
-- \c propertyfinderdb

CREATE TABLE IF NOT EXISTS properties (
    id BIGSERIAL PRIMARY KEY,
    propertycode VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    listingtype VARCHAR(20) NOT NULL,
    propertytype VARCHAR(30) NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    locality VARCHAR(150),
    address VARCHAR(500) NOT NULL,
    bedrooms INTEGER,
    bathrooms INTEGER,
    balconies INTEGER,
    areasqft NUMERIC(10,2),
    floor INTEGER,
    totalfloors INTEGER,
    furnishingstatus VARCHAR(30),
    parkingavailable BOOLEAN,
    petfriendly BOOLEAN,
    availablefrom DATE,
    ownername VARCHAR(150),
    ownercontact VARCHAR(20),
    featured BOOLEAN DEFAULT FALSE,
    active BOOLEAN DEFAULT TRUE,
    status VARCHAR(30) NOT NULL,
    createdat TIMESTAMP NOT NULL,
    updatedat TIMESTAMP NOT NULL
);

CREATE INDEX idx_properties_city ON properties(city);
CREATE INDEX idx_properties_listingtype ON properties(listingtype);
CREATE INDEX idx_properties_propertytype ON properties(propertytype);
CREATE INDEX idx_properties_price ON properties(price);

INSERT INTO properties (
propertycode,title,description,listingtype,propertytype,price,
city,state,country,locality,address,bedrooms,bathrooms,balconies,
areasqft,floor,totalfloors,furnishingstatus,parkingavailable,
petfriendly,availablefrom,ownername,ownercontact,featured,
active,status,createdat,updatedat)
VALUES

('PROP-1001ABCD','Sample Property 1','Sample villa located in Hyderabad.','SALE','VILLA',1500000,
'Hyderabad','Telangana','India','Locality 1','Street 1, Hyderabad',2,2,1,
1020,1,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-02','Owner 1','9876501001',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1002ABCD','Sample Property 2','Sample apartment located in Bengaluru.','RENT','APARTMENT',20000,
'Bengaluru','Karnataka','India','Locality 2','Street 2, Bengaluru',3,3,2,
1140,2,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-03','Owner 2','9876501002',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1003ABCD','Sample Property 3','Sample house located in Pune.','SALE','HOUSE',4500000,
'Pune','Maharashtra','India','Locality 3','Street 3, Pune',4,4,0,
1260,3,12,'UNFURNISHED',TRUE,TRUE,'2026-08-04','Owner 3','9876501003',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1004ABCD','Sample Property 4','Sample commercial located in Chennai.','RENT','COMMERCIAL',25000,
'Chennai','Tamil Nadu','India','Locality 4','Street 4, Chennai',5,1,1,
1380,4,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-05','Owner 4','9876501004',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1005ABCD','Sample Property 5','Sample farm_house located in Mumbai.','SALE','FARM_HOUSE',7500000,
'Mumbai','Maharashtra','India','Locality 5','Street 5, Mumbai',1,2,2,
1500,5,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-06','Owner 5','9876501005',TRUE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1006ABCD','Sample Property 6','Sample villa located in Hyderabad.','RENT','VILLA',30000,
'Hyderabad','Telangana','India','Locality 6','Street 6, Hyderabad',2,3,0,
1620,6,12,'UNFURNISHED',TRUE,TRUE,'2026-08-07','Owner 6','9876501006',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1007ABCD','Sample Property 7','Sample apartment located in Bengaluru.','SALE','APARTMENT',10500000,
'Bengaluru','Karnataka','India','Locality 7','Street 7, Bengaluru',3,4,1,
1740,7,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-08','Owner 7','9876501007',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1008ABCD','Sample Property 8','Sample house located in Pune.','RENT','HOUSE',35000,
'Pune','Maharashtra','India','Locality 8','Street 8, Pune',4,1,2,
1860,8,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-09','Owner 8','9876501008',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1009ABCD','Sample Property 9','Sample commercial located in Chennai.','SALE','COMMERCIAL',13500000,
'Chennai','Tamil Nadu','India','Locality 9','Street 9, Chennai',5,2,0,
1980,9,12,'UNFURNISHED',TRUE,TRUE,'2026-08-10','Owner 9','9876501009',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1010ABCD','Sample Property 10','Sample farm_house located in Mumbai.','RENT','FARM_HOUSE',40000,
'Mumbai','Maharashtra','India','Locality 10','Street 10, Mumbai',1,3,1,
2100,10,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-11','Owner 10','9876501010',TRUE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1011ABCD','Sample Property 11','Sample villa located in Hyderabad.','SALE','VILLA',16500000,
'Hyderabad','Telangana','India','Locality 11','Street 11, Hyderabad',2,4,2,
2220,11,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-12','Owner 11','9876501011',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1012ABCD','Sample Property 12','Sample apartment located in Bengaluru.','RENT','APARTMENT',45000,
'Bengaluru','Karnataka','India','Locality 12','Street 12, Bengaluru',3,1,0,
2340,0,12,'UNFURNISHED',TRUE,TRUE,'2026-08-13','Owner 12','9876501012',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1013ABCD','Sample Property 13','Sample house located in Pune.','SALE','HOUSE',19500000,
'Pune','Maharashtra','India','Locality 13','Street 13, Pune',4,2,1,
2460,1,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-14','Owner 13','9876501013',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1014ABCD','Sample Property 14','Sample commercial located in Chennai.','RENT','COMMERCIAL',50000,
'Chennai','Tamil Nadu','India','Locality 14','Street 14, Chennai',5,3,2,
2580,2,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-15','Owner 14','9876501014',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1015ABCD','Sample Property 15','Sample farm_house located in Mumbai.','SALE','FARM_HOUSE',22500000,
'Mumbai','Maharashtra','India','Locality 15','Street 15, Mumbai',1,4,0,
2700,3,12,'UNFURNISHED',TRUE,TRUE,'2026-08-16','Owner 15','9876501015',TRUE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1016ABCD','Sample Property 16','Sample villa located in Hyderabad.','RENT','VILLA',55000,
'Hyderabad','Telangana','India','Locality 16','Street 16, Hyderabad',2,1,1,
2820,4,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-17','Owner 16','9876501016',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1017ABCD','Sample Property 17','Sample apartment located in Bengaluru.','SALE','APARTMENT',25500000,
'Bengaluru','Karnataka','India','Locality 17','Street 17, Bengaluru',3,2,2,
2940,5,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-18','Owner 17','9876501017',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1018ABCD','Sample Property 18','Sample house located in Pune.','RENT','HOUSE',60000,
'Pune','Maharashtra','India','Locality 18','Street 18, Pune',4,3,0,
3060,6,12,'UNFURNISHED',TRUE,TRUE,'2026-08-19','Owner 18','9876501018',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1019ABCD','Sample Property 19','Sample commercial located in Chennai.','SALE','COMMERCIAL',28500000,
'Chennai','Tamil Nadu','India','Locality 19','Street 19, Chennai',5,4,1,
3180,7,12,'FULLY_FURNISHED',TRUE,TRUE,'2026-08-20','Owner 19','9876501019',FALSE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
('PROP-1020ABCD','Sample Property 20','Sample farm_house located in Mumbai.','RENT','FARM_HOUSE',65000,
'Mumbai','Maharashtra','India','Locality 20','Street 20, Mumbai',1,1,2,
3300,8,12,'SEMI_FURNISHED',TRUE,TRUE,'2026-08-21','Owner 20','9876501020',TRUE,
TRUE,'AVAILABLE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);