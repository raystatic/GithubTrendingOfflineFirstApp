# GithubTrendingOfflineFirstApp



https://user-images.githubusercontent.com/31301266/189485618-f0a8f7fd-8966-4eb2-a964-3632fc31a70c.mp4



## Overview
This app shows list of trending github repositories

## Architecture
### Follows MVVM architecture with Room Database as <b>Single Source of Truth</b><br>

### On new launch of the app, an API is request is sent <br>
### Response is fetched
    1. Synchronize room db with response fetched
    2. UI gets updated from room db
    
### Components used
    1. Retrofit
    2. Room DB
    3. Flows
    4. LiveData
    5. DiffUtil
    6. Dagger-Hilt
    
### Implementations
    1. Select/Unselect a particular repo
    2. Search from local list
    3. Supports orientation change
    4. Dark and light theme handled
    
### Light Mode Screenshots

<img
    height="480" src="https://user-images.githubusercontent.com/31301266/178583613-daa792d1-277b-417c-9140-8172a8b90d51.png"/> <img  height="480" src="https://user-images.githubusercontent.com/31301266/178583641-c094f926-99f6-4c0f-906d-ba0b18c00251.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178583643-087ef3c9-5055-4b3d-a7a1-45c2f2672913.png"/>  <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178583652-5d081d67-7d00-445d-b9dd-b33dafb3cf91.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178583656-48b412a8-9a1b-4aff-a11a-11c7d43e714e.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178583658-13e6b297-0acc-46f4-8121-37b94bbb711f.png"/>
        
### Dark Mode Screenshots
        
<img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585796-97f22077-1e0b-4b5b-b10d-8b3b54ea139e.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585819-59c45c95-c0e6-4f66-be26-27fb28c0ccbb.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585825-12783889-a0a8-4fe4-8ae5-f51c47e4f350.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585827-01a704da-8d0d-48cc-bf27-30cd45b51ec1.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585830-e5ef4d0e-97da-46db-86e2-10e06d721d87.png"/> <img
        height="480"
        src="https://user-images.githubusercontent.com/31301266/178585834-52646057-5b53-4ac1-b873-b4510442b6e3.png"/>



