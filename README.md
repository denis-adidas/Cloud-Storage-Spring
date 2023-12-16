# Эта тема реально крутая
1. **Простое файловое хранилище:** загружать, удалять, скачивать файлы, делиться ими. Есть директории
2. **Записки:** можно оставлять записки, удалять их и редактировать
3. **Хранение паролей:** небольшая утилита для хранения паролей

## Структура проекта:
Приложение позволяет регистрироваться и проходить аутефикацию с помощью Spring Security, все работает на базе даннных PostgreSQL. 

1. Back-end with Spring
2. The front-end with Thymleaf
3. Data in PostgreSQL


# It's better than google drive really

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.  


## Project Structure
A website on the Spring Framework that provides authenticated access and allows a user to save a list of files, notes,
and website credentials on a server.
 
1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. 3. Data in PostgreSQL


## Запуск\Launch: 

1. mvn clean install
2. cd target
3. java -jar cloudstorage-0.0.1-SNAPSHOT.jar

Открыть свой браузер и написать localhost:8080/ (если не работает, то localhost:8080/login)
Open your browser and enter localhost:8080/ (if it isn't work, then localhost:8080/login)

P.S
Нужно в application.propertis поменять базу данных на свою. Таблица должна будет создаться автоматически, но если не создаться, то потребуется создать 4 таблицы: files, users, notes, credentials. 
You need change DB in application.propertis. Table should be create automatically, but if not, then you need create 4 tables: files, users, notes, credentials.





