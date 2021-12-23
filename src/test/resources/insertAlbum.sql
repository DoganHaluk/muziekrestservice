insert into albums(artiestId, naam)
values ((select id from artiesten where naam = 'test'), 'test');
insert into tracks(albumId, naam, tijd)
values ((select id from albums where naam = 'test'), 'test', CURRENT_TIME);