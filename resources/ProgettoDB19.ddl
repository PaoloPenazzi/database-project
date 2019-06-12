-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.1              
-- * Generator date: Dec  4 2018              
-- * Generation date: Wed Jun 12 17:17:53 2019 
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
     Con_Nome varchar(20) not null,
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Prezzo_di_acquisto decimal(3,2) not null,
     Data_Inizio date not null,
     Data_Fine date not null,
     Codice_Dipendente int not null,
     constraint ID_ACCORDO_ID primary key (Con_Nome, Nome, Capacita, Marca, Data_Inizio));

create table AGENTE (
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Partita_IVA bigint not null,
     Provvigione decimal(2,2) not null,
     Telefono bigint not null,
     Codice_Zona int not null,
     constraint ID_AGENTE_ID primary key (Partita_IVA, Telefono));

create table CLIENTE (
     Nome varchar(25) not null,
     Tipologia varchar(20) not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Codice_Zona int not null,
     constraint ID_CLIENTE_ID primary key (Nome));

create table CONSEGNA (
     Cre_Codice_Zona int not null,
     Cre_Nome varchar(20) not null,
     Numero_Fattura int not null,
     Nome varchar(25) not null,
     Data_Consegna date not null,
     constraint ID_CONSEGNA_ID primary key (Numero_Fattura, Cre_Codice_Zona, Cre_Nome, Nome, Data_Consegna));

create table DIPENDENTE (
     Codice_Dipendente int not null,
     Nome varchar(20) not null,
     Cognome varchar(20) not null,
     Stipendio int not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Ruolo varchar(30) not null,
     Tipo varchar(20) not null,
     constraint ID_DIPENDENTE_ID primary key (Codice_Dipendente));

create table FORNITORE (
     Nome varchar(20) not null,
     Indirizzo varchar(40) not null,
     Telefono bigint not null,
     Partita_IVA bigint not null,
     constraint ID_FORNITORE_ID primary key (Nome));

create table GIACENZA (
     Pre_Codice_Zona int not null,
     Pre_Nome varchar(20) not null,
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Settore int not null,
     Quantita int not null,
     Giacenza_minima int not null,
     constraint ID_GIACENZA_ID primary key (Nome, Capacita, Marca, Pre_Codice_Zona, Pre_Nome, Settore));

create table MAGAZZINO (
     Codice_Zona int not null,
     Nome varchar(20) not null,
     Indirizzo varchar(40) not null,
     Dimensioni int not null,
     constraint ID_MAGAZZINO_ID primary key (Codice_Zona, Nome));

create table ORDINE (
     Numero_Fattura int not null,
     Quantita int not null,
     Data_ordine date not null,
     Data_consegna date not null,
     Codice_Zona int not null,
     Nome varchar(20) not null,
     In__Con_Nome varchar(20) not null,
     In__Nome varchar(20) not null,
     In__Capacita int not null,
     In__Marca varchar(20) not null,
     In__Data_Inizio date not null,
     constraint ID_ORDINE_ID primary key (Numero_Fattura));

create table PRODOTTO (
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Prezzo_unitario decimal(3,2) not null,
     Tipologia varchar(15) not null,
     Confezione varchar(15) not null,
     constraint ID_PRODOTTO_ID primary key (Nome, Capacita, Marca));

create table RECLAMO (
     Numero_Fattura int not null,
     ID_Reclamo int not null,
     Tipologia varchar(30) not null,
     constraint ID_RECLAMO_ID primary key (Numero_Fattura, ID_Reclamo));

create table UFFICIO (
     Tipo varchar(20) not null,
     Descrizione varchar(100) not null,
     constraint ID_UFFICIO_ID primary key (Tipo));

create table VENDITA (
     Numero_Fattura int not null,
     Quantita int not null,
     Data date not null,
     Sconto decimal(2,2) not null,
     Nome varchar(20) not null,
     Capacita int not null,
     Marca varchar(20) not null,
     Fat_Nome varchar(25) not null,
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
     foreign key (Nome, Capacita, Marca)
     references PRODOTTO (Nome, Capacita, Marca);

alter table ACCORDO add constraint FKcon_un
     foreign key (Con_Nome)
     references FORNITORE (Nome);

alter table AGENTE add constraint FKlavora_FK
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table CLIENTE add constraint FKappartenenza_FK
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table CONSEGNA add constraint FKverso_FK
     foreign key (Nome)
     references CLIENTE (Nome);

alter table CONSEGNA add constraint FKa_seguito
     foreign key (Numero_Fattura)
     references VENDITA (Numero_Fattura);

alter table CONSEGNA add constraint FKcrea_FK
     foreign key (Cre_Codice_Zona, Cre_Nome)
     references MAGAZZINO (Codice_Zona, Nome);

alter table DIPENDENTE add constraint FKassegnazione_FK
     foreign key (Tipo)
     references UFFICIO (Tipo);

alter table GIACENZA add constraint FKpresenza
     foreign key (Nome, Capacita, Marca)
     references PRODOTTO (Nome, Capacita, Marca);

alter table GIACENZA add constraint FKpresso_FK
     foreign key (Pre_Codice_Zona, Pre_Nome)
     references MAGAZZINO (Codice_Zona, Nome);

alter table MAGAZZINO add constraint FKcopertura
     foreign key (Codice_Zona)
     references ZONA (Codice_Zona);

alter table ORDINE add constraint FKin_consegna_FK
     foreign key (Codice_Zona, Nome)
     references MAGAZZINO (Codice_Zona, Nome);

alter table ORDINE add constraint FKin_base_a_FK
     foreign key (In__Con_Nome, In__Nome, In__Capacita, In__Marca, In__Data_Inizio)
     references ACCORDO (Con_Nome, Nome, Capacita, Marca, Data_Inizio);

alter table RECLAMO add constraint FKriguardo_a
     foreign key (Numero_Fattura)
     references VENDITA (Numero_Fattura);

alter table VENDITA add constraint FKriferita_FK
     foreign key (Nome, Capacita, Marca)
     references PRODOTTO (Nome, Capacita, Marca);

alter table VENDITA add constraint FKfatta_al_FK
     foreign key (Fat_Nome)
     references CLIENTE (Nome);

alter table VENDITA add constraint FKeffettua_FK
     foreign key (Partita_IVA, Telefono)
     references AGENTE (Partita_IVA, Telefono);


-- Index Section
-- _____________ 

create unique index ID_ACCORDO_IND
     on ACCORDO (Con_Nome, Nome, Capacita, Marca, Data_Inizio);

create index FKstipulazione_IND
     on ACCORDO (Codice_Dipendente);

create index FKrelativo_a_IND
     on ACCORDO (Nome, Capacita, Marca);

create unique index ID_AGENTE_IND
     on AGENTE (Partita_IVA, Telefono);

create index FKlavora_IND
     on AGENTE (Codice_Zona);

create unique index ID_CLIENTE_IND
     on CLIENTE (Nome);

create index FKappartenenza_IND
     on CLIENTE (Codice_Zona);

create unique index ID_CONSEGNA_IND
     on CONSEGNA (Numero_Fattura, Cre_Codice_Zona, Cre_Nome, Nome, Data_Consegna);

create index FKverso_IND
     on CONSEGNA (Nome);

create index FKcrea_IND
     on CONSEGNA (Cre_Codice_Zona, Cre_Nome);

create unique index ID_DIPENDENTE_IND
     on DIPENDENTE (Codice_Dipendente);

create index FKassegnazione_IND
     on DIPENDENTE (Tipo);

create unique index ID_FORNITORE_IND
     on FORNITORE (Nome);

create unique index ID_GIACENZA_IND
     on GIACENZA (Nome, Capacita, Marca, Pre_Codice_Zona, Pre_Nome, Settore);

create index FKpresso_IND
     on GIACENZA (Pre_Codice_Zona, Pre_Nome);

create unique index ID_MAGAZZINO_IND
     on MAGAZZINO (Codice_Zona, Nome);

create unique index ID_ORDINE_IND
     on ORDINE (Numero_Fattura);

create index FKin_consegna_IND
     on ORDINE (Codice_Zona, Nome);

create index FKin_base_a_IND
     on ORDINE (In__Con_Nome, In__Nome, In__Capacita, In__Marca, In__Data_Inizio);

create unique index ID_PRODOTTO_IND
     on PRODOTTO (Nome, Capacita, Marca);

create unique index ID_RECLAMO_IND
     on RECLAMO (Numero_Fattura, ID_Reclamo);

create unique index ID_UFFICIO_IND
     on UFFICIO (Tipo);

create unique index ID_VENDITA_IND
     on VENDITA (Numero_Fattura);

create index FKriferita_IND
     on VENDITA (Nome, Capacita, Marca);

create index FKfatta_al_IND
     on VENDITA (Fat_Nome);

create index FKeffettua_IND
     on VENDITA (Partita_IVA, Telefono);

create unique index ID_ZONA_IND
     on ZONA (Codice_Zona);

