Project 3rd Submission
Name: Fu-Syuan Hsieh
Student ID: 300384318
Project Name: Diving Project
Version of Android Studio used: Jellyfish
Features:
1.	Splash Screen
I created a splash screen as the theme of the App, the activities in the app will share the same background which makes it looks more like a fully functioning app.
 ![image](https://github.com/user-attachments/assets/23d1ac3a-aea3-4fdb-b2d7-2d566f7e52bf)

2.	Google Map API –Fetch Device Location
For the map view, I chose to use Google Map API since I believe Google Map is what all the people are familiar with and know how to navigate. When you first open the app, the App will automatically asked for permission to access the device location. You can select your preference. If you do not want to, it won’t locate your device.
 ![image](https://github.com/user-attachments/assets/e74b63f4-90ef-40af-840d-c3b8fd17f7d4)
![image](https://github.com/user-attachments/assets/95e253f4-6e76-4a56-a1eb-2a6ff04fa3fc)

 
3.	Google Map API, Dive Site API –Pinned your search
The map view includes basic function such as zoom in/out and a search bar. When you type the country name in the search bar, it will move the camera to the country you looked up and set a purple pin on it. As the country is pinned out, the Dive Site API I got from the Rapid API website will call out the dive sites data and put red pins on the dive site. Therefore, the user can have a big picture of where the diving sites are located.
API link: https://rapidapi.com/jojokcreator/api/world-scuba-diving-sites-api/playground/apiendpoint_a124b53b-a0d6-43e6-810e-c86ddc91fb8d
The problem of using this API is that it only allows users to get data 20 times per day.
![image](https://github.com/user-attachments/assets/91ff577f-8241-4c13-b668-1581d0d2a6e2)


4.	Google Map API – Clustering Function
When there are way too may markers that are pining the spots on the map, the clustering feature will appear to show the number of the pins that are clustered together, and will spread out as you zoom in.
![image](https://github.com/user-attachments/assets/10c77e74-1636-4480-be46-7367554fb991)


5.	Dive Site API, Recyclerview – Dive Sites Explore page
The Dive Site API will be called again as you click on the “Dive Site” button and Bring you to the DiveSiteActivity. It will display a list of the names of dive sites, the region the dive sites are located in, the ocean name of the site and a beautiful photo of scuba diving scenes. 
I registered another account so I can have another 20 times a day limit just for this function. I created a model DiveSite.java and a DiveSiteAdapter.java for this Activity
![image](https://github.com/user-attachments/assets/7b2962af-baba-4638-b90c-433518b44a94)

 
7.	Dive Center API, Recyclerview – Dive Center Explore page
When you clicked on the “Dive Center” button on the map view, it will bring you to the DiveCenterActivity. I created the model class DiveCenter.java and Recyclerview. Since most of the divers need to hire a qualified dive guide for their trip, learning what Dive Centers are out there is crucial. The Dive Center Activity will provide user with the information of the Dive Center’s name and the System they follow (PADI, SDI,SSI). Divers can take those into consideration.
![image](https://github.com/user-attachments/assets/31329ed3-8e68-4b89-91d9-cd500243b91b)



8.	Health information – Recyclerview, Expandable view
Before evey dive, it’s important for divers to make sure they are in good shape and condition for diving. The button “Health Info” will bring the user to the HealthRecommondation.java Activity. There, they will see the expandable view of different sections of the health recommendation they need to read through and conduct self-check on.
![image](https://github.com/user-attachments/assets/dda88973-8455-4a82-b65d-be39983e93ec) ![image](https://github.com/user-attachments/assets/3b560b89-cccf-4d4e-8d01-75e185299adb)




