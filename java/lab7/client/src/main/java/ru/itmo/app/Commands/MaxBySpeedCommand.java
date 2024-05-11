package ru.itmo.app.Commands;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.Action;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'max_by_impact_speed'
 * displays an object which impactSpeed value is maximum.
 */
public class MaxBySpeedCommand extends ServerCommand {
    public MaxBySpeedCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        send(Action.COMMAND, getName(), null, null);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "displays any object from the collection which impactSpeed field value is maximum";
    }
}
