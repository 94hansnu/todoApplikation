## Projektet
Detta projektet handlar om att skapa en Todo applikation med ett menysystem som kan skapa, läsa ut, redigera samt radera TODO. Applikationen existerar i MongoDB-databasen och ska testas och mockas.

## Planering
##
Jag började med att läsa igenom uppgiften. Försökte förstå uppgiften i helhet och vad som förväntades av mig för att nå mitt mål.
Jag skapade ett todo-projekt i Github och började knappa in de olika klasserna jag skulle ha med baserat på tidigare erfarenhet. Steg för steg för de viktiga klasserna jag skulle ha med, som ex Todo och USer klassen. 
####
CRUD principen underlättade min planeringen. Jag visste i princip vilka funktioner jag skulle ha med. 
När jag var klar med todo-projektet började jag skapa klasserna i projektet. 

## Arbetet och dess genomförande 
##
En av de svåra grejerna var att det tog mig två hela dagar att bestämma mig för vilken databas jag skulle använda mig utav. Jag funderade väldigt mycket, fram och tillbaka tills att jag kom fram till att jag ville använda mig utav MongoDB. Att jag använt SQLite i senaste projekten gjorde beslutet lite enklare. 
#### 
Vissa lösningar som jag fick göra var att, jag fick ändra lite i koden. Det var när upptäckte att jag kunde skapa en todo med samma id. Samma sak var det när jag skulle skapa användare. Jag tänkte inte på det under tiden jag kodade. Utan det såg jag när jag testade att köra mitt projekt. 
####
Det jag tyckte var besvärligt att få till var, att jag från början hade connection i både UserDB och TodoDB klasserna. Jag försökte att få rätt på det länge. Till slut Tog jag bort connection från DB-klasserna och skapade en egen klass för MongoDBConnection och körde på instans för DB klasserna. Jag förstår inte själv att det kunde ta så lång tid att förstå att jag egentligen bara skulle ha gjort det från början. 

####
Jag hade skapat klasserna i mitt projekt, och nästan kodat klart. Men då ville jag helt plötsligt ha package i projektet och dela upp klasserna så att det skulle vara tydligare. Det skapade också lite extra jobb för mig. Men det var värt arbetet för jag tyckte att det såg mycket bättre ut, än att ha massa klasser under samma package. 
## Reflektion & slutsatser 
##
I helhet så tyckte jag att allting gick bra. Det har varit jätte roligt att skapa detta projektet. Jag har fått planer på att bygga vidare på projektet och se vad jag kan utveckla den till. Har haft olika ideer i huvudet. Jag behöver definitivt en todo applikation som kan underlätta min vardag. 
####
Jag tyckte det var coolt att köra tester och vara säker på att applikationen funkar som den ska. Jag märkte även alla buggar och små detaljer som jag inte har tänkt på förut. 
####
Jag har lärt mig att kunna vara flexibel och hantera saker utanför planeringen. Planeringen för mitt projekt var färdigt och jag är stolt över mig själv att jag vågade ändra ovanligt mycket i min kod. Vilket jag inte vågat göra innan. Samtidigt har jag fått en fördjupning av att testa och mocka ett projekt i helhet. Även att använda GitHub Actions för att testa projektet. Det har varit en utmaning, men väldigt roligt. 
#### 
Det jag skulle kunna göra annorlunda om jag gjort om projektet är att jag hade skrivit testerna först sen logiken i metoderna. Denna gången drabbades projektet inte mycket, men det kommer jag att ta med mig till nästa projekt. Jag märkte i efter hand att om jag testat metoderna först, hade jag inte behövt ändra logiken i koden på samma sätt som jag gjorde för detta projektet. 
####
Möjligheten som denna kursen har gett för kunskaper är utveckling, som jag nämnde innan, jag vill vidare utveckla detta projektet till något jag själv kan använda. 
