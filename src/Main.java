import javax.swing.JOptionPane;
//ChatGPT. (2025). Java registration and messaging system with unit tests [Computer software]. OpenAI. https://openai.com/chatgpt

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        MessagingSystem messaging = new MessagingSystem();

        // Registration
        String username = JOptionPane.showInputDialog("Enter username (must include _ and be <= 5 chars):");
        String password = JOptionPane.showInputDialog("Enter password (must include capital, number, special char):");
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String phone = JOptionPane.showInputDialog("Enter SA phone number (e.g., +27831234567):");

        // Validate inputs
        if (username == null || password == null || firstName == null || lastName == null || phone == null) {
            JOptionPane.showMessageDialog(null, "Registration cancelled or incomplete");
            return;
        }

        String regMsg = login.registerUser(username, password, firstName, lastName, phone);
        JOptionPane.showMessageDialog(null, regMsg);

        if (regMsg == null || !regMsg.contains("successfully")) {
            return;
        }

        // Login
        String loginUser = JOptionPane.showInputDialog("Enter username to login:");
        String loginPass = JOptionPane.showInputDialog("Enter password to login:");
        String loginStatus = login.returnLoginStatus(loginUser, loginPass);
        JOptionPane.showMessageDialog(null, loginStatus);

        if (!login.loginUser(loginUser, loginPass)) {
            return;
        }

        // Main Menu
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        boolean running = true;

        while (running) {
            String menu = JOptionPane.showInputDialog(
                "Choose an option:\n1) Send Messages\n2) Show Results\n3) Exit"
            );
            
            if (menu == null) { // Handle cancel button
                running = false;
                continue;
            }

            switch (menu) {
                case "1":
                    messaging.sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;
                case "3":
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }
}