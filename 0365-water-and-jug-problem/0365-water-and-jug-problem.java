class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        if(y >x){
            y=y+x;
            x=y-x;
            y=y-x;
        }
        while(target <= (x+y) && x != 0 && y != 0 ){
            if(x == y){
                if(target % x == 0)
                    return true;
                return false;
            }
            if(target % x == 0 || target%y == 0 || target%(x-y) == 0 || target%(x+y) == 0 || target%x == x%y){
                return true;
            }
            x=x%y;
            y=y+x;
            x=y-x;
            y=y-x;
        }
        return false;
    }
}