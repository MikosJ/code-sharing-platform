The purpose of this project was to create a web application that enables its users to store code snippets in the database. The user should be able to retrieve the snippet freely when it's not restricted. When there are restrictions applied it can be shown only for a limited amount of time or for a limited view count.
Here is the functionality of the app:
1. The input of the code snippet without any restrictions. /code/new POST request
<img width="985" alt="no restriction web" src="https://user-images.githubusercontent.com/56031072/206565428-b60bde70-aaa0-44fa-89d2-a700aac65065.png">

2. The input of the code snippet with time and view restrictions. /code/new POST request
<img width="985" alt="restriction" src="https://user-images.githubusercontent.com/56031072/206565534-e3f8bac7-e4d7-4c47-b38a-73f65f74a3b0.png">

3. Unrestricted code snippet retrieved via id. /code/{id} GET request
<img width="985" alt="specifiedUUID no restriction" src="https://user-images.githubusercontent.com/56031072/206565722-91885b53-7018-4b56-926d-095363ba72de.png">

4. Restricted code snippet retireved via id. /code/{id} GET request
<img width="985" alt="specifiedUUID" src="https://user-images.githubusercontent.com/56031072/206565904-7beeb7af-01a5-41a6-a0ff-36915fb1a5bf.png">

5. Latest code snippets published, but only the unrestricted ones.
<img width="985" alt="latest" src="https://user-images.githubusercontent.com/56031072/206566178-b27ac0ec-b13e-41e7-9a8c-4eb336cbb783.png">
