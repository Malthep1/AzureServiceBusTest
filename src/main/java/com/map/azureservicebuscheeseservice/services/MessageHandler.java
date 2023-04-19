package com.map.azureservicebuscheeseservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.map.azureservicebuscheeseservice.models.GoudaCheese;
import com.map.azureservicebuscheeseservice.models.NotGoudaCheese;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Service
public class MessageHandler implements IMessageHandler {
    public static final int FIRST_MESSAGE_INDEX = 0;
    private final ObjectMapper objectMapper;
    private ImmutableMap<String, Consumer<byte[]>> messageHandlers;

    public MessageHandler(ObjectMapper _objectMapper){
        objectMapper = _objectMapper;
        System.out.println("Initializing message handlers");
        initMessageHandlers();
        System.out.println("Message handlers initialized");
        System.out.println("MessageHandler created");
    }
    public CompletableFuture<Void> onMessageAsync(IMessage _message)
    {

        System.out.println("Message received");
        byte[] messageByteArray = _message.getMessageBody().getBinaryData().get(FIRST_MESSAGE_INDEX);

        System.out.println("Handling message...");
        messageHandlers.getOrDefault(_message.getLabel(), this::handleUnknownMessage).accept(messageByteArray);

        System.out.println("Message handling complete, terminating thread");
        return CompletableFuture.completedFuture(null);
    }

    private void initMessageHandlers()
    {
        messageHandlers = new ImmutableMap.Builder<String, Consumer<byte[]>>()
                .put("gouda", this::handleGoudaCheese)
                .put("notgouda", this::handleNotGoudaCheese)
                .build();
    }

    private void handleGoudaCheese(byte[] _messageBody) {
        try
        {
            GoudaCheese goudaCheese = objectMapper.readValue(_messageBody, GoudaCheese.class);
            System.out.println("Cheese received: " + goudaCheese.getGoudaName());
        }
        catch (IOException e)
        {
            System.out.println("Malformed GoudaCheese");
        }
    }
    private void handleNotGoudaCheese(byte[] _messageBody) {
        try
        {
            NotGoudaCheese notGoudaCheese = objectMapper.readValue(_messageBody, NotGoudaCheese.class);
            System.out.println("Cheese received: " + notGoudaCheese.getNotGoudaName());
        }
        catch (IOException e)
        {
            System.out.println("Malformed NotGoudaCheese");
        }
    }

    private void handleUnknownMessage(byte[] _messageBody)
    {
        System.out.println("Unknown message received");
    }

    public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
        // Handle any exceptions here
    }
}