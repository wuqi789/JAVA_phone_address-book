import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//数据库基本操作
public class DBOperter {
    //查询所有信息
    public ArrayList<Contact> getAll(){
        //存放遍历的结果
        ArrayList<Contact> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建查询的sql语句
            String sql = "select * from t_phone";
            ps = conn.prepareStatement(sql);
            //3 执行查询，返回结果
            rs = ps.executeQuery();
            //4 遍历集合
            while (rs.next()) {
                //根据查询结果创建对象
                Contact contact = new Contact(rs.getInt("id"),rs.getString("name"),rs.getString("phone"));
                //将对象添加到list中
                list.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return list;
    }
    //添加信息
    public int insert(Contact contact){
        Connection conn = null;
        PreparedStatement ps = null;
        int row;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建添加的sql语句
            String sql = "insert into t_phone(name,phone) values(?,?)";
            ps = conn.prepareStatement(sql);
            //3 设置问号的值
            ps.setString(1,contact.getName());
            ps.setString(2,contact.getPhone());
            //4 执行添加，返回结果
            row = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return row;
    }
    //根据姓名修改电话
    public int update(Contact contact){
        Connection conn = null;
        PreparedStatement ps = null;
        int row;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建添加的sql语句
            String sql = "update t_phone set phone=? where name=?";
            ps = conn.prepareStatement(sql);
            //3 设置问号的值
            ps.setString(1,contact.getPhone());
            ps.setString(2,contact.getName());
            //4 执行添加，返回结果
            row = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return row;
    }
    //根据姓名查询电话号码
    public String getPhoneByName(String name){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String phone = null;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建查询的sql语句
            String sql = "select * from t_phone where name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            //3 执行查询，返回结果
            rs = ps.executeQuery();
            //4 遍历集合
            if (rs.next()) {
                phone = rs.getString("phone");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return phone;
    }
    //根据姓名删除信息
    public int delete(String name){

        Connection conn = null;
        PreparedStatement ps = null;
        int row;
        try {
            //1 获取数据库连接
            conn = DBUtil.getConnection();
            //2 创建查询的sql语句
            String sql = "delete from t_phone where name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            //3 执行查询，返回结果
            row = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeSt(ps);
            DBUtil.closeConn(conn);
        }
        return row;
    }
}
