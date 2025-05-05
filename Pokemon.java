public class Pokemon{
//Attributes
	private String name;
	private String type;
	private int level = 1;
	private int health;
	private double attackDamage;
//Constructors
	public Pokemon(String nameIn,String typeIn){
		this.name = nameIn;
		this.type = typeIn;
	}
	public Pokemon(String nameIn,String typeIn,int levelIn,int healthIn,double attackDamageIn){
		this.name = nameIn;
		this.type = typeIn;
		this.level = levelIn;
		this.health = healthIn;
		this.attackDamage = attackDamageIn;
	}
//Getters
	public String getName(){
		return this.name;
	}
	public String getType(){
		return this.type;
	}
	public int getLevel(){
		return this.level;
	}
	public int getHealth(){
		return this.health;
	}
	public double getAttackDamage(){
		return this.attackDamage;
	}
//Setters
	public void setName(String nameIn){
		this.name = nameIn;
	}
	public void setType(String typeIn){
		this.type = typeIn;
	}
	public void setLevel(int levelIn){
		this.level = levelIn;
	}
	public void setHealth(int healthIn){
		this.health = healthIn;
	}
	public void setAttackDamage(double attackDamageIn){
		this.attackDamage = attackDamageIn;
	}
}
