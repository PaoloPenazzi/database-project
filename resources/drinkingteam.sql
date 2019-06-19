-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 19, 2019 alle 09:17
-- Versione del server: 10.3.15-MariaDB
-- Versione PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `drinkingteam`
--
CREATE DATABASE IF NOT EXISTS `drinkingteam` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `drinkingteam`;

-- --------------------------------------------------------

--
-- Struttura della tabella `accordi`
--

CREATE TABLE `accordi` (
  `Nome_fornitore` varchar(50) NOT NULL,
  `Codice_prodotto` int(11) NOT NULL,
  `Prezzo_di_acquisto` decimal(4,2) NOT NULL,
  `Data_scadenza` date NOT NULL,
  `Codice_Dipendente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `accordi`
--

INSERT INTO `accordi` (`Nome_fornitore`, `Codice_prodotto`, `Prezzo_di_acquisto`, `Data_scadenza`, `Codice_Dipendente`) VALUES
('coca cola hbc italia srl', 3, '0.60', '2021-01-01', 1),
('coca cola hbc italia srl', 4, '0.65', '2022-01-01', 1),
('coca cola hbc italia srl', 5, '0.50', '2020-01-01', 1),
('coca cola hbc italia srl', 6, '0.75', '2021-01-01', 1),
('Heineken Italia Spa', 1, '1.20', '2021-01-01', 1),
('Heineken Italia Spa', 2, '1.70', '2020-01-01', 1),
('Heineken Italia Spa', 7, '0.40', '2022-01-01', 1),
('Heineken Italia Spa', 8, '0.50', '2020-01-01', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `agenti`
--

CREATE TABLE `agenti` (
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `partita_IVA` varchar(20) NOT NULL,
  `Provvigione` decimal(4,2) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `Codice_Zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `agenti`
--

INSERT INTO `agenti` (`Nome`, `Cognome`, `partita_IVA`, `Provvigione`, `telefono`, `Codice_Zona`) VALUES
('Davide', 'Magnani', '12839812742', '0.50', '3458956778', 2),
('Luigi', 'Verdi', '13634761004', '1.20', '3404680555', 1),
('Domenico', 'Ebner', '45643964534', '3.50', '3335895678', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE `clienti` (
  `Nome` varchar(25) NOT NULL,
  `Tipologia` varchar(20) NOT NULL,
  `Indirizzo` varchar(40) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `Codice_Zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`Nome`, `Tipologia`, `Indirizzo`, `telefono`, `Codice_Zona`) VALUES
('C\'entro', 'Ristorante', 'Contrada Uberti 3', '054727100', 1),
('Le Conserve Bio', 'gelateria', 'Via Carlo Seganti 5', '05431801841', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `consegne`
--

CREATE TABLE `consegne` (
  `Numero_Fattura` int(11) NOT NULL,
  `Nome_magazzino` varchar(20) NOT NULL,
  `Nome_cliente` varchar(25) NOT NULL,
  `Data_Consegna` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `dipendenti`
--

CREATE TABLE `dipendenti` (
  `Codice_Dipendente` int(11) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Stipendio` int(11) NOT NULL,
  `Indirizzo` varchar(40) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `Ruolo` varchar(30) NOT NULL,
  `Ufficio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `dipendenti`
--

INSERT INTO `dipendenti` (`Codice_Dipendente`, `Nome`, `Cognome`, `Stipendio`, `Indirizzo`, `telefono`, `Ruolo`, `Ufficio`) VALUES
(1, 'Mario', 'Rossi', 1300, 'Via Università 14', '3490560133', 'impiegato', 'Amministrazione'),
(2, 'Paolo', 'Penazzi', 2000, 'Via Donatini 144', '3383482188', 'public relations', 'Reclami'),
(3, 'Davide', 'Alpi', 2000, 'via marri 14', '3404680679', 'fattorino', 'Logistica');

-- --------------------------------------------------------

--
-- Struttura della tabella `fornitori`
--

CREATE TABLE `fornitori` (
  `Nome` varchar(50) NOT NULL,
  `Indirizzo` varchar(40) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `partita_IVA` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `fornitori`
--

INSERT INTO `fornitori` (`Nome`, `Indirizzo`, `telefono`, `partita_IVA`) VALUES
('coca cola hbc italia srl', 'Piazza Indro Montanelli 30', '800534934', '12363410155'),
('Heineken Italia Spa', 'Località Autoporto, 11', '02270761', '610140071');

-- --------------------------------------------------------

--
-- Struttura della tabella `giacenze`
--

CREATE TABLE `giacenze` (
  `Codice_prodotto` int(11) NOT NULL,
  `Magazzino` varchar(20) NOT NULL,
  `Settore` int(11) NOT NULL,
  `Quantita` int(11) NOT NULL,
  `Giacenza_minima` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `giacenze`
--

INSERT INTO `giacenze` (`Codice_prodotto`, `Magazzino`, `Settore`, `Quantita`, `Giacenza_minima`) VALUES
(1, 'Pietrasalda', 4, 1000, 200),
(2, 'Pietrasalda', 4, 600, 400),
(2, 'Roccavento', 1, 3000, 500),
(3, 'Rocciascura', 2, 200, 100),
(4, 'Roccavento', 2, 100, 400),
(5, 'Roccavento', 2, 300, 100),
(6, 'Pietrasalda', 1, 800, 50),
(6, 'Rocciascura', 2, 2000, 1500);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzini`
--

CREATE TABLE `magazzini` (
  `Nome` varchar(20) NOT NULL,
  `Indirizzo` varchar(40) NOT NULL,
  `Dimensioni` int(11) NOT NULL,
  `Codice_Zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `magazzini`
--

INSERT INTO `magazzini` (`Nome`, `Indirizzo`, `Dimensioni`, `Codice_Zona`) VALUES
('Pietrasalda', 'Via Università 50', 10000, 1),
('Roccavento', 'Via Gandhi 11', 25000, 3),
('Rocciascura', 'Via Murri 64', 15000, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini`
--

CREATE TABLE `ordini` (
  `Numero_Fattura` int(11) NOT NULL,
  `Quantita` int(11) NOT NULL,
  `Data_ordine` date NOT NULL,
  `Data_consegna` date NOT NULL,
  `Magazzino` varchar(20) NOT NULL,
  `Nome_fornitore` varchar(50) DEFAULT NULL,
  `Codice_prodotto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotti`
--

CREATE TABLE `prodotti` (
  `Codice_prodotto` int(11) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Capacita` int(11) NOT NULL,
  `Marca` varchar(20) NOT NULL,
  `Prezzo_unitario` decimal(4,2) NOT NULL,
  `Tipologia` varchar(15) NOT NULL,
  `Confezione` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `prodotti`
--

INSERT INTO `prodotti` (`Codice_prodotto`, `Nome`, `Capacita`, `Marca`, `Prezzo_unitario`, `Tipologia`, `Confezione`) VALUES
(1, 'unlimited', 66, 'heineken', '1.50', 'birra', 'vetro'),
(2, 'zeroassoluto', 100, 'heineken', '2.00', 'birra', 'vetro'),
(3, 'zero', 100, 'coca-cola', '1.00', 'bibita', 'plastica'),
(4, 'original taste', 100, 'coca-cola', '1.00', 'bibita', 'plastica'),
(5, 'light', 66, 'coca-cola', '0.80', 'bibita', 'plastica'),
(6, 'original taste', 150, 'coca-cola', '1.20', 'bibita', 'plastica'),
(7, 'ichnusa', 33, 'heineken', '0.50', 'birra', 'vetro'),
(8, 'dreher', 66, 'heineken', '0.80', 'birra', 'vetro');

-- --------------------------------------------------------

--
-- Struttura della tabella `reclami`
--

CREATE TABLE `reclami` (
  `Numero_Fattura` int(11) NOT NULL,
  `ID_Reclamo` int(11) NOT NULL,
  `Tipologia` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `uffici`
--

CREATE TABLE `uffici` (
  `Nome` varchar(20) NOT NULL,
  `Descrizione` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `uffici`
--

INSERT INTO `uffici` (`Nome`, `Descrizione`) VALUES
('Amministrazione', 'Si occupa delle questioni burocratiche'),
('Logistica', 'Si occupa di spostare la merce tra i magazzini e verso i clienti'),
('Reclami', 'Si occupa di gestire i reclami'),
('Riordini', 'Si occupa di ordinare la merce dai fornitori');

-- --------------------------------------------------------

--
-- Struttura della tabella `vendite`
--

CREATE TABLE `vendite` (
  `Numero_Fattura` int(11) NOT NULL,
  `Quantita` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Sconto` decimal(4,2) NOT NULL,
  `Codice_prodotto` int(11) NOT NULL,
  `Nome_cliente` varchar(25) NOT NULL,
  `Partita_IVA_agente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `vendite`
--

INSERT INTO `vendite` (`Numero_Fattura`, `Quantita`, `Data`, `Sconto`, `Codice_prodotto`, `Nome_cliente`, `Partita_IVA_agente`) VALUES
(1, 100, '2019-06-19', '0.00', 1, 'C\'entro', '13634761004'),
(2, 300, '2019-11-11', '10.00', 2, 'C\'entro', '12839812742');

-- --------------------------------------------------------

--
-- Struttura della tabella `zone`
--

CREATE TABLE `zone` (
  `Nome` varchar(25) NOT NULL,
  `Codice_Zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `zone`
--

INSERT INTO `zone` (`Nome`, `Codice_Zona`) VALUES
('Cesena e dintorni', 1),
('Faenza e dintorni', 2),
('Forlì e dintorni', 3);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `accordi`
--
ALTER TABLE `accordi`
  ADD PRIMARY KEY (`Nome_fornitore`,`Codice_prodotto`),
  ADD UNIQUE KEY `ID_ACCORDO_IND` (`Nome_fornitore`,`Codice_prodotto`),
  ADD KEY `FKstipulazione_IND` (`Codice_Dipendente`),
  ADD KEY `FKrelativo_a_IND` (`Codice_prodotto`);

--
-- Indici per le tabelle `agenti`
--
ALTER TABLE `agenti`
  ADD PRIMARY KEY (`partita_IVA`),
  ADD KEY `FKlavora_IND` (`Codice_Zona`);

--
-- Indici per le tabelle `clienti`
--
ALTER TABLE `clienti`
  ADD PRIMARY KEY (`Nome`),
  ADD UNIQUE KEY `ID_CLIENTE_IND` (`Nome`),
  ADD KEY `FKappartenenza_IND` (`Codice_Zona`);

--
-- Indici per le tabelle `consegne`
--
ALTER TABLE `consegne`
  ADD PRIMARY KEY (`Numero_Fattura`,`Nome_magazzino`,`Nome_cliente`,`Data_Consegna`),
  ADD UNIQUE KEY `ID_CONSEGNA_IND` (`Numero_Fattura`,`Nome_magazzino`,`Nome_cliente`,`Data_Consegna`),
  ADD KEY `FKverso_IND` (`Nome_cliente`),
  ADD KEY `FKcrea_IND` (`Nome_magazzino`);

--
-- Indici per le tabelle `dipendenti`
--
ALTER TABLE `dipendenti`
  ADD PRIMARY KEY (`Codice_Dipendente`),
  ADD UNIQUE KEY `ID_DIPENDENTE_IND` (`Codice_Dipendente`),
  ADD KEY `FKassegnazione_IND` (`Ufficio`);

--
-- Indici per le tabelle `fornitori`
--
ALTER TABLE `fornitori`
  ADD PRIMARY KEY (`Nome`),
  ADD UNIQUE KEY `ID_FORNITORE_IND` (`Nome`);

--
-- Indici per le tabelle `giacenze`
--
ALTER TABLE `giacenze`
  ADD PRIMARY KEY (`Codice_prodotto`,`Magazzino`),
  ADD UNIQUE KEY `ID_GIACENZA_IND` (`Codice_prodotto`,`Magazzino`,`Settore`),
  ADD KEY `FKpresso_IND` (`Magazzino`);

--
-- Indici per le tabelle `magazzini`
--
ALTER TABLE `magazzini`
  ADD PRIMARY KEY (`Nome`),
  ADD UNIQUE KEY `ID_MAGAZZINO_IND` (`Nome`),
  ADD KEY `FKcopertura_IND` (`Codice_Zona`);

--
-- Indici per le tabelle `ordini`
--
ALTER TABLE `ordini`
  ADD PRIMARY KEY (`Numero_Fattura`),
  ADD UNIQUE KEY `ID_ORDINE_IND` (`Numero_Fattura`),
  ADD KEY `FKin_consegna_IND` (`Magazzino`),
  ADD KEY `FKin_base_a_IND` (`Nome_fornitore`,`Codice_prodotto`);

--
-- Indici per le tabelle `prodotti`
--
ALTER TABLE `prodotti`
  ADD PRIMARY KEY (`Codice_prodotto`),
  ADD UNIQUE KEY `ID_PRODOTTO_IND` (`Codice_prodotto`);

--
-- Indici per le tabelle `reclami`
--
ALTER TABLE `reclami`
  ADD PRIMARY KEY (`ID_Reclamo`),
  ADD UNIQUE KEY `ID_RECLAMO_IND` (`Numero_Fattura`,`ID_Reclamo`);

--
-- Indici per le tabelle `uffici`
--
ALTER TABLE `uffici`
  ADD PRIMARY KEY (`Nome`),
  ADD UNIQUE KEY `ID_UFFICIO_IND` (`Nome`);

--
-- Indici per le tabelle `vendite`
--
ALTER TABLE `vendite`
  ADD PRIMARY KEY (`Numero_Fattura`),
  ADD UNIQUE KEY `ID_VENDITA_IND` (`Numero_Fattura`),
  ADD KEY `FKriferita_IND` (`Codice_prodotto`),
  ADD KEY `FKfatta_al_IND` (`Nome_cliente`),
  ADD KEY `FKeffettua` (`Partita_IVA_agente`);

--
-- Indici per le tabelle `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`Codice_Zona`),
  ADD UNIQUE KEY `ID_ZONA_IND` (`Codice_Zona`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `dipendenti`
--
ALTER TABLE `dipendenti`
  MODIFY `Codice_Dipendente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `ordini`
--
ALTER TABLE `ordini`
  MODIFY `Numero_Fattura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `prodotti`
--
ALTER TABLE `prodotti`
  MODIFY `Codice_prodotto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `reclami`
--
ALTER TABLE `reclami`
  MODIFY `ID_Reclamo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `vendite`
--
ALTER TABLE `vendite`
  MODIFY `Numero_Fattura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `zone`
--
ALTER TABLE `zone`
  MODIFY `Codice_Zona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `accordi`
--
ALTER TABLE `accordi`
  ADD CONSTRAINT `FKcon_un` FOREIGN KEY (`Nome_fornitore`) REFERENCES `fornitori` (`Nome`),
  ADD CONSTRAINT `FKrelativo_a_FK` FOREIGN KEY (`Codice_prodotto`) REFERENCES `prodotti` (`Codice_prodotto`),
  ADD CONSTRAINT `FKstipulazione_FK` FOREIGN KEY (`Codice_Dipendente`) REFERENCES `dipendenti` (`Codice_Dipendente`);

--
-- Limiti per la tabella `agenti`
--
ALTER TABLE `agenti`
  ADD CONSTRAINT `FKlavora_FK` FOREIGN KEY (`Codice_Zona`) REFERENCES `zone` (`Codice_Zona`);

--
-- Limiti per la tabella `clienti`
--
ALTER TABLE `clienti`
  ADD CONSTRAINT `FKappartenenza_FK` FOREIGN KEY (`Codice_Zona`) REFERENCES `zone` (`Codice_Zona`);

--
-- Limiti per la tabella `consegne`
--
ALTER TABLE `consegne`
  ADD CONSTRAINT `FKa_seguito` FOREIGN KEY (`Numero_Fattura`) REFERENCES `vendite` (`Numero_Fattura`),
  ADD CONSTRAINT `FKcrea_FK` FOREIGN KEY (`Nome_magazzino`) REFERENCES `magazzini` (`Nome`),
  ADD CONSTRAINT `FKverso_FK` FOREIGN KEY (`Nome_cliente`) REFERENCES `clienti` (`Nome`);

--
-- Limiti per la tabella `dipendenti`
--
ALTER TABLE `dipendenti`
  ADD CONSTRAINT `FKassegnazione_FK` FOREIGN KEY (`Ufficio`) REFERENCES `uffici` (`Nome`);

--
-- Limiti per la tabella `giacenze`
--
ALTER TABLE `giacenze`
  ADD CONSTRAINT `FKpresenza` FOREIGN KEY (`Codice_prodotto`) REFERENCES `prodotti` (`Codice_prodotto`),
  ADD CONSTRAINT `FKpresso_FK` FOREIGN KEY (`Magazzino`) REFERENCES `magazzini` (`Nome`);

--
-- Limiti per la tabella `magazzini`
--
ALTER TABLE `magazzini`
  ADD CONSTRAINT `FKcopertura_FK` FOREIGN KEY (`Codice_Zona`) REFERENCES `zone` (`Codice_Zona`);

--
-- Limiti per la tabella `ordini`
--
ALTER TABLE `ordini`
  ADD CONSTRAINT `FKin_base_a_FK` FOREIGN KEY (`Nome_fornitore`,`Codice_prodotto`) REFERENCES `accordi` (`Nome_fornitore`, `Codice_prodotto`),
  ADD CONSTRAINT `FKin_consegna_FK` FOREIGN KEY (`Magazzino`) REFERENCES `magazzini` (`Nome`);

--
-- Limiti per la tabella `reclami`
--
ALTER TABLE `reclami`
  ADD CONSTRAINT `FKriguardo_a` FOREIGN KEY (`Numero_Fattura`) REFERENCES `vendite` (`Numero_Fattura`);

--
-- Limiti per la tabella `vendite`
--
ALTER TABLE `vendite`
  ADD CONSTRAINT `FKeffettuaFK` FOREIGN KEY (`Partita_IVA_agente`) REFERENCES `agenti` (`partita_IVA`),
  ADD CONSTRAINT `FKfatta_al_FK` FOREIGN KEY (`Nome_cliente`) REFERENCES `clienti` (`Nome`),
  ADD CONSTRAINT `FKriferita_FK` FOREIGN KEY (`Codice_prodotto`) REFERENCES `prodotti` (`Codice_prodotto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
