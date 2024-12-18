package com.just.ui;

import cn.hutool.core.io.FileUtil;
import com.just.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterJFrame extends JFrame implements MouseListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JPasswordField jDualPasswordField;
    JLabel jLabelReset;
    JLabel jLabelRegister;
    ArrayList<User> userArrayList = null;

    public RegisterJFrame(ArrayList<User> users){
        userArrayList = users;
        init();
        initJFrame();
        this.setVisible(true);
    }

    private void init() {
        this.setSize(600, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
    }

    private void initJFrame() {

        this.getContentPane().removeAll();

        ImageIcon background = new ImageIcon("image/login/background.png");
        JLabel jLabelBackground = new JLabel(background);
        jLabelBackground.setBounds(40,40,470,390);

        JLabel jLabelUsername = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        JLabel jLabelPassword = new JLabel(new ImageIcon("image/register/注册密码.png"));
        JLabel jLabelDualPassword = new JLabel(new ImageIcon("image/register/再次输入密码.png"));

        textField = new JTextField(20);
        jPasswordField = new JPasswordField(20);
        jDualPasswordField = new JPasswordField(20);

        ImageIcon reset = new ImageIcon("image/register/重置按钮.png");
        ImageIcon register = new ImageIcon("image/register/注册按钮.png");
        jLabelReset = new JLabel(reset);
        jLabelRegister = new JLabel(register);

        jLabelUsername.setBounds(120, 150, 100, 30);
        textField.setBounds(230, 150, 150, 30);
        jLabelPassword.setBounds(120, 200, 100, 30);
        jPasswordField.setBounds(230, 200, 150, 30);
        jDualPasswordField.setBounds(230, 250, 150, 30);
        jLabelDualPassword.setBounds(120, 250, 100, 30);
        jLabelRegister.setBounds(150, 300, 128, 47);
        jLabelReset.setBounds(300, 300, 128, 47);

        this.getContentPane().add(jLabelUsername);
        this.getContentPane().add(textField);
        this.getContentPane().add(jLabelPassword);
        this.getContentPane().add(jPasswordField);
        this.getContentPane().add(jLabelDualPassword);
        this.getContentPane().add(jDualPasswordField);
        this.getContentPane().add(jLabelReset);
        this.getContentPane().add(jLabelRegister);
        this.getContentPane().add(jLabelBackground);

        jLabelRegister.addMouseListener(this);
        jLabelReset.addMouseListener(this);

        this.getContentPane().repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelReset) {
            initJFrame();
        } else if (o == jLabelRegister) {
            String username = textField.getText();
            String password = new String(jPasswordField.getPassword());
            String dualPassword = new String(jDualPasswordField.getPassword());
            System.out.println(password+","+dualPassword);
            if(checkRegister(username, password, dualPassword)) {
                JDialog jDialog = new JDialog();
                JLabel jLabel = new JLabel("注册成功！");
                jDialog.setSize(300,300);
                jDialog.setAlwaysOnTop(true);
                jDialog.setModal(true);
                jDialog.getContentPane().add(jLabel);
                jDialog.setVisible(true);

                this.setVisible(false);
                new LoginJFrame();

            }
        }
    }

    private boolean checkRegister(String username, String password, String dualPassword) {
        // 校验
        if (username.length() == 0 || username.length() == 0 || dualPassword.length() == 0) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel("用户名，密码都不能为空！");
            jDialog.setSize(300,300);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.getContentPane().add(jLabel);
            jDialog.setVisible(true);
            return false;
        } else if (!password.equals(dualPassword)) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel("两次密码不一致！");
            jDialog.setSize(300,300);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.getContentPane().add(jLabel);
            jDialog.setVisible(true);
            return false;
        } else if (password.length() < 6 || password.length() > 20 || dualPassword.length() < 6 || dualPassword.length() > 20) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel("密码长度不符合规范！");
            jDialog.setSize(300,300);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.getContentPane().add(jLabel);
            jDialog.setVisible(true);
            return false;
        }

        for (User user : userArrayList) {
            if (user.getUsername().equals(username)) {
                JDialog jDialog = new JDialog();
                JLabel jLabel = new JLabel("该用户名已存在！");
                jDialog.setSize(300,300);
                jDialog.setAlwaysOnTop(true);
                jDialog.setModal(true);
                jDialog.getContentPane().add(jLabel);
                jDialog.setVisible(true);
                return false;
            }
        }

        // 添加用户
        userArrayList.add(new User(username, password));

        // 写入文件
        FileUtil.writeLines(userArrayList, "C:\\Users\\49744\\Documents\\Jigsawpuzzlegame\\save\\userinfo.txt", "UTF-8");

        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelReset) {
            ImageIcon imageIcon = new ImageIcon("image/register/重置按下.png");
            jLabelReset.setIcon(imageIcon);
        } else if (o == jLabelRegister) {
            ImageIcon imageIcon = new ImageIcon("image/register/注册按下.png");
            jLabelRegister.setIcon(imageIcon);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object o = e.getSource();
        if (o == jLabelReset) {
            ImageIcon imageIcon = new ImageIcon("image/register/重置按钮.png");
            jLabelReset.setIcon(imageIcon);
        } else if (o == jLabelRegister) {
            ImageIcon imageIcon = new ImageIcon("image/register/注册按钮.png");
            jLabelRegister.setIcon(imageIcon);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
