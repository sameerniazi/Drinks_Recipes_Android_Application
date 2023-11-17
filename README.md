# Drinks_Recipes_Android_Application
**Drinks Recipes Android Application**

**Scenario:**
Mr. X needs a mobile application in android, he wants to get recipes of different types of drinks which can be searched by name or by a starting alphabet. He wants to save his favorite drinks so that he can view those favorites without internet. (A filled star is shown right next to each favorite item) In addition, he also wants to see if the drink is alcoholic (a checkbox is selected if it is alcoholic).
**Development Requirements:**

 Application should be created in KOTLIN
 It is recommended to use MVVM architecture for this implementation.
 API calls for getting response.
o Searchbyname:https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
o Searchbystartingalphabet:https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a
 Initially when the application is loaded, you can pass “margarita” in the search by name API call
 Use Retrofit for implementing API calls.
 Image thumbnails should be circular as seen in mockup below.
 Implement Recyclerview.
 Use a custom row item for each recycler or list view. Take row item reference from mockup below.
 You must store last state of the user e.g.
o Searchbyname
o Searchbyalphabet
o If user check any of the above by name or alphabet every time user see his last state.
 Save favorite drinks in new fragment name as “Favorite Fragment”
o It must save the favorite drinks info including drink images in database.
o This feature must work without using internet connection so keep in mind that image should be load
without internet.
<img width="880" alt="Screenshot 2023-11-17 at 6 43 02 AM" src="https://github.com/sameerniazi/Drinks_Recipes_Android_Application/assets/58805395/9583f258-ecc5-4564-940f-c4bb8accd3c0">
