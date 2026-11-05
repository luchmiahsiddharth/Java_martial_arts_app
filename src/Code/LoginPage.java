/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Code;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

/**
 *
 * @author sidlu
 */
public class LoginPage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginPage.class.getName());

    private static final String USERS_FILE = "Users.txt";

    private static final Gson GSON = new Gson();

    // Declared as User, but holds a subclass such as Instructor (polymorphism).
    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }
    
    public LoginPage() {
        initComponents();
        setSize(377,610);
        getContentPane().setBackground(new java.awt.Color(40,40,45));        
        setLocationRelativeTo(null);      
        getRootPane().setDefaultButton(enterbtn);
    }

    private void clearFields() {
        usernametxtfld.setText("");
        passwordtxtfld.setText("");
        usernametxtfld.requestFocusInWindow();
    }
    
    public static boolean login(String username, String password) throws IOException {
        currentUser = null;

        try (Reader reader = new InputStreamReader(new FileInputStream(USERS_FILE), StandardCharsets.UTF_8)) {
            // The file holds a JSON array: [ { ...user... }, { ...user... } ]
            JsonArray records = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : records) {
                JsonObject record = element.getAsJsonObject();

                if (record.get("username").getAsString().equals(username)) {
                    // Usernames are unique, so this is the only record that can match.
                    if (record.get("loginPassword").getAsString().equals(password)) {
                        currentUser = createUser(record);
                        return true;
                    }
                    return false; // right username, wrong password
                }
            }
        }
        return false; // username not found
    }    
    /**
     * Builds the right kind of User from a record, based on its "role" field.
     * Gson cannot create an abstract User directly, so we choose the concrete class first.
     */
    private static User createUser(JsonObject record) {
        String role = record.get("role").getAsString();

        switch (role) {
            case "Instructor":
                return GSON.fromJson(record, Instructor.class);
            //case "Student":
            //    return GSON.fromJson(record, Student.class);
            // case "Manager":
            //     return GSON.fromJson(record, Manager.class);
            // case "Administrator":
            //     return GSON.fromJson(record, Administrator.class);
            // case "Attendance_officer":
            //     return GSON.fromJson(record, Attendance_officer.class);
            // case "Facilities_officer":
            //     return GSON.fromJson(record, Facilities_officer.class);

                  
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    
    private void attemptLogin() {
        String username = usernametxtfld.getText().trim();
        String password = new String(passwordtxtfld.getPassword());

        try {
            if (login(username, password)) {
                dispose(); // close the login window
                // The current user is declared as a User, but at runtime it is e.g. an
                // Instructor, so this runs Instructor's version of dashboard().
                JOptionPane.showMessageDialog(null, "You have succesfully logged in!");
                
                javax.swing.SwingUtilities.invokeLater(() -> {
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        window.dispose();
                    }
                });
                
                
                LoginPage.getCurrentUser().dashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.",
                        "Login failed", JOptionPane.ERROR_MESSAGE);
                passwordtxtfld.setText("");
                passwordtxtfld.requestFocusInWindow();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not read the users file:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logintitlelbl = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();
        passwordlbl = new javax.swing.JLabel();
        usernametxtfld = new javax.swing.JTextField();
        passwordtxtfld = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        loginiconlbl = new javax.swing.JLabel();
        clearbtn = new javax.swing.JButton();
        enterbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 2, 0));
        setSize(new java.awt.Dimension(377, 610));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logintitlelbl.setFont(new java.awt.Font("Segoe UI Semibold", 3, 48)); // NOI18N
        logintitlelbl.setForeground(new java.awt.Color(255, 255, 255));
        logintitlelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logintitlelbl.setText("Login");
        getContentPane().add(logintitlelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, 80));

        usernamelbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        usernamelbl.setForeground(new java.awt.Color(255, 255, 255));
        usernamelbl.setText("Username");
        getContentPane().add(usernamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        passwordlbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        passwordlbl.setForeground(new java.awt.Color(255, 255, 255));
        passwordlbl.setText("Password");
        getContentPane().add(passwordlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        usernametxtfld.addActionListener(this::usernametxtfldActionPerformed);
        getContentPane().add(usernametxtfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 330, 30));
        getContentPane().add(passwordtxtfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 330, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("\"cool logo here\"");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 310, 40));

        loginiconlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Monochrome Deep Blue Field.png"))); // NOI18N
        getContentPane().add(loginiconlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 340, 80));

        clearbtn.setText("Clear");
        clearbtn.addActionListener(this::clearbtnActionPerformed);
        getContentPane().add(clearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        enterbtn.setText("Enter");
        enterbtn.addActionListener(this::enterbtnActionPerformed);
        getContentPane().add(enterbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernametxtfldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernametxtfldActionPerformed
        passwordtxtfld.requestFocusInWindow();
    }//GEN-LAST:event_usernametxtfldActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
        clearFields();
    }//GEN-LAST:event_clearbtnActionPerformed

    private void enterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterbtnActionPerformed
        attemptLogin();
    }//GEN-LAST:event_enterbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton enterbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel loginiconlbl;
    private javax.swing.JLabel logintitlelbl;
    private javax.swing.JLabel passwordlbl;
    private javax.swing.JPasswordField passwordtxtfld;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JTextField usernametxtfld;
    // End of variables declaration//GEN-END:variables
}
