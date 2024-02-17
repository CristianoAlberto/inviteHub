# Invite Hub Api
This is a simple API for managing guests.
## Functionalities

- [x] Create guest
- [x] Edit a guest
- [x] Get a specific guest
- [x] List guest

## Technology Used

- Java SpringBoot

## Pré-requisitos

- [Java jdk 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Environment Setting

1. **Clone this repository:**

    ```bash
    git clone https://github.com/CristianoAlberto/inviteHub.git
    cd inviteHub
    ```

2. **MySQL Database Configuration:**

   Before running the API, you need to set up a MySQL instance for the database. Follow the steps below:

   2.1. **MySQL Installation:**
   Make sure you have MySQL installed on your machine. You can download it [here](https://dev.mysql.com/downloads/mysql/).

## Usage Examples

### List guests
```http
Endpoint: GET /guests/
```

### Create a new guest
```http
Endpoint: POST /create
Content-Type: application/json
Body:
{
  "name": "Josue Ndombaxe",
  "number": 935000000,
  "gender": "M",
  "confirmation":true
}
```
### Update information for a guest
```http
Endpoint: PUT /update/{id}
Content-Type: application/json
Body:
{
  "name": "Marcelo Emanuel",
  "number": 945000000,
  "gender": "M",
  "confirmation":false
}
```
### Find a guest by ID
```http
Endpoint: GET /{id}
Content-Type: application/json
```

### Delete a guest by ID
```http
Endpoint: DELETE /{id}
```

**This comprehensive guide makes it easy to set up your environment, including installing MySQL. If you have any questions or issues, please feel free to get in touch. ❤🐱‍👤✨**
