# Eksamensprojekt-KEA-Datamatiker-2.-semester
Vi har lavet et intern adminstrativ system som hedder bilabonnement-portalen som er blevet efterspurgt af Bilabonnement A/S
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
