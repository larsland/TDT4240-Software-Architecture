package no.larsla.pong;

public class HardState implements ComputerState {

    @Override
    public void changeDifficulty(StateContext context) {
        context.setState(new EasyState());
        System.out.println("EASY DIFFICULTY");
    }
}
