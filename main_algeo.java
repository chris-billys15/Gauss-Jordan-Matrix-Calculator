import java.io.*;
import java.util.*;

public class main_algeo {

     public static void main(String[] args) throws Exception{
          // intro
          System.out.println("_//       _//      _/       _/// _//////_///////    _/ /_//   _//    _// //");
          System.out.println("_/ _//   _///     _/ //          _//    _//    _//  _/ /_//  _//   _//    _//");
          System.out.println("_// _// _ _//    _/  _//         _//    _//    _//  _/ /_// _//     _//");
          System.out.println("_//  _//  _//   _//   _//        _//    _/ _//      _/ /_/ _/         _//");
          System.out.println("_//   _/  _//  _////// _//       _//    _//  _//    _/ /_//  _//         _//");
          System.out.println("_//       _// _//       _//      _//    _//    _//  _/ /_//   _//  _//    _//");
          System.out.println("_//       _//_//         _//     _//    _//      _//_/ /_//     _//  _// //");

          int sisa = 0;
          Menu Ops = new Menu();
          Ops.MainMenu();
          if (Ops.state != "keluar"){
               Ops.Operasi();
               if (Ops.state == "keyboard"){
                    int N = 0;
                    int M = 0;
                    Scanner scan = new Scanner(System.in);
                    if (Ops.stateops == "spl"){
                         System.out.println("Masukkan ukuran matriks yang diinginkan (N x M) : ");
                         N = scan.nextInt();
                         M = scan.nextInt();
                         if (M>N){
                              sisa = M-N;
                         }
                    }
                    else if (Ops.stateops == "interpolasi"){
                         System.out.println("Masukkan N : ");
                         N = scan.nextInt();
                         M = 2;

                    }

                    /*Inisialisasi Matriks */
                    double[] B = new double[N + sisa];
                    double[] IA = new double[N+1];
                    double[] IB = new double[N+1];
                    double[][] A = new double[N + sisa][M];

                    /*Memasukkan nilai Matriks (Aij dan Bi) */
                    System.out.println("Masukkan SPL dalam bentuk matriks : ");
                    if (Ops.stateops == "spl"){
                         for (int i = 0; i < N; i++){
                           for (int j = 0; j < M + 1; j++){
                             if (j != M){
                                  A[i][j] = scan.nextDouble();
                             }
                             else
                             if (j == M) {
                                 B[i] = scan.nextDouble();
                             }
                           }
                         }
                         if (sisa != 0){
                              for (int i = N;i < N + sisa  ;i++ ) {
                                   for (int j = 0;j < M ;j++ ) {
                                        A[i][j] = 0;
                                   }
                                   B[i] = 0;
                              }
                         }
                    }
                    else if (Ops.stateops == "interpolasi"){
                         for (int i = 0; i <= N; i++){
                           IA[i] = scan.nextDouble();
                           IB[i] = scan.nextDouble();
                         }
                    }

                    System.out.println();
                    if (Ops.stateops == "spl"){
                         Ops.Fungsi();
                         if (Ops.state == "gauss"){
                              EliminasiGauss eg = new EliminasiGauss();
                              eg.solusiEG(A,B);

                              System.out.println();
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                         else if (Ops.state == "gauss-jordan") {
                              System.out.println("lakukan gauss-jordan");
                              EliminasiGaussJordan egj = new EliminasiGaussJordan();
                              egj.solusiEGJ(A,B);

                              System.out.println();
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                         else if (Ops.state == "keluar"){
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                    }
                    else if (Ops.stateops == "interpolasi"){
                         InterpolasiPolinom ip = new InterpolasiPolinom();
                         ip.solusiIP(IA,IB);

                         System.out.println();
                         System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                    }
               }
               else if (Ops.state == "file") {
                    double[][] matrix = new double[1000][1000];
                    double [] array = new double [1000];
                    int brs = 0;
                    int i = 0,j = 0;
                    int klm = 0;
                    try
                  {
                    String namafile = new String();

                    if (Ops.stateops == "spl"){
                         namafile = "MatriksSPL.txt";
                    }
                    else if (Ops.stateops == "interpolasi"){
                         namafile = "MatriksInterpol.txt";
                    }
                    BufferedReader in = new BufferedReader(new FileReader(namafile));	//reading files in specified directory

                         String line;
                         while ((line = in.readLine()) != null)	//file reading
                         {
                              String[] values = line.split(" ");
                                        klm = 0;
                         for (String str : values)
                         {
                              double str_double = Double.parseDouble(str);
                                             matrix[brs][klm]=str_double;
                                             klm = klm + 1;
                         }
                                        brs = brs + 1;
                         }
                         klm = klm -1;
                         if (klm>brs){
                              sisa = klm-brs;
                         }
                         for (i = 0; i < brs; i++){
                              array[i] = matrix[i][klm];
                         }
                         if (sisa != 0){
                              for (i = brs;i < brs + sisa  ;i++ ) {
                                   for (j = 0;j < klm ;j++ ) {
                                        matrix[i][j] = 0;
                                   }
                                   array[i] = 0;
                              }
                         }
                         brs += sisa;
                    in.close();
                                   //Print Matrix
                                   for (i = 0; i<brs; i++) {
                                         for (j = 0; j<klm + 1; j++) {
                                              if (j != klm){
                                                   System.out.printf("%.3f ",matrix[i][j]);
                                              }
                                              else if (j == klm){
                                                   System.out.printf("%.3f",array[i]);
                                              }
                                         }
                                         System.out.println();
                                    }
                  } catch( IOException ioException ) {}

                    double[] B = new double[brs];
                    double[][] A = new double[brs][klm];
                    double[] IA = new double[brs];
                    double[] IB = new double[brs];
                    if (Ops.stateops == "spl"){
                         for (i = 0;i < brs ;i++ ) {
                              for (j = 0; j < klm ;j++ ) {
                                   A[i][j] = matrix[i][j];
                              }
                              B[i] = array[i];
                         }
                    }
                    else if (Ops.stateops == "interpolasi"){
                         for (i = 0;i < brs ;i++ ) {
                              for (j = 0; j < klm ;j++ ) {
                                   IA[i] = matrix[i][j];
                              }
                              IB[i] = array[i];
                         }
                    }

                    if (Ops.stateops == "spl"){
                         Ops.Fungsi();
                         if (Ops.state == "gauss"){
                              EliminasiGauss eg = new EliminasiGauss();
                              eg.solusiEG(A,B);

                              System.out.println();
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                         else if (Ops.state == "gauss-jordan") {
                              System.out.println("lakukan gauss-jordan");
                              EliminasiGaussJordan egj = new EliminasiGaussJordan();
                              egj.solusiEGJ(A,B);

                              System.out.println();
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                         else if (Ops.state == "keluar"){
                              System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                         }
                    }
                    else if (Ops.stateops == "interpolasi"){
                         InterpolasiPolinom ip = new InterpolasiPolinom();
                         ip.solusiIP(IA,IB);

                         System.out.println();
                         System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
                    }
          }
          else{
               System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
          }
     } else
          System.out.println("Terima kasih telah menjalankan program Matriks ini!!");
 }
}
