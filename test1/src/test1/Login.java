package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Login {
	
	private static String DBDriver="com.mysql.cj.jdbc.Driver";
	private static final String DBUrl = "jdbc:mysql://localhost:3306/java?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
	
	public static void main(String[] args) throws Exception {
		Class.forName(DBDriver);//驱动
		Connection conn=DriverManager.getConnection(DBUrl,"root","123456");//连接
		PreparedStatement ps=null;//sql运行环境
		ResultSet rs=null;//结果集
		Scanner scanner =new Scanner(System.in);
		int count=0;
		boolean isSuccess=false;
		while(count<3&&!isSuccess)
		{
			String id;
			String password;
			System.out.print("请输入账户：");
			id=scanner.nextLine();
			System.out.print("请输入密码：");
			password=scanner.nextLine();
			ps=conn.prepareStatement("SELECT * from t_yonghu where yonghuming=? and mima=?");
			ps.setString(1, id);
			ps.setString(2, password);
			rs= ps.executeQuery();
			if(rs.next())
			{
				isSuccess=true;
				String xingming=rs.getString("xingming");
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
				String currentTime=format.format(new Date());
                System.out.println("欢迎使用阳光超市收银系统");
                System.out.println("当前收银员：" + xingming);
                System.out.println("当前时间：" + currentTime);
			}
			else {
				count++;
				System.out.println("用户名或密码不正确，请重新输入");
			}
		}
		
		if(count>=3)
		{
			System.out.println("最多只能尝试3次");
		}
	}
}
