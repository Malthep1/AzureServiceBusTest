package com.map.azureservicebuscheeseservice.services;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CheeseQueueListenerService {

    public static final int N_THREADS = 10;
    private final ExecutorService executorService;
    private final MessageHandler messageHandler;

    public CheeseQueueListenerService(MessageHandler _messageHandler) throws ServiceBusException, InterruptedException {
        messageHandler = _messageHandler;
        executorService = Executors.newFixedThreadPool(N_THREADS);
        String connectionString = "<SERVICE BUS STRING>";
        QueueClient queueClient = new QueueClient(new ConnectionStringBuilder(connectionString), ReceiveMode.PEEKLOCK);
        queueClient.registerMessageHandler(messageHandler, executorService);
    }

}
