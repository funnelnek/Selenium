package unit;

public class Human implements Comparable<Human> {
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected double weight;
    protected double height;

    public Human(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String fullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    @Override
    public int compareTo(Human human) {
        int length1 = getSortableName().length();
        int length2 = human.getSortableName().length();
        int limit = Math.min(length1, length2);
        char[] v1 = getSortableName().toCharArray();
        char[] v2 = human.getSortableName().toCharArray();

        int i = 0;
        while (i < limit) {
            char ch1 = v1[i];
            char ch2 = v2[i];
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
            i++;
        }
        return length1 - length2;
    }

    public String getSortableName() {
        return lastName + " " + firstName + " " + middleName;
    }
}
