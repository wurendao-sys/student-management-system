import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Wrd003754@"; // 替换为你的真实数据库密码

    // 静态代码块，加载数据库驱动（只需一个）
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库驱动加载失败!");
        }
    }

    /**
     * 获取数据库连接对象
     * @return Connection 数据库连接对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("数据库连接失败！");
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("数据库连接已关闭。");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("关闭数据库连接时出错！");
            }
        }
    }

    // 测试方法
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            closeConnection(conn);
        }
    }
}