package com.revature;

import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		while(!input.equals("quit")) {
			System.out.println("enter next");
			input = scan.nextLine();
		}
		scan.close();
	}

}
