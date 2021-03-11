package tlgBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


public class ServerMessages {

    static Game game = new Game();
    static Keyboards keyboards = new Keyboards();

    public static SendMessage start(long chat_id) {
        return createMessage(chat_id, welcome, keyboards.startKeyboard());
    }

    public static SendMessage agree(long chat_id) {
        return createMessage(chat_id, instructions, keyboards.agreementKeyboard());
    }

    public static SendMessage cancelGame(long chat_id) {
        return createMessage(chat_id, cancel, keyboards.cancelKeyboard());
    }

    public static SendMessage desieseSelect(long chat_id) {
        return createMessage(chat_id, select, keyboards.desieseSelectionKeyboard());
    }

    public static SendMessage game(long chat_id, int desieseNumber) {
        pauseBetweenQuests();
        game.play(desieseNumber);
        return createMessage(chat_id, game.play(desieseNumber), keyboards.gameKeyboard());
    }




    private static SendMessage createMessage(long chat_id, String text, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(text);
        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }
    static SendMessage createMessageWithoutKeyboard(long chat_id, String text) {
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(text);
        return message;
    }

    private static void pauseBetweenQuests() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private static final String welcome = "Этот симулятор предлагает вам прожить опыт человека с ментальными особенностями. \n" +
            "После принятия решения об участии: в жизни люди не выбирают себе заболевание, поэтому, если вы хотите, чат-бот выберет вам особенность случайным образом. Или  вы сможете выбрать ментальную особенность из предложенных вариантов:\n" +
            "-Обсессивно-компульсивное расстройство\n" +
            "-Тревожное расстройство\n" +
            "-Расстройство аутистического спектра\n" +
            "-Самоповреждающее поведение\n" +
            "Важно: Мы не рекомендуем участвовать в симуляторе, если вы — человек с ментальными особенностями, заболеваниями или если они были у вас в прошлом, потому что вы можете столкнуться с триггерами. Сверьтесь с собой и продолжайте, если уверены, что готовы. \n";

    private static final String instructions = "Инструкция к симулятору.\n" +
            "Чат-бот будет присылать вам задания, с промежутками между ними от 10 до 20 минут. Игра займет около 3 часов вашего времени и будет проходить параллельно с другими мероприятиями фестиваля, вплетаясь в канву вашей жизни.\n" +
            "\n" +
            "Вы должны выполнить задание сразу, как только его увидели, даже если это неудобно в данный момент, а затем нажать кнопку ВЫПОЛНЕНО. \n" +
            "Разумеется, чат-бот никак не сможет проконтролировать, действительно ли вы выполнили задание, но мы рекомендуем делать их все. Так вы сможете пережить опыт жизни с ментальными особенностями наиболее полно. \n" +
            "\n" +
            "Новое задание чат-бот пришлет только после выполнения предыдущего. Отсчет начинается с момента нажатия кнопки ВЫПОЛНЕНО. \n" +
            " Если вы планируете посетить Живую психобиблиотеку, пожалуйста, на время чтения Книг поставьте чат-бот на паузу. Если вы будете выполнять задания во время рассказа Книги, ему или ей это помешает и может ранить.\n" +
            "\n" +
            "Вы можете остановить симулятор в любой момент.\n" +
            "\n" +
            "Задания построены таким образом, что они будут приносить некоторый дискомфорт. Однако если от выполнения заданий вы почувствуете дискомфорт, с которым не можете справиться самостоятельно, мы советуем остановить симулятор и обратиться за поддержкой к близкому человеку или специалисту.\n";

    private static final String cancel = "На нет и суда нет.";

    private static final String select = "Вы можете выбрать, какой опыт вы хотите пережить.";
}
