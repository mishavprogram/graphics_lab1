package part3;

class Logger {

    private static final boolean LOGGING = false;

    static void log(String str){
        if (LOGGING)
        System.out.println(str);
    }
}
