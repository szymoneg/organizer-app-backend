# Ogranizer 
Serwer aplikacji "Organizer" służacy do przechowywania notatek oraz planowania zadań na kalendarzu.
https://github.com/szymoneg/organizer-app-frontend

# Endpointy

# User Service
### Rejestracja użytkownia - method - POST
```
127.0.0.1:8080/user/register
```
```json
{
    "username":"newuser",
    "password":"password",
    "email":"newuser@mail.com"
}
```

### Logowanie użytkownika - method - POST
```
127.0.0.1:8080/user/login
```
```json
{
    "username": "newuser",
    "password": "password"
}
```

### Dane użytkownika - method - GET
```
127.0.0.1:8080/user/details/{username}
127.0.0.1:8080/user/details/newuser
```
Odpowiedz serwera
```json
{
    "username": "newuser",
    "email": "newuser@mail.com",
    "name": null,
    "surname": null,
    "phoneNumber": 0
}
```

### Edycja użytkownika - method - PUT
```
127.0.0.1:8080/user/edit
```
```json
{
    "username":"newuser",
    "name": "Szymon",
    "surname": "Bilinski",
    "phoneNumber": 123456789
}
```

# Note Service

### Dodanie nowej notatki - method - POST
```
127.0.0.1:8080/note/add
```
```json
{
    "idUser":20,
    "titleNote": "new note",
    "descriptionNote": "new description note"
}
```

### Wyświetlanie notatek - method - GET
```
127.0.0.1:8080/note/getAll/{username}
127.0.0.1:8080/note/getAll/newuser
```
Odpowiedz serwera
```json
[
    {
        "idUser": 20,
        "idNote": 21,
        "titleNote": "new note",
        "descriptionNote": "new description note"
    }
]
```

### Dane notatki - method - GET
```
127.0.0.1:8080/note/getById/{idNote}
127.0.0.1:8080/note/getById/21
```
Odpowiedz serwera
```json
{
  "titleNote": "new note",
  "descriptionNote": "new description note"
}
```

### Edycja notatki - method - PUT
```
127.0.0.1:8080/note/edit
```
```json
{
    "idNote": 21,
    "titleNote": "edit title note",
    "descriptionNote":"edit desc note"
}
```

### Usuwanie notatki - method - DELETE
```
127.0.0.1:8080/note/deleteById/{idNote}
127.0.0.1:8080/note/deleteById/21
```