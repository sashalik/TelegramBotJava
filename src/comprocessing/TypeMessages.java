package comprocessing;

import dbclass.ConnectionSQL;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TypeMessages{

    private ConnectionSQL dbc;

    public TypeMessages()
    {
        dbc = new ConnectionSQL();
    }


    public SendMessage sendMsgGetBrow(Message message, String s)
    {
        return getEasyMessage(message,s);
    }

    public SendMessage sendMsgGetVELVETBrow(Message message, String s)
    {
        return getEasyMessage(message,s);
    }

    public SendMessage sendMsgGetVELVETLash(Message message, String s)
    {
        return getEasyMessage(message,s);
    }

    public SendMessage sendMsgGetMAN(Message message, String s)
    {
        return getEasyMessage(message,s);
    }

    public SendMessage sendMsgInlineGetService(Message message, String s)
    {
        InlineKeyboardMarkup markupService = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInlineGetService = new ArrayList<>();

        //Заполняем список кнопок
        rowsInlineGetService.add(getButton("Оформление бровей","GetBrow"));
        rowsInlineGetService.add(getButton("VELVET бровей","GetVELVETBrow"));
        rowsInlineGetService.add(getButton("VELVET ресниц","GetVELVETLash"));
        rowsInlineGetService.add(getButton("Я мужик. И так сойдет!)","GetMAN"));
        markupService.setKeyboard(rowsInlineGetService);

        return getMessageWithInline(message,s,markupService);
    }

    public SendMessage sendMsgInlineStart(Message message, String s)
    {
        InlineKeyboardMarkup markupStart = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInlineStart = new ArrayList<>();
        List<InlineKeyboardButton> rowInlineStart = new ArrayList<>();
        rowInlineStart.add(new InlineKeyboardButton().setText("Получить услугу").setCallbackData("GetService"));
        rowsInlineStart.add(rowInlineStart);

        markupStart.setKeyboard(rowsInlineStart);

        return getMessageWithInline(message,s,markupStart);
    }

    public SendMessage sendMsgNull(Message message, String s)
    {
        return getEasyMessage(message,s);
    }

    private List<InlineKeyboardButton> getButton(String NameService, String CallBackData)
    {
        List<InlineKeyboardButton> rowInlineService = new ArrayList<>();
        // Заполняем список кнопками
        rowInlineService.add(new InlineKeyboardButton().setText(NameService).setCallbackData(CallBackData));

        return rowInlineService;
    }

    private SendMessage getEasyMessage(Message message, String s)
    {
        SendMessage sendMessage  = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(s);

        dbc.qNonQuery("insert into logs(text, ChatId) values('"+message.getText()+"',"+message.getChatId().toString()+");");

        return sendMessage;
    }

    private SendMessage getMessageWithInline(Message message, String s, InlineKeyboardMarkup markup)
    {
        SendMessage sendMessage  = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(s);
        sendMessage.setReplyMarkup(markup);

        dbc.qNonQuery("insert into logs(text, ChatId) values('"+message.getText()+"',"+message.getChatId().toString()+");");

        return sendMessage;
    }
}
