-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.1              
-- * Generator date: Dec  4 2018              
-- * Generation date: Thu Jun 13 15:27:55 2019 
-- * LUN file: C:\Users\Davide\eclipse-workspace\DrinkingTeam\resources\ProgettoDB.lun 
-- * Schema: Drinks&Co.REL/1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database Drinks&Co.REL;
use Drinks&Co.REL;


-- Tables Section
-- _____________ 

create table ACCORDO (
     Nome_fornitore varchar(20) not null,
     Codice_prodotto int not null,
     Prezzo_di_acquisto decimal(3,2) not null,
     Data_Inizio date not null,
     Data_Fine date not null,
     Codice_Dipendente int not null,
     constraint ID_ACCORDO_ID primary key (Nome_fornitore, Codice_prodotto, Data_Inizio));

create table AGENTE (
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Partita_IVA bigint not null,
     Provvigione decimal(2,2) not null,
     Telefono bigint not null,
     Codice_Zona int not null,
     constraint IDAGENTE primary key (Partita_IVA));

create table CLIENTE (
     Nome varchar(25) not null,
     Tipologia varchar(20) not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Codice_Zona int not null,
     constraint ID_CLIENTE_ID primary key (Nome));

create table CONSEGNA (
     Numero_Fattura int not null,
     Nome_magazzino varchar(20) not null,
     Nome_cliente varchar(25) not null,
     Data_Consegna date not null,
     constraint ID_CONSEGNA_ID primary key (Numero_Fattura, Nome_magazzino, Nome_cliente, Data_Consegna));

create table DIPENDENTE (
     Codice_Dipendente int not null,
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Stipendio int not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Ruolo varchar(30) not null,
     Ufficio varchar(20) not null,
     constraint ID_DIPENDENTE_ID primary key (Codice_Dipendente));

create table FORNITORE (
     Nome varchar(20) not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Partita_IVA bigint not null,
     constraint ID_FORNITORE_ID primary key (Nome));

create table GIACENZA (
     Codice_prodotto int not null,
     Magazzino varchar(20) not null,
     Settore int not null,
     Quantita int not null,
     Giacenza_minima int not null,
     constraint ID_GIACENZA_ID primary key (Codice_prodotto, Magazzino, Settore));

create table MAGAZZINO (
     Nome varchar(20) not null,
     Indirizzo varchar(40) not null,
     Dimensioni int not null,
     Codice_Zona int not null,
     constraint ID_MAGAZZINO_ID primary key (Nome));

create table ORDINE (
     Numero_Fattura int not null,
     Quantita int not null,
     Data_ordine date not null,
     Data_consegna date not null,
     Nome varchar(20) not null,
     Nome_fornitore varchar(20) not null,
     Codice_prodotto int not null,
     constraint ID_ORDINE_ID primary key (Numero_Fattura));

create table PRODOTTO (
     Codice_prodotto int not null,
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Prezzo_unitario decimal(3,2) not null,
     Tipologia varchar(15) not null,
     Confezione varchar(15) not null,
     constraint ID_PRODOTTO_ID primary key (Codice_prodotto));

create table RECLAMO (
     Numero_Fattura int not null,
     ID_Reclamo int not null,
     Tipologia varchar(30) not null,
     constraint ID_RECLAMO_ID primary key (Numero_Fattura, ID_Reclamo));

create table UFFICIO (
     Nome varchar(20) not null,
     Descrizione varchar(100) not null,
     constraint ID_UFFICIO_ID primary key (Nome));

create table VENDITA (
     Numero_Fattura int not null,
     Quantita int not null,
     Data date not null,
     Sconto decimal(2,2) not null,
     Codice_prodotto int not null,
     Nome_cliente varchar(25) not null,
     Partita_IVA bigint not null,
     Telefono bigint not null,
     constraint ID_VENDITA_ID primary key (Numero_Fattura));

create table ZONA (
     Nome varchar(25) not null,
     Codice_Zona int not null,
     constraint ID_ZONA_ID primary key (Codice_Zona));


-- Constraints Section
-- ___________________ 

alter table ACCORDO add constraint FKstipulazione_FK
     foreign key (Codice_Dipendente)
     references DIPENDENTE (Codice_Dipendente);

alter table ACCORDO add constraint FKrelativo_a_FK
     foreign key (Codice_prodotto)
     references PRODOTTO (Codice_prodotto);

alter table ACCORDO add constraint FKcon_un
     foreign key (Nome_fornitore)
     references FORNITORE (Nome);

alter table AGENTE add constraint FKlavora_FK
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table CLIENTE add constraint FKappartenenza_FK
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table CONSEGNA add constraint FKverso_FK
     foreign key (Nome_cliente)
     references CLIENTE (Nome);

alter table CONSEGNA add constraint FKcrea_FK
     foreign key (Nome_magazzino)
     references MAGAZZINO (Nome);

alter table CONSEGNA add constraint FKa_seguito
     foreign key (Numero_Fattura)
     references VENDITA (Numero_Fattura);

alter table DIPENDENTE add constraint FKassegnazione_FK
     foreign key (Ufficio)
     references UFFICIO (Nome);

alter table GIACENZA add constraint FKpresso_FK
     foreign key (Magazzino)
     references MAGAZZINO (Nome);

alter table GIACENZA add constraint FKpresenza
     foreign key (Codice_prodotto)
     references PRODOTTO (Codice_prodotto);

alter table MAGAZZINO add constraint FKcopertura_FK
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table ORDINE add constraint FKin_consegna_FK
     foreign key (Nome)
     references MAGAZZINO (Nome);

alter table ORDINE add constraint FKin_base_a_FK
     foreign key (Nome_fornitore, Codice_prodotto)
     references ACCORDO (Nome_fornitore, Codice_prodotto, Data_Inizio);

alter table RECLAMO add constraint FKriguardo_a
     foreign key (Numero_Fattura)
     references VENDITA (Numero_Fattura);

alter table VENDITA add constraint FKriferita_FK
     foreign key (Codice_prodotto)
     references PRODOTTO (Codice_prodotto);

alter table VENDITA add constraint FKfatta_al_FK
     foreign key (Nome_cliente)
     references CLIENTE (Nome);


-- Index Section
-- _____________ 

create unique index ID_ACCORDO_IND
     on ACCORDO (Nome_fornitore, Codice_prodotto, Data_Inizio);

create index FKstipulazione_IND
     on ACCORDO (Codice_Dipendente);

create index FKrelativo_a_IND
     on ACCORDO (Codice_prodotto);

create index FKlavora_IND
     on AGENTE (Codice_Zona);

create unique index ID_CLIENTE_IND
     on CLIENTE (Nome);

create index FKappartenenza_IND
     on CLIENTE (Codice_Zona);

create unique index ID_CONSEGNA_IND
     on CONSEGNA (Numero_Fattura, Nome_magazzino, Nome_cliente, Data_Consegna);

create index FKverso_IND
     on CONSEGNA (Nome_cliente);

create index FKcrea_IND
     on CONSEGNA (Nome_magazzino);

create unique index ID_DIPENDENTE_IND
     on DIPENDENTE (Codice_Dipendente);

create index FKassegnazione_IND
     on DIPENDENTE (Ufficio);

create unique index ID_FORNITORE_IND
     on FORNITORE (Nome);

create unique index ID_GIACENZA_IND
     on GIACENZA (Codice_prodotto, Magazzino, Settore);

create index FKpresso_IND
     on GIACENZA (Magazzino);

create unique index ID_MAGAZZINO_IND
     on MAGAZZINO (Nome);

create index FKcopertura_IND
     on MAGAZZINO (Codice_Zona);

create unique index ID_ORDINE_IND
     on ORDINE (Numero_Fattura);

create index FKin_consegna_IND
     on ORDINE (Nome);

create index FKin_base_a_IND
     on ORDINE (Nome_fornitore, Codice_prodotto);

create unique index ID_PRODOTTO_IND
     on PRODOTTO (Codice_prodotto);

create unique index ID_RECLAMO_IND
     on RECLAMO (Numero_Fattura, ID_Reclamo);

create unique index ID_UFFICIO_IND
     on UFFICIO (Nome);

create unique index ID_VENDITA_IND
     on VENDITA (Numero_Fattura);

create index FKriferita_IND
     on VENDITA (Codice_prodotto);

create index FKfatta_al_IND
     on VENDITA (Nome_cliente);

create index FKeffettua
     on VENDITA (Partita_IVA, Telefono);

create unique index ID_ZONA_IND
     on ZONA (Codice_Zona);

