package com.just.ui;

import cn.hutool.core.io.FileUtil;
import com.just.domain.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> userArrayList = new ArrayList<>();

    String code;
    int length = 5;
    JLabel jLabelVerifyCode;
    JLabel jLabelLogin;
    JLabel jLabelRegister;
    JLabel jLabelVisiblePassword;
    JTextField textField;
    JPasswordField jPasswordField;
    JTextField textFieldCode;

    public LoginJFrame(){

        loadUserinfo();

        init();

        code = verifyCode(length);
        initJFrame(code);

        this.setVisible(true);

    }

    private void loadUserinfo() {

        List<String> userinfoList = FileUtil.readLines("C:\\Users\\49744\\Documents\\Jigsawpuzzlegame\\save\\userinfo.txt", "UTF-8");
        for (String s : userinfoList) {
            String[] split = s.split("&");
            User user = new User(split[0].split("=")[1], split[1].split("=")[1]);
            userArrayList.add(user);
        }

    }

    private String verifyCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int countNumber = 0;
        while(true){
            switch (random.nextInt(3)){
                case 0 : {
                    if (countNumber == 0)
                        sb.append((char)('0' + random.nextInt(10)));
                    countNumber++;
                    break;
                }
                case 1 : {
                    sb.append((char)('a' + random.nextInt(26)));
                    break;
                }
                case 2 : {
                    sb.append((char)('A' + random.nextInt(26)));
                    break;
                }
                default:{}
            }
            if (sb.length() == length){
                break;
            }
        }
        return sb.toString();
    }

    private void initJFrame(String code) {

        this.getContentPane().removeAll();

        ImageIcon background = new ImageIcon("image/login/background.png");
        JLabel jLabelBackground = new JLabel(background);
        jLabelBackground.setBounds(40,40,470,390);

        JLabel jLabelUsername = new JLabel(new ImageIcon("image/login/用户名.png"));
        JLabel jLabelPassword = new JLabel(new ImageIcon("image/login/密码.png"));
        JLabel jLabelCode = new JLabel(new ImageIcon("image/login/验证码.png"));
        jLabelVerifyCode = new JLabel(code);

        textField = new JTextField(20);
        jPasswordField = new JPasswordField(20);
        textFieldCode = new JTextField(20);

        ImageIcon login = new ImageIcon("image/login/登录按钮.png");
        ImageIcon register = new ImageIcon("image/login/注册按钮.png");
        ImageIcon visiblePassword = new ImageIcon("image/login/显示密码.png");
        jLabelLogin = new JLabel(login);
        jLabelRegister = new JLabel(register);
        jLabelVisiblePassword = new JLabel(visiblePassword);

        jLabelUsername.setBounds(150, 150, 80, 30);
        textField.setBounds(230, 150, 150, 30);
        jLabelPassword.setBounds(150, 200, 80, 30);
        jLabelVisiblePassword.setBounds(400,200,18,29);
        jPasswordField.setBounds(230, 200, 150, 30);
        jLabelCode.setBounds(150, 250, 80, 30);
        textFieldCode.setBounds(230, 250, 150, 30);
        jLabelVerifyCode.setBounds(400,250,50,30);
        jLabelLogin.setBounds(150, 300, 128, 47);
        jLabelRegister.setBounds(300, 300, 128, 47);

        this.getContentPane().add(jLabelUsername);
        this.getContentPane().add(textField);
        this.getContentPane().add(jLabelPassword);
        this.getContentPane().add(jPasswordField);
        this.getContentPane().add(jLabelVisiblePassword);
        this.getContentPane().add(jLabelCode);
        this.getContentPane().add(jLabelVerifyCode);
        this.getContentPane().add(textFieldCode);
        this.getContentPane().add(jLabelLogin);
        this.getContentPane().add(jLabelRegister);
        this.getContentPane().add(jLabelBackground);

        jLabelVerifyCode.addMouseListener(this);
        jLabelLogin.addMouseListener(this);
        jLabelRegister.addMouseListener(this);
        jLabelVisiblePassword.addMouseListener(this);

        this.getContentPane().repaint();
    }

    private void init() {
        this.setSize(600, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelVerifyCode){
            code = verifyCode(length);
            initJFrame(code);
        } else if (o == jLabelLogin) {
            String verifyCode = textFieldCode.getText();
            System.out.println(verifyCode);
            if (code.equalsIgnoreCase(verifyCode)) {
                if (checkLoginUser()) {
                    this.setVisible(false);
                    new GameJFrame();
                } else {
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("密码错误，请重新输入密码！");
                    jDialog.setSize(300,300);
                    jDialog.setAlwaysOnTop(true);
                    jDialog.setModal(true);
                    jDialog.getContentPane().add(jLabel);
                    jDialog.setVisible(true);
                }
            } else {
                JDialog jDialog = new JDialog();
                JLabel jLabel = new JLabel("验证码错误，请重新输入验证码！");
                jDialog.setSize(300,300);
                jDialog.setAlwaysOnTop(true);
                jDialog.setModal(true);
                jDialog.getContentPane().add(jLabel);
                jDialog.setVisible(true);
                code = verifyCode(5);
                initJFrame(code);
            }
        } else if (o == jLabelRegister) {
            this.setVisible(false);
            new RegisterJFrame(userArrayList);
        }
    }

    public boolean checkLoginUser() {
        String username = textField.getText();
        char[] passwordChar = jPasswordField.getPassword();
        for (int i = 0; i < userArrayList.size(); i++) {
            User user = userArrayList.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(new String(passwordChar))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelLogin) {
            ImageIcon imageIcon = new ImageIcon("image/login/登录按下.png");
            jLabelLogin.setIcon(imageIcon);
            this.getContentPane().repaint();
        } else if (o == jLabelRegister) {
            ImageIcon imageIcon = new ImageIcon("image/login/注册按下.png");
            jLabelRegister.setIcon(imageIcon);
            this.getContentPane().repaint();
        } else if (o == jLabelVisiblePassword) {
            jPasswordField.setEchoChar((char)0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelLogin) {
            ImageIcon imageIcon = new ImageIcon("image/login/登录按钮.png");
            jLabelLogin.setIcon(imageIcon);
            this.getContentPane().repaint();
        } else if (o == jLabelRegister) {
            ImageIcon imageIcon = new ImageIcon("image/login/注册按钮.png");
            jLabelRegister.setIcon(imageIcon);
            this.getContentPane().repaint();
        } else if (o == jLabelVisiblePassword) {
            jPasswordField.setEchoChar('*');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
