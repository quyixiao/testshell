package tsh.exception;

public class ExeError extends Exception {
    private String message;

    public ExeError() {

    }

    public ExeError(String message) {
        this.message = message;
    }


    public void printStackTrace() {
        System.out.println(message);
        super.printStackTrace();
    }


}
