package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    /**
     * verificar se há um novo update disponivel
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update)
    {
        System.out.println(update);
    }

    /**
     * editar o nome do bot
     * @return nome do bot
     */
    @Override
    public String getBotUsername() {
        return "senhorJuiz_bot";
    }

    /**
     * token
     * @return
     */
    @Override
    public String getBotToken(){
        return "6040993503:AAHzXNo9rlgB4pOZvkQS8gS1vhayWuLdDCc";
    }
}
