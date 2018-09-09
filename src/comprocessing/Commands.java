package comprocessing;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Commands {

    public Commands()
    {
        //
    }

    public SendMessage processMessages(Update update)
    {
        TypeMessages typeMessages = new TypeMessages();

        if (update.hasMessage() && update.getMessage().hasText())
        {
           if (update.getMessage().getText().equals("/start"))
           {
               return typeMessages.sendMsgInlineStart(update.getMessage(),"Привет!");
           }
       }
       if (update.hasCallbackQuery())
       {
           String callData = update.getCallbackQuery().getData();

           switch (callData){

               case "GetService"    : return typeMessages.sendMsgInlineGetService(update.getCallbackQuery().getMessage(),"Выберите услугу из списка ниже:");
               case "GetBrow"       : return typeMessages.sendMsgGetBrow(update.getCallbackQuery().getMessage(),"Услуга брови");
               case "GetVELVETBrow" : return typeMessages.sendMsgGetVELVETBrow(update.getCallbackQuery().getMessage(),"Услуга вельвет бровей");
               case "GetVELVETLash" : return typeMessages.sendMsgGetVELVETLash(update.getCallbackQuery().getMessage(),"Услуга вельвет ресниц");
               case "GetMAN"        : return typeMessages.sendMsgGetMAN(update.getCallbackQuery().getMessage(),"Мужики не танцуют!");
               default: break;
           }
       }

       return typeMessages.sendMsgNull(update.getMessage(),"Пустота");

    }
}
