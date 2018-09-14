package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();

        if (update.hasMessage()) {
            String txt = msg.getText();
            System.err.println(txt);
            if (txt != null && txt.equals("/start")) {
                sendMsg(msg, "ПРИВЕТИКИ!");
                return;
            }
            if (txt != null && txt.equals("Привет")) {
                sendMsg(msg, "ПРИВЕТИКИ!");
                return;
            }
            if (txt != null && txt.equals("Бросить монету")) {
                int test = (int) (Math.random() * 10) % 2;
                String text = (test == 1) ? ("Орел") : ("Решка");
                sendMsg(msg, text);
                return;
            }
            if (txt != null && txt.equals("Помощь!")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(msg.getChatId());
                sendMessage.setText("Кто хочет стать миллионером?");
                sendMessage.setReplyMarkup(setInline());
                setInline();
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                return;
            }

        }
        //notused
        if (update.hasCallbackQuery()) {
            String txt = update.getCallbackQuery().getData();
            msg = update.getCallbackQuery().getMessage();
            if (txt.equals("Привет")) {
                sendMsg(msg, "ПРИВЕТИКИ!");
            }
        }

    }


    private synchronized void sendMsg(Message chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId.getChatId());
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Привет"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Бросить монету"));
        // Вторая строчка клавиатуры

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardThirdRow.add(new KeyboardButton("Помощь!"));
        // Вторая строчка клавиатуры

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    private InlineKeyboardMarkup setInline() {
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        //InlineKeyboardButton btn1 = new InlineKeyboardButton().setText("ПРИВЕТИКИ!").setCallbackData("Привет");
        //InlineKeyboardButton btn2 = new InlineKeyboardButton().setText("Бросить монету").setCallbackData("Бросить монету");
        InlineKeyboardButton btn3 = new InlineKeyboardButton().setText("Я хочу!!!").setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        List<List<InlineKeyboardButton>> buttons = new ArrayList<List<InlineKeyboardButton>>();
        //List<InlineKeyboardButton> buttons1 = new ArrayList<InlineKeyboardButton>();
        //List<InlineKeyboardButton> buttons2 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> buttons3 = new ArrayList<InlineKeyboardButton>();
        // buttons1.add(btn1);
        //buttons2.add(btn2);
        buttons3.add(btn3);
        // buttons.add(buttons1);
        // buttons.add(buttons2);
        buttons.add(buttons3);

        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    }

    @Override
    public String getBotUsername() {
        return "MY_BOT";
    }

    @Override
    public String getBotToken() {
        return "MY_TOKEN";
    }
}