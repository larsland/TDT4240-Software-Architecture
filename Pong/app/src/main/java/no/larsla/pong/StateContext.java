package no.larsla.pong;

public class StateContext {

    private ComputerState state;

    public StateContext() {
        state = new EasyState();
    }

    public void setState(ComputerState state) {
        this.state = state;
    }

    public void change() {
        state.changeDifficulty(this);
    }

    public ComputerState getState() {
        return this.state;
    }
}
