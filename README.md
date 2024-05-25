# Getting Started
run as normal springboot port 8080

on service layer,change the qualifier name that can interchange the dependency to inmemory collection repository or file repository

assumption to be thread safe using synchronize and atomic because of the requirement to use inmemory variable and using single file repository

assumption that this will be 1 replica/pod/node only because of the design

### Reference Documentation
can be test on given endpoints

· POST /notes: Create a new note.
· GET /notes: Retrieve all notes.
· GET /notes/:id: Retrieve a specific note by ID.
· PUT /notes/:id: Update a specific note.
· DELETE /notes/:id: Delete a specific note.

curl --location 'localhost:8080/notes'

curl --location 'localhost:8080/notes' \
--header 'Content-Type: application/json' \
--data '{
"title": "test title",
"body": "body me"
}'

curl --location --globoff --request PUT 'localhost:8080/notes/{id}' \
--header 'Content-Type: application/json' \
--data '{
"title": "test title",
"body": "body me222"
}'

curl --location --globoff --request DELETE 'localhost:8080/notes/{id}' \
--header 'Content-Type: application/json' \
--data '{
"title": "test title",
"body": "body me"
}'

curl --location --globoff 'localhost:8080/notes/{id}' \

