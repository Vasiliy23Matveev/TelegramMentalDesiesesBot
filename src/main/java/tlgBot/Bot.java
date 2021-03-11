package tlgBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    static private final String BOT_USERNAME = "mental_desieses_bot";
    static private final String BOT_TOKEN = "1294331817:AAE3fr--qp4JSaoXU9XCEqehHEmAeWSLb3g";
    int desieseNumber;

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            if (update.getMessage().getText().equals("/start")) {
                sendMessageFromServer(ServerMessages.start(chat_id));
            }
            if (update.getMessage().getText().equals("Продолжить")) {
                sendMessageFromServer(ServerMessages.agree(chat_id));
            }
            if (update.getMessage().getText().equals("Нет, спасибо")
                    || update.getMessage().getText().equals("Все-таки нет")) {
                sendMessageFromServer(ServerMessages.cancelGame(chat_id));
            }
            if (update.getMessage().getText().equals("О'кей") ||
                    update.getMessage().getText().equals("К выбору")) {
                sendMessageFromServer(ServerMessages.desieseSelect(chat_id));
            }
            if (update.getMessage().getText().equals("Обсессивно-компульсивное расстройство"))
            {
                desieseNumber = 1;
                sendMessageFromServer(ServerMessages.createMessageWithoutKeyboard(chat_id, "Вы выбрали Обсессивно-компульсивное расстройство."));
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }
            if (update.getMessage().getText().equals("Тревожное расстройство"))
            {
                desieseNumber = 2;
                sendMessageFromServer(ServerMessages.createMessageWithoutKeyboard(chat_id, "Вы выбрали Тревожное расстройство."));
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }
            if (update.getMessage().getText().equals("Расстройство аутистического спектра"))
            {
                desieseNumber = 3;
                sendMessageFromServer(ServerMessages.createMessageWithoutKeyboard(chat_id, "Вы выбрали Расстройство аутистического спектра."));
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }
            if (update.getMessage().getText().equals("Самоповреждающее поведение"))
            {
                desieseNumber = 4;
                sendMessageFromServer(ServerMessages.createMessageWithoutKeyboard(chat_id, "Вы выбрали Самоповреждающее поведение."));
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }
            if (update.getMessage().getText().equals("Выбрать случайным образом"))
            {
                desieseNumber = (int) (Math.random() * ((0 - 1) + 4));
                sendMessageFromServer(ServerMessages.createMessageWithoutKeyboard(chat_id, "Вы выбрали случайное заболевание."));
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }

            if (update.getMessage().getText().equals("Выполнено")) {
                sendMessageFromServer(ServerMessages.game(chat_id, desieseNumber));
            }

        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage()
                        .setText(update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }


    private void sendMessageFromServer(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}