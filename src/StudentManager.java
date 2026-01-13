import java.sql.*;
import java.util.Scanner;

/**
 * 学生管理类
 * 实现对学生信息的增删改查操作
 */
public class StudentManager {
    private Connection connection;
    private Scanner scanner;

    // 构造方法
    public StudentManager() {
        scanner = new Scanner(System.in);
        connection = DatabaseConnection.getConnection();
    }

    // 释放资源
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
        DatabaseConnection.closeConnection(connection);
    }

    // 1. 添加学生信息到数据库
    public void addStudent() {
        System.out.println("\n=== 添加学生信息 ===");

        try {
            System.out.print("请输入学生姓名: ");
            String name = scanner.nextLine();

            System.out.print("请输入学生性别(男/女): ");
            String gender = scanner.nextLine();

            System.out.print("请输入班级: ");
            String className = scanner.nextLine();

            System.out.print("请输入高数成绩: ");
            double mathScore = scanner.nextDouble();

            System.out.print("请输入Java成绩: ");
            double javaScore = scanner.nextDouble();
            scanner.nextLine(); // 消耗换行符

            // 创建Student对象
            Student student = new Student(0, name, gender, className, mathScore, javaScore);

            // SQL插入语句
            String sql = "INSERT INTO students(name, gender, class, math_score, java_score) VALUES(?, ?, ?, ?, ?)";

            // 使用PreparedStatement防止SQL注入
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                // 获取自动生成的ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("✅ 学生信息添加成功！学生ID: " + generatedId);
                }
                generatedKeys.close();
            } else {
                System.out.println("❌ 学生信息添加失败！");
            }

            pstmt.close();

        } catch (SQLException e) {
            System.err.println("数据库操作失败: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("输入错误: " + e.getMessage());
            scanner.nextLine(); // 清除错误输入
        }
    }

    // 2. 根据ID查询学生信息
    public void findStudentById() {
        System.out.println("\n=== 按ID查询学生 ===");

        try {
            System.out.print("请输入学生ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 创建Student对象
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("class"),
                        rs.getDouble("math_score"),
                        rs.getDouble("java_score")
                );

                System.out.println("\n✅ 找到学生信息:");
                System.out.println(student); // 使用Student类的toString方法
            } else {
                System.out.println("❌ 未找到ID为 " + id + " 的学生");
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            System.err.println("数据库查询失败: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("输入错误: " + e.getMessage());
            scanner.nextLine(); // 清除错误输入
        }
    }

    // 3. 显示所有学生信息
    public void showAllStudents() {
        System.out.println("\n=== 所有学生信息 ===");

        try {
            String sql = "SELECT * FROM students ORDER BY id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            boolean hasData = false;
            int count = 0;

            while (rs.next()) {
                hasData = true;
                count++;

                // 创建Student对象
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("class"),
                        rs.getDouble("math_score"),
                        rs.getDouble("java_score")
                );

                System.out.println("\n--- 学生 " + count + " ---");
                System.out.println(student);
            }

            if (!hasData) {
                System.out.println("暂无学生信息");
            } else {
                System.out.println("\n总计: " + count + " 名学生");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("数据库查询失败: " + e.getMessage());
        }
    }

    // 4. 计算学生各科目的平均分数
    public void calculateAverageScores() {
        System.out.println("\n=== 各科目平均分 ===");

        try {
            String sql = "SELECT " +
                    "AVG(math_score) as avg_math, " +
                    "AVG(java_score) as avg_java, " +
                    "AVG((math_score + java_score) / 2) as avg_total, " +
                    "COUNT(*) as student_count " +
                    "FROM students";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double avgMath = rs.getDouble("avg_math");
                double avgJava = rs.getDouble("avg_java");
                double avgTotal = rs.getDouble("avg_total");
                int studentCount = rs.getInt("student_count");

                if (studentCount > 0) {
                    System.out.printf("高数平均分: %.2f\n", avgMath);
                    System.out.printf("Java平均分: %.2f\n", avgJava);
                    System.out.printf("总平均分: %.2f\n", avgTotal);
                    System.out.println("学生总数: " + studentCount);

                    // 比较哪个科目平均分更高
                    if (avgMath > avgJava) {
                        System.out.println("📈 高数平均分高于Java平均分");
                    } else if (avgMath < avgJava) {
                        System.out.println("📈 Java平均分高于高数平均分");
                    } else {
                        System.out.println("⚖️ 两门科目平均分相同");
                    }
                } else {
                    System.out.println("暂无学生成绩数据");
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("数据库计算失败: " + e.getMessage());
        }
    }

    // 5. 显示菜单
    public void showMenu() {
        while (true) {
            System.out.println("\n======= 学生信息管理系统 =======");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 按ID查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 计算科目平均分");
            System.out.println("5. 清空控制台");
            System.out.println("0. 退出系统");
            System.out.println("================================");
            System.out.print("请选择操作(0-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    findStudentById();
                    break;
                case "3":
                    showAllStudents();
                    break;
                case "4":
                    calculateAverageScores();
                    break;
                case "5":
                    clearConsole();
                    break;
                case "0":
                    System.out.println("感谢使用，再见！");
                    close();
                    return;
                default:
                    System.out.println("❌ 无效选择，请重新输入");
            }
        }
    }

    // 清空控制台
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // 如果清屏失败，至少输出一些空行
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    // 6. 主方法 - 程序入口
    public static void main(String[] args) {
        System.out.println("正在初始化学生管理系统...");

        // 创建管理器实例
        StudentManager manager = new StudentManager();

        if (manager.connection == null) {
            System.err.println("❌ 数据库连接失败，程序退出");
            return;
        }

        System.out.println("✅ 学生管理系统启动成功！");

        // 显示菜单
        manager.showMenu();
    }
}