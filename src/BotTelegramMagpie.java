import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import comprocessing.Commands;

public class BotTelegramMagpie extends TelegramLongPollingBot {

    private static String PROXY_HOST = "telegram.vpn99.ru" /* proxy host */;
    private static Integer PROXY_PORT = 55655 /* proxy port */;

    public static void main(String args[]){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        ///////////////////////////////////////
        /*DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);


        HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);

        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
        botOptions.setRequestConfig(requestConfig);
        botOptions.setHttpProxy(httpHost);*/
        ///////////////////////

       // FrmMain formMain = new FrmMain();

        try{
            telegramBotsApi.registerBot(new BotTelegramMagpie());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*public void sendMessageUsers(SendMessage sendMessage)
    {
        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }

    }*/

    @Override
    public void onUpdateReceived(Update update)
    {
        Commands com = new Commands();

        try
        {
            execute(com.processMessages(update));
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {

        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}

