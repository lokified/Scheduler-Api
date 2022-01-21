# Scheduler-Api
A REST API for querying and retrieving information about session and modules in a particular class. 


## API Features

  1. Users can create module.
  2. Users can create session
  3. Users can add session in a particular module and be able to search for them.
  4. User can add other users as well as add them in a module.
  5. Users can see all the users in a module as well as the sessions in the module.
  6. User can add announcements and see them as well.
### Dependencies

    1. [junit-jupiter-api] 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    2. [junit-jupiter-engine] 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
    3. [spark-core] 'com.sparkjava:spark-core:2.9.3'
    4. [slf4j-simple] 'org.slf4j:slf4j-simple:1.7.32'
    5. [gson] 'com.google.code.gson:gson:2.8.9'
    6. [sql2o]  group: 'org.sql2o', name: 'sql2o', version: '1.5.4'
    7. [postgresql] group: 'org.postgresql', name: 'postgresql', version: '42.2.2'    

   
## Getting Started

    1. git clone https://github.com/lokified/Scheduler-Api.git

    2. cd Scheduler-Api

    3. edit postgresql username and password in App.java 

    4. run psql < create.sql in the project root to create the database

    5. gradle run 


## Tests
    1. cd Scheduler-Api
    2. gradle test

## API Endpoints


| EndPoint                                |   Functionality                      |
| --------------------------------------- | ------------------------------------:|
| POST /module/new                        | Create a new module              |
| GET /module                             | View modules                     |
| GET /modules/:id/users                  | View users in a module               |
| DELETE module/:id/delete                | Delete a module        |
| POST /announcements/new                 | create announcement         |
| GET /announcements                      | View all announcements                  |
| POST /sessions/new                      | Create a session                  |
| POST /modules/:moduleId/user/new        | Add user to module                    |

## Limitations

  1. The API only responds with JSON

## Contribution

1. Fork it! :fork_and_knife:
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git add -A && git commit -m 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request