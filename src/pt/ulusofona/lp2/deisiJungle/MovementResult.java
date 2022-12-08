package pt.ulusofona.lp2.deisiJungle;



public class MovementResult {

    MovementResultCode code;
    String message;

    public record MovementResults(MovementResultCode code, String message) {}

    public MovementResult(MovementResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setMessage(String message) {
        if(MovementResultCode.CAUGHT_FOOD != null) {
            this.message = message;
        }
    }
}
