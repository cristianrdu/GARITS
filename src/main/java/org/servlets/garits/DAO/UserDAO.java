/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.DAO;

import org.servlets.garits.Accounts.Admin;
import org.servlets.garits.Accounts.Foreperson;
import org.servlets.garits.Accounts.Franchisee;
import org.servlets.garits.Accounts.Mechanic;
import org.servlets.garits.Accounts.Receptionist;
import org.servlets.garits.Accounts.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author glaxo
 */
public class UserDAO {

    private Connection connection;

    public UserDAO() throws SQLException, ClassNotFoundException {

//    class.forName("org.postgresql.Driver");
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GARITS", "postgres", "password");

            // System.out.println("Connection made to database!");
            connection.setAutoCommit(false);
            // connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public User logIn(String username, String password) throws SQLException {
        User user = null;

        Statement statement;
        statement = connection.createStatement();

        // Chack if the username is in the database
        ResultSet result = statement.executeQuery("SELECT exists(SELECT username FROM public.Login WHERE username='" + username + "' AND password='" + password + "');");
        result.next();
        boolean u = result.getBoolean("exists");

        // If the username is found and the password is correct check the role of the user
        // Each role will be testend and the output will be a boolean and
        // using the boolean the corresponding user object will be created
        if (u) {
            // Since we there is no

            // Check if the role is administrator
            result = statement.executeQuery("SELECT exists(SELECT staffstaff_no FROM public.admin,public.staff "
                    + "WHERE staff.loginusername='" + username + "' AND staff.staff_no=admin.staffstaff_no);");
            result.next();
            boolean a = result.getBoolean("exists");

            // Check if the role is franchisee
            result = statement.executeQuery("SELECT exists(SELECT staffstaff_no FROM public.franchisee,public.staff "
                    + "WHERE staff.loginusername='" + username + "' AND staff.staff_no=franchisee.staffstaff_no);");
            result.next();
            boolean fr = result.getBoolean("exists");

            // Check if the role is foreperson
            result = statement.executeQuery("SELECT exists(SELECT staffstaff_no FROM public.foreperson,public.staff "
                    + "WHERE staff.loginusername='" + username + "' AND staff.staff_no=foreperson.staffstaff_no);");
            result.next();
            boolean fo = result.getBoolean("exists");

            // Check if the role is receptionist
            result = statement.executeQuery("SELECT exists(SELECT staffstaff_no FROM public.receptionist,public.staff "
                    + "WHERE staff.loginusername='" + username + "' AND staff.staff_no=receptionist.staffstaff_no);");
            result.next();
            boolean r = result.getBoolean("exists");

            // Check if the role is mechanic
            result = statement.executeQuery("SELECT exists(SELECT staffstaff_no FROM public.mechanic,public.staff "
                    + "WHERE staff.loginusername='" + username + "' AND staff.staff_no=mechanic.staffstaff_no);");
            result.next();
            boolean m = result.getBoolean("exists");

            if (a) {
                result = statement.executeQuery("SELECT * FROM public.staff WHERE loginusername='" + username + "';");
                user = new Admin();

            } else if (fr) {

                result = statement.executeQuery("SELECT * FROM public.staff WHERE loginusername='" + username + "';");
                user = new Franchisee();

                System.out.println("franchisee");
            } else if (fo) {
                result = statement.executeQuery("SELECT * FROM public.staff WHERE loginusername='" + username + "';");
                user = new Foreperson();

                System.out.println("foreperson");
            } else if (r) {
                result = statement.executeQuery("SELECT * FROM public.staff WHERE loginusername='" + username + "';");
                user = new Receptionist();

                System.out.println("receptionist");
            } else if (m) {
                result = statement.executeQuery("SELECT * FROM public.staff WHERE loginusername='" + username + "';");
                result.next();
                Mechanic mechanic = new Mechanic();
                mechanic.setAddress(result.getString("address").trim());
                mechanic.setUserName(username);
                mechanic.setEmail(result.getString("email").trim());
                mechanic.setName(result.getString("name").trim());
                mechanic.setPostCode(result.getString("post_code").trim());
                mechanic.setStaffNo(result.getInt("staff_no"));
                mechanic.setTel(result.getString("tel").trim());
                mechanic.setRole(result.getString("role"));

                result = statement.executeQuery("SELECT hourly_rate FROM public.mechanic,public.staff "
                        + "WHERE staff.loginusername='" + username + "' AND mechanic.staffstaff_no=staff.staff_no;");
                result.next();
                mechanic.setHourlyRate(result.getDouble("hourly_rate"));

                System.out.println("mechanic");
                return mechanic;
            }
            result.next();
            user.setAddress(result.getString("address").trim());
            user.setUserName(username);
            user.setEmail(result.getString("email").trim());
            user.setName(result.getString("name").trim());
            user.setPostCode(result.getString("post_code").trim());
            user.setStaffNo(result.getInt("staff_no"));
            user.setTel(result.getString("tel").trim());

        } else {
            return null;
        }
        connection.commit();
        connection.close();
        return user;
    }

    public ArrayList<User> findAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        Statement statement;
        statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM public.staff;");
        while (results.next()) {
            User user = new User();
            if (!results.getString("role").trim().equals("Administrator")) {
                user.setAddress(results.getString("address").trim());
                user.setUserName(results.getString("loginusername").trim());
                user.setEmail(results.getString("email").trim());
                user.setName(results.getString("name").trim());
                user.setPostCode(results.getString("post_code").trim());
                user.setStaffNo(results.getInt("staff_no"));
                user.setTel(results.getString("tel").trim());
                user.setRole(results.getString("role").trim());

                users.add(user);
            }
        }
        connection.commit();

        connection.close();
        return users;
    }

    public void addUser(User newUser, String password) throws SQLException {
        System.out.println("Hello");
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO public.login("
                + "	password, username)"
                + "	VALUES ( ?,?  )");
        statement.setString(1, newUser.getUserName());
        statement.setString(2, password);
        statement.executeUpdate();

        statement = connection.prepareStatement("INSERT INTO public.staff("
                + "staff_no, loginusername, name, address, tel, email, post_code,role)"
                + "VALUES ( (SELECT 1 + MAX(staff_no) FROM public.staff ),  ?,"
                + " ?,  ?,  ?,  ?, ?,?); ");

        statement.setString(1, newUser.getUserName());
        statement.setString(2, newUser.getName());
        statement.setString(3, newUser.getAddress());
        statement.setString(4, newUser.getTel());
        statement.setString(5, newUser.getEmail());
        statement.setString(6, newUser.getPostCode());
        statement.setString(7, newUser.getRole());
        statement.executeUpdate();

        statement = connection.prepareStatement("INSERT INTO public." + newUser.getRole().toLowerCase() + "("
                + "staffstaff_no) VALUES ( (SELECT MAX(staff_no) FROM public.staff )) ");
        statement.execute();

        System.out.println("done!");
        connection.commit();
        connection.close();
    }

    public void EditUserDetails(User user) throws SQLException {
        PreparedStatement statement;

        statement = connection.prepareStatement("UPDATE public.staff "
                + "SET  loginusername=?, name=?, address=?, tel=?, email=?, post_code=?, role=? "
                + "WHERE staff_no=? ;");
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getName());
        statement.setString(3, user.getAddress());
        statement.setString(4, user.getTel());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPostCode());
        statement.setString(7, user.getRole());
        statement.setInt(8, user.getStaffNo());
        statement.execute();
        connection.commit();
        connection.close();
    }

    public void DeleteUser(User user) throws SQLException {
        // Create a prepared statement object
        PreparedStatement statement;

        // Get the role of the user
        Statement s;
        s = connection.createStatement();
        ResultSet result = s.executeQuery("SELECT role,staff_no FROM public.staff WHERE loginusername='" + user.getUserName() + "'");
        result.next();
        // store the role in a temporary variable
        String tmp = "";
        tmp = tmp + result.getString("role");
        int id = result.getInt("staff_no");
        System.out.println(tmp);

        // Check if the user has a role in order to delete it from the respective user table
        if (!tmp.isEmpty()) {
            statement = connection.prepareStatement("DELETE FROM public." + tmp.toLowerCase() + " "
                    + "WHERE staffstaff_no=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        }

        statement = connection.prepareStatement("DELETE FROM public.staff "
                + "WHERE loginusername= ?");
        // statement.execute();

        statement.setString(1, user.getUserName());
        int rowsDeleted = statement.executeUpdate();
        System.out.println(rowsDeleted + " rows deleted!");
        statement.close();

        // Delete From Login
        statement = connection.prepareStatement("DELETE FROM public.login "
                + "WHERE username=?");
        statement.setString(1, user.getUserName());
        statement.executeUpdate();
        statement.close();

        connection.close();
    }

    public Mechanic getMechanic(int staffNo) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet results;
        results = statement.executeQuery("SELECT * FROM public.mechanic WHERE staffstaff_no=" + staffNo + "");
        results.next();
        Mechanic mechanic = new Mechanic();
        mechanic.setHourlyRate(results.getInt("hourly_rate"));
        mechanic.setStaffNo(staffNo);
        results = statement.executeQuery("SELECT * FROM public.staff WHERE staff_no=" + staffNo + "");
        results.next();
        mechanic.setName(results.getString("name"));
        mechanic.setPostCode(results.getString("post_code"));
        mechanic.setEmail(results.getString("email"));
        mechanic.setRole("role");
        mechanic.setTel(results.getString("tel"));
        mechanic.setUserName(results.getString("loginusername"));

        connection.commit();
        connection.close();
        return mechanic;
    }

    public ArrayList<Mechanic> findAllMechanics() throws SQLException {
        ArrayList<Mechanic> m = new ArrayList<Mechanic>();

        Statement statement;
        statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM public.staff WHERE role='Mechanic'");
        while (results.next()) {
            Mechanic user = new Mechanic();
            user.setAddress(results.getString("address").trim());
            user.setUserName(results.getString("loginusername").trim());
            user.setEmail(results.getString("email").trim());
            user.setName(results.getString("name").trim());
            user.setPostCode(results.getString("post_code").trim());
            user.setStaffNo(results.getInt("staff_no"));
            user.setTel(results.getString("tel").trim());
            user.setRole(results.getString("role").trim());
            m.add(user);

        }
        results.close();
        for (Mechanic mech : m) {
            ResultSet r = statement.executeQuery("SELECT hourly_rate FROM public.mechanic WHERE staffstaff_no=" + mech.getStaffNo());
            r.next();
            mech.setHourlyRate(Double.parseDouble(r.getString("hourly_rate").trim()));

        }
        connection.commit();
        connection.close();

        return m;
    }

}
