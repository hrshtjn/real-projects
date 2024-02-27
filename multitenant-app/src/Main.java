public class Main {

    // Configuration property indicating which method to execute
    private static boolean useChildMethod=true;

    public static void main(String[] args) {

        MyBaseClass instance;

        if(useChildMethod){
            instance=new MyChildClass();
        } else {
            instance=new MyBaseClass();
        }

        instance.myMethod();
    }
}