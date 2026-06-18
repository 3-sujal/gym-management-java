import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class GymGUI extends JFrame {
    private ArrayList<GymMember> members;
    
    // UI Components
    private JTextField idFieldTop, idField, nameField, locationField, phoneField, emailField;
    private JComboBox<String> dobDayBox, dobMonthBox, dobYearBox, startDayBox, startMonthBox, startYearBox, genderBox;
    private JRadioButton regularButton, premiumButton;
    private JTextField referralField, trainerField, paidAmountField, regularPriceField, premiumChargeField;
    private JComboBox<String> planBox;

    public GymGUI() {
        members = new ArrayList<>();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Gym Management System");
        setSize(700, 950);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Title
        JLabel title = new JLabel("Gym Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(0, 10, 700, 30);
        add(title);

        // Top Section
        JLabel idLabelTop = new JLabel("ID:");
        idLabelTop.setBounds(50, 50, 30, 25);
        add(idLabelTop);

        idFieldTop = new JTextField();
        idFieldTop.setBounds(90, 50, 100, 25);
        add(idFieldTop);

        JButton attendanceButton = new JButton("Mark Attendance");
        attendanceButton.setBounds(200, 50, 150, 25);
        attendanceButton.addActionListener(e -> markAttendance());
        styleButton(attendanceButton);
        add(attendanceButton);

        JButton displayButton = new JButton("Display");
        displayButton.setBounds(360, 50, 100, 25);
        displayButton.addActionListener(e -> displayMembers());
        styleButton(displayButton);
        add(displayButton);

        JButton saveButton = new JButton("Save to File");
        saveButton.setBounds(470, 50, 100, 25);
        saveButton.addActionListener(e -> saveToFile());
        styleButton(saveButton);
        add(saveButton);

        JButton loadButton = new JButton("Load from File");
        loadButton.setBounds(580, 50, 120, 25);
        loadButton.addActionListener(e -> readFromFile());
        styleButton(loadButton);
        add(loadButton);

        JButton deactivateButton = new JButton("Deactivate");
        deactivateButton.setBounds(150, 90, 150, 25);
        deactivateButton.addActionListener(e -> deactivateMembership());
        styleButton(deactivateButton);
        add(deactivateButton);

        // Add Member Section
        JLabel addLabel = new JLabel("Add Member");
        addLabel.setFont(new Font("Arial", Font.BOLD, 16));
        addLabel.setBounds(300, 130, 100, 25);
        add(addLabel);

        // Member Details
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 170, 30, 25);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(90, 170, 150, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 210, 50, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(110, 210, 150, 25);
        add(nameField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(270, 210, 50, 25);
        add(genderLabel);
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setBounds(330, 210, 100, 25);
        add(genderBox);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 250, 60, 25);
        add(locationLabel);
        locationField = new JTextField();
        locationField.setBounds(120, 250, 200, 25);
        add(locationField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 290, 50, 25);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(110, 290, 150, 25);
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 330, 50, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(110, 330, 250, 25);
        add(emailField);

        // Date Fields
        addDateFields();
        
        // Membership Panel
        JPanel membershipPanel = new JPanel();
        membershipPanel.setLayout(null);
        membershipPanel.setBounds(30, 450, 630, 200);
        membershipPanel.setBorder(BorderFactory.createTitledBorder("Membership Details"));
        add(membershipPanel);

        // Membership Type
        JLabel typeLabel = new JLabel("Membership Type:");
        typeLabel.setBounds(10, 20, 120, 25);
        membershipPanel.add(typeLabel);

        regularButton = new JRadioButton("Regular", true);
        premiumButton = new JRadioButton("Premium");
        regularButton.setBounds(130, 20, 80, 25);
        premiumButton.setBounds(220, 20, 80, 25);
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(regularButton);
        typeGroup.add(premiumButton);
        membershipPanel.add(regularButton);
        membershipPanel.add(premiumButton);

        // Regular Member Fields
        JLabel planLabel = new JLabel("Plan:");
        planLabel.setBounds(10, 55, 80, 25);
        membershipPanel.add(planLabel);
        planBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        planBox.setBounds(10, 80, 150, 25);
        membershipPanel.add(planBox);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 115, 80, 25);
        membershipPanel.add(priceLabel);
        regularPriceField = new JTextField("6500");
        regularPriceField.setBounds(10, 140, 150, 25);
        membershipPanel.add(regularPriceField);

        JLabel referralLabel = new JLabel("Referral:");
        referralLabel.setBounds(180, 55, 150, 25);
        membershipPanel.add(referralLabel);
        referralField = new JTextField();
        referralField.setBounds(180, 80, 150, 25);
        membershipPanel.add(referralField);

        // Premium Member Fields
        JLabel paidLabel = new JLabel("Paid Amount:");
        paidLabel.setBounds(350, 55, 100, 25);
        membershipPanel.add(paidLabel);
        paidAmountField = new JTextField();
        paidAmountField.setBounds(350, 80, 150, 25);
        membershipPanel.add(paidAmountField);

        JLabel chargeLabel = new JLabel("Premium Charge:");
        chargeLabel.setBounds(350, 115, 120, 25);
        membershipPanel.add(chargeLabel);
        premiumChargeField = new JTextField("50000");
        premiumChargeField.setBounds(350, 140, 150, 25);
        membershipPanel.add(premiumChargeField);

        JLabel trainerLabel = new JLabel("Trainer:");
        trainerLabel.setBounds(520, 55, 120, 25);
        membershipPanel.add(trainerLabel);
        trainerField = new JTextField();
        trainerField.setBounds(520, 80, 100, 25);
        membershipPanel.add(trainerField);

        // Membership Type Listeners
        regularButton.addActionListener(e -> updateMembershipFields());
        premiumButton.addActionListener(e -> updateMembershipFields());

        // Action Buttons
        JButton addRegularButton = new JButton("Add Regular");
        addRegularButton.setBounds(50, 670, 150, 30);
        addRegularButton.addActionListener(e -> addRegularMember());
        styleButton(addRegularButton);
        add(addRegularButton);

        JButton addPremiumButton = new JButton("Add Premium");
        addPremiumButton.setBounds(210, 670, 150, 30);
        addPremiumButton.addActionListener(e -> addPremiumMember());
        styleButton(addPremiumButton);
        add(addPremiumButton);

        JButton activateButton = new JButton("Activate");
        activateButton.setBounds(370, 670, 150, 30);
        activateButton.addActionListener(e -> activateMembership());
        styleButton(activateButton);
        add(activateButton);

        JButton revertRegularButton = new JButton("Revert Regular");
        revertRegularButton.setBounds(50, 710, 150, 30);
        revertRegularButton.addActionListener(e -> revertRegularMember());
        styleButton(revertRegularButton);
        add(revertRegularButton);

        JButton revertPremiumButton = new JButton("Revert Premium");
        revertPremiumButton.setBounds(210, 710, 150, 30);
        revertPremiumButton.addActionListener(e -> revertPremiumMember());
        styleButton(revertPremiumButton);
        add(revertPremiumButton);

        JButton upgradePlanButton = new JButton("Upgrade Plan");
        upgradePlanButton.setBounds(370, 710, 150, 30);
        upgradePlanButton.addActionListener(e -> upgradePlan());
        styleButton(upgradePlanButton);
        add(upgradePlanButton);

        updateMembershipFields();
    }

    private void addDateFields() {
        // Date of Birth
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 370, 100, 25);
        add(dobLabel);
        
        dobDayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) dobDayBox.addItem(String.format("%02d", i));
        dobDayBox.setBounds(160, 370, 50, 25);
        add(dobDayBox);
        
        dobMonthBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) dobMonthBox.addItem(String.format("%02d", i));
        dobMonthBox.setBounds(220, 370, 50, 25);
        add(dobMonthBox);
        
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        dobYearBox = new JComboBox<>();
        for (int i = 1900; i <= currentYear; i++) dobYearBox.addItem(String.valueOf(i));
        dobYearBox.setBounds(280, 370, 70, 25);
        add(dobYearBox);

        // Membership Start Date
        JLabel startLabel = new JLabel("Start Date:");
        startLabel.setBounds(50, 410, 100, 25);
        add(startLabel);
        
        startDayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) startDayBox.addItem(String.format("%02d", i));
        startDayBox.setBounds(160, 410, 50, 25);
        add(startDayBox);
        
        startMonthBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) startMonthBox.addItem(String.format("%02d", i));
        startMonthBox.setBounds(220, 410, 50, 25);
        add(startMonthBox);
        
        startYearBox = new JComboBox<>();
        startYearBox.addItem(String.valueOf(currentYear));
        startYearBox.setBounds(280, 410, 70, 25);
        add(startYearBox);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private void updateMembershipFields() {
        boolean isRegular = regularButton.isSelected();
        planBox.setEnabled(isRegular);
        regularPriceField.setEnabled(isRegular);
        referralField.setEnabled(isRegular);
        paidAmountField.setEnabled(!isRegular);
        premiumChargeField.setEnabled(!isRegular);
        trainerField.setEnabled(!isRegular);
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("members.txt"))) {
            writer.println("ID,Name,Location,Phone,Email,Gender,DOB,StartDate,Type,Plan,Price,Attendance,Points,Active");
            
            for (GymMember member : members) {
                String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s",
                    member.get_id(),
                    member.get_name(),
                    member.get_location(),
                    member.get_phone(),
                    member.get_email(),
                    member.get_gender(),
                    member.get_DOB(),
                    member.get_membershipStartDate(),
                    member instanceof RegularMember ? "Regular" : "Premium");
                
                if (member instanceof RegularMember) {
                    RegularMember rm = (RegularMember) member;
                    line += String.format(",%s,%.2f,%d,%.2f,%b",
                        rm.getPlan(),
                        rm.getPrice(),
                        rm.get_attendance(),
                        rm.get_loyaltyPoints(),
                        rm.get_activeStatus());
                } else {
                    PremiumMember pm = (PremiumMember) member;
                    line += String.format(",Premium,%.2f,%d,%.2f,%b",
                        pm.getPremiumCharge(),
                        pm.get_attendance(),
                        pm.get_loyaltyPoints(),
                        pm.get_activeStatus());
                }
                
                writer.println(line);
            }
            
            JOptionPane.showMessageDialog(this, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("members.txt"))) {
            members.clear();
            reader.readLine(); // Skip header
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // Parse and create members
                // [Implementation depends on your file structure]
            }
            
            JOptionPane.showMessageDialog(this, "Data loaded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String dob = dobDayBox.getSelectedItem() + "-" + dobMonthBox.getSelectedItem() + "-" + dobYearBox.getSelectedItem();
            String startDate = startDayBox.getSelectedItem() + "-" + startMonthBox.getSelectedItem() + "-" + startYearBox.getSelectedItem();
            String referral = referralField.getText();
            String plan = (String) planBox.getSelectedItem();
            double price = Double.parseDouble(regularPriceField.getText());

            RegularMember member = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referral, plan, price);
            member.activateMembership();
            members.add(member);
            
            JOptionPane.showMessageDialog(this, "Regular member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String dob = dobDayBox.getSelectedItem() + "-" + dobMonthBox.getSelectedItem() + "-" + dobYearBox.getSelectedItem();
            String startDate = startDayBox.getSelectedItem() + "-" + startMonthBox.getSelectedItem() + "-" + startYearBox.getSelectedItem();
            String trainer = trainerField.getText();
            double paid = paidAmountField.getText().isEmpty() ? 0 : Double.parseDouble(paidAmountField.getText());
            double charge = Double.parseDouble(premiumChargeField.getText());

            PremiumMember member = new PremiumMember(id, name, location, phone, email, gender, dob, startDate, trainer);
            member.activateMembership();
            if (paid > 0) member.payDueAmount(paid);
            members.add(member);
            
            JOptionPane.showMessageDialog(this, "Premium member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void activateMembership() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            for (GymMember member : members) {
                if (member.get_id() == id) {
                    member.activateMembership();
                    JOptionPane.showMessageDialog(this, "Membership activated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deactivateMembership() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            for (GymMember member : members) {
                if (member.get_id() == id) {
                    member.deactivateMembership();
                    JOptionPane.showMessageDialog(this, "Membership deactivated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAttendance() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            for (GymMember member : members) {
                if (member.get_id() == id) {
                    if (member.get_activeStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(this, "Attendance marked", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Member is inactive", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void revertRegularMember() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            String reason = JOptionPane.showInputDialog(this, "Enter removal reason:");
            if (reason == null || reason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Reason is required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            for (GymMember member : members) {
                if (member.get_id() == id && member instanceof RegularMember) {
                    ((RegularMember) member).revertRegularMember(reason);
                    JOptionPane.showMessageDialog(this, "Member reverted", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Regular member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void revertPremiumMember() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            for (GymMember member : members) {
                if (member.get_id() == id && member instanceof PremiumMember) {
                    ((PremiumMember) member).revertPremiumMember();
                    JOptionPane.showMessageDialog(this, "Member reverted", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Premium member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void upgradePlan() {
        try {
            int id = Integer.parseInt(idFieldTop.getText());
            for (GymMember member : members) {
                if (member.get_id() == id && member instanceof RegularMember) {
                    RegularMember regularMember = (RegularMember) member;
                    
                    String[] plans = {"Basic", "Standard", "Deluxe"};
                    String selectedPlan = (String) JOptionPane.showInputDialog(
                        this,
                        "Select new plan:",
                        "Upgrade Plan",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        plans,
                        plans[0]
                    );
                    
                    if (selectedPlan != null) {
                        String result = regularMember.upgradePlan(selectedPlan);
                        JOptionPane.showMessageDialog(this, result, "Upgrade Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Regular member not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayMembers() {
        JFrame displayFrame = new JFrame("Member List");
        displayFrame.setSize(800, 600);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        if (members.isEmpty()) {
            textArea.setText("No members to display");
        } else {
            for (GymMember member : members) {
                textArea.append(member.toString() + "\n\n");
            }
        }
        
        displayFrame.add(scrollPane);
        displayFrame.setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        genderBox.setSelectedIndex(0);
        dobDayBox.setSelectedIndex(0);
        dobMonthBox.setSelectedIndex(0);
        dobYearBox.setSelectedIndex(dobYearBox.getItemCount() - 1);
        startDayBox.setSelectedIndex(0);
        startMonthBox.setSelectedIndex(0);
        startYearBox.setSelectedIndex(0);
        referralField.setText("");
        trainerField.setText("");
        planBox.setSelectedIndex(0);
        paidAmountField.setText("");
        regularPriceField.setText("6500");
        premiumChargeField.setText("50000");
        regularButton.setSelected(true);
        updateMembershipFields();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GymGUI gui = new GymGUI();
            gui.setVisible(true);
        });
    }
}