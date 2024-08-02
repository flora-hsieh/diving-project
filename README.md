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
The map view includes basic functions such as zoom in/out and a search bar. When you type the country name in the search bar, it will move the camera to the country you looked up and set a purple pin on it. Once the country is pinned, the Dive Site API, which I obtained from the Rapid API website, will call out the dive sites data and place red pins on the dive sites. This allows the user to get a comprehensive view of where the diving sites are located.

API link: https://rapidapi.com/jojokcreator/api/world-scuba-diving-sites-api/playground/apiendpoint_a124b53b-a0d6-43e6-810e-c86ddc91fb8d

The problem with using this API is that it only allows users to retrieve data 20 times per day.

![image](https://github.com/user-attachments/assets/91ff577f-8241-4c13-b668-1581d0d2a6e2)


5.	Google Map API – Clustering Function
When there are too many markers pinning spots on the map, the clustering feature will appear to show the number of pins that are clustered together. These clusters will spread out as you zoom in.
![image](https://github.com/user-attachments/assets/10c77e74-1636-4480-be46-7367554fb991)


6.	Dive Site API, Recyclerview – Dive Sites Explore page
The Dive Site API will be called again when you click on the “Dive Site” button, bringing you to the DiveSiteActivity. This will display a list of dive site names, the regions they are located in, the ocean names, and a beautiful photo of scuba diving scenes.

I registered another account to have an additional 20 requests per day limit specifically for this function. I created a model called DiveSite.java and an adapter called DiveSiteAdapter.java for this activity.

![image](https://github.com/user-attachments/assets/7b2962af-baba-4638-b90c-433518b44a94)

 
7.	Dive Center API, Recyclerview – Dive Center Explore page
When you click on the “Dive Center” button in the map view, it will bring you to the DiveCenterActivity. I created the model class DiveCenter.java and implemented a RecyclerView. Since most divers need to hire a qualified dive guide for their trips, learning about available dive centers is crucial. The Dive Center Activity provides users with information about the dive centers' names and the systems they follow (PADI, SDI, SSI). Divers can take this information into consideration.
![image](https://github.com/user-attachments/assets/31329ed3-8e68-4b89-91d9-cd500243b91b)



8.	Health information – Recyclerview, Expandable view
Before every dive, it’s important for divers to ensure they are in good shape and condition for diving. The “Health Info” button will bring the user to the HealthRecommendation.java Activity. There, they will see an expandable view of different sections of health recommendations that they need to read through and conduct self-checks on.
![image](https://github.com/user-attachments/assets/dda88973-8455-4a82-b65d-be39983e93ec) ![image](https://github.com/user-attachments/assets/3b560b89-cccf-4d4e-8d01-75e185299adb)




