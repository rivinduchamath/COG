
INSERT INTO users (id, dlNumber,firstName,lastName,zipcode) VALUES
                                      (1,111,'Kamala', 'Gamage', 20555),
                                      (2,121,'Vimala','Perera',53332),
                                      (3,131,'Nayana','Kumari',23342),
                                      (4,132,'Amara','Kakuli',54422),
                                      (5,144,'Manika','Piumi',54422),
                                      (6,155,'Sunila','Jagoda',54422);


UPDATE hibernate_sequence
SET next_val = 7
where next_val = 1;
