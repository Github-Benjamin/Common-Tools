/**
 * Created by Benjamin on 2019/4/29.
 */
import Utils.GetTimeUtil;

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

    JTextField unixText,bjText;
    JTextArea  lenText;


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
        JPanel panel0 = createPanel("panel0");
        //指定标签名，标签图标，panel，和提示信息
        tp.addTab("panel0",ii,panel0,"do noting");
        //设置标签的快捷键
        tp.setMnemonicAt(0, KeyEvent.VK_0);


        //第二个标签
        JPanel panel1 = createPanel("panel1");
        tp.addTab("panel1",ii,panel1,"do noting");
        tp.setMnemonicAt(1, KeyEvent.VK_1);


        //第三个标签
        JPanel panel2 = createTab2Panel();
        tp.addTab("Unix时间戳",ii,panel2,"do noting");
        tp.setMnemonicAt(2, KeyEvent.VK_2);



        //第四个标签
        JPanel panel3 = createTab3Panel();
        tp.addTab("字符长度",ii,panel3,"do noting");
        tp.setMnemonicAt(3, KeyEvent.VK_3);


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
            System.out.println("the image "+string+" is not exist!");
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

        if(e.getActionCommand()=="转换")
        {
            String unixTime = unixText.getText();
            bjText.setText(GetTimeUtil.TimeStamp2Date(unixTime));

        }

        if(e.getActionCommand()=="反转")
        {
           String bjTime = bjText.getText();
            try {
                unixText.setText(GetTimeUtil.dateToStamp(bjTime));
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

    }


    // unix时间戳转换
    private JPanel createTab2Panel(){

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
    private JPanel createTab3Panel(){

        JPanel panel = new JPanel(false);
        panel.setLayout(null);  // new GridLayout()

        JLabel unixTab = new JLabel("字符串:");
        lenText = new JTextArea (3, 10);

        JButton btnSubmit = new JButton("获取长度");
        btnSubmit.addActionListener(this);

        panel.add(unixTab);
        panel.add(lenText);
        panel.add(btnSubmit);

        unixTab.setBounds(30, 20, 100, 25);
        lenText.setBounds(80, 20, 300, 200);

        btnSubmit.setBounds(160, 230, 100, 25);

        return panel;
    }






}