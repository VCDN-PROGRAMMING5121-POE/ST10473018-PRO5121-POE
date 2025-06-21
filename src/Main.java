import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        MessagingSystem messaging = new MessagingSystem();

        // Registration
        String username = JOptionPane.showInputDialog("Enter username (must include _ and be ≤ 5 characters):");
        String password = JOptionPane.showInputDialog("Enter password (must include capital, number, special char, min 8 chars):");
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String phone = JOptionPane.showInputDialog("Enter SA phone number (e.g., +27831234567):");

        String regMsg = login.registerUser(username, password, firstName, lastName, phone);
        JOptionPane.showMessageDialog(null, regMsg);

        if (!regMsg.contains("successfully")) return;

        // Login
        String loginUser = JOptionPane.showInputDialog("Enter username to login:");
        String loginPass = JOptionPane.showInputDialog("Enter password to login:");
        JOptionPane.showMessageDialog(null, login.returnLoginStatus(loginUser, loginPass));

        if (!login.loginUser(loginUser, loginPass)) return;

        // Menu
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        boolean running = true;
        while (running) {
            String menu = JOptionPane.showInputDialog("Choose an option:\n1) Send Messages\n2) Show Recently Sent Messages\n3) Quit");
            switch (menu) {
                case "1" -> messaging.sendMessages();
                case "2" -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                case "3" -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat.");
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }
}