# EnjoyMovie App

## Tools and Set-up
Rxjava2, dagger2 help in the set-up of the architecture. 
Stetho as a usefull devtool, build variant debug and release and injection are used to set it up correctly

CI with travis, check if release and debug flavor compile, also look at the Unit test at the moment
(Todo: run androidTest for the databases)

Retrofit and Picasso for download of images

## Architecture

The Architecture has 3 layers:
* Data
* Domain
* Presentation

![Alt text](https://github.com/guillaumevalverde/images/blob/master/architecture.png?raw=true "Title")

## DataLayer

Data is the layer which takes care of retrieving the data from network, disk or memory.
there is 

* service: fetch the data through an api
* store: store the data, can be in sharedPref, in Sql database, using contentProvider or in memory
* repository: combine both service and store, the repo is the link to the domain layer


The Store implemented is using a SQl database with the library Room. this one is used to save the details of Albums and Songs.

## Domain
we put the buissness logic to get a list with paging fetching

## Presentation
using MVVM with livedata and ViewModel
This is all ui related

## Component
RoomJsonStore, is a store backed up by Room(sql data base), this store serve the data, when the data
is put in the store we save the timestamp. then we can implement a deprecated method.


