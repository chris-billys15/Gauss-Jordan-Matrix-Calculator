import java.util.*;

// Class Declaration

public class Menu{

     // Atribut
     int choice;
     String state,stateops;

     // Method

     Menu(){
          // Konstruktor
     }

     public void MainMenu(){
          Scanner in = new Scanner (System.in);

          System.out.println();
          System.out.println("Matriks/");
          System.out.println("MENU");
          System.out.println("1. Mulai");
          System.out.println("2. Keluar");
          System.out.println();

          System.out.print(">> "); this.choice = in.nextInt();
          if (this.choice == 1){
               Mulai();
          }
          else if (this.choice == 2){
               Keluar();
          }
     }

     public void Mulai(){
          Scanner in = new Scanner (System.in);

          System.out.println();
          System.out.println("Matriks/Mulai");
          System.out.println("MENU");
          System.out.println("1. Sistem Persamaan Lanjar");
          System.out.println("2. Interpolasi");
          System.out.println("3. Keluar");
          System.out.println();

          System.out.print(">> "); this.choice = in.nextInt();
          if (this.choice == 1){
               SPL();
          }
          else if (this.choice == 2){
               Interpolasi();
          }
          else if (this.choice == 3){
               Keluar();
          }
     }

     public void Keyboard(){
          System.out.println();
          if (this.stateops == "spl")
               System.out.println("Matriks/Mulai/Sistem Persamaan Lanjar/Input Keyboard/");
          else if (this.stateops == "interpolasi")
               System.out.println("Matriks/Mulai/Interpolasi/Input Keyboard/");
          this.state = "keyboard";
     }

     public void File(){
          System.out.println();
          if (this.stateops == "spl")
               System.out.println("Matriks/Mulai/Sistem Persamaan Lanjar/Input File/");
          else if (this.stateops == "interpolasi")
               System.out.println("Matriks/Mulai/Interpolasi/Input File/");
          this.state = "file";
     }

     public void Operasi(){
          System.out.println();
          if (this.stateops == "spl")
               System.out.println("Matriks/Mulai/Sistem Persamaan Lanjar/");
          else if (this.stateops == "interpolasi")
               System.out.println("Matriks/Mulai/Interpolasi/");
          Scanner in = new Scanner (System.in);

          System.out.println("MENU");
          System.out.println("1. Input Keyboard");
          System.out.println("2. Input File");
          System.out.println("3. Keluar");
          System.out.println();

          System.out.print(">> "); this.choice = in.nextInt();
          if (this.choice == 1){
               Keyboard();
          }
          else if (this.choice == 2){
               File();
          }
          else if (this.choice == 3){
               Keluar();
          }
     }

     public void Fungsi(){
          System.out.println();
          System.out.println("MENU");
          System.out.println("1. Gauss");
          System.out.println("2. Gauss Jordan");
          System.out.println("3. Keluar");
          System.out.println();

          Scanner in = new Scanner (System.in);
          System.out.print(">> "); this.choice = in.nextInt();
          if (this.choice == 1){
               Gauss();
          }
          else if (this.choice == 2){
               GaussJordan();
          }
          else if (this.choice == 3){
               Keluar();
          }
     }

     public void Gauss(){
          System.out.println();
          this.state = "gauss";
     }

     public void GaussJordan(){
          System.out.println();
          this.state = "gauss-jordan";
     }

     public void Interpolasi(){
          System.out.println();
          this.stateops = "interpolasi";
     }
     public void SPL(){
          System.out.println();
          this.stateops = "spl";
     }

     public void Keluar(){
          System.out.println();
          this.state = "keluar";
     }

}
