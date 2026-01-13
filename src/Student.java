/**
 * 学生类
 * 用于表示学生信息
 */
public class Student {
    // 1. 定义私有属性（对应数据库表中的字段）
    private int id;           // 学生ID
    private String name;      // 姓名
    private String gender;    // 性别
    private String className; // 班级名称
    private double mathScore; // 高数成绩
    private double javaScore; // Java成绩

    // 2. 无参构造方法
    public Student() {
    }

    // 3. 带参构造方法
    public Student(int id, String name, String gender, String className, double mathScore, double javaScore) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    // 4. Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    // 5. 计算平均分的方法
    public double getAverageScore() {
        return (mathScore + javaScore) / 2.0;
    }

    // 6. toString 方法，用于打印学生信息
    @Override
    public String toString() {
        return "学生信息：" +
                "\nID: " + id +
                "\n姓名: " + name +
                "\n性别: " + gender +
                "\n班级: " + className +
                "\n高数成绩: " + mathScore +
                "\nJava成绩: " + javaScore +
                "\n平均成绩: " + String.format("%.2f", getAverageScore());
    }

    // 7. 测试主方法
    public static void main(String[] args) {
        // 测试无参构造 + setter
        Student student1 = new Student();
        student1.setId(1001);
        student1.setName("张三");
        student1.setGender("男");
        student1.setClassName("计算机科学与技术1班");
        student1.setMathScore(88.5);
        student1.setJavaScore(92.0);
        System.out.println("=== 测试学生1（无参构造）===");
        System.out.println(student1);

        System.out.println("\n=== 测试学生2（带参构造）===");
        // 测试带参构造
        Student student2 = new Student(1002, "李四", "女", "软件工程2班", 95.0, 89.5);
        System.out.println(student2);

        // 测试getter方法
        System.out.println("\n=== 测试getter方法 ===");
        System.out.println("学生1姓名：" + student1.getName());
        System.out.println("学生2平均分：" + student2.getAverageScore());
    }
}