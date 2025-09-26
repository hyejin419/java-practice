import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementEx {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 연결 성공!");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from words");

            while(rs.next()){
                String eng = rs.getString("eng");  //apple
                String kor = rs.getString("kor");  //사과
                int lev = rs.getInt("lev");        //1
                System.out.println(eng+"\t"+kor+"\t"+lev+"\t");
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                rs.close();
                        stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            }
        }

    }
}
