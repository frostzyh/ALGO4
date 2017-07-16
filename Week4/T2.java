public class T2{
    public static void main(String[] args){
        int[] a  = {1,2,3,4,5,6};
        
        int[] b = a.clone();
        
        a[0] = 88;
        
        System.out.println(a[0]);
        System.out.println(b[0]);
    }
}