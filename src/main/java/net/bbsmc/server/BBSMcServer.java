package net.bbsmc.server;

import net.bbsmc.server.core.ServerSetter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BBSMcServer {
    public static String name = BBSMcServer.class.getSimpleName();
    public static Logger log = LogManager.getLogger(name);

    public static ServerSetter serverSetter = new ServerSetter();
    public static ServerSetter getServerSetter() {
        return serverSetter;
    }
}
