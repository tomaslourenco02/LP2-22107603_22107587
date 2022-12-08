package pt.ulusofona.lp2.deisiJungle;
public record MovementResult(MovementResultCode code, String message) {
    public MovementResult (MovementResultCode code){
        this(code, "");
    }

}
