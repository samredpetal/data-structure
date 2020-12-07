package final_work.srs;

/**
 * Студент группы имеет данные как:
 * ФИО, пол, год рождения и средний доход на одного человека в семье.
 */
public class Student {
    private String fio;
    private char gender;
    private String date;
    private double averageIncome;
    private boolean needHostel;

    public Student(String fio, char gender, String date, double averageIncome, boolean needHostel) {
        this.fio = fio;
        this.gender = gender;
        this.date = date;
        this.averageIncome = averageIncome;
        this.needHostel = needHostel;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAverageIncome() {
        return averageIncome;
    }

    public void setAverageIncome(double averageIncome) {
        this.averageIncome = averageIncome;
    }

    public boolean isNeedHostel() {
        return needHostel;
    }

    public void setNeedHostel(boolean needHostel) {
        this.needHostel = needHostel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fio='" + fio + '\'' +
                ", gender=" + gender +
                ", date='" + date + '\'' +
                ", averageIncome=" + averageIncome +
                ", needHostel=" + needHostel +
                '}';
    }
}
