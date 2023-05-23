package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    /**
     * MY METHODS
     */
    /**
     * Responder ao utilizador
     * @param who quem (user)
     * @param what o quê (mensagem a enviar)
     */
    public void sendText(Long who, String what){
        SendMessage sendMSG = SendMessage.builder()
                .chatId(who.toString()) //quem irá receber a mensagem
                .text(what).build(); //mensagem a enviar

        try {
            execute(sendMSG);
        } catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }



    /**
     * DEFAULT METHODS
     */
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
     * @return token
     */
    @Override
    public String getBotToken() {
        return "6040993503:AAHzXNo9rlgB4pOZvkQS8gS1vhayWuLdDCc";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    /**
     * verificar se há uma nova nensagem privada disponivel
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); //mensagem
        User user = msg.getFrom(); //nome do utilizador

        System.out.println(user.getId() + " enviou: " + msg.getText());

        /**
         * responder ao utilizador
         */
        String content = "Hi";

        //enviar mensagem
        sendText(user.getId(), content);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
