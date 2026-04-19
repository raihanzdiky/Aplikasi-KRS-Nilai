-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Apr 2026 pada 20.44
-- Versi server: 10.4.32-MariaDB-log
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_uts`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_dosen`
--

CREATE TABLE `tb_dosen` (
  `ids` int(10) NOT NULL,
  `nidn` varchar(50) NOT NULL,
  `nama_dosen` varchar(50) NOT NULL,
  `jabatan` enum('wali','kaprodi','pengajar') NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Prempuan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_dosen`
--

INSERT INTO `tb_dosen` (`ids`, `nidn`, `nama_dosen`, `jabatan`, `jenis_kelamin`) VALUES
(1, 'DSN2026001', 'Danny Aidil Rismayadi, S.SI., M.Kom.', 'kaprodi', 'Laki-laki'),
(2, 'DSN2026002', ' Dhany Indra Gunawan, S.T., M.Kom.', 'pengajar', 'Laki-laki'),
(3, 'DSN2026003', 'Hena Sulaeman, ST.', 'pengajar', 'Laki-laki'),
(4, 'DSN2026004', 'Yasti Aisyah Primianjani, S.Kom.', 'pengajar', 'Prempuan'),
(5, 'DSN2026005', 'Iwan Ridwan, S.T., M.Kom.', 'pengajar', 'Laki-laki'),
(6, 'DSN2026006', 'Erryck Norrys, S.Kom.', 'pengajar', 'Laki-laki'),
(7, 'DSN2026007', 'Sri Kuswayati, S.Si., M.Kom.', 'pengajar', 'Prempuan'),
(8, 'DSN2026008', ' Fahmi Abdullah, S.T., M.Kom.', 'pengajar', 'Laki-laki'),
(9, 'DSN2026009', 'Tarsinah Sumarni, S.Kom., M.Kom.', 'wali', 'Prempuan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_krs`
--

CREATE TABLE `tb_krs` (
  `id_krs` int(12) NOT NULL,
  `idm` int(10) NOT NULL,
  `idmk` int(10) NOT NULL,
  `tanggal_isi` date NOT NULL,
  `status` enum('diterima','ditolak') NOT NULL,
  `ids` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_krs`
--

INSERT INTO `tb_krs` (`id_krs`, `idm`, `idmk`, `tanggal_isi`, `status`, `ids`) VALUES
(6, 2, 3, '2026-04-04', 'diterima', 7),
(7, 1, 3, '2026-04-04', 'diterima', 1),
(11, 1, 2, '2026-04-03', 'diterima', 3),
(13, 15, 1, '2026-04-04', 'diterima', 1),
(14, 15, 2, '2026-04-09', 'diterima', 4),
(15, 15, 3, '2026-04-09', 'diterima', 1),
(16, 1, 4, '2026-04-10', 'diterima', 1),
(17, 1, 5, '2026-04-17', 'diterima', 1),
(18, 1, 6, '2026-04-06', 'diterima', 1),
(19, 1, 7, '2026-04-01', 'diterima', 1),
(20, 1, 8, '2026-04-13', 'diterima', 1),
(21, 1, 1, '2026-04-09', 'diterima', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_mahasiswa`
--

CREATE TABLE `tb_mahasiswa` (
  `idm` int(10) NOT NULL,
  `nim` bigint(15) NOT NULL,
  `nama_mahasiswa` varchar(50) NOT NULL,
  `angkatan` int(4) NOT NULL,
  `departemen` enum('TIF','TI','DKV','MR','BD') NOT NULL,
  `jenis_kelamin` enum('laki-laki','prempuan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_mahasiswa`
--

INSERT INTO `tb_mahasiswa` (`idm`, `nim`, `nama_mahasiswa`, `angkatan`, `departemen`, `jenis_kelamin`) VALUES
(1, 23262011029, 'Ashila Fauziyya', 23, 'TI', 'prempuan'),
(2, 24552011130, 'Ayipnoor Irfan Putra Wahyudin', 24, 'TIF', 'laki-laki'),
(3, 24552011148, 'Abil Fida Ismail', 24, 'TIF', 'laki-laki'),
(5, 24552011306, 'Dafa Irsyad Nashrullah', 24, 'TIF', 'laki-laki'),
(6, 24612091080, 'Amelisa Putri', 24, 'BD', 'laki-laki'),
(7, 24612091127, 'Bima Adha Dinata', 24, 'BD', 'laki-laki'),
(8, 24612161016, 'Shabiya Najla Qanita', 24, 'MR', 'prempuan'),
(9, 24902411070, 'Alvin Adam', 24, 'DKV', 'laki-laki'),
(10, 25262011025, 'Suci Febriyani', 25, 'TI', 'prempuan'),
(12, 24552011194, 'Diky Raihan S', 24, 'TIF', 'laki-laki'),
(15, 234, 'diky', 123, 'TIF', 'laki-laki');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_matakuliah`
--

CREATE TABLE `tb_matakuliah` (
  `idmk` int(10) NOT NULL,
  `id_matkul` varchar(50) NOT NULL,
  `nama_matkul` varchar(50) NOT NULL,
  `jumlah_sks` int(2) NOT NULL,
  `semester` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_matakuliah`
--

INSERT INTO `tb_matakuliah` (`idmk`, `id_matkul`, `nama_matkul`, `jumlah_sks`, `semester`) VALUES
(1, 'MK2026004', 'Teknik Kompilasi', 3, 4),
(2, 'MK2026005', 'Penambangan Data', 3, 6),
(3, 'MK2026006', 'Pemrograman Mobile I ', 3, 4),
(4, 'MK2026007', 'Pemrograman Berorientasi Objek II', 3, 4),
(5, 'MK2026008', 'Keamanan Komputer', 3, 4),
(6, 'MK2026009', 'Jaringan Komputer II', 3, 4),
(7, 'MK2026010', 'Digital Preneurship', 3, 4),
(8, 'MK2026011', 'Analisa dan Perancangan Berorientasi Objek ', 3, 4);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nilai`
--

CREATE TABLE `tb_nilai` (
  `idn` int(12) NOT NULL,
  `idm` int(10) NOT NULL,
  `idmk` int(10) NOT NULL,
  `nakhir` decimal(10,2) DEFAULT NULL,
  `grade` enum('A','B','C','D','E') NOT NULL,
  `status_akhir` enum('Lulus','Tidak Lulus') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_nilai`
--

INSERT INTO `tb_nilai` (`idn`, `idm`, `idmk`, `nakhir`, `grade`, `status_akhir`) VALUES
(16, 12, 1, 90.00, 'A', 'Lulus'),
(18, 1, 1, 90.00, 'A', 'Lulus'),
(19, 1, 3, 90.00, 'A', 'Lulus'),
(20, 1, 4, 90.00, 'A', 'Lulus'),
(21, 2, 1, 91.00, 'A', 'Lulus'),
(23, 15, 1, 1.00, 'E', 'Tidak Lulus'),
(25, 2, 8, 87.00, 'A', 'Lulus'),
(26, 12, 2, 86.00, 'A', 'Lulus'),
(27, 12, 3, 86.00, 'A', 'Lulus'),
(28, 12, 4, 100.00, 'A', 'Lulus'),
(29, 12, 5, 86.00, 'A', 'Lulus'),
(30, 12, 6, 100.00, 'A', 'Lulus'),
(31, 12, 7, 90.00, 'A', 'Lulus'),
(32, 12, 8, 86.00, 'A', 'Lulus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(3) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('admin','operator') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'dd', '12', 'admin'),
(5, 'zz', '12', 'operator'),
(10, 'Diky Raihan Subagja', '1234qwer', 'operator'),
(11, 'Admin01', 'adminpass', 'admin'),
(12, 'operator01', 'operatorpass', 'operator');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_dosen`
--
ALTER TABLE `tb_dosen`
  ADD PRIMARY KEY (`ids`);

--
-- Indeks untuk tabel `tb_krs`
--
ALTER TABLE `tb_krs`
  ADD PRIMARY KEY (`id_krs`),
  ADD KEY `idm` (`idm`),
  ADD KEY `idmk` (`idmk`),
  ADD KEY `ids` (`ids`);

--
-- Indeks untuk tabel `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  ADD PRIMARY KEY (`idm`);

--
-- Indeks untuk tabel `tb_matakuliah`
--
ALTER TABLE `tb_matakuliah`
  ADD PRIMARY KEY (`idmk`);

--
-- Indeks untuk tabel `tb_nilai`
--
ALTER TABLE `tb_nilai`
  ADD PRIMARY KEY (`idn`),
  ADD KEY `idm` (`idm`),
  ADD KEY `idmk` (`idmk`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_dosen`
--
ALTER TABLE `tb_dosen`
  MODIFY `ids` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `tb_krs`
--
ALTER TABLE `tb_krs`
  MODIFY `id_krs` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT untuk tabel `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  MODIFY `idm` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `tb_matakuliah`
--
ALTER TABLE `tb_matakuliah`
  MODIFY `idmk` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `tb_nilai`
--
ALTER TABLE `tb_nilai`
  MODIFY `idn` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_krs`
--
ALTER TABLE `tb_krs`
  ADD CONSTRAINT `tb_krs_ibfk_1` FOREIGN KEY (`idm`) REFERENCES `tb_mahasiswa` (`idm`),
  ADD CONSTRAINT `tb_krs_ibfk_2` FOREIGN KEY (`idmk`) REFERENCES `tb_matakuliah` (`idmk`),
  ADD CONSTRAINT `tb_krs_ibfk_3` FOREIGN KEY (`ids`) REFERENCES `tb_dosen` (`ids`);

--
-- Ketidakleluasaan untuk tabel `tb_nilai`
--
ALTER TABLE `tb_nilai`
  ADD CONSTRAINT `tb_nilai_ibfk_1` FOREIGN KEY (`idm`) REFERENCES `tb_mahasiswa` (`idm`),
  ADD CONSTRAINT `tb_nilai_ibfk_2` FOREIGN KEY (`idmk`) REFERENCES `tb_matakuliah` (`idmk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
