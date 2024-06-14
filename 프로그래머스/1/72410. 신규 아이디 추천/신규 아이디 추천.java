class Solution {
    public String solution(String new_id) {
        String str = new_id.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '.' || c == '_')
                sb.append(c);
        }
        str = sb.toString();

        sb = new StringBuilder();
        boolean dot = false;
        for (char c : str.toCharArray()) {
            if (c == '.') {
                if (!dot) {
                    sb.append(c);
                    dot = true;
                }
            } else {
                sb.append(c);
                dot = false;
            }
        }

        str = sb.toString();

        if (!str.isEmpty() && str.charAt(0) == '.') str = str.substring(1);
        if (!str.isEmpty() && str.charAt(str.length() - 1) == '.') str = str.substring(0, str.length() - 1);

        if (str.isEmpty())
            str = "a";

        if (str.length() >= 16) {
            str = str.substring(0, 15);
            if (str.charAt(str.length() - 1) == '.') str = str.substring(0, str.length() - 1);
        }

        while (str.length() < 3)
            str += str.charAt(str.length() - 1);

        return str;
    }
}