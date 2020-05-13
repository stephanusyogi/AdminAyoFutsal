-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Bulan Mei 2020 pada 07.55
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ayofutsal`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_lapangan` (IN `id` INT(255))  BEGIN
DELETE FROM tbl_lapangan WHERE id_lap = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `del_pemesanan` (IN `id` INT(255))  BEGIN
DELETE FROM tbl_pemesanan WHERE id_pesanan = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `del_user` (IN `id` INT(255))  BEGIN
DELETE FROM tbl_user WHERE id_user = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_lapangan` (IN `id` INT(255))  BEGIN
SELECT * FROM tbl_lapangan WHERE tbl_lapangan.id_lap = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_pemesanan` (IN `id` INT(255))  BEGIN
SELECT * FROM tbl_pemesanan WHERE tbl_pemesanan.id_pesanan= id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_user` (IN `id` INT(255))  BEGIN
SELECT * FROM tbl_user WHERE tbl_user.id_user = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ins_lapangan` (IN `name` VARCHAR(255), IN `status` VARCHAR(255))  BEGIN
INSERT INTO tbl_lapangan values (null, name, status);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ins_pemesanan` (IN `id_user` INT(255), IN `lapangan` VARCHAR(255), IN `mulai_jam` TIME, IN `selesai_jam` TIME, IN `tanggal` DATE, IN `catatan` VARCHAR(255))  NO SQL
BEGIN
INSERT INTO tbl_pemesanan values (null, id_user, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login_admin` (IN `usern` VARCHAR(255), IN `passwd` VARCHAR(32))  BEGIN
SELECT * FROM tbl_admin WHERE tbl_admin.username = usern AND tbl_admin.password = MD5(passwd);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `login_user` (IN `usern` VARCHAR(30), IN `passwd` VARCHAR(32))  BEGIN
SELECT * FROM tbl_user WHERE tbl_user.username = usern AND tbl_user.password = MD5(passwd);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `reg_admin` (IN `name` VARCHAR(255), IN `usern` VARCHAR(255), IN `passwd` VARCHAR(255))  BEGIN
INSERT INTO tbl_admin values (null, name, usern, MD5(passwd));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `reg_user` (IN `name` VARCHAR(30), IN `nohp` VARCHAR(30), IN `point` INT(50), IN `usern` VARCHAR(30), IN `passwd` VARCHAR(32))  BEGIN
INSERT INTO tbl_user values (null, name, nohp, point, usern, MD5(passwd));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `upd_lapangan` (IN `id` INT(255), IN `nam` VARCHAR(255), IN `statu` VARCHAR(255))  BEGIN
UPDATE tbl_lapangan SET tbl_lapangan.name = nam , tbl_lapangan.status = statu WHERE tbl_lapangan.id_lap = id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `upd_pemesanan` (IN `id` INT(255), IN `status` VARCHAR(255))  BEGIN
UPDATE tbl_pemesanan SET tbl_pemesanan.status_bayar = status WHERE tbl_pemesanan.id_pesanan = id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `upd_userp` (IN `usern` VARCHAR(255), IN `nam` VARCHAR(50), IN `nhp` VARCHAR(50), IN `passwd` VARCHAR(32))  NO SQL
BEGIN
UPDATE tbl_user SET tbl_user.name = nam, tbl_user.nohp = nhp, tbl_user.password = MD5(passwd) WHERE tbl_user.username = usern ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `upd_userpass` (IN `id` INT(255), IN `usern` VARCHAR(30), IN `passwd` VARCHAR(32))  BEGIN
UPDATE tbl_user SET tbl_user.username = usern , tbl_user.password = MD5(passwd) WHERE id_user = id ;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id_admin` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_admin`
--

INSERT INTO `tbl_admin` (`id_admin`, `name`, `username`, `password`) VALUES
(1, 'Admin 1', 'admin', '0192023a7bbd73250516f069df18b500');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_lapangan`
--

CREATE TABLE `tbl_lapangan` (
  `id_lap` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_lapangan`
--

INSERT INTO `tbl_lapangan` (`id_lap`, `name`, `status`) VALUES
(1, 'Lapangan 1', 'Jenis Sintetis, Scoreboard, Kipas'),
(2, 'Lapangan 2', 'Jenis Plester, Scoreboard, Kipas'),
(3, 'Lapangan 3', 'Jenis Sintetis'),
(4, 'Lapangan 4', 'Jenis Plester');

--
-- Trigger `tbl_lapangan`
--
DELIMITER $$
CREATE TRIGGER `log_edit_status` AFTER UPDATE ON `tbl_lapangan` FOR EACH ROW INSERT INTO update_log VALUES("UPD-lapangan", new.status, new.id_lap, now(), now())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pemesanan`
--

CREATE TABLE `tbl_pemesanan` (
  `id_pesanan` int(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `lapangan` varchar(255) NOT NULL,
  `mulai_jam` time NOT NULL,
  `selesai_jam` time NOT NULL,
  `tanggal` date NOT NULL,
  `catatan` varchar(255) NOT NULL,
  `status_bayar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_pemesanan`
--

INSERT INTO `tbl_pemesanan` (`id_pesanan`, `nama`, `lapangan`, `mulai_jam`, `selesai_jam`, `tanggal`, `catatan`, `status_bayar`) VALUES
(3, 'Tirjo', '1', '00:00:14', '00:00:15', '2020-11-01', 'coba', 'lunas'),
(4, 'Beta', '2', '10:00:00', '12:00:00', '2020-03-11', 'Tolong Mas', 'lunas'),
(9, 'jarwo', '1', '19:00:00', '20:00:00', '2020-05-12', 'coba', 'menunggu'),
(10, 'Pakndul', '4', '07:00:00', '08:00:00', '2020-06-11', 'Core of the core', 'menunggu');

--
-- Trigger `tbl_pemesanan`
--
DELIMITER $$
CREATE TRIGGER `log_delete_pemesanan` AFTER DELETE ON `tbl_pemesanan` FOR EACH ROW INSERT INTO update_log VALUES("DEL-pesanan", "Batal",old.id_pesanan, old.tanggal, now())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `log_tambah_detail` AFTER INSERT ON `tbl_pemesanan` FOR EACH ROW INSERT INTO update_log VALUES("INS-pesanan", " ", new.id_pesanan, new.tanggal, now())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `log_update_detail` AFTER UPDATE ON `tbl_pemesanan` FOR EACH ROW INSERT INTO update_log VALUES("UPD-pesanan", new.status_bayar, new.id_pesanan, new.tanggal, now())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nohp` varchar(50) NOT NULL,
  `point` int(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `name`, `nohp`, `point`, `username`, `password`) VALUES
(2, 'Ewing HD', '081', 10, 'ewinghd', 'dbf7b0ef7d75485ea9f96021612c998e'),
(3, 'yogi', '089', 10, 'yogi', '0ea1b32a4d7252fb7efb9ce3acf27a'),
(4, 'rijal', '085', 6, 'rijal', '5aaeb857d4ee88b574367a11b7817269');

-- --------------------------------------------------------

--
-- Struktur dari tabel `update_log`
--

CREATE TABLE `update_log` (
  `aksi` varchar(250) NOT NULL,
  `status` varchar(255) NOT NULL,
  `id` int(50) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `update_log`
--

INSERT INTO `update_log` (`aksi`, `status`, `id`, `tanggal`, `waktu`) VALUES
('INS-pesanan', '0', 2, '0000-00-00', '2020-03-10 18:36:06'),
('INS-pesanan', '0', 3, '2020-11-01', '2020-03-10 18:39:31'),
('INS-pesanan', ' ', 4, '2020-03-11', '2020-03-10 19:52:23'),
('UPD-lapangan', 'Rumput Vinyl, Papan Skor', 1, '0000-00-00', '2020-03-10 19:56:02'),
('UPD-lapangan', 'Rumput Vinyl, AC', 1, '2020-03-10', '2020-03-10 19:58:03'),
('UPD-pesanan', ' ', 3, '2020-11-01', '2020-03-10 20:01:44'),
('DEL-pesanan', 'Batal', 2, '0000-00-00', '2020-03-10 20:17:34'),
('UPD-lapangan', '', 1, '2020-04-14', '2020-04-14 15:05:19'),
('UPD-lapangan', '', 2, '2020-04-14', '2020-04-14 15:05:19'),
('UPD-lapangan', 'Sintetis, Scoreboard, Kipas', 1, '2020-05-10', '2020-05-10 22:40:20'),
('UPD-lapangan', 'Plester, Scoreboard, Kipas', 2, '2020-05-10', '2020-05-10 22:40:57'),
('UPD-lapangan', 'Jenis Sintetis, Scoreboard, Kipas', 1, '2020-05-10', '2020-05-10 22:41:11'),
('UPD-lapangan', 'Jenis Plester, Scoreboard, Kipas', 2, '2020-05-10', '2020-05-10 22:41:22'),
('UPD-pesanan', 'menunggu', 4, '2020-03-11', '2020-05-11 11:27:52'),
('UPD-pesanan', 'lunas', 4, '2020-03-11', '2020-05-11 11:31:10'),
('INS-pesanan', ' ', 5, '0000-00-00', '2020-05-11 12:28:53'),
('INS-pesanan', ' ', 6, '0000-00-00', '2020-05-11 12:30:26'),
('UPD-pesanan', 'lunas', 3, '2020-11-01', '2020-05-11 12:31:54'),
('UPD-pesanan', 'lunas', 4, '2020-03-11', '2020-05-11 12:32:15'),
('DEL-pesanan', 'Batal', 5, '0000-00-00', '2020-05-11 12:32:25'),
('DEL-pesanan', 'Batal', 6, '0000-00-00', '2020-05-11 12:32:26'),
('INS-pesanan', ' ', 7, '0000-00-00', '2020-05-11 12:33:31'),
('INS-pesanan', ' ', 8, '0000-00-00', '2020-05-11 12:35:01'),
('INS-pesanan', ' ', 9, '2020-05-12', '2020-05-11 12:37:33'),
('DEL-pesanan', 'Batal', 7, '0000-00-00', '2020-05-11 12:37:54'),
('DEL-pesanan', 'Batal', 8, '0000-00-00', '2020-05-11 12:37:54'),
('INS-pesanan', ' ', 10, '2020-06-11', '2020-05-11 12:46:56');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `tbl_lapangan`
--
ALTER TABLE `tbl_lapangan`
  ADD PRIMARY KEY (`id_lap`);

--
-- Indeks untuk tabel `tbl_pemesanan`
--
ALTER TABLE `tbl_pemesanan`
  ADD PRIMARY KEY (`id_pesanan`);

--
-- Indeks untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id_admin` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tbl_lapangan`
--
ALTER TABLE `tbl_lapangan`
  MODIFY `id_lap` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `tbl_pemesanan`
--
ALTER TABLE `tbl_pemesanan`
  MODIFY `id_pesanan` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
