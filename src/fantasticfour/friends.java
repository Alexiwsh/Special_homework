package fantasticfour;
import java.util.ArrayList;
// does not work
import java.util.List;
public class friends {
	private final static int n_size = 3;
	@SuppressWarnings("unused")
	public static int transit(int arr_size, int initial_position)
	{
		List<Integer> intersections = new ArrayList<Integer>();
		int transites = 0;
		intersections.add(initial_position);
		byte field[][];
		if(n_size == 5)
			field = new byte[][] {
			{0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0},
			{0, 1, 0, 0, 1},
			{0, 0, 0, 0, 1},
			{0, 0, 1, 1, 0}};
		else
			field = new byte[][] {
			{0, 1, 0},
			{1, 0, 1},
			{0, 1, 0}};
		for(int y = 0; y < n_size; ++y){
			for(int x = 0; x < n_size; ++x){
					if(y == initial_position) {
						if(!intersections.contains(x))
							intersections.add(x);
						if(!intersections.contains(y))
							intersections.add(y);
						transites++;
						field[x][y] = 0;
						continue;
					}
				}
			}
		for(int y = 0; y < n_size; ++y)
			for(int x = 0; x < n_size; ++x)
				if(field[x][y] == 1 && (intersections.contains(x) || intersections.contains(y))){
					transites++;
					field[x][y] = 0;
				}
		for(int i : intersections)
			System.out.println("inter " + i);
		return --transites;
	}
}
/*Example2:
N = 5 
S = 2
Result: 3*/