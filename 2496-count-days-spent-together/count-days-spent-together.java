class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = dateToDay(arriveAlice);
        int endA = dateToDay(leaveAlice);
        int startB = dateToDay(arriveBob);
        int endB = dateToDay(leaveBob);

        int overlapStart = Math.max(startA, startB);
        int overlapEnd = Math.min(endA, endB);

        if (overlapStart <= overlapEnd) {
            return overlapEnd - overlapStart + 1;
        }
        return 0;
    }

    private int dateToDay(String date) {
        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysPerMonth[i];
        }
        totalDays += day;
        return totalDays;
    }
}
