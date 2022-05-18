# Eksamens projekt på KEA Datamatiker 2.semester
Vi har lavet et intern adminstrativ system som hedder bilabonnement-portalen som er blevet efterspurgt af Bilabonnement A/S. Dette system har vi lavet i forbindelse med vores 2.semester eksamen.
Vi er 3 i vores gruppe:

---------------------------
- Mohammad Adel Murtada
- GitHub brugernavn: Mojens
- https://github.com/mojens

---------------------------
Simon Igild
GitHub brugernavn: Chinesethunder
https://github.com/chinesethunder

----------------------------------
Malthe Holm Karlsson
GitHub brugernavn: IfMalthereturnawesome
https://github.com/ifmalthereturnawesome/

------------------------------------------

# Importer repository fra github
1. Kopier github link :https://github.com/Mojens/Eksamensprojekt-KEA-Datamatiker-2.-semester.git
2. åben IntelliJ
3. Gå til file/new/projekt from version control
4. Indsæt link i Url 
5. aflsut ved at klikke på knappen clone
#Opsætning af database i MySQL
1. i MySQL klik på create new schema
2. Navngiv skemaet bilabonnement
3. klik Apply og derefter Apply igen
4. Åben den vedlagte fil i MySQL (MySQL-Generated-Data.sql) og apply
5. Du har nu en sql server med data
# Opsætning af database i intellij
1. Klik på knappen Database oppe i højre hjørne 
2. Klik på plus iconet
3. Gå til datasource/Mysql
4. Ret User til dit root navn på din MySQL server
5. I Password skal du indskrive dit password til din MySQL server
6. Ret Database til hvad du har navngivet den importerede database
7. Indsæt jdbc:mysql://localhost:3306/bilabonnementer i URL, hvis du har valgt en anden port skal linket rettes til denne, ligeså hvis du har navngivet din database anderledes.
8. Aflut ved at klikke på Apply.
9. Naviger til Run/Edit configurations
10. Klik på Enviroment for at udvide menuen
11. Klik derefter på iconet til højre for Enviroment variables.
12. Tilføj en variable ved at klikke på plus tegnet oppe i venstre hjørne
13. Første variable skal navn gives URl og value er samme link fra step 7.
14. Derefter skal der oprettes en user med value som er dit root navn på din MySQL server
15. Sidste variable er pwd og value er din kode til din mysql server
16. Aflut ved at klikke OK.
17. Repositoriet kan nu kørers localt
# Vil i hoste vores projekt ? så er der nogle krav
1. hosting hos Heroku
2. En online database i MySQL (Man tilvælger en add on som hedder "ClearDB MySQL" )
# Opsætning af Database i Heroku
1. Opret en ny app inde i Heroku
2. Tilvælg ClearDB MySQL
3. Gå ind på din app
4. Gå ind på indstillinger
5. Find Config Vars
6. Tryk på Reveal Config vars

Nu skal man så indtaste nogle oplyser ind i noget der hedder Environmental variables
1. Tilføj den første Key, som hedder url
- Her er det præcis det samme som ClearDB url'en man får, dog med jdbc: foran mysql:
2. Tilføj derefter user
- value til user er fra efter // til den næste : i din url
4. Tilføjer derefter passoword
- value til password er fra efter : som kom efter user til den næste @ i din url.
5. Upload applikationen fra denne git, ind til heroku
- Hvis der skulle opstå problemer ved upload af applikationen kan i følge den guide: https://devcenter.heroku.com/articles/git#deploy-your-code
ps. Hvis der skulle stødes på nogle problemer ved upload til heroku, gå venligst til denne side: https://help.heroku.com
6. Tillykke du hoster nu vores projekt på din egen Heroku Konti
