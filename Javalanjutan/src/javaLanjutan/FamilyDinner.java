package javaLanjutan;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FamilyDinner {
	ArrayList<Normal> nolist = new ArrayList<>();
	ArrayList<Special> slist= new ArrayList<Special>();
	Scanner scan= new Scanner(System.in);
	boolean flag=false;
	public static void main(String aargs[]) {
		new FamilyDinner();
	}
	public FamilyDinner(){
		int choice=0;
		do {
		System.out.println("Family Restaurant");
		System.out.println("===============================");
		System.out.println("1. Add Regular Menu");
		System.out.println("2. Add Special Menu");
		System.out.println("3. Show All menu");
		System.out.println("4. Delete Regular Menu");
		System.out.println("5. Delete Special Menu");
		System.out.println("6. Exit");
		System.out.print("Choice [1-6]:");
		choice=scan.nextInt();
		scan.nextLine();
		switch (choice) {
		case 1:
			addnormal();
			break;
		case 2:
			addspecial();
			break;
		case 3:
			view();
			break;
		case 4:
			denormal();
			break;
		case 5:
			despecial();
			break;
		default:
			System.out.println("Please input between 1-5 and 6 for exit");
			break;
		}
		}while(choice!=6);
		System.out.println("Exiting program");
	}
	public void denormal() {
		String kode;
		int idx=-1;
		System.out.println("Delete Regular Menu");
		System.out.println("=========================");
		System.out.print("input menu code[R...]\t:");
		kode=scan.nextLine();
		if(Pattern.matches("R\\d{3}", kode)) {
			flag=true;
		}else {
			System.out.println("Code is wrong");
			return;
		}
		if(flag) {
			for(int i=0;i<nolist.size();i++) {
				if(nolist.get(i).getKode().equals(kode)) {
					idx=i;
				}
			}
		}
		if(idx==-1) {
			System.out.println("Code is wrong");
			return;
		}else {
			nolist.remove(idx);
			System.out.println("Delete Success");
			return;
		}
	}
	public void despecial() {
		String kode;
		int idx=-1;
		System.out.println("Delete Special Menu");
		System.out.println("=========================");
		System.out.print("input menu code[S...]\t:");
		kode=scan.nextLine();
		if(Pattern.matches("S\\d{3}", kode)) {
			flag=true;
		}else {
			System.out.println("Code is wrong");
			return;
		}
		if(flag) {
			for(int i=0;i<slist.size();i++) {
				if(slist.get(i).getKode().equals(kode)) {
					idx=i;
				}
			}
		}
		if(idx==-1) {
			System.out.println("Code is wrong");
			return;
		}else {
			slist.remove(idx);
			System.out.println("Delete Success");
			return;
		}
	}
	public void view() {
		if(nolist.size()==0 && slist.size()==0) {
			System.out.println("kosong bos-");
		}else {
			System.out.println("Regular Menu");
			System.out.println("=================================");
			System.out.println("No.| Kode |\tNama\t| harga |");
			System.out.println("=================================");
			for(int i=0;i<nolist.size();i++) {
				System.out.println(i+1 +"   "+ nolist.get(i).getKode()+ " \t"+ nolist.get(i).getNama() +"\t  "+ nolist.get(i).getHarga());
			}
			System.out.println();
			System.out.println("Special Menu");
			System.out.println("=========================================");
			System.out.println("No.| Kode |\tNama\t| harga | Diskon |");
			System.out.println("=========================================");
			for(int i=0;i<slist.size();i++) {
				System.out.println(i+1 +"    "+ slist.get(i).getKode()+ "\t"+ slist.get(i).getNama()+ "\t  "+ slist.get(i).getHarga()+"  "+slist.get(i).getDiskon()+"%");
			}
		}
		
	}
	public void addnormal() {
		String kode;
		String nama;
		int price;
		System.out.println("Add Regular Menu");
		System.out.println("===============================");
		do {
			boolean kodeexist=false;
			System.out.print("Input Menu Code [R...]\t\t:");
			
			kode=scan.nextLine();
			if(Pattern.matches("R\\d{3}", kode)) {
				for(int i=0;i<nolist.size();i++) {
					System.out.println(nolist.get(i).getKode());
					if(nolist.get(i).getKode().equals(kode)) {
						kodeexist=true;
					}
				}
				if(kodeexist!=true) {
					flag=true;
				}else if(kodeexist==true){
					System.out.println("Use different code!");
				}
			
			}else {
				System.out.println("Menu Kode Must be R followed by 3 number");
			}
			
			
			
		}while(flag==false);
		do {
			flag=false;
			System.out.print("Input menu name[5-25] \t\t:");
			nama=scan.nextLine();
			if(nama.length()>=5 && nama.length()<=25) {
				flag=true;
			}
		}while(flag==false);
		flag=false;
		do {
			System.out.print("Input menu price[10000-100000]\t:");
			price=scan.nextInt();
			scan.nextLine();
			if(price>=10000 && price<=1000000) {
				flag=true;
			}
		}while(flag==false);
		int temp=nolist.size();
		nolist.add(new Normal(kode,nama,price));
		if(nolist.size()!=temp) {
			System.out.println("Add Success");
		}else {
			System.out.println("Add failed");
		}
		
	}
	public void addspecial() {
		String kode;
		String nama;
		int price;
		int diskon;
		System.out.println("Add Regular Menu");
		System.out.println("===============================");
		
		do {
			boolean kodeexist=false;
			System.out.print("Input Menu Code [S...]\t\t:");
			
			kode=scan.nextLine();
			if(Pattern.matches("S\\d{3}", kode)) {
				for(int i=0;i<slist.size();i++) {
					System.out.println(slist.get(i).getKode());
					if(slist.get(i).getKode().equals(kode)) {
						kodeexist=true;
					}
				}
				if(kodeexist!=true) {
					flag=true;
				}else if(kodeexist==true){
					System.out.println("Kode dah ada pake yang lain dongs!");
				}
			
			}else {
				System.out.println("Menu Kode Must be S followed by 3 number");
			}
			
			
			
		}while(flag==false);
		do {
			flag=false;
			System.out.print("Input menu name[5-20] \t\t:");
			nama=scan.nextLine();
			if(nama.length()>=5 && nama.length()<=20) {
				flag=true;
			}
		}while(flag==false);
		flag=false;
		do {
			System.out.print("Input menu price[10000-100000]\t:");
			price=scan.nextInt();
			scan.nextLine();
			if(price>=10000 && price<=1000000) {
				flag=true;
			}
		}while(flag==false);
		flag=false;
		do {
			System.out.print("Input menu discount[10/25/50]\t:");
			diskon=scan.nextInt();
			scan.nextLine();
			if(diskon==10|| diskon==25|| diskon==50) {
				flag=true;
			}
		}while(flag==false);
		int temp=slist.size();
		slist.add(new Special(kode,nama,price,diskon));
		if(slist.size()!=temp) {
			System.out.println("Add Success");
		}else {
			System.out.println("Add failed");
		}
	}
	
}
