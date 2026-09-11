class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }
        
        int totalUniqueEvenNumbers = 0;
        
        for (int i = 100; i <= 998; i += 2) {
            int hundredth = i / 100;
            int tenth = (i / 10) % 10;
            int unit = i % 10;
            
            count[hundredth]--;
            count[tenth]--;
            count[unit]--;
            
            if (count[hundredth] >= 0 && count[tenth] >= 0 && count[unit] >= 0) {
                totalUniqueEvenNumbers++;
            }
            
            count[hundredth]++;
            count[tenth]++;
            count[unit]++;
        }
        
        return totalUniqueEvenNumbers;
    }
}
