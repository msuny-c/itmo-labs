package ru.itmo.app.Commands;
import ru.itmo.app.Managers.ScannerManager;
import ru.itmo.app.Managers.ClientManager;
import ru.itmo.app.Network.CommandRequest;

/**
 * Class for command 'reorder'
 * reverses current order of elements in the collection.
 */
public class ReorderCommand extends ServerCommand {
    public ReorderCommand(String name, ClientManager clientManager) {
        super(name, clientManager);
    }

    @Override
    public void execute(String[] args, ScannerManager scannerManager) {
        CommandRequest request = new CommandRequest(getName(), null, null);
        send(request);
    }

    @Override
    public int numOfArgs() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "reverses current order of elements";
    }
}
