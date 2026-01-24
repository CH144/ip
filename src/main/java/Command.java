abstract class Command {
    private boolean isBye;

    protected Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }

    public abstract void run(TaskList taskList, Ui ui) throws JohnDoeException;
}
