
package crecheapp;


public class Child {
    private String name;
    private String gender;

    public Child(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Gender: " + gender;
    }
}
