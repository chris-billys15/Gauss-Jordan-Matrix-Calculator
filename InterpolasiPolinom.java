import java.io.*;
import java.util.*;

public class InterpolasiPolinom{

  public void solusiIP(double []A, double []B) throws Exception{
    int N = B.length;
    double [][]C = new double[N][N+1];
    double []D = new double[N];
    boolean ceksingular = false;
    int pangkat = 0;
    Scanner scan = new Scanner(System.in);

    /*Memasukkan inputan pengguna ke dalam matriks persamaan*/
    for (int i = 0; i < N ; i++){
      for (int j = 0; j < N; j++){
        C[i][j] = Math.pow(A[i], pangkat);
        pangkat++;
        if (j == N - 1){
          D[i] = B[i];
        }
      }
      pangkat = 0;
    }

    for (int k = 0; k < N ; k++){
      /*Cari baris yang ingin ditukar*/
        int baristukar = k;

      /*Mencari baris yang ingin dipindah ke atas*/
        for (int i = k + 1; i < N ; i++ ){
          if (Math.abs(C[i][k]) > Math.abs(C[baristukar][k])){
            baristukar = i; /*baris yang lebih besar*/
          }


        /* Mengecek matriks singular atau tidak*/
        if (C[k][baristukar] == 0){
          ceksingular = true;
          break;
        }

        else {


        /*tukaran baris, yang besar ke atas, yg kecil ke bawah*/
        double [] temp = C[k];
        double t = D[k]; /*bagian b*/
        C[k] = C[baristukar];
        C[baristukar] = temp;
        D[k] = D[baristukar];
        D[baristukar] = t;
      }
    }

        /*rekursi untuk OBE */
        for (int i = k + 1; i < N ; i++){
          if (C[k][k] == 0) { /*mencegah elemen dibagi 0*/
            break;
          }
          else{
          double obe = C[i][k] / C[k][k];
          D[i] -= obe * D[k];
          for (int j = k; j < N ; j++){
            C[i][j] -= obe * C[k][j];
          }
        }
      }
    }

        cetakMatriksEselon(C, D);

        /*melakukan rekursi mencari solusi*/
        double[] solusi = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double hasil = 0.0;
            for (int j = i + 1; j < N ; j++)
                hasil += C[i][j] * solusi[j];
            solusi[i] = (D[i] - hasil) / C[i][i];
        }
        /* cetak solusi ke layar */
        cetakSolusi(solusi,N);
      }




  public void cetakMatriksEselon(double[][] C, double []D) throws Exception{
    int N = D.length; /*mendapatkan panjang kolom*/
 /*mendapatkan panjang baris (hanya sampai matriks A)*/
    double koefbagi = C[0][0];

    System.out.println();
    System.out.println("Matriks eselon yang terbentuk adalah : ");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++){
        C[i][j] = C[i][j] / koefbagi; /*Membuat leading one*/
        System.out.printf("%.3f ", C[i][j]);
      }
        D[i] = D[i] / koefbagi;
        System.out.printf("%.3f\n", D[i]);
    }
      System.out.println();
  }


  public void cetakSolusi (double[] sol,int Baris) throws Exception{
    int N = sol.length;
    int j;

    System.out.println("Maka solusi dari interpolasi di atas : ");
    FileWriter writer = new FileWriter("HasilInterpolasi.txt");
    BufferedWriter file = new BufferedWriter(writer);
    try{
    /*Solusi unik*/
         for (int i = 0; i < N; i++){
           /*Kalau tidak ada solusi*/
           if (sol[i] == Double.POSITIVE_INFINITY || sol[i] == Double.NEGATIVE_INFINITY){
             System.out.printf("Solusi tidak ada (Sistem inkonsisten)");
             file.write("Solusi tidak ada (Sistem inkonsisten)");
             file.newLine();
             break;
           }
           else if (Double.isNaN(sol[i])) { /*Solusi banyak/infinite*/
             System.out.printf("Solusi banyak");
             file.write("Solusi banyak");
             file.newLine();
             break;
           }
           else {
           System.out.printf("a%d = ", i);
           System.out.printf("%.4f\n", sol[i]);
           file.write("a"+i+" = "+sol[i]);
           file.newLine();
         }
         }
         /*Memasukkan x secara sembarang*/
        System.out.println("Masukkan nilai x secara sembarang :");
        Scanner scan = new Scanner(System.in);
        double x = scan.nextDouble();
        double sum = 0.00;

        int pangkat = 0;
        for (int l = 0; l < N; l++){
          sum = sum + sol[l] * Math.pow(x, pangkat);
          pangkat++;
        }
        System.out.println(sum);
        file.write("f("+ x +") = "+sum+"");
        file.newLine();
    }catch (IOException ioe) {
        ioe.printStackTrace();
     }
     file.close();
  }
  }
