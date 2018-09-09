package forms;

import dbclass.ConnectionSQL;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMain extends JFrame {

    ConnectionSQL cmd;
    //Points
    private Point sizePanelUsers           = new Point(240,300);
    private Point sizePanelMessage         = new Point(240,300);
    private Point sizeButtonPanelUsers     = new Point(236,20);

    private Point locationPanelUsers       = new Point(0,0);
    private Point locationPanelMessage     = new Point(245,0);
    private Point locationButtonPanelUsers = new Point(2,302);

    // Модели списков
    private DefaultListModel modelListIdUsers = new DefaultListModel();

    // Списки
    private JList listIdUsers;
    private JTextArea textArea;

    public FrmMain()
    {
        cmd = new ConnectionSQL();

        new JFrame ("Первый Фрейм");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLayout(null);
        this.getContentPane().add(getListUsers());
        this.getContentPane().add(getBtnSendMEssage());
        this.getContentPane().add(getPanelMessage());
        this.setPreferredSize(new Dimension(640,430));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private JPanel getListUsers()
    {
        JPanel panelUsers = new JPanel();
        Border b = BorderFactory.createTitledBorder("Список ID-Юзеров");
        panelUsers.setBorder(b);
        panelUsers.setSize(sizePanelUsers.x,sizePanelUsers.y);
        panelUsers.setLayout(new BorderLayout());
        panelUsers.setLocation(locationPanelUsers);

        modelListIdUsers = cmd.getListIdUsers();

        listIdUsers = new JList(modelListIdUsers);
        listIdUsers.setVisible(true);

        JScrollPane listIdUsersScroll = new JScrollPane(listIdUsers);
        listIdUsersScroll.setPreferredSize(new Dimension(100,100));

        panelUsers.add(listIdUsersScroll);

        return panelUsers;
    }

    private JPanel getPanelMessage()
    {
        JPanel panelMessage = new JPanel();
        Border b = BorderFactory.createTitledBorder("Список ID-Юзеров");
        panelMessage.setBorder(b);
        panelMessage.setSize(sizePanelMessage.x,sizePanelMessage.y);
        panelMessage.setLayout(new BorderLayout());
        panelMessage.setLocation(locationPanelMessage);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panelMessage.add(new JScrollPane(textArea));

        return panelMessage;
    }

    private JButton getBtnSendMEssage()
    {
        JButton btnSendMessage = new JButton();
        btnSendMessage.setSize(sizeButtonPanelUsers.x,sizeButtonPanelUsers.y);
        btnSendMessage.setText("Отправить сообщение");
        btnSendMessage.setLocation(locationButtonPanelUsers);
        btnSendMessage.setFont(new Font("TimesRoman",Font.BOLD,10));

        btnSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listIdUsers.getSelectedIndex() != 1)
                {
                    SendMessage sendMessage = new SendMessage();

                    sendMessage.setChatId(modelListIdUsers.getElementAt(listIdUsers.getSelectedIndex()).toString());
                    sendMessage.setText(textArea.getText());
                }
            }
        });

        return btnSendMessage;
    }


}
