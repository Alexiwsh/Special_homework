package fantasticfour;

import java.util.Random;

public class Xerox {
	private int speed;
	private int work_time;
	private boolean has_paper;
	private int cur_printed;
	Xerox(int work_speed){
		this.speed = work_speed;
		work_time = 0;
		has_paper = false;
		cur_printed = 0;
	}
	public boolean do_print() {
		if(!has_paper)
			return false;
		if(++work_time == speed)
		{
			work_time = 0;
			cur_printed++;
			return true;
		}
		else
			return false;
	}
	public void insert_paper() {
		has_paper = true;
	}
	public boolean is_faster(Xerox obj) {
		return this.speed < obj.speed;
	}
	public static int do_job(int papers, int speed1, int speed2) {
		Xerox xeroxes[] = new Xerox[2];
		xeroxes[0] = new Xerox(speed2);
		xeroxes[1] = new Xerox(speed1);
		if(xeroxes[0].is_faster(xeroxes[1]))
			xeroxes[0].insert_paper();
		else
			xeroxes[1].insert_paper();
		int printed = 0;
		int spent_time = 0;
		while(printed <= papers) {
			spent_time++;
			for(Xerox x : xeroxes) {
				if(!x.has_paper)
					if(printed > 0)
						x.insert_paper();
				if(printed <= papers && x.do_print())
					printed++;
			}
		}
		System.out.println("Two xeroxes has printed " + papers +" papers for a " + spent_time +
				" seconds with work speed: one paper per " + speed1 + " and " + speed2  + " seconds");
		System.out.println("Xerox one has printed " + xeroxes[0].cur_printed +
				" papers; Xerox two has printed " + xeroxes[1].cur_printed + " papers"); 
		return spent_time;
	}
	public static int do_job() {
	Random random = new Random();
	return do_job(random.nextInt(90) + 10, random.nextInt(10), random.nextInt(5));
	}
}
