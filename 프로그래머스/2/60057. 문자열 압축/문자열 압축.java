class Solution {
    public int solution(String s) {
        int len = s.length();
        int minLen = len;

        for (int i = 1; i <= len / 2; i++) {
            String sub = s.substring(0, i);
            String zip = "";
            int count = 1;

            for (int j = i; j < len; j += i) {
                String subString;
                
                if (i + j > len)
                    subString = s.substring(j);
                else
                    subString = s.substring(j, i + j);

                if (sub.equals(subString)) count++;
                else {
                    if (count > 1) zip += count + sub;
                    else zip += sub;

                    sub = subString;
                    count = 1;
                }
            }

            if (count > 1) zip += count + sub;
            else zip += sub;

            minLen = Math.min(minLen, zip.length());
        }
        return minLen;
    }
}