package dev.vivek.springapp;

import dev.vivek.springapp.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringAppApplication implements CommandLineRunner {
	private CommandRegistry commandRegistry;
	public static void main(String[] args) {

		SpringApplication.run(SpringAppApplication.class, args);
	}
	// create-user <name> <uname> <pwd>
	// get-user <user-id>
	// settle-group <group-name>

	@Override
	public void run(String... args) throws Exception{
		Scanner scn = new Scanner(System.in);
		while(true) {
			String input = scn.nextLine();
			if(input.equals("quit")){
				break;
			} else {
				commandRegistry.process(input);
			}
		}
	}
}
