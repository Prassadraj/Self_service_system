# Self_service_system
Self service system is a system which is used to validate the user credentials and maintain their data into system.

We are using front end as Angular and Typescript and backend Java
Databse: Postgresql 13
Tools : Visual Studio Code and Eclipse

Database Level :
We have 3 Tables. They are
1. QuestionTable
2.AnawerTable
3.UserTable

Question table is master table, stable data are need to maintain here for security questions display

UserTable and AnswerTable are transaction table to stored user information

1. This Application contains Login, Signup and Forget Password Options and etc...


New User:
Signup option 
1. If your new customer, you have sign up option to enter your personal details like Username, Password, etc
2. Security questions are also provided in this section, you have to answer 5 questions here, These answers are maintained against the user registerd
3. Once all fields entered and press submit button
4. It will store your personal data into UserTable And Security Questions Into AnswersTable


Existing User:
Login Option 
1. If your a existing user, you can proceed with username name password in login portal to access this application


Forget Password:
Forget Link
1. If your forget your password, then it will helpful to retrieve your old password
2. userId Is mandatory to get your old password as backup
3. You need to give 5 answer to 5 questions which your entered while in singup process
4. If your all answer is right it will redirect to login page along with username and password
5. If your any answer is wrong it will show errors message in popup

Note: You have to insert this below query into question table...it's mandatory for this application
insert into public.questions (id, is_active, question)  
values (1, true, 'What is your favorite food?'), (2, true, 'What city were you born?'), 
  (3, true, 'What is your favorite sports team?'), (4, true, 'What is your best friend name?'), 
  (5, true, 'What was the name of your first pet?');
