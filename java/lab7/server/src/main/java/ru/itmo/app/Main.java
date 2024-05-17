package ru.itmo.app;

import ru.itmo.app.Commands.*;
import ru.itmo.app.Database.AuthManager;
import ru.itmo.app.Database.DatabaseManager;
import ru.itmo.app.Database.HumanBeingProcessor;
import ru.itmo.app.Interfaces.IServerManager;
import ru.itmo.app.Managers.*;
import ru.itmo.app.Models.HumanBeing;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        DatabaseManager<HumanBeing> databaseManager = new DatabaseManager<>(new HumanBeingProcessor(),"s408194","GsVXRpw7kHqtNIp6");
        AuthManager authManager = new AuthManager();
        authManager.setConnection(databaseManager.getConnection());
        CollectionManager collectionManager = new CollectionManager(databaseManager);
        CommandManager commandManager = new CommandManager();

        commandManager.set(new AddCommand("add"));
        commandManager.set(new ReorderCommand("reorder"));
        commandManager.set(new AvgOfSpeedCommand("average_of_impact_speed"));
        commandManager.set(new UpdateCommand("update"));
        commandManager.set(new ShowCommand("show"));
        commandManager.set(new ClearCommand("clear"));
        commandManager.set(new InfoCommand("info"));
        commandManager.set(new RemoveGreaterCommand("remove_greater"));
        commandManager.set(new ReorderCommand("reorder"));
        commandManager.set(new MaxBySpeed("max_by_impact_speed"));
        commandManager.set(new RemoveByIdCommand("remove_by_id"));
        commandManager.set(new FilterNameCommand("filter_starts_with_name"));
        commandManager.set(new AddIfMinCommand("add_if_min"));

        CommandHandler commandHandler = new CommandHandler(collectionManager, commandManager, authManager);
        IServerManager serverManager = new ServerManager(9142, commandHandler);
        serverManager.run();

    }
}