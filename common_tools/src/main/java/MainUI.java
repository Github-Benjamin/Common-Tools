/**
 * Created by Benjamin on 2019/4/29.
 */
import Utils.*;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.ParseException;
import javax.swing.*;


public class MainUI extends JPanel implements ActionListener {

    JTextField unixText,bjText,urlDEcode,urlENcode,base64DEcode,base64ENcode,UniDEcode,UniENcode;
    JTextArea  lenText,formatStrText;
    JRadioButton JsonSelect,XmlSelect,HtmlSelect;


    public MainUI()
    {

        //设置布局管理器，默认的布局管理器是 BorderLayout,这里没那么复杂
        //选择GridLayout(1,1)即可，就是整个为一块
        super(new GridLayout(1,1));
        //创建JTabbedPane
        JTabbedPane tp = new JTabbedPane();
        //创建标签显示的图标
        ImageIcon ii = createImageIcon("images/middle.gif");

        //创建第一个标签下的panel
        JPanel panel0 = createTabStrFormatPanel();
        //指定标签名，标签图标，panel，和提示信息
        tp.addTab("字符串格式",ii,panel0,"do noting");
        //设置标签的快捷键
        tp.setMnemonicAt(0, KeyEvent.VK_0);

        //第二个标签
        JPanel panel1 = createTabUnixPanel();
        tp.addTab("Unix时间戳",ii,panel1,"do noting");
        tp.setMnemonicAt(1, KeyEvent.VK_2);

        //第三个标签
        JPanel panel2 = createTabStrLenPanel();
        tp.addTab("字符长度",ii,panel2,"do noting");
        tp.setMnemonicAt(2, KeyEvent.VK_3);

        //第四个标签
        JPanel panel3 = createTabBase64Panel();
        tp.addTab("Base64 编&解码",ii,panel3,"do noting");
        tp.setMnemonicAt(3, KeyEvent.VK_1);

        //第五个标签
        JPanel panel4 = createTabUrlPanel();
        tp.addTab("URL 编&解码",ii,panel4,"do noting");
        tp.setMnemonicAt(4, KeyEvent.VK_1);

        //第六个标签
        JPanel panel5 = createTabUniCodePanel();
        tp.addTab("Unicode 编&解码",ii,panel5,"do noting");
        tp.setMnemonicAt(5, KeyEvent.VK_3);

        //第七个标签
        JPanel panel6 = createTabAboutPanel();
        tp.addTab("关于",ii,panel6,"do noting");
        tp.setMnemonicAt(6, KeyEvent.VK_3);

        //设置合适的显示尺寸，这个是必须的，因为如果所有的标签都
        //不指定适合的显示尺寸，系统无法判断初始显示尺寸大小
        //默认是使用最小化，并且对一个标签设计即可
        tp.setPreferredSize(new Dimension(500,300));

        //将tabbedPanel添加到Jpanel中
        add(tp);

        //设置窗口过小时，标签的显示策略
        tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //设置标签停放的位置，这里设置为左侧停放
        tp.setTabPlacement(JTabbedPane.LEFT);

    }




    private JPanel createPanel(String string) {
        //创建一个JPanel，并为构造函数初始false
        //表示不适用双缓冲
        JPanel panel = new JPanel(false);

        //设置布局
        panel.setLayout(new GridLayout(1,1));

        //创建一个label放到panel中
        JLabel filler = new JLabel(string);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.add(filler);
        return panel;

    }


    private ImageIcon createImageIcon(String string) {
        URL url = MainUI.class.getResource(string);
        if(url == null)
        {
//            System.out.println("the image "+string+" is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }


    public static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Common Test Tools");
        frame.setLocationRelativeTo(null);//窗体居中显示
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MainUI());
        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }



    // 事件判断
    public void actionPerformed(ActionEvent e) {



        if(e.getActionCommand()=="格式化")
        {
            String formatStr = formatStrText.getText();
            if( formatStr.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                // 选中字符串格式化
                String nowTIme = GetTimeUtil.GetNowTimeStamp();
                if(JsonSelect.isSelected())
                {
                    String JsonData  = JsonUtil.JsonFormat(formatStr);
                    TxtFileUtil.WriteStringToFile("JSON",nowTIme,JsonData);
                    JOptionPane.showMessageDialog(null,String.format("Create JSON File Success\nPlease Check: JSON%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else if(XmlSelect.isSelected())
                {
                    try {
                        String XmlData  = XmlUtil.Format(formatStr);
                        TxtFileUtil.WriteStringToFile("XML",nowTIme,XmlData);
                        JOptionPane.showMessageDialog(null,String.format("Create XML File Success\nPlease Check: XML%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }catch (Exception a){
                        JOptionPane.showMessageDialog(null,"Create XML File Failed\nPlease Check XML DATA","提示消息",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }else if (HtmlSelect.isSelected())
                {
                    String HtmlData  = HtmlUtil.Format(formatStr);
                    TxtFileUtil.WriteStringToFile("HTML",nowTIme,HtmlData);
                    JOptionPane.showMessageDialog(null,String.format("Create HTML File Success\nPlease Check: HTML%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

        }


        if(e.getActionCommand()=="压缩")
        {
            String formatStr = formatStrText.getText();
            if( formatStr.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                // 选中字符串格式化
                String nowTIme = GetTimeUtil.GetNowTimeStamp();
                if(JsonSelect.isSelected())
                {
                    String JsonRar  = RarString.RarString(formatStr);
                    TxtFileUtil.WriteStringToFile("JSON",nowTIme,JsonRar);
                    JOptionPane.showMessageDialog(null,String.format("Create JSON File Success\nPlease Check: JSON%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else if(XmlSelect.isSelected())
                {
                    try {
                        String XmlRar  = RarString.RarString(formatStr);
                        TxtFileUtil.WriteStringToFile("XML",nowTIme,XmlRar);
                        JOptionPane.showMessageDialog(null,String.format("Create XML File Success\nPlease Check: XML%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }catch (Exception a){
                        JOptionPane.showMessageDialog(null,"Create XML File Failed\nPlease Check XML DATA","提示消息",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }else if (HtmlSelect.isSelected())
                {
                    String HtmlRar  = RarString.RarString(formatStr);
                    TxtFileUtil.WriteStringToFile("HTML",nowTIme,HtmlRar);
                    JOptionPane.showMessageDialog(null,String.format("Create HTML File Success\nPlease Check: HTML%s:.txt",nowTIme),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

        }



        if(e.getActionCommand()=="转换")
        {
            String unixTime = unixText.getText();
            if(unixTime.isEmpty()){
                bjText.setText("no data");
            }else{
                bjText.setText(GetTimeUtil.TimeStamp2Date(unixTime));
            }


        }

        if(e.getActionCommand()=="反转")
        {
           String bjTime = bjText.getText();
            try {
                if(bjTime.isEmpty()){
                    unixText.setText("no data");
                }else{
                    unixText.setText(GetTimeUtil.dateToStamp(bjTime));
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

        }

        if(e.getActionCommand()=="获取")
        {
            String getNoteTime = GetTimeUtil.GetNowTimeStamp();
            unixText.setText(getNoteTime);
            bjText.setText(GetTimeUtil.TimeStamp2Date(getNoteTime));
        }


        if(e.getActionCommand()=="获取长度")
        {
            String lentext = lenText.getText();
            if( lentext.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                JOptionPane.showMessageDialog(null,"字符串长度为：" + StringLenUtil.StringLen(lentext),"提示消息",JOptionPane.INFORMATION_MESSAGE);
                return;
            }


        }


        if(e.getActionCommand()=="编码")
        {
            String decodeText = urlDEcode.getText();
            if( decodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                urlENcode.setText(UrlCodeUtil.EnCode(decodeText));
            }

        }


        if(e.getActionCommand()=="解码")
        {
            String encodeText = urlENcode.getText();
            if( encodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                urlDEcode.setText(UrlCodeUtil.DECode(encodeText));
            }

        }


        if(e.getActionCommand()=="DeCode")
        {
            String encodeText = base64ENcode.getText();
            if( encodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                base64DEcode.setText(Base64CodeUtil.DECode(encodeText));
            }

        }

        if(e.getActionCommand()=="EnCode")
        {
            String encodeText = base64DEcode.getText();
            if( encodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                base64ENcode.setText(Base64CodeUtil.ENCode(encodeText));
            }

        }


        if(e.getActionCommand()=="UdeCode")
        {
            String encodeText = UniENcode.getText();
            if( encodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                UniDEcode.setText(UniCodeUtil.DECode(encodeText));
            }

        }

        if(e.getActionCommand()=="UenCode")
        {
            String encodeText = UniDEcode.getText();
            if( encodeText.isEmpty()){
                JOptionPane.showMessageDialog(null,"数据为空！","提示消息",JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                UniENcode.setText(UniCodeUtil.ENCode(encodeText));
            }

        }




    }


    // 字符串格式化
    private JPanel createTabStrFormatPanel(){


        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel selectTab = new JLabel("格式选择:");
        JsonSelect = new JRadioButton("JSON");
        XmlSelect = new JRadioButton("XML");
        HtmlSelect = new JRadioButton("HTML");
        ButtonGroup bg = new ButtonGroup();
        bg.add(JsonSelect);
        bg.add(XmlSelect);
        bg.add(HtmlSelect);
        JsonSelect.setSelected(true);
        XmlSelect.setSelected(false);
        HtmlSelect.setSelected(false);


        JLabel strTab = new JLabel("字符串:");
        formatStrText = new JTextArea (3, 10);

        JButton btnSubmit = new JButton("格式化");
        JButton rarSubmit = new JButton("压缩");

        btnSubmit.addActionListener(this);
        rarSubmit.addActionListener(this);

        panel.add(selectTab);
        panel.add(JsonSelect);
        panel.add(XmlSelect);
        panel.add(HtmlSelect);
        panel.add(strTab);
        panel.add(formatStrText);
        panel.add(btnSubmit);
        panel.add(rarSubmit);

        selectTab.setBounds(15, 15, 60, 25);
        JsonSelect.setBounds(75, 15, 60, 25);
        XmlSelect.setBounds(135, 15, 60, 25);
        HtmlSelect.setBounds(191, 15, 60, 25);
        strTab.setBounds(15, 50, 100, 25);
        formatStrText.setBounds(80, 50, 300, 200);
        btnSubmit.setBounds(80, 260, 100, 25);
        rarSubmit.setBounds(220, 260, 100, 25);

        return panel;

    }


    // Base64 编码解码
    private JPanel createTabBase64Panel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel enTab = new JLabel("Base64编码:");
        base64DEcode = new JTextField(10);
        JButton btnZH = new JButton("DeCode");

        JLabel deTab = new JLabel("Base64解码:");
        base64ENcode = new JTextField(10);
        JButton btnFZ = new JButton("EnCode");

        // 设置监听
        btnZH.addActionListener(this);
        btnFZ.addActionListener(this);

        panel.add(enTab);
        panel.add(base64ENcode);
        panel.add(btnZH);
        panel.add(deTab);
        panel.add(base64DEcode);
        panel.add(btnFZ);

        enTab.setBounds(30, 75, 100, 25);
        base64ENcode.setBounds(120, 75, 160, 25);
        btnZH.setBounds(290, 75, 78, 25);

        deTab.setBounds(30, 120, 100, 25);
        base64DEcode.setBounds(120, 120, 160, 25);
        btnFZ.setBounds(290, 120, 78, 25);

        return panel;
    }




    // URL 编码解码
    private JPanel createTabUrlPanel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel enTab = new JLabel("URL编码:");
        urlENcode = new JTextField(10);
        JButton btnZH = new JButton("解码");

        JLabel deTab = new JLabel("URL解码:");
        urlDEcode = new JTextField(10);
        JButton btnFZ = new JButton("编码");

        // 设置监听
        btnZH.addActionListener(this);
        btnFZ.addActionListener(this);

        panel.add(enTab);
        panel.add(urlENcode);
        panel.add(btnZH);
        panel.add(deTab);
        panel.add(urlDEcode);
        panel.add(btnFZ);

        enTab.setBounds(50, 75, 100, 25);
        urlENcode.setBounds(130, 75, 160, 25);
        btnZH.setBounds(300, 75, 60, 25);

        deTab.setBounds(50, 120, 100, 25);
        urlDEcode.setBounds(130, 120, 160, 25);
        btnFZ.setBounds(300, 120, 60, 25);

        return panel;
    }




    // unix时间戳转换
    private JPanel createTabUnixPanel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel unixTab = new JLabel("Unix时间戳:");
        unixText = new JTextField(10);
        JButton btnZH = new JButton("转换");

        JLabel bjTab = new JLabel("北京时间:");
        bjText = new JTextField(10);
        JButton btnFZ = new JButton("反转");

        JButton btnSubmit = new JButton("获取");

        // 设置监听
        btnZH.addActionListener(this);
        btnFZ.addActionListener(this);
        btnSubmit.addActionListener(this);

        panel.add(unixTab);
        panel.add(unixText);
        panel.add(btnZH);
        panel.add(bjTab);
        panel.add(bjText);
        panel.add(btnFZ);
        panel.add(btnSubmit);

        unixTab.setBounds(50, 75, 100, 25);
        unixText.setBounds(130, 75, 160, 25);
        btnZH.setBounds(300, 75, 60, 25);

        bjTab.setBounds(50, 120, 100, 25);
        bjText.setBounds(130, 120, 160, 25);
        btnFZ.setBounds(300, 120, 60, 25);

        btnSubmit.setBounds(150, 170, 80, 25);

        return panel;
    }


    // 字符串长度
    private JPanel createTabStrLenPanel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel strTab = new JLabel("字符串:");
        lenText = new JTextArea (3, 10);

        JButton btnSubmit = new JButton("获取长度");
        btnSubmit.addActionListener(this);

        panel.add(strTab);
        panel.add(lenText);
        panel.add(btnSubmit);

        strTab.setBounds(30, 20, 100, 25);
        lenText.setBounds(80, 20, 300, 200);

        btnSubmit.setBounds(160, 230, 100, 25);

        return panel;
    }

    // UniCode 编码解码
    private JPanel createTabUniCodePanel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel enTab = new JLabel("Unicode编码:");
        UniDEcode = new JTextField(10);
        JButton btnZH = new JButton("UdeCode");

        JLabel deTab = new JLabel("Unicode解码:");
        UniENcode = new JTextField(10);
        JButton btnFZ = new JButton("UenCode");

        // 设置监听
        btnZH.addActionListener(this);
        btnFZ.addActionListener(this);

        panel.add(enTab);
        panel.add(UniENcode);
        panel.add(btnZH);
        panel.add(deTab);
        panel.add(UniDEcode);
        panel.add(btnFZ);

        enTab.setBounds(30, 75, 100, 25);
        UniENcode.setBounds(110, 75, 160, 25);
        btnZH.setBounds(280, 75, 85, 25);

        deTab.setBounds(30, 120, 100, 25);
        UniDEcode.setBounds(110, 120, 160, 25);
        btnFZ.setBounds(280, 120, 85, 25);

        return panel;
    }

    // 关于
    private JPanel createTabAboutPanel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()


//        JLabel Use = new JLabel("集成常用测试开发工具，只为提供方便。");
        JLabel Comment = new JLabel("人生没有白费的努力，也没有碰巧的成功。");
//        JLabel QQGroup = new JLabel("工具交流群：519288336");

        JLabel Author = new JLabel("Author:");
        JLabel AuthorInfo = new JLabel("Benjamin");

        JLabel Email = new JLabel("Email:");
        JLabel EmailInfo = new JLabel("Benjamin_v@qq.com");

//        panel.add(Use);
        panel.add(Comment);
//        panel.add(QQGroup);

        panel.add(Author);
        panel.add(AuthorInfo);

        panel.add(Email);
        panel.add(EmailInfo);

//        Use.setBounds(60, 60, 250, 25);
        Comment.setBounds(60, 90, 250, 25);
//        QQGroup.setBounds(60, 120, 250, 25);

        Author.setBounds(60, 180, 100, 25);
        AuthorInfo.setBounds(140, 180, 160, 25);

        Email.setBounds(60, 210, 100, 25);
        EmailInfo.setBounds(140, 210, 160, 25);

        return panel;
    }




}