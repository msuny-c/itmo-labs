package ru.itmo.app.Managers;

import ru.itmo.app.Exceptions.*;
import ru.itmo.app.Network.*;
import ru.itmo.app.Network.Error;

import java.sql.SQLException;

public class CommandHandler {
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final AuthManager authManager;
    public CommandHandler(CollectionManager collectionManager, CommandManager commandManager, AuthManager authManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.authManager = authManager;
    }
    public Response handle(CommandRequest request) {
        try {
            if (request.action() == Action.REGISTER) {
                authManager.register(request.login(), request.pass());
                return new Response("Successfully registered account " + "\"" + request.login() + "\"" + ".", Status.OK, new Error(null));
            }
            if (request.action() == Action.LOGIN) {
                authManager.authUser(request.login(), request.pass());
                return new Response("Successful login to the account " + "\"" + request.login() + "\"" + ".", Status.OK, new Error(null));
            }
            if (request.action() == Action.COMMAND) {
                authManager.authUser(request.login(), request.pass());
                Session.setCurrentUser(request.login());
                String result = commandManager.execute(collectionManager, request.command(), request.object(), request.args());
                Session.removeCurrentUser();
                return new Response(result, Status.OK, null);
            }
            return new Response(null, Status.FAIL, new Error(new ServerException("Unknown action.")));
        } catch (UserException exception) {
            return new Response(exception.getMessage(), Status.FAIL, null);
        } catch (CommandNotFoundException exception) {
            return new Response(null, Status.FAIL, new Error(new ServerException("Command \"" + request.command() + "\" not found.")));
        } catch (NullPointerException exception) {
            return new Response(null, null, new Error(new ServerException("Objects or arguments wasn't provided to execute \"" + request.command() + "\" command.")));
        } catch (IllegalArgumentException exception) {
            return new Response(null, null, new Error(new ServerException("Provided illegal arguments.")));
        } catch (SQLException exception) {
            return new Response("Unknown server side error.", Status.FAIL, null);
        }
    }
}