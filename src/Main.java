import java.util.Scanner;

/**
 * 学生管理系统主类
 * 提供命令行界面与用户交互
 */
public class Main {
    private static Scanner scanner;
    private static StudentManager studentManager;

    // 程序入口
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("    欢迎使用学生信息管理系统");
        System.out.println("====================================");

        initialize(); // 初始化系统

        showMainMenu(); // 显示主菜单

        cleanup(); // 清理资源
    }

    // 初始化系统
    private static void initialize() {
        scanner = new Scanner(System.in);
        studentManager = new StudentManager();
        System.out.println("✅ 系统初始化完成...");
    }

    // 显示主菜单
    private static void showMainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== 主菜单 ==========");
            System.out.println("1. 学生信息管理");
            System.out.println("2. 成绩统计分析");
            System.out.println("3. 查看系统信息");
            System.out.println("0. 退出系统");
            System.out.println("============================");
            System.out.print("请选择操作 (0-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    studentManagementMenu();
                    break;
                case "2":
                    scoreAnalysisMenu();
                    break;
                case "3":
                    showSystemInfo();
                    break;
                case "0":
                    System.out.println("\n确定要退出系统吗？(Y/N): ");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        running = false;
                        System.out.println("正在退出系统...");
                    }
                    break;
                default:
                    System.out.println("❌ 无效选择，请输入 0-3 之间的数字");
            }
        }
    }

    // 学生信息管理子菜单
    private static void studentManagementMenu() {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n===== 学生信息管理 =====");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 按ID查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 修改学生信息");
            System.out.println("5. 删除学生信息");
            System.out.println("9. 返回主菜单");
            System.out.println("0. 退出系统");
            System.out.println("========================");
            System.out.print("请选择操作 (0-5或9): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    studentManager.addStudent();
                    break;
                case "2":
                    studentManager.findStudentById();
                    break;
                case "3":
                    studentManager.showAllStudents();
                    break;
                case "4":
                    modifyStudentInfo();
                    break;
                case "5":
                    deleteStudentInfo();
                    break;
                case "9":
                    inSubMenu = false;
                    System.out.println("返回主菜单...");
                    break;
                case "0":
                    System.out.println("感谢使用，再见！");
                    System.exit(0);
                default:
                    System.out.println("❌ 无效选择，请重新输入");
            }
        }
    }

    // 成绩统计分析子菜单
    private static void scoreAnalysisMenu() {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n===== 成绩统计分析 =====");
            System.out.println("1. 计算科目平均分");
            System.out.println("2. 统计成绩分布");
            System.out.println("3. 查询最高分/最低分");
            System.out.println("4. 学生排名");
            System.out.println("9. 返回主菜单");
            System.out.println("0. 退出系统");
            System.out.println("========================");
            System.out.print("请选择操作 (0-4或9): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    studentManager.calculateAverageScores();
                    break;
                case "2":
                    analyzeScoreDistribution();
                    break;
                case "3":
                    findHighestLowestScores();
                    break;
                case "4":
                    showStudentRanking();
                    break;
                case "9":
                    inSubMenu = false;
                    System.out.println("返回主菜单...");
                    break;
                case "0":
                    System.out.println("感谢使用，再见！");
                    System.exit(0);
                default:
                    System.out.println("❌ 无效选择，请重新输入");
            }
        }
    }

    // 修改学生信息（扩展功能）
    private static void modifyStudentInfo() {
        System.out.println("\n=== 修改学生信息 ===");
        System.out.println("此功能待实现...");
        System.out.println("提示：可以调用studentManager的方法实现");
        // 这里可以扩展具体的修改逻辑
    }

    // 删除学生信息（扩展功能）
    private static void deleteStudentInfo() {
        System.out.println("\n=== 删除学生信息 ===");
        System.out.println("此功能待实现...");
        System.out.println("提示：可以使用DELETE FROM students WHERE id=?");
        // 这里可以扩展具体的删除逻辑
    }

    // 分析成绩分布（扩展功能）
    private static void analyzeScoreDistribution() {
        System.out.println("\n=== 成绩分布分析 ===");
        System.out.println("此功能待实现...");
        System.out.println("提示：可以按分数段(0-59,60-79,80-89,90-100)统计人数");
    }

    // 查询最高分/最低分（扩展功能）
    private static void findHighestLowestScores() {
        System.out.println("\n=== 最高分/最低分查询 ===");
        System.out.println("此功能待实现...");
        System.out.println("提示：使用SELECT MAX(math_score), MIN(math_score) FROM students");
    }

    // 显示学生排名（扩展功能）
    private static void showStudentRanking() {
        System.out.println("\n=== 学生排名 ===");
        System.out.println("此功能待实现...");
        System.out.println("提示：按平均分排序，使用ORDER BY (math_score+java_score)/2 DESC");
    }

    // 显示系统信息
    private static void showSystemInfo() {
        System.out.println("\n=== 系统信息 ===");
        System.out.println("系统名称: 学生信息管理系统");
        System.out.println("版本: v1.0");
        System.out.println("作者: 实训项目");
        System.out.println("开发环境: Java + MySQL");
        System.out.println("功能模块:");
        System.out.println("  - 学生信息管理（增删改查）");
        System.out.println("  - 成绩统计分析");
        System.out.println("  - 数据持久化存储");
        System.out.println("使用方法:");
        System.out.println("  1. 在命令行输入选项数字");
        System.out.println("  2. 按照提示输入数据");
        System.out.println("  3. 查看操作结果");
        System.out.println("=========================");

        System.out.print("\n按回车键返回主菜单...");
        scanner.nextLine();
    }

    // 清理资源
    private static void cleanup() {
        if (scanner != null) {
            scanner.close();
            System.out.println("✅ 扫描器资源已释放");
        }
        if (studentManager != null) {
            studentManager.close();
            System.out.println("✅ 数据库连接已关闭");
        }
        System.out.println("\n====================================");
        System.out.println("    学生信息管理系统已安全退出");
        System.out.println("====================================");
    }

    // 辅助方法：获取用户输入的整数
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ 请输入有效的整数");
            }
        }
    }

    // 辅助方法：获取用户输入的浮点数
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ 请输入有效的数字");
            }
        }
    }
}