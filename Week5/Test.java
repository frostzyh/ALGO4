public class Test{
    private Integer val;
    public Test(int k){
        val = k;
    }
    
    public Integer getVal(){
        return val;
    }
    
    public static void main(String[] args){
        Test obj = new Test(15);
        Integer myVal = obj.getVal();
        System.out.println(myVal);
        myVal = myVal + 1;
        System.out.println(myVal);
        System.out.println(obj.getVal());
        
        Boolean myBol = true;
        Boolean antBol = !myBol;
        System.out.println(antBol);
    }
}