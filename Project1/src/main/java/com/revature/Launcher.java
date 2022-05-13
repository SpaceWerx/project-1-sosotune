package com.revature;

import java.util.Scanner;
import com.revature.Service.*;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		CLI_Menu_Service options = new CLI_Menu_Service();
		options.displayLoginMenu();
		options.displayMenu();
		
	}

}
