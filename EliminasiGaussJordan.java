import java.io.*;
import java.util.*;

public class EliminasiGaussJordan{

  public void solusiEGJ(double [][]A, double []B) throws Exception{
    int N = B.length;
    int M = A[0].length;
    boolean ceksingular = false;
    int l = 0;

    for (int k = 0; k < Math.min(N,M); k++){
      /*Cari baris yang ingin ditukar*/
        int baristukar = k;

      /*Mencari baris yang ingin dipindah ke atas*/
        for (int i = k + 1; i < N; i++ ){
          if (Math.abs(A[i][k]) > Math.abs(A[baristukar][k])){
            baristukar = i; /*baris yang lebih besar*/
          }


        /* Mengecek matriks singular atau tidak*/
     /*   if (A[k][baristukar] == 0){
          ceksingular = true;
          break;
     }*/

        else {


        /*tukaran baris, yang besar ke atas, yg kecil ke bawah*/
        double [] temp = A[k];
        double t = B[k]; /*bagian b*/
        A[k] = A[baristukar];
        A[baristukar] = temp;
        B[k] = B[baristukar];
        B[baristukar] = t;
      }
    }

        /*rekursi untuk OBE */
        for (int i = k + 1; i < N; i++){
          if (A[k][k] == 0) {
            break;
          }
          else{
          double obe = A[i][k] / A[k][k];
          B[i] -= obe * B[k];
          for (int j = k; j < M; j++){
            A[i][j] -= obe * A[k][j];
          }
        }
      }

      for (int i = 0; i < Math.min(N,M); i++){
      for (int j = 0; j < Math.min(N,M); j++) {

         if (i != j) {

             /*Membentuk matriks eselon tereduksi*/
             double pro = A[j][i] / A[i][i];
             if (A[i][i] == 0){ /*mencegah elemen dibagi 0*/
               break;
             }
             for (k = 0; k < Math.min(N,M); k++)
                 A[j][k] = A[j][k] - (A[i][k]) * pro;
                 B[j] = B[j] - (B[i]) * pro;
         }
     }
    }
  }

        cetakMatriksEselon(A, B);

        /*melakukan rekursi mencari solusi*/
        double[] solusi = new double[N];
        for (int i = Math.min(M - 1, N - 1); i >= 0; i--)
        {
            double hasil = 0.0;
            for (int j = i + 1; j < M; j++){
                hasil += A[i][j] * solusi[j];
           }
            solusi[i] = (B[i] - hasil) / A[i][i];
        }
        /** Print solution **/
        cetakSolusi(solusi,B);
      }




  public void cetakMatriksEselon(double[][] A, double []B) throws Exception{
    int N = B.length; /*mendapatkan panjang kolom*/
    int M = A[0].length; /*mendapatkan panjang baris (hanya sampai matriks A)*/
    double koefbagi = A[0][0];

    System.out.println();
    System.out.println("Matriks eselon yang terbentuk adalah : ");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++){
        A[i][j] = A[i][j] / koefbagi;
        System.out.printf("%.3f ", A[i][j]);
      }
        B[i] = B[i] / koefbagi;
        System.out.printf("%.3f\n", B[i]);
    }
      System.out.println();
  }


  public void cetakSolusi (double[] sol, double[]B) throws Exception{
    int N = sol.length;
    int j;

    System.out.println("Maka solusi dari SPL di atas adalah : ");
    char variabel = 'r';
    FileWriter writer = new FileWriter("HasilGaussJordan.txt");
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
                for (j = 0;j < N ;j++ ) {
                     System.out.printf("X%d = ", j + 1);
                     System.out.printf("%c\n", variabel);
                      //coba ganti file write
                      int k;
                     k = j+1;
                     file.write("X"+k+" = "+variabel);
                     file.newLine();
                     if (variabel == 'z'){
                          variabel = 'a';
                     }
                     else {
                          variabel = (char)((int)variabel + 1);
                     }
                }
             break;
           }
           else if (B[i] != 0){
                System.out.printf("X%d = ", i + 1);
                System.out.printf("%.3f\n", sol[i]);
                j = i+1;
                file.write("X"+j+" = "+sol[i]);
                file.newLine();
         }
         }
         System.out.println("File written Successfully");
    } catch (IOException ioe) {
        ioe.printStackTrace();
     }
     file.close();
  }

}
