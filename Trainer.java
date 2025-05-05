import java.util.Scanner;
public class Trainer{
//Attributes
	private String name;
	private Pokemon[] pokemonTeam = new Pokemon[5];
	private String[] badges;
	private boolean pokemonChampion;
	private Pokemon partner;
//Default Constructor
//Constructors
	public Trainer(String nameIn){
		this.name = nameIn;
	}
	public Trainer(String nameIn,Pokemon[]pokemonTeamIn,String[] badgesIn,boolean pokemonChampionIn){
		this.name = nameIn;
		this.pokemonTeam = pokemonTeamIn;
		this.badges = badgesIn;
		this.pokemonChampion = pokemonChampionIn;
	}
//Getters
	public String getName(){
		return this.name;
	}
	public Pokemon[] getPokemonTeam(){
		return this.pokemonTeam;
	}
	public String[] getBadges(){
		return this.badges;
	}
	public boolean getPokemonChampion(){
		return this.pokemonChampion;
	}
	public Pokemon getPartner(){
		return this.partner;
	}
//Setters
	public void setName(String nameIn){
		this.name = nameIn;
	}
	public void setPokemonTeam(Pokemon[] pokemonTeamIn){
		this.pokemonTeam = pokemonTeamIn;
	}
	public void setBadges(String[] badgesIn){
		this.badges = badgesIn;
	}
	public void setPokemonChampion(boolean pokemonChampionIn){
		this.pokemonChampion = pokemonChampionIn;
	}
	public void setPartner(String partnerIn){
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i].getName().equals(partnerIn)){
				this.partner = pokemonTeam[i];
				return;
			}
		}
		System.out.println("This Pokemon is not in the current team!");	
	}
//SPECIAL METHODS
	public void addPokemon(Pokemon pokeAdd){
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i]==null){
				pokemonTeam[i] = pokeAdd;
				return;
			}
		}
		System.out.println("Team is currently full!");
	}
	public void removePokemon(String pokeRem){
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i]!=null){
				if(pokemonTeam[i].getName().equals(pokeRem)){
					System.out.println("Pokemon successfully removed from team.");
					pokemonTeam[i] = null;
					return;
				}
			}
		}		
			System.out.println("Pokemon not found!");	
	}
	public void trainPokemon(){
		System.out.println("Your Pokemon have leveled up! Stats increased!");
		for(int i=0;i<pokemonTeam.length;i++){
			pokemonTeam[i].setLevel(pokemonTeam[i].getLevel()+1);
			pokemonTeam[i].setHealth(pokemonTeam[i].getHealth()+14);
			pokemonTeam[i].setAttackDamage(pokemonTeam[i].getAttackDamage()+1);
		}
	}
	public void getDetails(){
		System.out.println("Name: "+this.name+"\n"+"Team: ");
		if(pokemonTeam!=null){
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i]!= null){
				System.out.print(pokemonTeam[i].getName()+" ");
			}	
		}
	}	
		System.out.println("Badges: ");
		if(badges!=null){
		for(int i =0;i<badges.length;i++){
			if(badges[i]!=null){
			System.out.print(badges[i]+" ");
			}	
		}
	}
		System.out.println("Pokemon champion? "+pokemonChampion);
		if(partner!=null){
			System.out.println("Current Partner: "+partner.getName());
		}
	}
	public void choosePartner(){
		Scanner userIn = new Scanner(System.in);
		String partnerChoice; 
		System.out.print("Choose your partner: ");
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i]!=null){
				System.out.println(pokemonTeam[i].getName());
			}
		}
		partnerChoice = userIn.nextLine();
		for(int i=0;i<pokemonTeam.length;i++){
			if(pokemonTeam[i]!=null){
				if(pokemonTeam[i].getName().equalsIgnoreCase(partnerChoice)){
					System.out.println("Partner set!");
					this.partner = pokemonTeam[i];
					return;
					}
				}
			}
		System.out.println("Pokemon not found!");
		return;
	}
}