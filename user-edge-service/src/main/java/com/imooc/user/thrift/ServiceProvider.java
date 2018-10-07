package com.imooc.user.thrift;

import com.imooc.thrift.message.MessageService;
import com.imooc.thrift.user.UserService;
import com.imooc.user.controller.UserController;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Michael on 2017/10/30.
 */
@Component
public class ServiceProvider {

    private Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

    @Value("${thrift.user.ip}")
    private String serverIp;

    @Value("${thrift.user.port}")
    private int serverPort;

    @Value("${thrift.message.ip}")
    private String messageServerIp;
    @Value("${thrift.message.port}")
    private int messageServerPort;

    private enum ServiceType {
        USER,
        MESSAGE
    }

    public UserService.Client getUserService() {

        return getService(serverIp, serverPort, ServiceType.USER);
    }

    public MessageService.Client getMessasgeService() {

        return getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType) {
        logger.error("===ServiceProvider===getService()===serviceType: " + serviceType);
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            logger.error("===ServiceProvider===getService()===exception:");
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }
        return (T)result;
    }

}
