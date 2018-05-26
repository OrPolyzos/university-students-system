LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users`  VALUES
(1,'Glykeria@Katsari.com','Glykeria','Katsari','JEGJBewHfRsoL','Admin')
,(2,'Glykeria@Argyroiliopoulos.com','Glykeria','Argyroiliopoulos','pebwZGWVqKAJPA','Student')
,(3,'Glykeria@Peristeris.com','Glykeria','Peristeris','eJTnrQ','Teacher')
,(4,'Glykeria@Vlahopoulos.com','Glykeria','Vlahopoulos','ZgpSdPWrjvJ','Teacher')
,(5,'Spyros@Katsari.com','Spyros','Katsari','cEnixfHk','Teacher')
,(6,'Spyros@Argyroiliopoulos.com','Spyros','Argyroiliopoulos','wPxjdJKCnvCnHDQ','Teacher')
,(7,'Spyros@Vlahopoulos.com','Spyros','Vlahopoulos','tUAbhcerohzEi','Student')
,(8,'Spyros@Kormaris.com','Spyros','Kormaris','yzkURaeNF','Student')
,(9,'Spyros@Kotsidimos.com','Spyros','Kotsidimos','YgifbpQNo','Student')
,(10,'Christos@Katsari.com','Christos','Katsari','AxSHozIIyqHluW','Student')
,(11,'Christos@Peristeris.com','Christos','Peristeris','WGPcOPsv','Student')
,(12,'Christos@Vlahopoulos.com','Christos','Vlahopoulos','FsIQhRqhbxk','Student')
,(13,'Christos@Kotsidimos.com','Christos','Kotsidimos','VhgHgbbR','Student')
,(14,'Christos@Psarrakos.com','Christos','Psarrakos','fQffJnvvFoyMbnC','Student')
,(15,'Christos@Melakis.com','Christos','Melakis','TSBqTxavygjxs','Student')
,(16,'John@Katsari.com','John','Katsari','iCCNeqBgms','Student')
,(17,'John@Argyroiliopoulos.com','John','Argyroiliopoulos','wHrOvQsZenORnOe','Student')
,(18,'John@Vlahopoulos.com','John','Vlahopoulos','ZYgFtGQCUl','Student')
,(19,'John@Melakis.com','John','Melakis','nAxPgChldwl','Student')
,(20,'John@Matthaios.com','John','Matthaios','SHMXNxNcdkSR','Student')
,(21,'Nasos@Katsari.com','Nasos','Katsari','KbyBzpSnOffRL','Student')
,(22,'Nasos@Peristeris.com','Nasos','Peristeris','pXUOXDvRZw','Student')
,(23,'Nasos@Kormaris.com','Nasos','Kormaris','MfGYNZk','Student')
,(24,'Nasos@Kotsidimos.com','Nasos','Kotsidimos','vgpOzGwnajhK','Student')
,(25,'Nasos@Melakis.com','Nasos','Melakis','UcFKrjOstzgP','Student')
,(26,'Giannis@Katsari.com','Giannis','Katsari','NmoiUS','Student')
,(27,'Giannis@Argyroiliopoulos.com','Giannis','Argyroiliopoulos','AjIlPyAvUZ','Student')
,(28,'Giannis@Vlahopoulos.com','Giannis','Vlahopoulos','MXzxLyTEt','Student')
,(29,'Giannis@Psarrakos.com','Giannis','Psarrakos','UPMsHtcpIxxHC','Teacher')
,(30,'Giannis@Pagonis.com','Giannis','Pagonis','DZJQUchrsOg','Teacher')
,(31,'admin@admin.com','Admin','Admin','admin123','Admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses`  VALUES
(1,1,'Analush I'),
(2,2,'Analush II'),
(3,3,'Java I'),
(4,5,'Java II'),
(5,4,'Web I'),
(6,6,'Web II'),
(7,7,'SQL I'),
(8,8,'SQL II');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_courses` WRITE;
/*!40000 ALTER TABLE `user_courses` DISABLE KEYS */;
INSERT INTO `user_courses`  VALUES
(1,1,'a2'),
(1,2,'a2'),
(1,3,'a2'),
(1,4,'a2'),
(1,5,'a2'),
(1,6,'a2'),
(2,6,'a2');
/*!40000 ALTER TABLE `user_courses` ENABLE KEYS */;
UNLOCK TABLES;