package fantasticfour;
import java.util.Random;
public class Candies {
	public static int scale_variations() {
		Random random = new Random();
		return scale_variations(random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100) + 100);
	}
	public static int scale_variations(int candy_weight, int tangerine_weight, int apple_weight, final int overall_weight) {
		int variations = 0;	
		System.out.println("Candy weight = " + candy_weight
				+ "; Tangerine_weight = " + tangerine_weight
				+ "; apple_weight = " + apple_weight 
				+ "; overall_weight = " + overall_weight);
		
		int num[] = new int[]{candy_weight, tangerine_weight, apple_weight};
		for(int i = 0; i < num.length; ++i)
			variations += recursive_scale(overall_weight, 0, num, i);
		return variations;
	}
	public static int recursive_scale(int overall_weight, int iteration_sum, int num[], int index) {
		int variations = 0;
		while(overall_weight - iteration_sum >= num[index]) {
			iteration_sum += num[index];
			if(iteration_sum == overall_weight)
				variations++;
			for(int n = index + 1; n < num.length; ++n)
				variations += recursive_scale(overall_weight, iteration_sum, num, n);
		}
		return variations;
	}
}