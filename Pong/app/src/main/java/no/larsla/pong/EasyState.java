package no.larsla.pong;

public class EasyState implements ComputerState {

    @Override
    public void changeDifficulty(StateContext context) {
        context.setState(new HardState());
        System.out.println("HARD DIFFICULTY");
    }
}
