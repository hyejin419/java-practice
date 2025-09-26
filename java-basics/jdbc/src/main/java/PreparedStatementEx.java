import java.sql.*;

public class PreparedStatementEx {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 연결 성공!");

            // select
//            String sql = "select * from words where eng = ?";

            // insert
//            String sql = "insert into words (eng, kor, lev) values (?, ?, ?)";

            //update
//            String sql = "update words set kor = ?, lev = ? where eng=?";

            //delete
              String sql = "delete from words where eng = ?";
              pstmt = conn.prepareStatement(sql);

            // select
//            pstmt.setString(1, "apple");

            // insert
//            pstmt.setString(1, "banana");
//            pstmt.setString(2, "바나나");
//            pstmt.setInt(3, 1);

            //update
//            pstmt.setString(1, "애플");
//            pstmt.setInt(2, 1);
//            pstmt.setString(3, "apple");

            //delete
              pstmt.setString(1, "apple");

            // select
//            rs = pstmt.executeQuery();
            // insert, update, delete

            //delete

            int count = pstmt.executeUpdate();

            // select
//            while(rs.next()) {
//                String eng = rs.getString("eng");   // apple
//                String kor = rs.getString("kor");   // 김사과
//                int lev = rs.getInt("lev");         // 1
//                System.out.println(eng + "\t" + kor + "\t" + lev + "\t");
//            }
            // insert, update, delete
            System.out.println(count + "개 행이 처리되었습니다.");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
//                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

