public class T2{
    private Integer val;
    
    public T2(){
        val = null;
    }
    
    public Integer getVal(){
        return val;
    }
    
    public void setVal(int a){
        val = a;
    }
    
    public static void main(String[] args){
        T2 obj = new T2();
        System.out.println(obj.getVal() == null);
        obj.setVal(10);
        System.out.println(obj.getVal() == null);
        System.out.println(obj.getVal());
    }
}