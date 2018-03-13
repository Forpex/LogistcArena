package gamecore;


public class Settings {
	
	public static Boolean isDebugOutputEnabled = false;
	
	//AVATAR
	public static final int MAX_HEALTH = 200;
	public static final int MAX_ARMOR = 200;
	public static final int START_HEALTH = 125;
	public static final int START_ARMOR = 0;
	public static final int ARMOR_BLEED_ABOVE = 100;
	public static final int HEALTH_BLEED_ABOVE = 100;
	
	public static final int FRAG_RESPAWN_TIME = 10;

	//ITEMS
	public static int TOTAL_NUMBER_OF_ITEM_TYPES = 6;						//Item.values().length;

	public static final int WEAPON_RESPAWN_TIMER = 25;
	
	//WEAPONS
	public static final int MAX_AMMO = 10;
	
	public static final int PISTOL_DPS = 10;
	public static final int PISTOL_RANGE = 6;
	public static final int RAILGUN_DPS = 20;
	public static final int RAILGUN_RANGE = 8;
	public static final int LIGHTNING_DPS = 40;
	public static final int LIGHTNING_RANGE = 4;
	public static final int SHOTGUN_DPS = 80;
	public static final int SHOTGUN_RANGE = 2;

	public static final int AMMO_AMOUNT = 10;

	
	//Intel and Sight
	public static final int INTEL_DISTANCE = 10;

	//Graph Generation
	public static final int EDGE_SIMPLE_STANDART_LENGTH = 8;
	public static final int MINIMAL_SPAWN_DISTANCE = 4;

	


	
	
	double timescale = 1;
	public Boolean IS_DISPLAYED_TIME_INCREMENTING = true;
	public final Time gamelength = new Time(60*5);

	
}
