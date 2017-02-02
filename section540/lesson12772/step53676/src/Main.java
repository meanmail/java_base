class Main {
    // Don't edit this method
    public static void main(String... args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    // Don't edit this method
    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    //Stepik code: start
    public static String getCallerClassAndMethodName() {
        return null; // your implementation here
    }
//Stepik code: end
}
