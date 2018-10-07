package com.imooc.user.thrift;

import com.imooc.thrift.user.UserService;
import com.imooc.user.service.UserSerivceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by Michael on 2017/10/28.
 */
@Configuration
public class ThriftServer {

    private Logger logger = LoggerFactory.getLogger(ThriftServer.class);

    @Value("${service.port}")
    private int servicePort;

    @Autowired
    private UserService.Iface userService;

    @PostConstruct
    public void startThriftServer() {
        logger.error("===ThriftServer===startThriftServer()");

        TProcessor processor = new UserService.Processor<>(userService);

        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            logger.error("===ThriftServer===startThriftServer()===exception");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TNonblockingServer(args);
        server.serve();
        logger.error("===ThriftServer===startThriftServer()===finished.");
    }
}
