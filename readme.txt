Guida alla corretta installazione dell'applicativo:

-decomprimere questo archivio zip;
-avviare xampp, attivando i servizi Apache e MySQL;
-accedere su browser all'indirizzo '//localhost' e cliccare in alto a destra su 'phpmyadmin';
-cliccare su 'Importa' (indicativamente in alto a destra) e selezionare il file drinkingteam.sql che trovate nella cartella /resources del progetto;
-eseguire l'import, che creerà il database;
-dopo aver selezionato drinkingteam nella lista dei database a sinistra, cliccare su 'Privilegi' sempre in alto a destra;
-configurare un nuovo utente con:
	Nome utente: prova
	Nome host -> Locale
	password: prova
    				e attribuirgli tutti i privilegi;
-importare il progetto in eclipse e avviare la classe contenente il main ('Menu').
N.B.	Nel caso ci siano errori occorre cliccare sulla cartella del progetto nel package explorer -> Properties -> Java build path -> tab 'Libraries'  
	e aggiungete tutti i jar che dovreste vedere disponibili (a scanso di equivoci, sono quelli contenuti in /resources/libraries).


