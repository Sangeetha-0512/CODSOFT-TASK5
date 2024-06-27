import java.util.*;
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getEnrolledStudents() {
        return enrolledStudents;
    }
    public String getSchedule() {
        return schedule;
    }
    public boolean addStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        } else {
            return false;
        }
    }
    public boolean removeStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        } else {
            return false;
        }
    }
    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }
}
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public String getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    public void registerCourse(Course course) {
        if (course.addStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Registration failed. Course is full.");
        }
    }
    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent();
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}
public class CourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();
        while (true) {
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void initializeCourses() {
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30, "Mon/Wed/Fri 9:00-10:00 AM"));
        courses.put("CS102", new Course("CS102", "Data Structures", "Learn about different data structures.", 25, "Tue/Thu 10:00-11:30 AM"));
        courses.put("CS103", new Course("CS103", "Algorithms", "Learn about algorithms and problem-solving techniques.", 20, "Mon/Wed/Fri 11:00-12:00 PM"));
        courses.put("CS104", new Course("CS104", "Operating Systems", "Learn about operating system concepts.", 20, "Tue/Thu 1:00-2:30 PM"));
        courses.put("CS105", new Course("CS105", "Database Systems", "Learn about database design and management.", 25, "Mon/Wed 2:00-3:30 PM"));
    }
    private static void initializeStudents() {
        students.put("S1001", new Student("S1001", "Alice"));
        students.put("S1002", new Student("S1002", "Bob"));
        students.put("S1003", new Student("S1003", "Charlie"));
        students.put("S1004", new Student("S1004", "Diana"));
        students.put("S1005", new Student("S1005", "Eve"));
    }
    private static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle() + " - " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity() + ", Enrolled: " + course.getEnrolledStudents() + ", Available Slots: " + course.getAvailableSlots());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println();
        }
    }
    private static void registerCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.registerCourse(course);
    }
    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.dropCourse(course);
    }
    private static void viewRegisteredCourses() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Registered Courses for " + student.getName() + ":");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle() + " - " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println();
        }
    }
}
