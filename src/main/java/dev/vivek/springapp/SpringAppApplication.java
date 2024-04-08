package dev.vivek.springapp;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/*@SpringBootApplication
public class SpringAppApplication implements CommandLineRunner {*/
@SpringBootApplication
public class SpringAppApplication {
	/*private CommandRegistry commandRegistry;
	public SpringAppApplication(CommandRegistry commandRegistry){
		this.commandRegistry = commandRegistry;
	}*/
	public static void main(String[] args) {

		SpringApplication.run(SpringAppApplication.class, args);
	}
	// create-user <name> <uname> <pwd>
	// get-user <user-id>
	// settle-group <group-name>

	/*@Override
	public void run(String... args) throws Exception{
		Scanner scn = new Scanner(System.in);
		while(true) {
			System.out.println("Enter command:");
			String input = scn.nextLine();
			if(input.equals("quit")){
				break;
			} else {
				commandRegistry.process(input);
			}
		}
	}*/
}
