

import java.io.*;
import java.util.*;

class Student {
    private static int index = 1;
    private int id;
    private String ten;
    private String gioitinh;
    private int tuoi;
    private double diemtoan;
    private double diemli;
    private double diemhoa;
    private double diemtb;
    private String hocluc;

    public Student(String ten, String gioitinh, int tuoi, double diemtoan, double diemli, double diemhoa) {
        this.id = index++;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
        this.diemtoan = diemtoan;
        this.diemli = diemli;
        this.diemhoa = diemhoa;
        diemtrungbinh();
        xephocluc();
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getDiemtoan() {
        return diemtoan;
    }

    public void setDiemtoan(double diemtoan) {
        this.diemtoan = diemtoan;
    }

    public double getDiemli() {
        return diemli;
    }

    public void setDiemli(double diemli) {
        this.diemli = diemli;
    }

    public double getDiemhoa() {
        return diemhoa;
    }

    public void setDiemhoa(double diemhoa) {
        this.diemhoa = diemhoa;
    }

    private void diemtrungbinh() {
        this.diemtb = (diemtoan + diemli + diemhoa) / 3;
    }

    public double getDiemtb() {
        return diemtb;
    }

    private void xephocluc() {
        if (diemtb >= 8) this.hocluc = "Giỏi";
        else if (diemtb >= 6.5) this.hocluc = "Khá";
        else if (diemtb >= 5) this.hocluc = "Trung Bình";
        else this.hocluc = "Yếu";
    }

    @Override
    public String toString() {
        String loadString = "-------------------------------------\n"
                + "Mã sinh viên: " + id + "\n"
                + "Tên sinh viên: " + ten + "\n"
                + "Tuổi: " + tuoi + "\n"
                + "Điểm toán: " + diemtoan + "\n"
                + "Điểm lí: " + diemli + "\n"
                + "Điểm hóa: " + diemhoa + "\n"
                + "GPA: " + diemtb + "\n"
                + "Xếp học lực: " + hocluc + "\n";
        return loadString;
    }

    public String saveString() {
        return id + "," + ten + "," + gioitinh + "," + tuoi + "," + diemtoan + "," + diemli + "," + diemhoa + "," + diemtb + "," + hocluc;
    }

    public static Student fromString(String data) {
        String[] parts = data.split(",");
        Student student = new Student(parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
        student.id = Integer.parseInt(parts[0]);
        index = Math.max(index, student.id + 1);
        return student;
    }
}

public class Quanlisinhvien {
    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "student.txt";

    private static void showMenu() {
        System.out.println("---------------------------------");
        System.out.println("1. Thêm sinh viên.");
        System.out.println("2. Cập nhật thông tin sinh viên bởi ID.");
        System.out.println("3. Xóa sinh viên bởi ID.");
        System.out.println("4. Tìm kiếm sinh viên theo tên.");
        System.out.println("5. Sắp xếp sinh viên theo điểm trung bình (GPA).");
        System.out.println("6. Sắp xếp sinh viên theo tên.");
        System.out.println("7. Hiển thị danh sách sinh viên.");
        System.out.println("8. Ghi danh sách sinh viên vào file \"student.txt\".");
        System.out.println("9. Thoát.");
        System.out.print("Nhập lựa chọn: ");
    }

    private static void themSinhVien(Scanner scanner) {
        System.out.print("Tên: ");
        String ten = scanner.nextLine();
        System.out.print("Giới tính: ");
        String tuoi = scanner.nextLine();
        System.out.print("Tuổi: ");
        int age = scanner.nextInt();
        System.out.print("Điểm Toán: ");
        double diemtoan = scanner.nextDouble();
        System.out.print("Điểm Lý: ");
        double diemli = scanner.nextDouble();
        System.out.print("Điểm Hóa: ");
        double diemhoa = scanner.nextDouble();
        Student student = new Student(ten, tuoi, age, diemtoan, diemli, diemhoa);
        students.add(student);
    }

    private static Student timKiemSinhVienTheoId(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static void capNhatSinhVien(Scanner scanner) {
        System.out.print("Nhập ID sinh viên cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = timKiemSinhVienTheoId(id);
        if (student != null) {
            System.out.print("Tên mới: ");
            student.setTen(scanner.nextLine());
            System.out.print("Giới tính mới: ");
            student.setGioitinh(scanner.nextLine());
            System.out.print("Tuổi mới: ");
            student.setTuoi(scanner.nextInt());
            System.out.print("Điểm Toán mới: ");
            student.setDiemtoan(scanner.nextDouble());
            System.out.print("Điểm Lý mới: ");
            student.setDiemli(scanner.nextDouble());
            System.out.print("Điểm Hóa mới: ");
            student.setDiemhoa(scanner.nextDouble());
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    private static void xoaSinhVien(Scanner scanner) {
        System.out.print("Nhập ID sinh viên cần xóa: ");
        int id = scanner.nextInt();
        Student student = timKiemSinhVienTheoId(id);
        if (student != null) {
            students.remove(student);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    private static void timKiemSinhVienTheoTen(Scanner scanner) {
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String name = scanner.nextLine();
        for (Student student : students) {
            if (student.getTen().equalsIgnoreCase(name)) {
                System.out.println(student);
            }
        }
    }

    private static void sapXepTheoGPA() {
        students.sort(Comparator.comparingDouble(Student::getDiemtb).reversed());
    }

    private static void sapXepTheoTen() {
        students.sort(Comparator.comparing(Student::getTen));
    }

    private static void docSinhVienTuFileTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
            System.out.println("Đọc thành công " + students.size() + " sinh viên trong student.txt");
        } catch (IOException e) {
            System.out.println("Không thể đọc file: " + e.getMessage());
        }
    }

    private static void inSinhVien() {
        if(students.size() <1) {
            System.out.println("Chưa có sinh viên nào để hiển thị!");
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void luuSinhVienVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                bw.write(student.saveString());
                bw.newLine();
            }
            System.out.println("Ghi thành công!");
        } catch (IOException e) {
            System.out.println("Không thể ghi file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        docSinhVienTuFileTxt();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    themSinhVien(scanner);
                    break;
                case 2:
                    capNhatSinhVien(scanner);
                    break;
                case 3:
                    xoaSinhVien(scanner);
                    break;
                case 4:
                    timKiemSinhVienTheoTen(scanner);
                    break;
                case 5:
                    sapXepTheoGPA();
                    inSinhVien();
                    break;
                case 6:
                    sapXepTheoTen();
                    inSinhVien();
                    break;
                case 7:
                    inSinhVien();
                    break;
                case 8:
                    luuSinhVienVaoFile();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}

