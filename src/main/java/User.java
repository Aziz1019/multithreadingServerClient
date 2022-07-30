public class User {
    private int id;
    private String username;
    private String first_name;
    private int age;

    public User(int id, String username, String first_name, int age) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                "Username: '" + username +
                ", First_name: '" + first_name +
                ", Age: " + age;
    }
}
