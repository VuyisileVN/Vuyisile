
package securemessages;

public class MessageEncryptor {
    public static String encrypt(String plainText) {
        StringBuilder encrypted = new StringBuilder();
        int shift = 3;

        for (char c : plainText.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char ch = (char) (((c - 'A' + shift) % 26) + 'A');
                encrypted.append(ch);
            } else if (Character.isLowerCase(c)) {
                char ch = (char) (((c - 'a' + shift) % 26) + 'a');
                encrypted.append(ch);
            } else {
                encrypted.append(c); // Keep symbols, spaces, numbers unchanged
            }
        }

        return encrypted.toString();
    }
}
