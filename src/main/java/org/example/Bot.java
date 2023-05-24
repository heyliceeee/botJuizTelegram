package org.example;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
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

    public void goChatGPT()
    {
        String API_KEY = "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";
        OpenAiService service = new OpenAiService(API_KEY);

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
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
        String msgFromUser = msg.getText(); //mensagem do utilizador

        System.out.println(user.getId() + " enviou: " + msgFromUser);

        /**
         * responder ao utilizador
         */
        //enviar msg do utilizador para o chatgpt
        goChatGPT();

        String content = msg.getText();

        //enviar mensagem
        sendText(user.getId(), content);

        System.out.println("o bot enviou ao: "+user.getId()+", "+content);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
