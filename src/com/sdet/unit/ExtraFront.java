package unit;

public class ExtraFront {
    public String extraFront(String str) {
        if (str.isEmpty()) {
            return str;
        }

        if (str.length() == 1) {
            return repeat(3, str);
        }

        String temp = str.substring(0,2);
        return repeat(3, temp);
    }

    protected String repeat(int times, String str) {
        String value = "";

        for (int i = 0; i < times; i++) {
            value += str;
        }

        return value;
    }
}
