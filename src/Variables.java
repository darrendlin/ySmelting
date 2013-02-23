import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;


public class Variables
{
	public static boolean initialized = false;
	public static boolean started = false;
	
	public static String bgLink = "http://i818.photobucket.com/albums/zz107/mrjohnson90/paintbg_zpsbd351e64.png";
	public static String cursorLink = "http://i818.photobucket.com/albums/zz107/mrjohnson90/cursor_zps534b6b0c.png";
	
	public static Timer timer = new Timer(0);
	public static int oreSelectIndex = -1;
	public static int locationSelectIndex = -1;
	
	public static String barToMake = "";
	public static String location = "";
	
	public static long bars = 0;
	public static long profit = 0;
	public static double expGained = 0;
	
	public static int barProfit = 0;
	public static double barExp = 0;
	
	public static int tinOreId = 438;
	public static int copperOreId = 436;
	public static int ironOreId = 440;
	public static int silverOreId = 442;
	public static int coalId = 453;
	public static int goldOreId = 444;
	public static int mithrilOreId = 447;
	public static int adamantiteOreId = 449;
	public static int runiteOreId = 451;
	
	public static String locations[] = {
		"Al Kharid",
		"Edgeville"
	};
	public static Tile[] pathTiles[] = {
		new Tile[]{new Tile(3270, 3167, 0), new Tile(3275, 3166, 0), new Tile(3275, 3175, 0), new Tile(3277, 3184, 0), new Tile(3280, 3190, 0), new Tile(3274, 3190, 0)},
		new Tile[]{new Tile(3096, 3497, 0), new Tile(3102, 3499, 0), new Tile(3108, 3500, 0)}};
	public static int furnaceIds[] = {
		76293,
		26814
	};
	public static double expList[] = {6.2, 12.5, 13.7, 17.5, 22.5, 30, 37.5, 50};
	public static int barIdList[] = {2349, 2351, 2355, 2353, 2357, 2359, 2361, 2363};
	
	public static Tile[] bankToFurnace = {};//{new Tile(3270, 3167, 0), new Tile(3275, 3166, 0), new Tile(3275, 3175, 0), new Tile(3277, 3184, 0), new Tile(3280, 3190, 0), new Tile(3274, 3190, 0)};
	
	//public static int furnaceId = 76293;
	//public static int boothId = 76274;
}
