package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {
    private String message;

    public InvalidInitialJungleException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isInvalidPlayer(){

        return true;
    }

    public boolean isInvalidFood(){

        return true;
    }

}

