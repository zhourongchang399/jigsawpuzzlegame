package com.just.ui;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.just.domain.Game;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] imageList;
    int total_length;
    int total_count = 0;
    JMenuItem jMenuItemReset;
    JMenuItem jMenuItemRelist;
    JMenuItem jMenuItemClose;
    JMenuItem jMenuItemConnection;
    JMenuItem jMenuItemGirl;
    JMenuItem jMenuItemAnimal;
    JMenuItem jMenuItemSport;
    JMenu jMenuSave;
    JMenu jMenuLoad;
    JMenuItem jMenuItemSave1;
    JMenuItem jMenuItemSave2;
    JMenuItem jMenuItemSave3;
    JMenuItem jMenuItemSave4;
    JMenuItem jMenuItemSave5;
    JMenuItem jMenuItemLoad1;
    JMenuItem jMenuItemLoad2;
    JMenuItem jMenuItemLoad3;
    JMenuItem jMenuItemLoad4;
    JMenuItem jMenuItemLoad5;
    Random random = new Random();
    String filepath = "image/animal/animal1/";
    ArrayList<Game> gameArrayList = new ArrayList<>();
    String account = null;

    public GameJFrame(){

        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader("src/setting.properties");
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        account = properties.getProperty("account");
        System.out.println(account);
        loadSave();

        initJFrame();

        initMenu();

        imageList = initData(true);
        total_length = imageList.length ^ 2;
        initImage(imageList);

        this.addKeyListener(this);

        this.setVisible(true);
    }

    private void loadSave() {
        File saveDir = new File("save");
        File[] files = saveDir.listFiles();
        for (File file : files) {
            Pattern pattern = Pattern.compile("save(.)\\.data");
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.find()) {
                String s = matcher.group(1);
                ObjectInputStream objectInputStream = null;
                try {
                    System.out.println("save/save" + s + ".data");
                    objectInputStream = new ObjectInputStream(new FileInputStream("save/save" + s + ".data"));
                    Game obj = (Game)  objectInputStream.readObject();
                    gameArrayList.add(obj);
                    objectInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initStepCount() {
        JLabel stepCount = new JLabel("步数：" + total_count);
        stepCount.setBounds(10,10,100,50);
        this.add(stepCount);
    }

    private void initImage(int[][] imageList) {
        this.getContentPane().removeAll();
        initStepCount();
        if (checkWinable(imageList)){
            ImageIcon winIcon = new ImageIcon("image/win.png");
            JLabel jLabelWin = new JLabel(winIcon);
            jLabelWin.setBounds(203,283,197,73);
            this.add(jLabelWin);
        }

        for (int i = 0; i < imageList[0].length; i++) {
            for (int j = 0; j < imageList[0].length; j++) {
                if (imageList[i][j] != total_length) {
                    ImageIcon imageIcon = new ImageIcon(filepath + imageList[i][j] + ".jpg");
                    JLabel jLabel = new JLabel(imageIcon);
                    jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                    jLabel.setBorder(new BevelBorder(0));
                    this.getContentPane().add(jLabel);
                }
            }
        }

        ImageIcon background = new ImageIcon("image/background.png");
        JLabel jLabel = new JLabel(background);
        jLabel.setBounds(40,40,508,560);
        this.getContentPane().add(jLabel);

        this.getContentPane().repaint();

    }

    private int[][] initData(boolean state) {
        int[] indexList = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

        if (state) {
            for (int i = 0; i < indexList.length; i++) {
                int index = random.nextInt(indexList.length);
                int temp = indexList[i];
                indexList[i] = indexList[index];
                indexList[index] = temp;
            }
        }

        int[][] tempList = new int[4][4];
        for (int i = 0; i < indexList.length; i++) {
            tempList[i / 4][i % 4] = indexList[i];
        }

        return tempList;
    }

    private void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenuSetting = new JMenu("设置");
        JMenu jMenuHelp = new JMenu("帮助");
        JMenu jMenuChange = new JMenu("更换图片");
        jMenuSave = new JMenu("存档");
        jMenuLoad = new JMenu("读档");

        jMenuItemGirl = new JMenuItem("美女");
        jMenuItemAnimal = new JMenuItem("动物");
        jMenuItemSport = new JMenuItem("运动");
        jMenuItemReset = new JMenuItem("重置游戏");
        jMenuItemRelist = new JMenuItem("退出登录");
        jMenuItemClose = new JMenuItem("退出游戏");
        jMenuItemConnection = new JMenuItem("联系我们");
        
        jMenuItemSave1 = new JMenuItem("存档1（空）");
        jMenuItemSave2 = new JMenuItem("存档2（空）");
        jMenuItemSave3 = new JMenuItem("存档3（空）");
        jMenuItemSave4 = new JMenuItem("存档4（空）");
        jMenuItemSave5 = new JMenuItem("存档5（空）");
        jMenuItemLoad1 = new JMenuItem("存档1（空）");
        jMenuItemLoad2 = new JMenuItem("存档2（空）");
        jMenuItemLoad3 = new JMenuItem("存档3（空）");
        jMenuItemLoad4 = new JMenuItem("存档4（空）");
        jMenuItemLoad5 = new JMenuItem("存档5（空）");

        jMenuItemGirl.addActionListener(this);
        jMenuItemAnimal.addActionListener(this);
        jMenuItemSport.addActionListener(this);
        jMenuItemReset.addActionListener(this);
        jMenuItemRelist.addActionListener(this);
        jMenuItemClose.addActionListener(this);
        jMenuItemConnection.addActionListener(this);
        jMenuItemSave1.addActionListener(this);
        jMenuItemSave2.addActionListener(this);
        jMenuItemSave3.addActionListener(this);
        jMenuItemSave4.addActionListener(this);
        jMenuItemSave5.addActionListener(this);
        jMenuItemLoad1.addActionListener(this);
        jMenuItemLoad2.addActionListener(this);
        jMenuItemLoad3.addActionListener(this);
        jMenuItemLoad4.addActionListener(this);
        jMenuItemLoad5.addActionListener(this);

        jMenuChange.add(jMenuItemGirl);
        jMenuChange.add(jMenuItemAnimal);
        jMenuChange.add(jMenuItemSport);
        jMenuSetting.add(jMenuChange);
        jMenuSetting.add(jMenuSave);
        jMenuSetting.add(jMenuLoad);
        jMenuSetting.add(jMenuItemReset);
        jMenuSetting.add(jMenuItemRelist);
        jMenuSetting.add(jMenuItemClose);
        jMenuHelp.add(jMenuItemConnection);
        jMenuSave.add(jMenuItemSave1);
        jMenuSave.add(jMenuItemSave2);
        jMenuSave.add(jMenuItemSave3);
        jMenuSave.add(jMenuItemSave4);
        jMenuSave.add(jMenuItemSave5);
        jMenuLoad.add(jMenuItemLoad1);
        jMenuLoad.add(jMenuItemLoad2);
        jMenuLoad.add(jMenuItemLoad3);
        jMenuLoad.add(jMenuItemLoad4);
        jMenuLoad.add(jMenuItemLoad5);

        jMenuBar.add(jMenuSetting);
        jMenuBar.add(jMenuHelp);

        if (gameArrayList.size() != 0) {
            for (int i = 0; i < gameArrayList.size(); i++) {
                int index = i + 1;
                jMenuSave.getItem(i).setText("存档" + index + "（" + gameArrayList.get(i).getTotal_count() + "）");
                jMenuLoad.getItem(i).setText("存档" + index + "（" + gameArrayList.get(i).getTotal_count() + "）");
            }
        }

        this.setJMenuBar(jMenuBar);

    }

    private void initJFrame() {
        this.setTitle("拼图小游戏 v1.0");
        this.setAlwaysOnTop(true);
        this.setSize(605, 680);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 65 && !checkWinable(imageList)) {
            this.getContentPane().removeAll();
            ImageIcon imageIcon = new ImageIcon(filepath + "all.jpg");
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(83,134,420,420);
            this.add(jLabel);
            ImageIcon background = new ImageIcon("image/background.png");
            JLabel jLabelBackground = new JLabel(background);
            jLabelBackground.setBounds(40,40,508,560);
            this.getContentPane().add(jLabelBackground);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!checkWinable(imageList)) {
            int action = e.getKeyCode();
            int[] index = getIndex(imageList, total_length);
            switch (action) {
                case 37: {
                    if (index[1] != imageList.length - 1) {
                        int temp = imageList[index[0]][index[1]];
                        imageList[index[0]][index[1]] = imageList[index[0]][index[1] + 1];
                        imageList[index[0]][index[1] + 1] = temp;
                        total_count++;
                        initImage(imageList);
                    }
                    break;
                }
                case 38: {
                    if (index[0] != imageList.length - 1) {
                        int temp = imageList[index[0]][index[1]];
                        imageList[index[0]][index[1]] = imageList[index[0] + 1][index[1]];
                        imageList[index[0] + 1][index[1]] = temp;
                        total_count++;
                        initImage(imageList);
                    }
                    break;
                }
                case 39: {
                    if (index[1] != 0) {
                        int temp = imageList[index[0]][index[1]];
                        imageList[index[0]][index[1]] = imageList[index[0]][index[1] - 1];
                        imageList[index[0]][index[1] - 1] = temp;
                        total_count++;
                        initImage(imageList);
                    }
                    break;
                }
                case 40: {
                    if (index[0] != 0) {
                        int temp = imageList[index[0]][index[1]];
                        imageList[index[0]][index[1]] = imageList[index[0] - 1][index[1]];
                        imageList[index[0] - 1][index[1]] = temp;
                        total_count++;
                        initImage(imageList);
                    }
                    break;
                }
                case 67: {
                    imageList = initData(false);
                    initImage(imageList);
                    break;
                }
                case 65: {
                    initImage(imageList);
                }
            }
        }
    }

    public boolean checkWinable(int[][] imageList) {
        int k = 1;
        for (int i = 0; i < imageList.length; i++) {
            for (int j = 0; j < imageList.length; j++) {
                if (imageList[i][j] != k++) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getIndex(int[][] indexList, int k) {
        int[] result = {-1,-1};
        for (int i = 0; i < indexList[0].length; i++) {
            for (int j = 0; j < indexList[0].length; j++) {
                if (indexList[i][j] == k){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == jMenuItemReset) {
            imageList = initData(true);
            total_count = 0;
            initImage(imageList);
        } else if (o== jMenuItemRelist) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (o== jMenuItemClose) {
            System.exit(0);
        } else if (o== jMenuItemConnection) {
            JDialog jDialog = new JDialog();
            ImageIcon imageIcon = new ImageIcon(account);
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if(o == jMenuItemGirl) {
            int index = 1 + random.nextInt(12);
            filepath = "image/girl/girl" + index + "/";
            total_count = 0;
            imageList = initData(true);
            initImage(imageList);
        } else if(o == jMenuItemAnimal) {
            int index = 1 + random.nextInt(7);
            filepath = "image/animal/animal" + index + "/";
            total_count = 0;
            imageList = initData(true);
            initImage(imageList);
        } else if(o == jMenuItemSport) {
            int index = 1 + random.nextInt(9);
            filepath = "image/sport/sport" + index + "/";
            total_count = 0;
            imageList = initData(true);
            initImage(imageList);
        } else if (o == jMenuItemSave1 || o == jMenuItemSave2 || o == jMenuItemSave3 || o == jMenuItemSave4 || o == jMenuItemSave5) {
            JMenuItem jMenuItem = (JMenuItem) o;
            String text = jMenuItem.getText().substring(2,3);
            System.out.println(text);
            Game game = new Game(imageList, total_count, filepath);
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream("save/save" + text + ".data"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            IoUtil.writeObj(objectOutputStream, true, game);

            jMenuSave.getItem(Integer.parseInt(text)-1).setText("存档" + text + "（" + game.getTotal_count() + "）");
            jMenuLoad.getItem(Integer.parseInt(text)-1).setText("存档" + text + "（" + game.getTotal_count() + "）");
        } else if(o == jMenuItemLoad1 || o == jMenuItemLoad2 || o == jMenuItemLoad3 || o == jMenuItemLoad4 || o == jMenuItemLoad5) {
            JMenuItem jMenuItem = (JMenuItem) o;
            String text = jMenuItem.getText().substring(2,3);
            ObjectInputStream objectInputStream = null;
            File file = new File("save/save" + text + ".data");
            if (file.exists()) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    Game game = (Game) objectInputStream.readObject();
                    filepath = game.getFilepath();
                    total_count = game.getTotal_count();
                    imageList = game.getImageList();
                    initImage(imageList);
                    objectInputStream.close();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
