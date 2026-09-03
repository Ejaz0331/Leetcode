class Solution {
    public int countPrimes(int n) {
        int fc=0;
        boolean []arr=new boolean [n+1];
        Arrays.fill(arr,true);

        for(int i=2; i<Math.sqrt(n);i++){
            if(arr[i]){
                for(int j=i*i;j<n;j+= i){
                    arr[j]=false;
                }
            }
        }
        int count=0;
        for(int i=2;i<n;i++){
            if(arr[i]){
                count++;
            }
        }
        return count;
    }
}