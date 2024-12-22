

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MyFrame {
    private JFrame frame = new JFrame("通讯录");
    private JTextArea jta = new JTextArea(15,30);
    private JScrollPane jsp = new JScrollPane(jta);
    private JLabel nameLabel = new JLabel("姓名：");
    private JTextField nameField = new JTextField(15);
    private JLabel phoneLabel = new JLabel("电话：");
    private JTextField phoneField = new JTextField(15);
    private JButton addBtn = new JButton("添加");
    private JButton deleteBtn = new JButton("删除");
    private JButton updateBtn = new JButton("修改");
    private JButton queryBtn = new JButton("查询");
    //创建数据库操作的对象
    private DBOperter db = new DBOperter();
    public MyFrame(){
        //设置文本域不可编辑
        jta.setEditable(false);
        //获取数据
        getData();
        //给添加按钮添加监听事件
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameField.getText().isEmpty()|| phoneField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"警告","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //调用添加方法
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                //创建Contact对象
                Contact contact = new Contact(name,phone);
                int row = db.insert(contact);
                if (row >0){
                    JOptionPane.showMessageDialog(frame,"添加成功");
                }
                //清空输入框
                nameField.setText("");
                phoneField.setText("");
                //再次调用获取数据的方法
                getData();
            }
        });
        //修改信息的方法
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameField.getText().isEmpty()|| phoneField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"警告","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //调用修改方法
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                //创建Contact对象
                Contact contact = new Contact(name,phone);
                int row = db.update(contact);
                if (row >0){
                    JOptionPane.showMessageDialog(frame,"修改成功");
                }
                //清空输入框
                nameField.setText("");
                phoneField.setText("");
                //再次调用获取数据的方法
                getData();
            }
        });
        //给查询按钮添加监听事件
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"警告","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //调用根据姓名查询电话的方法
                String phone = db.getPhoneByName(nameField.getText().trim());
                //将查询的信息显示到电话输入框上
                if (phone != null){
                    phoneField.setText(phone);
                }
            }
        });
        //给删除按钮添加监听事件
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"警告","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //调用删除信息的方法
                int row = db.delete(nameField.getText().trim());
                if (row >0){
                    JOptionPane.showMessageDialog(frame,"删除成功");
                }
                nameField.setText("");
                getData();

            }
        });
        Box nBox = Box.createHorizontalBox();
        nBox.add(nameLabel);
        nBox.add(Box.createHorizontalStrut(15));
        nBox.add(nameField);

        Box pBox = Box.createHorizontalBox();
        pBox.add(phoneLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(phoneField);

        Box all = Box.createVerticalBox();
        all.add(nBox);
        all.add(Box.createVerticalStrut(15));
        all.add(pBox);

        JPanel panel =  new JPanel();
        panel.add(all);

        frame.add(panel,BorderLayout.NORTH);
        JPanel tPanel = new JPanel();
        tPanel.add(jsp);
        frame.add(tPanel,BorderLayout.CENTER);

        Box btnBox = Box.createHorizontalBox();
        btnBox.add(addBtn);
        btnBox.add(updateBtn);
        btnBox.add(deleteBtn);
        btnBox.add(queryBtn);
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnBox);
        frame.add(btnPanel,BorderLayout.SOUTH);

        frame.setSize(350,430);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //获取数据的方法
    private void getData(){
        //清空文本域
        jta.setText("");
        //获取数据
        ArrayList<Contact> list = db.getAll();
        //遍历集合将数据显示到文本域上
        for (Contact contact : list) {
            jta.append(contact.getName() + "\t" + contact.getPhone() + "\n");
        }
    }



}
