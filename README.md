# ForeDocuments-v1.0
исходный код плагина ForeDocuments-v1.0 для версии 1.16.5
Плагин универсальный имеет гибкую настройку каждого сообщения и открытый код
Весь код и плагин находиться под моим копирайтом и лицензией AGPLv3 с дополнительным условием.
### ПРОЧИТАЙТЕ LICENSE

## Команды:
- foredocuments / fd reload - перезагрузка конфигурации ( если в конфиге включен параметр reload_with_updatedata то при релоаде будет обновляться еще data.yml
- foredocuments / fd updatedata - обновляет данные в data.yml Например: вы создали новый документ в конфиге и во избежания ошибок при этой команде data.yml обновляется и записывает новый документ каждому записанному игроку в файле так же наоборот работает с удалением документа из конфига
- foredocuments / fd give <имя_документа> <игрок> - выдача документа игроку
- foredocuments / fd take <имя_документа> <игрок> - изъятие документа игроку

## Пермишен
- foredocuments.use-admin - разрешение на использование основной команды /foredocuments ( алиас - /fd )

## PlaceholderAPI Expansion
- %foredocuments_<название_документа>%
