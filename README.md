## What's this repo?
This repo made to keep updates of my app project, Silau.
Scroll down for english version.

# SilauAPP
Apa itu Silau?\
SILAU(Sistem Informasi Laundry) adalah sebuah aplikasi yang dibuat untuk mengikuti alur informasi pada sebuah Laundry. Penggunanya adalah pelanggan dan pekerja laundry. Pekerja laundry memiliki tangggung jawab dan akses untuk membuat data transaksi pelanggan. Sementara pelanggan dapat melakukan track pada Laundry mereka. Jika waktu dan ilmu saya meyanggupi, fitur seperti informasi antar jemput Laundry akan ditambahkan

## Kebutuhan Fungsional Dan Kebutuhan Non Fungsional:
### Kebutuhan Fungsional 
1. Sistem mampu menampilkan data transaksi Laundry pelanggan
2. Sistem dapat menambahkan data transaksi Laundry
3. Sistem dapat menambahkan, mengubah, dan menghapus data paket Laundry tersedia
4. Sistem dapat menghapus data transaksi Laundry
5. Sistem dapat menampilkan penghasilan bulanan Laundry
6. Sistem dapat menambahkan, menghapus, mengubah data pelanggan

### Kebutuhan Non Fungsional
1. Sistem tersedia dalam bahasa Indonesia
2. Sistem mampu bekerja pada device berbasis android

## Cara Menjalankan
Sebelum menjalankan aplikasi ini di ponsel anda, perlu diketahui bahwa server deploy yang saya gunakan
adalah jaringan lokal dengan komputer saya sebagai server(menggunakan NodeJS dan MSSQL) sehingga nantinya
data dapat ditarik oleh retrofit untuk dijadikan objek pada aplikasi.
Spesifikasi :
-Dibutuhkan Komputer Server Terinstal SQLServer dan NodeJS
### 1. Membuat database pada SQLserver komputer server
membuat struktur Database baru + dummy Data :
drop table if exists pelanggan;
drop table if exists paket;
drop table if exists pekerja;
drop table if exists transaksi;

create table pelanggan (
	id int identity ,
	nama varchar(20),
	nomor varchar(15),
	username varchar(15),
	passwd varchar(256)
	);

create table pekerja(
	id int identity ,
	nama varchar(20),
	nomor varchar(15),
	username varchar(15),
	passwd varchar(256)
	);

create table paket (
	id int identity ,
	namapaket varchar(20),
	harga int,
    deskripsi varchar(200)
	);

create table transaksi (
	id int identity ,
	id_pelanggan int,
	id_paket int,
	id_admin int,
	tgl_masuk date,
	berat int,
	status_bayar int,
);
insert into pelanggan values ('Carlo Win Marshal', '0821132132132','carlo','carlo')
insert into pekerja values ('Super Admin', '0821132132132','admin','admin')
insert into paket values ('CuciKering', '4000','Cuci, dikeringkan tanpa disetrika')

### 2. Menjalankan ./server/server.js pada node js komputer server
Server menggunakan NodeJS dengan mempublikasikan SQLServer menggunakan Node untuk menghasilkan CRUD berbentuk 

### 3. Melakukan pengaturan pada link di APIClient.java
Mengganti 192.168.100.12 dengan konfigurasi IP server.

### 4. Menginstall aplikasi pada Device yang digunakan.
Bisa melalui Android studio atau menginstall ./app/build/outputs/apk/debug/app-debug.apk pada device anda.
