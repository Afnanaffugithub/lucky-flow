 package luckyFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Lucky {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		boolean flag = true;
		while (flag) {
			System.out.println("Enter '1' to Enter the Game");
			System.out.println("Enter '2' to Exit the Game");
			switch (sc.nextInt()) {
			case 1: {

				int generate_no[] = new int[5];
				for (int i = 0; i < generate_no.length; i++) {
					generate_no[i] = r.nextInt(9);
				}
				System.out.println("Enter the SL No.");
				int sl_no = sc.nextInt();
				System.out.println("Enter Your Name");
				String name = sc.next();
				int user_no[] = new int[5];
				int Credit_points = 0;
				for (int i = 0; i < user_no.length; i++) {
					System.out.println("Enter Number Between 0 - 9");
					user_no[i] = sc.nextInt();
					if (user_no == generate_no) {
						Credit_points += 10;
						System.out.println("WOW You geussed Correct");
					} else {
						System.out.println("opps You geussed Wrong");
					}
				}
				System.out.println(name + " you Got Points are :" + Credit_points + "/50");
				System.out.println("Lucky Numbers are :" + Arrays.toString(generate_no));
				System.out.println("User Numbers are :" + Arrays.toString(user_no));
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucky_flow", "root",
						"Afnan@88");
				PreparedStatement p = con.prepareStatement("insert into lucky values(?,?,?,?,?)");
				p.setInt(1, sl_no);
				p.setString(2, name);
				p.setString(3, Arrays.toString(generate_no));
				p.setString(4, Arrays.toString(user_no));
				p.setInt(5, Credit_points);
				p.execute();
				break;
			}

			case 2: {
				System.out.println("Thank you");
				flag = false;
			}
				break;
			default: {
				System.out.println("Enter Valid Option");
			}

			}
		}
	}
}
