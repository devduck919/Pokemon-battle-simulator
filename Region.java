import java.util.Random;
public class Region{
//Attributes
	private String name;
	private String climate;
	private int difficulty;
	private Trainer[] trainerInRegion = new Trainer[10];
	private Pokemon[] wildPokemon = new Pokemon[20];
//Constructors
	public Region(String nameIn,int difficultyIn,String climateIn){
		this.name = nameIn;
		this.difficulty = difficultyIn;
		this.climate = climateIn;
	}
	public Region(String nameIn,int difficultyIn,String climateIn,Trainer[] trainerIn){
		this.name = nameIn;
		this.difficulty = difficultyIn;
		this.climate = climateIn;
		this.trainerInRegion = trainerIn;
	}	
//Getters
	public String getName(){
		return this.name;
	}
	public String getClimate(){
		return this.climate;
	}
	public int getDifficulty(){
		return this.difficulty;
	}
	public Trainer[] getTrainerInRegion(){
		return this.trainerInRegion;
	}
	public Pokemon[] getWildPokemon(){
		return this.wildPokemon;
	}
//Setters
	public void setName(String nameIn){
		this.name = nameIn;
	}
	public void setClimate(String climateIn){
		this.climate = climateIn;
	}
	public void setDifficulty(int difficultyIn){
		this.difficulty = difficultyIn;
	}
	public void setTrainerInRegion(Trainer[] trainerIn){
		this.trainerInRegion = trainerIn;
	}
	public void setWildPokemon(Pokemon[] wildPokemonIn){
		this.wildPokemon = wildPokemonIn;
	}
//SPECIAL METHODS
	public void addTrainer(Trainer trainerAdd){
		for(int i=0;i<trainerInRegion.length;i++){
			if(trainerInRegion[i]==null){
				trainerInRegion[i] = trainerAdd;
				break;
			}else if(i==trainerInRegion.length){
				System.out.println("Trainers in this region are full!");
				break;
			}
		}
	}
	public void removeTrainer(String trainerRem){
		for(int i=0;i<trainerInRegion.length;i++){
			if(trainerInRegion[i]!=null){
				if(trainerInRegion[i].getName().equalsIgnoreCase(trainerRem)){
					System.out.println("Trainer successfully removed from region.");
					trainerInRegion[i] = null;
					return;
				}else if(i==trainerInRegion.length){
					System.out.println("Trainer not found!");
					return;
				}
			}
		}
		System.out.println("Trainer Not Found!");
		return;
	}
	public Pokemon generateWildPokemon(){
		Random rand = new Random();
		int randNum = rand.nextInt(20);
		Pokemon randPokemon = wildPokemon[randNum];
		System.out.println("A wild "+randPokemon.getName()+" has appeared in the region!");
		return randPokemon;
	}
	public void describeRegion(){
		System.out.println("Region Name: "+this.name+"\n"+"Climate: "+this.climate+"\n"+"Difficulty: "
			+this.difficulty+"\n"+"Trainers in Region: ");
		if(trainerInRegion!=null){
			for(int i=0;i<trainerInRegion.length;i++){
				if(trainerInRegion[i]!=null){
					System.out.print(trainerInRegion[i].getName()+" ");
				}
			}
		}
		return;
	}
	public void getDetails(){
		System.out.println("Pokemon in region:");
		if(wildPokemon!=null){
			for(int i=0;i<wildPokemon.length;i++){
				if(wildPokemon[i]!=null){
					System.out.print("Name: "+wildPokemon[i].getName()+" Type: "+wildPokemon[i].getType()
						+" Level: "+wildPokemon[i].getLevel()+" Health: "
						+wildPokemon[i].getHealth()+" Damage: "+wildPokemon[i].getAttackDamage()+"\n");
				}
			}
		}
	}
	public void simulateInteraction(String trainerA,String trainerB){
		Trainer home = null;
		Trainer away = null;
		Pokemon homePoke = null;
		Pokemon awayPoke = null;
		double homePokeHealth = 0;
		double awayPokeHealth = 0;
		boolean isGameWon = false;
		boolean homePokeIsEffective = false;
		boolean awayPokeIsEffective = false;
		for(int i=0;i<trainerInRegion.length;i++){
			if(trainerInRegion[i]!=null){
				if(trainerInRegion[i].getName().equals(trainerA)){
					home = trainerInRegion[i];
				}else if(i==trainerInRegion.length){
					System.out.println("Trainer not found!");
					return;
				}
			}
		}
		for(int i=0;i<trainerInRegion.length;i++){
			if(trainerInRegion[i]!=null){
				if(trainerInRegion[i].getName().equals(trainerB)){
					away = trainerInRegion[i];
					if(home.getPartner()==null||away.getPartner()==null){
						System.out.println("Hello!");
						return;
						}else if(i==trainerInRegion.length){
							System.out.println("Trainer not found!");
							return;
						}	
					}
				}
			}
		homePoke = home.getPartner();
		awayPoke = away.getPartner();
		if(homePoke.getType().equals("Fire")&&awayPoke.getType().equals("Water")||
		   homePoke.getType().equals("Water")&&awayPoke.getType().equals("Grass")||
		   homePoke.getType().equals("Grass")&&awayPoke.getType().equals("Fire")){
			awayPokeIsEffective = true;
			homePokeIsEffective = false;
			homePoke.setAttackDamage(homePoke.getAttackDamage()/2.0);
			awayPoke.setAttackDamage(awayPoke.getAttackDamage()*2);
		}else if(awayPoke.getType().equals("Water")&&homePoke.getType().equals("Fire")||
				 awayPoke.getType().equals("Grass")&&homePoke.getType().equals("Water")||
				 awayPoke.getType().equals("Fire")&&homePoke.getType().equals("Grass")){
			awayPokeIsEffective = false;
			homePokeIsEffective = true;
			awayPoke.setAttackDamage(homePoke.getAttackDamage()/2.0);
			homePoke.setAttackDamage(awayPoke.getAttackDamage()*2);
		}
		homePokeHealth = homePoke.getHealth();
		awayPokeHealth = awayPoke.getHealth();
		while(isGameWon!=true){
			System.out.println(home.getName()+" attacks with "+homePoke.getName()+" ("+homePoke.getLevel()+")");
			awayPokeHealth = awayPokeHealth-homePoke.getAttackDamage();
			if(homePokeIsEffective==true){
				System.out.println("It's super effective!");
			}else if(homePokeIsEffective == false){
				System.out.println("It's not very effective!");
			}
			 if(awayPokeHealth<=0){
				System.out.println(awayPoke.getName()+" health reaches below 0 pts");
				System.out.println(homePoke.getName()+" wins!");
				isGameWon = true;
			}
			System.out.println(awayPoke.getName()+" health reaches "+awayPokeHealth+" pts");
			System.out.println(away.getName()+" attacks with "+awayPoke.getName()+" ("+awayPoke.getLevel()+")");
			homePokeHealth = homePokeHealth-awayPoke.getAttackDamage();
			if(awayPokeIsEffective==true){
				System.out.println("It's super effective!");
			}else if(awayPokeIsEffective == false){
				System.out.println("It's not very effective!");
			}   
			if(homePokeHealth<=0){
				System.out.println(homePoke.getName()+" health reaches below 0 pts");
				System.out.println(awayPoke.getName()+" wins!");
				isGameWon = true;
			}
		}
	}
	public void addWildPokemon(Pokemon[] arr){
		Random rand = new Random();
		int randNum = rand.nextInt(20);
		for(int i=0;i<wildPokemon.length;i++){
			if(wildPokemon[i]==null){
				wildPokemon[i] = arr[randNum];
				System.out.println(wildPokemon[i].getName()+" has been added to the region.");
				return;
			}
		}
	}
	public void removeWildPokemon(String p){
		for(int i=0;i<wildPokemon.length;i++){
			if(wildPokemon[i]!=null){
				if(wildPokemon[i].getName().equalsIgnoreCase(p)){
					System.out.println(wildPokemon[i].getName()+" has fled the region.");
					wildPokemon[i] = null;
					return;
					}
				}
			}
			System.out.println("No wild pokemon to remove!");
			return;	
		}
	}