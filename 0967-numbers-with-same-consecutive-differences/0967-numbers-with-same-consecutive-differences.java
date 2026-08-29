class Solution {
    private List<Integer> ans;
    private int n;
    private int k;
    private void f(int num,int cnt){
        if(cnt == n)
        {
            ans.add(num);
            return;
        }
        for(int i=0;i<=9;i++){
            if(Math.abs((num%10)-i) == k)
                f(num*10+i,cnt+1);
        }
    }
    public int[] numsSameConsecDiff(int n, int k) {
        this.n=n;
        this.k=k;
        ans=new ArrayList<>();
        for(int i=1;i<=9;i++){
            f(i,1);
        }
        int a[]=new int[ans.size()];
        for(int i=0;i<ans.size();i++)
            a[i]=ans.get(i);
        return a;
    }
}