package com.revature;

import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin = new Scanner(System.in);
		String input = stdin.nextLine();
		
		while(!input.equals("quit")) {
			System.out.println("enter next");
			input = stdin.nextLine();
		}
		stdin.close();
	}

}
