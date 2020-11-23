# Getting Started

### Creating a new MariaDB instance using Docker


``$ docker run -p 3306:3306 -d --name mariadb -eMARIADB_ROOT_PASSWORD=Password123! mariadb/server:10.4``

``$ docker exec -it containerID bash``

``$ mysql --host 127.0.0.1 -P 3306 --user root -pPassword123!``



