import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random; 
public class CL3Duque{
//GLOBAL VARIABLES
	public static void main(String[] args){		
		Region defaultRegion = new Region("Kanto",1,"Warm"); //STARTER REGION
		Pokemon[] pokedex = new Pokemon[20]; //POKEDEX WHICH FILE WILL FILL 
		boolean validSession = true; //IF TRUE PROGRAM KEEPS RUNNING 
		int index = 0; //ACTS AS A REFERENCE ON WHERE TO PUT EACH POKEMON IN THE POKEDEX
		try{
			File pokeFile = new File("pokedex.txt");
			Scanner pokeScan = new Scanner(pokeFile);
			while(pokeScan.hasNextLine()){
				String name = pokeScan.next();
				String type = pokeScan.next();
				int health = pokeScan.nextInt();
				double attack = pokeScan.nextDouble();
				pokedex[index] = new Pokemon(name,type,1,health,attack);
				index ++;
			}
			pokeScan.close();
		}catch(FileNotFoundException error){
			System.out.println("File not found!");
		}
//MAIN MENU INTERFACE
		Random rand = new Random();
		Scanner lineScan = new Scanner(System.in); //FOR USER INPUTS EXPECTING STRING
		Scanner intScan = new Scanner(System.in); //FOR USER INPUT EXPECTING INTEGER
		int option = 0; //FOR OPTION SELECTION
		while(validSession==true){
			System.out.print("******************************"+"\n"+
				                "*Welcome to Poke-Miner*"+"\n"+
				             "******************************"+"\n"+
				             "Options"+"\n"+
							 "1. Modify Region"+"\n"+
							 "2. Add/Remove Trainer to Region"+"\n"+
							 "3. Add/Remove Wild Pokemon to Region"+"\n"+
							 "4. Modify Trainer"+"\n"+
							 "5. Add/remove Pokemon From Trainer"+"\n"+
							 "6. List Pokemon in Trainer"+"\n"+
							 "7. Simulate interaction between two trainers"+"\n"+
							 "8. Exit"+"\n"+"**************************"+"\n");
//CATCHES USER MISINPUT FOR OPTION
		while(option < 1 || option > 8){
			try{
				System.out.print("Option: ");
				option = intScan.nextInt();
			}catch(InputMismatchException error){
				System.out.println("Invalid! ");
				intScan.nextLine();
			}

		}
			switch(option){
			case 1: //Modify Region 
				defaultRegion.describeRegion();
				while(true){
					System.out.print("Would you like to modify region name, climate, difficulty or continue? ");
					String choice = lineScan.nextLine();
					if(choice.equalsIgnoreCase("Name")){
						System.out.print("New Region Name: ");
						String newName = lineScan.nextLine();
						defaultRegion.setName(newName);
						defaultRegion.describeRegion();
					}else if(choice.equalsIgnoreCase("Climate")){
						System.out.print("New Climate: ");
						String newClimate = lineScan.nextLine();
						defaultRegion.setClimate(newClimate);
						defaultRegion.describeRegion();
					}else if(choice.equalsIgnoreCase("Difficulty")){
						System.out.print("New Difficulty: ");
						int newDifficulty = intScan.nextInt();
						defaultRegion.setDifficulty(newDifficulty);
						defaultRegion.describeRegion();
					}else if(choice.equalsIgnoreCase("Continue")){
						break;
					}else{
						System.out.println("Invalid Input!");
					}
				} //WHILE LOOP FOR CASE 1 
				option = 0;
				break;
			case 2: //Add/Remove Trainer to Region
				while(true){
					System.out.print("Would you like to add or remove a trainer to/from the region? ");
					String addOrRem = lineScan.nextLine();
					if(addOrRem.equalsIgnoreCase("Add")){
						System.out.print("Trainer Name: ");
						String addName = lineScan.nextLine();
						Trainer newTrainer = new Trainer(addName);
						defaultRegion.addTrainer(newTrainer);
						System.out.println("Trainer Successfully Added!");
						break;
					}else if(addOrRem.equalsIgnoreCase("Remove")){
						System.out.print("Trainer Name: ");
						String removeName = lineScan.nextLine();
						defaultRegion.removeTrainer(removeName);
						break;
					}else{
						System.out.println("Invalid Input!");
					}
				} //WHILE LOOP FOR CASE 2
				option = 0;
				break;
			case 3: //Add/Remove Wild Pokemon to Region
				while(true){
					System.out.print("Would you like to add or remove a wild Pokemon to/from the region? ");
					String addOrRem = lineScan.nextLine();
					if(addOrRem.equalsIgnoreCase("Add")){
						defaultRegion.addWildPokemon(pokedex);
						break;
					}else if(addOrRem.equalsIgnoreCase("Remove")){
						System.out.print("Wild Pokemon: ");
						String removeName = lineScan.nextLine();
						defaultRegion.removeWildPokemon(removeName);
						break;
					}else{
						System.out.println("Invalid!");
					}
				} //WHILE LOOP FOR CASE 3 
				option = 0;
				break;
			case 4: //Modify Trainer
				while(true){
					System.out.print("Which Trainer? ");
					String trainer = lineScan.nextLine();
					for(int i=0;i<defaultRegion.getTrainerInRegion().length;i++){
						if(defaultRegion.getTrainerInRegion()[i]!=null){
							if(defaultRegion.getTrainerInRegion()[i].getName().equalsIgnoreCase(trainer)){
								defaultRegion.getTrainerInRegion()[i].getDetails();
								while(true){
									System.out.print("Would you like to modify name, champ status, partner or continue? ");
									String choice = lineScan.nextLine();
									if(choice.equalsIgnoreCase("Name")){
										System.out.print("New Name: ");
										String newName = lineScan.nextLine();
										defaultRegion.getTrainerInRegion()[i].setName(newName);
										defaultRegion.getTrainerInRegion()[i].getDetails();
									}else if(choice.equalsIgnoreCase("Champ Status")){
										while(true){
											System.out.print("New Champ Status (yes/no): ");
											String champStatus = lineScan.nextLine();
											if(champStatus.equalsIgnoreCase("Yes")){
												defaultRegion.getTrainerInRegion()[i].setPokemonChampion(true);
												defaultRegion.getTrainerInRegion()[i].getDetails();
												break;
											}else if(champStatus.equalsIgnoreCase("No")){
												defaultRegion.getTrainerInRegion()[i].setPokemonChampion(false);
												defaultRegion.getTrainerInRegion()[i].getDetails();
												break;
											}else{
												System.out.println("Invalid!");
											}
										} //INNERMOST WHILE LOOP FOR CHAMP STATUS
									}else if(choice.equalsIgnoreCase("Partner")){
										defaultRegion.getTrainerInRegion()[i].choosePartner();
									}else if(choice.equalsIgnoreCase("Continue")){
										break;
									}else{
										System.out.println("Invalid");
									}
								} //INNER WHILE LOOP FOR CASE 2 
							}
						} 
					}
					break;
				} //WHILE LOOP FOR CASE 4
				option = 0;
				break;
			case 5: //Add/remove Pokemon From Trainer
				int randNum = 0;
				int randPoke = 0;
				for(int i=0;i<defaultRegion.getWildPokemon().length;i++){
					if(defaultRegion.getWildPokemon()[i]!=null){
						randNum ++;
					}
				}
				if(randNum<0){
					System.out.println("No Wild Pokemon Detected!");
					return;
				}else{
					randPoke = rand.nextInt(randNum);
				}
				System.out.print("Which Trainer? ");
				String trainer = lineScan.nextLine();
				for(int i=0;i<defaultRegion.getTrainerInRegion().length;i++){
					if(defaultRegion.getTrainerInRegion()[i]!=null){
						if(defaultRegion.getTrainerInRegion()[i].getName().equalsIgnoreCase(trainer)){
							while(true){
								System.out.print("Would you like to add or remove Pokémon for Trainer? ");
								String addOrRem = lineScan.nextLine();
								if(addOrRem.equalsIgnoreCase("Add")){
									defaultRegion.getTrainerInRegion()[i].addPokemon(defaultRegion.getWildPokemon()[randPoke]);
									System.out.print("Name: "+defaultRegion.getWildPokemon()[randPoke].getName()
											+"\n"+"Type: "+defaultRegion.getWildPokemon()[randPoke].getType()
											+"\n"+"Level: "+defaultRegion.getWildPokemon()[randPoke].getLevel()
											+"\n"+"Health: "+defaultRegion.getWildPokemon()[randPoke].getHealth()
											+"\n"+"Attack Damage: "+defaultRegion.getWildPokemon()[randPoke].getAttackDamage()+"\n");
										System.out.println("There's a new team addition for "+defaultRegion.getTrainerInRegion()[i].getName());
										break;
								}else if(addOrRem.equalsIgnoreCase("Remove")){
									System.out.print("Which Pokemon? ");
									String remPoke = lineScan.nextLine();
									defaultRegion.getTrainerInRegion()[i].removePokemon(remPoke);
									break;
								}else{
									System.out.println("Invalid Input!");
								}
							} // WHILE LOOP FOR CASE 5
						}
					}
				}
				option = 0;
				break;
			case 6: //List Pokemon in Trainer
				System.out.print("Which Trainer? ");
					String trainer6 = lineScan.nextLine();
					for(int i=0;i<defaultRegion.getTrainerInRegion().length;i++){
						if(defaultRegion.getTrainerInRegion()[i]!=null){
							if(defaultRegion.getTrainerInRegion()[i].getName().equalsIgnoreCase(trainer6)){
								System.out.println(defaultRegion.getTrainerInRegion()[i].getName()+"'s Pokemon Team:");
								int count = 1;
								for(int j=0;j<defaultRegion.getTrainerInRegion()[i].getPokemonTeam().length;j++){
									if(defaultRegion.getTrainerInRegion()[i].getPokemonTeam()[j]!=null){
										System.out.println(count+": "+defaultRegion.getTrainerInRegion()[i].getPokemonTeam()[j].getName()
											+", Type: "+defaultRegion.getTrainerInRegion()[i].getPokemonTeam()[j].getType()
											+", Level: "+defaultRegion.getTrainerInRegion()[i].getPokemonTeam()[j].getLevel()
											+", Health: "+defaultRegion.getTrainerInRegion()[i].getPokemonTeam()[j].getHealth());
										count ++;
									}
								}
								System.out.println("*************************");
							}else if(i==defaultRegion.getTrainerInRegion().length&&defaultRegion.getTrainerInRegion()[i].getName()!=trainer6){
								System.out.println("Trainer Does Not Exist!");
							}
						}
					}
				option = 0;
				break;
			case 7: //Simulate interaction between two trainers
				System.out.print("Simulate Interaction Bewtween Two Trainers."+"\n"+"First Trainer: ");
				String trainer1 = lineScan.nextLine();
				System.out.print("Second Trainer: ");
				String trainer2 = lineScan.nextLine();
				defaultRegion.simulateInteraction(trainer1,trainer2);
				option = 0;
				break;
			case 8: //EXIT PROGRAM
				validSession = false;
				break;

			}//SWITCH OPTION BRACKET

		}//VALID SESSION WHILE LOOP BRACKET
lineScan.close();
intScan.close();

	}
//OUTSIDE MAIN 
	public static void printArr(Pokemon[] arr){
  	 	for(int i =0;i < arr.length; i++){
  			System.out.println(arr[i].getName()+" "+arr[i].getType()+" "
  				+arr[i].getLevel()+" "+arr[i].getHealth()+" "+arr[i].getAttackDamage());
  		}
  	}
}
