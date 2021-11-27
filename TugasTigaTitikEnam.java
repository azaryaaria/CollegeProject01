package Pemdas;

import java.awt.*;
import java.util.Scanner;

public class TugasTigaTitikEnam {
    //baru
    public static Scanner scr = new Scanner(System.in);
    public static String str_pilihan, jenis_tiket = "", stasiun_asal = "", stasiun_tujuan = "", tanggal_keberangkatan = "", nama = "", nomor_telp = "",
            nomor_ktp = "", alamat = "", ID = "", arraySemuanya[][], formatted_nomor_ktp = "", formatted_jumlah_tiket, ktptostring, kode_tiket,
            kode_kota_asal, kode_kota_tujuan, lower_stasiun, kode_asal, kode_tujuan, lower_tiket;
    public static long harga, jarak ;
    public static boolean acuan_asal, sudah, acuan = true, haha = true, bait = false;
    public static double total_tagihan, total_tagihan_diskon, kembalian, nominal_pembayaran;
    public static byte jumlah_tiket;
    public static int titik, input_baris;


    public static void main(String[] args) {
        String    tumbal = "";
        titik = 0;
        String ID_for_array = "";
        sudah = false;
        String ktp_array[] = new String[5];
        ktp_array[0] = " ";


        System.out.println("|===============================================================|");
        System.out.println("|                  \uD83D\uDE86  FILKOM RAIL EXPRESS  \uD83D\uDE86                   |");
        System.out.println("|                 PROGRAM PEMESANAN TIKET KERETA                |");
        System.out.println("|                SILAKAN MASUKKAN DATA YANG SESUAI              |");
        System.out.println("|===============================================================|");
        //System.out.println(HitungHarga());
        arraySemuanya = new String[5][10];
        arraySemuanya[0][0] = "";
        input_baris = 0;

        do {
            //=====Pemanggilan Fungsi Menu Utama=====
            MenuUtama();
            //=======================================

            if (str_pilihan.equalsIgnoreCase("1")) {
                //=====Pemanggilan Fungsi Input Data Pesanan=====
                InputDataPesanan();
                //===============================================

                //=====Menentukan Harga=====
                MenentukanHarga();
                //==============================

                //=====Menghitung Jarak=====
                HitungJarak();
                //==============================

                //=====Menghitung Tagihan=====
                HitungBiaya();
                //==============================

                //=====Pemanggilan fungsi Hitung Diskon=====
                HitungDiskon();
                //=============================================

                System.out.println("\n===============================================");
                System.out.printf("Nominal yang harus dibayar   : Rp. %,.0f", total_tagihan);
                System.out.println("\n===============================================");

                System.out.print("\n Nominal pembayaran            : ");
                nominal_pembayaran = scr.nextLong();
                scr.nextLine();


                //=====Membuat Kode Tiket=====
                PembuatanKodeTiket();
                //==============================

                //=====Membuat Kode Stasiun=====
                kode_asal = PembuatanKodeStasiun(stasiun_asal, kode_kota_asal);
                kode_tujuan = PembuatanKodeStasiun(stasiun_tujuan, kode_kota_tujuan);
                //======================================================================

                formatted_nomor_ktp = nomor_ktp.substring(nomor_ktp.length() - 3);
                if (nominal_pembayaran < total_tagihan) {


                    System.out.println("\n|===============================================================|");
                    System.out.println("|                     UANG TIDAK MENCUKUPI!                     |");
                    System.out.println("|                      Coba ulangi kembali                      |");
                    System.out.println("|Pastikan uang yang dimasukkan pas atau lebih dari total tagihan|");
                    System.out.println("|===============================================================|");

                } else {

                    ktptostring = formatted_nomor_ktp.toString();
                    formatted_jumlah_tiket = String.format("%03d", jumlah_tiket);
                    ID = kode_asal + "-" + kode_tujuan + kode_tiket + ktptostring + formatted_jumlah_tiket;

                    kembalian = nominal_pembayaran - total_tagihan;
                    System.out.println("\n==============================================================");
                    System.out.println("\nPemesanan Tiket Diproses..");
                    System.out.println("\n==============================================================");
                    System.out.println("\nPemesanan Anda Berhasil Diproses!");

                    //=====Melihat Detail Info Pesanan=====
                    DetailPemesanan();
                    //========================================


                    if (input_baris >= 5) {
                        sudah = true;
                        input_baris = 0;
                    }
                    //=====Pemanggilan Fungsi Pembuatan Array=====
                    PembuatanArray();
                    //=============================================

                }
            }
            //=====Menampilkan Jenis Tiket=====
            JenisTiket();
            //===================================

            //=====Menampilkan Daftar Stasiun=====
            DaftarStasiun();
            //========================================

            //=====Menampilkan Riwayat Pemesanan=====
            RiwayatPemesanan();
            //========================================

            //=====Melacak Detail Pesanan=====
            CekPesanan();
            //===================================

            //=====Keluar Dari Program=====
            keluar();
            //===================================

            if (haha = false) {
                bait = true;
            }


        } while (acuan);
    }

    public static void MenuUtama() {
        System.out.println("\nMenu");
        System.out.println("1. Daftar");
        System.out.println("2. Lihat jenis tiket");
        System.out.println("3. Stasiun");
        System.out.println("4. Riwayat pemesanan");
        System.out.println("5. Cek pesanan");
        System.out.println("6. Keluar");
        Scanner input_pilihan = new Scanner(System.in);
        System.out.print("Masukkan pilihan anda : ");
        str_pilihan = input_pilihan.nextLine();
    }
    public static void HitungJarak() {
        jarak = 0;

        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Surabaya")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Surabaya")) {

            jarak += 100;

        }
        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Yogyakarta")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Yogyakarta")) {

            jarak += 400;

        }
        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Semarang")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Semarang")) {

            jarak += 450;

        }
        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Bandung")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Bandung")) {

            jarak += 800;

        }
        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Jakarta")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Jakarta")) {

            jarak += 900;

        }
        if (stasiun_asal.equalsIgnoreCase("Malang") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Malang") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 1000;

        }
        //stasiun_asal
        if (stasiun_asal.equalsIgnoreCase("Surabaya") &&
                stasiun_tujuan.equalsIgnoreCase("Yogyakarta")
                || stasiun_tujuan.equalsIgnoreCase("Surabaya") &&
                stasiun_asal.equalsIgnoreCase("Yogyakarta")) {

            jarak += 300;

        }
        if (stasiun_asal.equalsIgnoreCase("Surabaya") &&
                stasiun_tujuan.equalsIgnoreCase("Semarang")
                || stasiun_tujuan.equalsIgnoreCase("Surabaya") &&
                stasiun_asal.equalsIgnoreCase("Semarang")) {

            jarak += 350;

        }
        if (stasiun_asal.equalsIgnoreCase("Surabaya") &&
                stasiun_tujuan.equalsIgnoreCase("Bandung")
                || stasiun_tujuan.equalsIgnoreCase("Surabaya") &&
                stasiun_asal.equalsIgnoreCase("Bandung")) {

            jarak += 700;

        }
        if (stasiun_asal.equalsIgnoreCase("Surabaya") &&
                stasiun_tujuan.equalsIgnoreCase("Jakarta")
                || stasiun_tujuan.equalsIgnoreCase("Surabaya") &&
                stasiun_asal.equalsIgnoreCase("Jakarta")) {

            jarak += 800;

        }
        if (stasiun_asal.equalsIgnoreCase("Surabaya") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Surabaya") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 900;

        }

        //jogja

        if (stasiun_asal.equalsIgnoreCase("Yogyakarta") &&
                stasiun_tujuan.equalsIgnoreCase("Semarang")
                || stasiun_tujuan.equalsIgnoreCase("Yogyakarta") &&
                stasiun_asal.equalsIgnoreCase("Semarang")) {

            jarak += 50;

        }
        if (stasiun_asal.equalsIgnoreCase("Yogyakarta") &&
                stasiun_tujuan.equalsIgnoreCase("Bandung")
                || stasiun_tujuan.equalsIgnoreCase("Yogyakarta") &&
                stasiun_asal.equalsIgnoreCase("Bandung")) {

            jarak += 400;

        }
        if (stasiun_asal.equalsIgnoreCase("Yogyakarta") &&
                stasiun_tujuan.equalsIgnoreCase("Jakarta")
                || stasiun_tujuan.equalsIgnoreCase("Yogyakarta") &&
                stasiun_asal.equalsIgnoreCase("Jakarta")) {

            jarak += 500;

        }
        if (stasiun_asal.equalsIgnoreCase("Yogyakarta") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Yogyakarta") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 600;

        }

        //SMG

        if (stasiun_asal.equalsIgnoreCase("Semarang") &&
                stasiun_tujuan.equalsIgnoreCase("Bandung")
                || stasiun_tujuan.equalsIgnoreCase("Semarang") &&
                stasiun_asal.equalsIgnoreCase("Bandung")) {

            jarak += 350;

        }
        if (stasiun_asal.equalsIgnoreCase("Semarang") &&
                stasiun_tujuan.equalsIgnoreCase("Jakarta")
                || stasiun_tujuan.equalsIgnoreCase("Semarang") &&
                stasiun_asal.equalsIgnoreCase("Jakarta")) {

            jarak += 450;

        }
        if (stasiun_asal.equalsIgnoreCase("Semarang") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Semarang") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 550;

        }

        //BDG

        if (stasiun_asal.equalsIgnoreCase("Bandung") &&
                stasiun_tujuan.equalsIgnoreCase("Jakarta")
                || stasiun_tujuan.equalsIgnoreCase("Bandung") &&
                stasiun_asal.equalsIgnoreCase("Jakarta")) {

            jarak += 100;

        }
        if (stasiun_asal.equalsIgnoreCase("Bandung") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Bandung") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 200;

        }

        //jkt

        if (stasiun_asal.equalsIgnoreCase("Jakarta") &&
                stasiun_tujuan.equalsIgnoreCase("Serang")
                || stasiun_tujuan.equalsIgnoreCase("Jakarta") &&
                stasiun_asal.equalsIgnoreCase("Serang")) {

            jarak += 100;

        }
    }
    public static void InputDataPesanan(){
        System.out.println("\n           Silakan isi data diri dibawah ini dengan benar            ");

        System.out.print("\n Nama                          : ");
        nama = scr.nextLine();
        System.out.print(" Nomor KTP                     : ");
        nomor_ktp = scr.nextLine();
        System.out.print(" Alamat                        : ");
        alamat = scr.nextLine();
        System.out.print(" Nomor Telepon                 : ");
        nomor_telp = scr.nextLine();
        System.out.println("\n           ===============================================            ");
        System.out.println("           Silakan isi data tiket dibawah ini dengan benar            ");
        System.out.println("           ===============================================            ");

        System.out.println("           \nDaftar stasiun yang tersedia saat ini :             ");
        System.out.println("1. Malang\t \t 4. Semarang \t 7. Serang");
        System.out.println("2. Surabaya\t \t 5. Bandung");
        System.out.println("3. Yogyakarta\t 6. Jakarta");


        System.out.println("           \nJenis Tiket :             ");
        System.out.println("1. Hijau (Ekonomi)\t 2. Kuning (Premium) \t 3. Merah (Eksklusif)");

        System.out.printf(" \n %-15s : ", "Tanggal Keberangkatan ");
        tanggal_keberangkatan = scr.nextLine();
        StasiunValid();
        TiketValid();
        System.out.printf(" %-15s %c ", "Jumlah Tiket", ':');
        jumlah_tiket = scr.nextByte();
    }
    public static long ManggilHitungJarak(){
        return jarak;
    }
    public static void DetailPemesanan(){
        System.out.println("\n==============================================================");
        System.out.printf(" %-35s %c %s", "ID", ':', ID);
        System.out.printf("\n %-35s %c %s", "Nama", ':', nama);
        System.out.printf("\n %-35s %c %s", "Nomor KTP", ':', nomor_ktp);
        System.out.printf("\n %-35s %c %s", "Alamat", ':', alamat);
        System.out.printf("\n %-35s %c %s", "Nomor Telepon", ':', nomor_telp);
        System.out.printf("\n %-35s %c %s", "Tanggal Keberangkatan", ':', tanggal_keberangkatan);
        System.out.printf("\n %-35s %c %s - %s (%d Km)", "Rute", ':', stasiun_asal, stasiun_tujuan, jarak);
        System.out.printf("\n %-35s %c %d", "Jumlah Tiket", ':', jumlah_tiket);
        System.out.printf("\n %-35s %c Rp. %,.2f, -", "Total Tagihan", ':', total_tagihan);
        System.out.printf("\n %-35s %c Rp. %,.2f, -", "Nominal Pembayaran", ':', nominal_pembayaran);
        System.out.printf("\n %-35s %c Rp. %,.2f, -", "Kembalian", ':', kembalian);
        System.out.println("\n\n Pemesanan Tiket Berhasil! \uD83D\uDE01                            ");
        System.out.println(" Terima kasih sudah memesan! \uD83D\uDE4F                            ");
        System.out.println("============================================================");
    }
    public static void JenisTiket(){
        if (str_pilihan.equalsIgnoreCase("2")) {

            System.out.println("           \nJenis Tiket :             ");
            System.out.println("1. Hijau (Ekonomi)\t 2. Kuning (Premium) \t 3. Merah (Eksklusif)");
        }

    }
    public static void DaftarStasiun(){
        if (str_pilihan.equalsIgnoreCase("3")) {
            System.out.println("           \nDaftar stasiun yang tersedia saat ini :             ");
            System.out.println("1. Malang\t \t 4. Semarang \t 7. Serang");
            System.out.println("2. Surabaya\t \t 5. Bandung");
            System.out.println("3. Yogyakarta\t 6. Jakarta");
        }

    }
    public static void RiwayatPemesanan(){
            if (str_pilihan.equalsIgnoreCase("4")) {
                if (arraySemuanya[0][0].equalsIgnoreCase("")) {
                    System.out.println("Belum ada pemesanan!");
                } else {
                    System.out.println("--".repeat(25));
                    System.out.println("Riwayat Pemesanan Tiket");
                    System.out.println("--".repeat(25));
                    if (sudah == true) {
                        for (titik = 0; titik < 5; titik++) {
                            System.out.println();
                            System.out.print(titik+1 + ". ");
                            System.out.println(arraySemuanya[titik][0] + ": " + arraySemuanya[titik][4]);
                        }
                    } else {
                        for (titik = 0; titik < input_baris; titik++) {
                            System.out.println();
                            System.out.print(titik+1 + ". ");
                            System.out.println(arraySemuanya[titik][0] + ": " + arraySemuanya[titik][4]);
                        }
                    }
                }
            }
    }
    public static void HitungBiaya(){
        total_tagihan += harga * jumlah_tiket * jarak;
    }
    public static void HitungDiskon(){
        total_tagihan_diskon = 0;
        if (jarak > 700) {
            if (jenis_tiket.equalsIgnoreCase("Hijau")) {
                total_tagihan_diskon += total_tagihan * 20 / 100;
                total_tagihan -= total_tagihan_diskon;
                System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 20%");
            }
            if (jenis_tiket.equalsIgnoreCase("Kuning")) {
                total_tagihan_diskon = total_tagihan * 25 / 100;
                total_tagihan -= total_tagihan_diskon;
                System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 25%");
            }

            if (jenis_tiket.equalsIgnoreCase("Merah")) {
                total_tagihan_diskon = total_tagihan * 30 / 100;
                total_tagihan -= total_tagihan_diskon;
                System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 30%");

            }
        } else if (jarak > 400 && 700 >= jarak) {
            if (jenis_tiket.equalsIgnoreCase("Kuning")) {
                total_tagihan_diskon = total_tagihan * 20 / 100;
                total_tagihan -= total_tagihan_diskon;
                System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 20%");

            }

            if (jenis_tiket.equalsIgnoreCase("Merah")) {
                total_tagihan_diskon = total_tagihan * 25 / 100;
                total_tagihan -= total_tagihan_diskon;
                System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 25%");

            }

        } else if (jarak >= 300 && 400 >= jarak && jenis_tiket.equalsIgnoreCase("Merah")) {
            total_tagihan_diskon = total_tagihan * 20 / 100;
            total_tagihan -= total_tagihan_diskon;
            System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 20%");
        } else {
            total_tagihan_diskon = total_tagihan * 5 / 100;
            total_tagihan -= total_tagihan_diskon;
            System.out.println("\nSelamat! Anda mendapatkan diskon sebesar 5%");
        }
    }
    public static void CekPesanan(){
        if (str_pilihan.equalsIgnoreCase("5")) {

            boolean cek = true;
            do {
                boolean sudah_ditemukan = false;
                System.out.print("Masukkan ID Pesanan Anda :");
                String cek_id = scr.nextLine();
                System.out.print("Masukkan Nomor KTP Anda :");
                String cek_ktp = scr.nextLine();
                for (int c = 0; c < 5; c++) {

                    if (cek_id.equalsIgnoreCase(arraySemuanya[c][0]) && cek_ktp.equalsIgnoreCase(arraySemuanya[c][1])) {

                        System.out.printf(" %-35s %c %s", "ID", ':', arraySemuanya[c][0]);
                        System.out.printf("\n %-35s %c %s", "Nomor KTP", ':', arraySemuanya[c][1]);
                        System.out.printf("\n %-35s %c %s", "Nama", ':', arraySemuanya[c][2]);
                        System.out.printf("\n %-35s %c %s", "Rute", ':', arraySemuanya[c][3]);
                        System.out.printf("\n %-35s %c %s", "Tanggal Keberangkatan", ':', arraySemuanya[c][4]);
                        System.out.printf("\n %-35s %c %s", "Jenis Tiket", ':', arraySemuanya[c][5]);
                        System.out.printf("\n %-35s %c %s", "Jumlah Tiket", ':', arraySemuanya[c][6]);
                        cek = false;
                        sudah_ditemukan = true;
                    }
                }
                if (sudah_ditemukan == false) {
                    System.out.println("\nPesanan tidak ditemukan");
                    cek = false;

                }


            } while (cek);
        }
    }
    public static void keluar() {
    if (str_pilihan.equalsIgnoreCase("6")) {
            acuan = false;
        }
    }
    public static void PembuatanArray(){
        arraySemuanya[input_baris][0] = ID;
        arraySemuanya[input_baris][1] = nomor_ktp;
        arraySemuanya[input_baris][2] = nama;
        arraySemuanya[input_baris][3] = stasiun_asal + "-" + stasiun_tujuan;
        arraySemuanya[input_baris][4] = tanggal_keberangkatan;
        arraySemuanya[input_baris][5] = jenis_tiket;
        arraySemuanya[input_baris][6] = formatted_jumlah_tiket;
        input_baris++;
    }
    public static String PembuatanKodeStasiun(String a, String kode_kota_asalmaupuntujuan){
        lower_stasiun = a.toLowerCase();
        switch (lower_stasiun) {
            case "malang":
                kode_kota_asalmaupuntujuan = "MLG";
                break;
            case "jakarta":
                kode_kota_asalmaupuntujuan = "JKT";
                break;
            case "surabaya":
                kode_kota_asalmaupuntujuan = "SBY";
                break;
            case "bandung":
                kode_kota_asalmaupuntujuan = "BDG";
                break;
            case "semarang":
                kode_kota_asalmaupuntujuan = "SMR";
                break;
            case "yogyakarta":
                kode_kota_asalmaupuntujuan = "YOG";
                break;
            case "serang":
                kode_kota_asalmaupuntujuan = "SRG";
                break;
        }
        return kode_kota_asalmaupuntujuan;
    }
    public static void PembuatanKodeTiket(){
        lower_tiket = jenis_tiket.toLowerCase();
        kode_tiket = "";
        switch (lower_tiket) {
            case "merah":
                kode_tiket = "03";
                break;
            case "kuning":
                kode_tiket = "02";
                break;
            case "hijau":
                kode_tiket = "01";
                break;
        }
    }
    public static void StasiunValid(){
        boolean acuan_asal = false;
        do {
            System.out.printf(" %-15s %c ", "Stasiun Asal", ':');
            stasiun_asal = scr.nextLine();
            if (stasiun_asal.equalsIgnoreCase("Malang") || stasiun_asal.equalsIgnoreCase("Surabaya") || stasiun_asal.equalsIgnoreCase("Yogyakarta")
                    || stasiun_asal.equalsIgnoreCase("Semarang") || stasiun_asal.equalsIgnoreCase("Bandung") ||
                    stasiun_asal.equalsIgnoreCase("Jakarta") || stasiun_asal.equalsIgnoreCase("Serang")) {
                acuan_asal = false;

            } else {
                acuan_asal = true;
                System.out.println("\nStasiun tidak tersedia!\n");
            }
        }
        while (acuan_asal);


        boolean acuan_tujuan = false;
        do {
            System.out.printf(" %-15s %c ", "Stasiun Tujuan", ':');
            stasiun_tujuan = scr.nextLine();
            if (stasiun_tujuan.equalsIgnoreCase("Malang") || stasiun_tujuan.equalsIgnoreCase("Surabaya") || stasiun_tujuan.equalsIgnoreCase("Yogyakarta")
                    || stasiun_tujuan.equalsIgnoreCase("Semarang") || stasiun_tujuan.equalsIgnoreCase("Bandung") ||
                    stasiun_tujuan.equalsIgnoreCase("Jakarta") || stasiun_tujuan.equalsIgnoreCase("Serang")) {
                acuan_tujuan = false;

            } else {
                acuan_tujuan = true;
                System.out.println("\nStasiun tidak tersedia!\n");
            }
        }
        while (acuan_tujuan);
    }
    public static void TiketValid(){
        boolean acuan_jenisTiket = false;
        do {
            System.out.printf(" %-15s %c ", "Jenis Tiket", ':');
            jenis_tiket = scr.nextLine();
            if (jenis_tiket.equalsIgnoreCase("hijau") || jenis_tiket.equalsIgnoreCase("kuning") || jenis_tiket.equalsIgnoreCase("merah")
            ) {
                acuan_jenisTiket = false;

            } else {
                acuan_jenisTiket = true;
                System.out.println("\nJenis tiket tidak tersedia!\n");
            }
        }
        while (acuan_jenisTiket);
    }
    public static void MenentukanHarga(){
        if (jenis_tiket.equals("hijau") || jenis_tiket.equals("Hijau") || jenis_tiket.equals("1")) {
            harga += 440;
        }
        if (jenis_tiket.equals("Kuning") || jenis_tiket.equals("kuning") || jenis_tiket.equals("2")) {
            harga += 710;
        }
        if (jenis_tiket.equals("Merah") || jenis_tiket.equals("merah") || jenis_tiket.equals("3")) {
            harga += 1000;
        }
    }

}