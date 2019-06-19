-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.1              
-- * Generator date: Dec  4 2018              
-- * Generation date: Tue Jun 18 12:38:52 2019 
-- * LUN file: C:\Users\Davide\eclipse-workspace\DrinkingTeam\resources\ProgettoDB.lun 
-- * Schema: Drinks&Co.REL/1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database ciao;
use ciao;


-- Tables Section
-- _____________ 

create table ACCORDI (
     Nome_fornitore varchar(50) not null,
     Codice_prodotto int not null,
     Prezzo_di_acquisto decimal(4,2) not null,
     Data_scadenza date not null,
     Codice_Dipendente int not null,
     constraint ID_ACCORDO_ID primary key (Nome_fornitore, Codice_prodotto));

create table AGENTI (
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Partita_IVA varchar(20) not null,
     Provvigione decimal(4,2) not null,
     Telefono varchar(16) not null,
     Codice_Zona int not null,
     constraint IDAGENTE primary key (Partita_IVA));

create table CLIENTI (
     Nome varchar(25) not null,
     Tipologia varchar(20) not null,
     Indirizzo varchar(40) not null,
     Telefono varchar(16) not null,
     Codice_Zona int not null,
     constraint ID_CLIENTE_ID primary key (Nome));

create table CONSEGNE (
     Numero_Fattura int not null,
     Nome_magazzino varchar(20) not null,
     Nome_cliente varchar(25) not null,
     Data_Consegna date not null,
     constraint ID_CONSEGNA_ID primary key (Numero_Fattura, Nome_magazzino, Nome_cliente));

create table DIPENDENTI (
     Codice_Dipendente int not null AUTO_INCREMENT,
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Stipendio int not null,
     Indirizzo varchar(40) not null,
     Telefono varchar(16) not null,
     Ruolo varchar(30) not null,
     Ufficio varchar(20) not null,
     constraint ID_DIPENDENTE_ID primary key (Codice_Dipendente));

create table FORNITORI (
     Nome varchar(50) not null,
     Indirizzo varchar(40) not null,
     Telefono varchar(16) not null,
     Partita_IVA varchar(20) not null,
     constraint ID_FORNITORE_ID primary key (Nome));

create table GIACENZE (
     Codice_prodotto int not null,
     Magazzino varchar(20) not null,
     Settore int not null,
     Quantita int not null,
     Giacenza_minima int not null,
     constraint ID_GIACENZA_ID primary key (Codice_prodotto, Magazzino));

create table MAGAZZINI (
     Nome varchar(20) not null,
     Indirizzo varchar(40) not null,
     Dimensioni int not null,
     Codice_Zona int not null,
     constraint ID_MAGAZZINO_ID primary key (Nome));

create table ORDINI (
     Numero_Fattura int not null AUTO_INCREMENT,
     Quantita int not null,
     Data_ordine date not null,
     Data_consegna date not null,
     Magazzino varchar(20) not null,
     Nome_fornitore varchar(50) not null,
     Codice_prodotto int not null,
     constraint ID_ORDINE_ID primary key (Numero_Fattura));

create table PRODOTTI (
     Codice_prodotto int not null AUTO_INCREMENT,
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Prezzo_unitario decimal(4,2) not null,
     Tipologia varchar(15) not null,
     Confezione varchar(15) not null,
     constraint ID_PRODOTTO_ID primary key (Codice_prodotto));

create table RECLAMI (
     Numero_Fattura int not null,
     ID_Reclamo int not null AUTO_INCREMENT,
     Tipologia varchar(30) not null,
     constraint ID_RECLAMO_ID primary key (ID_Reclamo));

create table UFFICI (
     Nome varchar(20) not null,
     Descrizione varchar(100) not null,
     constraint ID_UFFICIO_ID primary key (Nome));

create table VENDITE (
     Numero_Fattura int not null AUTO_INCREMENT,
     Quantita int not null,
     Data date not null,
     Sconto decimal(4,2) not null,
     Codice_prodotto int not null,
     Nome_cliente varchar(25) not null,
     Partita_IVA_agente varchar(20) not null,
     constraint ID_VENDITA_ID primary key (Numero_Fattura));

create table ZONE (
     Nome varchar(25) not null,
     Codice_Zona int not null AUTO_INCREMENT,
     constraint ID_ZONA_ID primary key (Codice_Zona));


-- Constraints Section
-- ___________________ 

alter table ACCORDI add constraint FKstipulazione_FK
     foreign key (Codice_Dipendente)
     references DIPENDENTI (Codice_Dipendente);

alter table ACCORDI add constraint FKrelativo_a_FK
     foreign key (Codice_prodotto)
     references PRODOTTI (Codice_prodotto);

alter table ACCORDI add constraint FKcon_un
     foreign key (Nome_fornitore)
     references FORNITORI (Nome);

alter table AGENTI add constraint FKlavora_FK
     foreign key (Codice_Zona)
     references ZONE (Codice_Zona);

alter table CLIENTI add constraint FKappartenenza_FK
     foreign key (Codice_Zona)
     references ZONE (Codice_Zona);

alter table CONSEGNE add constraint FKverso_FK
     foreign key (Nome_cliente)
     references CLIENTI (Nome);

alter table CONSEGNE add constraint FKcrea_FK
     foreign key (Nome_magazzino)
     references MAGAZZINI (Nome);

alter table CONSEGNE add constraint FKa_seguito
     foreign key (Numero_Fattura)
     references VENDITE (Numero_Fattura);

alter table DIPENDENTI add constraint FKassegnazione_FK
     foreign key (Ufficio)
     references UFFICI (Nome);

alter table GIACENZE add constraint FKpresso_FK
     foreign key (Magazzino)
     references MAGAZZINI (Nome);

alter table GIACENZE add constraint FKpresenza
     foreign key (Codice_prodotto)
     references PRODOTTI (Codice_prodotto);

alter table MAGAZZINI add constraint FKcopertura_FK
     foreign key (Codice_Zona)
     references ZONE (Codice_Zona);

alter table ORDINI add constraint FKin_consegna_FK
     foreign key (Magazzino)
     references MAGAZZINI (Nome);

alter table ORDINI add constraint FKin_base_a_FK
     foreign key (Nome_fornitore, Codice_prodotto)
     references ACCORDI (Nome_fornitore, Codice_prodotto);

alter table RECLAMI add constraint FKriguardo_a
     foreign key (Numero_Fattura)
     references VENDITE (Numero_Fattura);

alter table VENDITE add constraint FKriferita_FK
     foreign key (Codice_prodotto)
     references PRODOTTI (Codice_prodotto);

alter table VENDITE add constraint FKfatta_al_FK
     foreign key (Nome_cliente)
     references CLIENTI (Nome);

alter table VENDITE add constraint FKeffettuaFK
     foreign key (Partita_IVA_agente)
     references AGENTI (Partita_IVA);


-- Index Section
-- _____________ 

create unique index ID_ACCORDO_IND
     on ACCORDI (Nome_fornitore, Codice_prodotto);

create index FKstipulazione_IND
     on ACCORDI (Codice_Dipendente);

create index FKrelativo_a_IND
     on ACCORDI (Codice_prodotto);

create index FKlavora_IND
     on AGENTI (Codice_Zona);

create unique index ID_CLIENTE_IND
     on CLIENTI (Nome);

create index FKappartenenza_IND
     on CLIENTI (Codice_Zona);

create unique index ID_CONSEGNA_IND
     on CONSEGNE (Numero_Fattura, Nome_magazzino, Nome_cliente);

create index FKverso_IND
     on CONSEGNE (Nome_cliente);

create index FKcrea_IND
     on CONSEGNE (Nome_magazzino);

create unique index ID_DIPENDENTE_IND
     on DIPENDENTI (Codice_Dipendente);

create index FKassegnazione_IND
     on DIPENDENTI (Ufficio);

create unique index ID_FORNITORE_IND
     on FORNITORI (Nome);

create unique index ID_GIACENZA_IND
     on GIACENZE (Codice_prodotto, Magazzino);

create index FKpresso_IND
     on GIACENZE (Magazzino);

create unique index ID_MAGAZZINO_IND
     on MAGAZZINI (Nome);

create index FKcopertura_IND
     on MAGAZZINI (Codice_Zona);

create unique index ID_ORDINE_IND
     on ORDINI (Numero_Fattura);

create index FKin_consegna_IND
     on ORDINI (Nome);

create index FKin_base_a_IND
     on ORDINI (Nome_fornitore, Codice_prodotto);

create unique index ID_PRODOTTO_IND
     on PRODOTTI (Codice_prodotto);

create unique index ID_RECLAMO_IND
     on RECLAMI (ID_Reclamo);

create unique index ID_UFFICIO_IND
     on UFFICI (Nome);

create unique index ID_VENDITA_IND
     on VENDITE (Numero_Fattura);

create index FKriferita_IND
     on VENDITE (Codice_prodotto);

create index FKfatta_al_IND
     on VENDITE (Nome_cliente);

create index FKeffettua
     on VENDITE (Partita_IVA, Telefono);

create unique index ID_ZONA_IND
     on ZONE (Codice_Zona);

