import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 修正URL格式
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "Wrd003754@"; // 改成你的密码

    // 静态代码块
    static {
        try {
            // 尝试新的驱动类名
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ 数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            // 如果失败，尝试旧版本的驱动类名
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("✅ 数据库驱动加载成功（使用旧版本驱动）！");
            } catch (ClassNotFoundException ex) {
                System.err.println("❌ 数据库驱动加载失败！");
                System.err.println("请检查：");
                System.err.println("1. 是否已添加MySQL驱动JAR包到项目");
                System.err.println("2. 驱动版本是否正确");
                throw new RuntimeException("找不到数据库驱动类，请添加mysql-connector-java的JAR包到项目依赖中");
            }
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ 数据库连接成功！");
        } catch (SQLException e) {
            System.err.println("❌ 数据库连接失败！");
            System.err.println("错误：" + e.getMessage());
            System.err.println("请检查：");
            System.err.println("1. MySQL服务是否启动（在Windows服务中查看）");
            System.err.println("2. 数据库'student_management'是否存在");
            System.err.println("3. 用户名和密码是否正确");
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("连接已关闭");
            } catch (SQLException e) {
                System.err.println("关闭连接出错：" + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 测试开始 ===");
        Connection conn = getConnection();
        if (conn != null) {
            closeConnection(conn);
        }
        System.out.println("=== 测试结束 ===");
    }
}