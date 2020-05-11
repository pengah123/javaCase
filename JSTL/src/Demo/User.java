package Demo;

import java.util.Date;

public class User {
    private String name;
    private int age;
    private Date Birthday;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    @Override
    public String toString() {
        return "Demo.User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Birthday=" + Birthday +
                '}';
    }

    public User(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        Birthday = birthday;
    }

    public User() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
