import java.awt.*;
import javax.swing.*;

public class HotelUI {
    private JFrame frame;

    public HotelUI() {
        frame = new JFrame("Hotel Management UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton featuresBtn = new JButton("Display Room Features");
        JButton availBtn = new JButton("Display Availability");
        JButton bookBtn = new JButton("Book Room");
        JButton orderBtn = new JButton("Order Food");
        JButton checkoutBtn = new JButton("Checkout");
        JButton exitBtn = new JButton("Save & Exit");

        c.gridx = 0; c.gridy = 0; frame.add(featuresBtn, c);
        c.gridx = 1; c.gridy = 0; frame.add(availBtn, c);
        c.gridx = 0; c.gridy = 1; frame.add(bookBtn, c);
        c.gridx = 1; c.gridy = 1; frame.add(orderBtn, c);
        c.gridx = 0; c.gridy = 2; frame.add(checkoutBtn, c);
        c.gridx = 1; c.gridy = 2; frame.add(exitBtn, c);

        featuresBtn.addActionListener(e -> showFeatures());
        availBtn.addActionListener(e -> showAvailability());
        bookBtn.addActionListener(e -> bookRoomDialog());
        orderBtn.addActionListener(e -> orderFoodDialog());
        checkoutBtn.addActionListener(e -> checkoutDialog());
        exitBtn.addActionListener(e -> exitAndSave());

        frame.setVisible(true);
    }

    private void showFeatures() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Luxury Double Room\nNumber of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000\n\n");
        sb.append("2. Deluxe Double Room\nNumber of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000\n\n");
        sb.append("3. Luxury Single Room\nNumber of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2200\n\n");
        sb.append("4. Deluxe Single Room\nNumber of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200\n\n");
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(ta), "Room Features", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAvailability() {
        StringBuilder sb = new StringBuilder();
        int count;
        // 1
        count = 0;
        for(int j=0;j<Hotel.hotel_ob.luxury_doublerrom.length;j++) if(Hotel.hotel_ob.luxury_doublerrom[j]==null) count++;
        sb.append("Luxury Double rooms available: " + count + "\n");
        // 2
        count = 0;
        for(int j=0;j<Hotel.hotel_ob.deluxe_doublerrom.length;j++) if(Hotel.hotel_ob.deluxe_doublerrom[j]==null) count++;
        sb.append("Deluxe Double rooms available: " + count + "\n");
        // 3
        count = 0;
        for(int j=0;j<Hotel.hotel_ob.luxury_singleerrom.length;j++) if(Hotel.hotel_ob.luxury_singleerrom[j]==null) count++;
        sb.append("Luxury Single rooms available: " + count + "\n");
        // 4
        count = 0;
        for(int j=0;j<Hotel.hotel_ob.deluxe_singleerrom.length;j++) if(Hotel.hotel_ob.deluxe_singleerrom[j]==null) count++;
        sb.append("Deluxe Single rooms available: " + count + "\n");

        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(ta), "Availability", JOptionPane.INFORMATION_MESSAGE);
    }

    private void bookRoomDialog() {
        String[] options = {"Luxury Double (1-10)", "Deluxe Double (11-30)", "Luxury Single (31-40)", "Deluxe Single (41-60)"};
        String choice = (String) JOptionPane.showInputDialog(frame, "Choose room type:", "Book Room",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(choice == null) return;

        int type = 0;
        int[] availableIndices = null;
        switch(choice) {
            case "Luxury Double (1-10)":
                type = 1;
                availableIndices = getAvailableIndexes(Hotel.hotel_ob.luxury_doublerrom);
                break;
            case "Deluxe Double (11-30)":
                type = 2;
                availableIndices = getAvailableIndexes(Hotel.hotel_ob.deluxe_doublerrom);
                break;
            case "Luxury Single (31-40)":
                type = 3;
                availableIndices = getAvailableIndexes(Hotel.hotel_ob.luxury_singleerrom);
                break;
            case "Deluxe Single (41-60)":
                type = 4;
                availableIndices = getAvailableIndexes(Hotel.hotel_ob.deluxe_singleerrom);
                break;
        }

        if(availableIndices == null || availableIndices.length==0) {
            JOptionPane.showMessageDialog(frame, "No rooms available for this type.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Build room number list for user (human-facing numbers)
        String[] roomNumbers = new String[availableIndices.length];
        for(int i=0;i<availableIndices.length;i++){
            int rn;
            if(type==1) rn = availableIndices[i]+1;
            else if(type==2) rn = availableIndices[i]+11;
            else if(type==3) rn = availableIndices[i]+31;
            else rn = availableIndices[i]+41;
            roomNumbers[i] = String.valueOf(rn);
        }

        String rnStr = (String) JOptionPane.showInputDialog(frame, "Select room number:", "Rooms",
                JOptionPane.QUESTION_MESSAGE, null, roomNumbers, roomNumbers[0]);
        if(rnStr==null) return;
        int chosenRoomNo = Integer.parseInt(rnStr);

        // Collect customer details
        JPanel p = new JPanel(new GridLayout(0,2,5,5));
        p.add(new JLabel("Primary Name:")); JTextField nameField = new JTextField(); p.add(nameField);
        p.add(new JLabel("Primary Contact:")); JTextField contactField = new JTextField(); p.add(contactField);
        p.add(new JLabel("Primary Gender:")); JTextField genderField = new JTextField(); p.add(genderField);

        JTextField name2Field = new JTextField();
        JTextField contact2Field = new JTextField();
        JTextField gender2Field = new JTextField();

        if(type<=2) { // double rooms -> ask second customer
            p.add(new JLabel("Second Name:")); p.add(name2Field);
            p.add(new JLabel("Second Contact:")); p.add(contact2Field);
            p.add(new JLabel("Second Gender:")); p.add(gender2Field);
        }

        int res = JOptionPane.showConfirmDialog(frame, p, "Enter Customer Details", JOptionPane.OK_CANCEL_OPTION);
        if(res != JOptionPane.OK_OPTION) return;

        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String gender = genderField.getText().trim();

        try {
            int idx;
            if(type==1) {
                idx = chosenRoomNo - 1;
                Hotel.hotel_ob.luxury_doublerrom[idx] = new Doubleroom(name, contact, gender,
                        name2Field.getText().trim(), contact2Field.getText().trim(), gender2Field.getText().trim());
            } else if(type==2) {
                idx = chosenRoomNo - 11;
                Hotel.hotel_ob.deluxe_doublerrom[idx] = new Doubleroom(name, contact, gender,
                        name2Field.getText().trim(), contact2Field.getText().trim(), gender2Field.getText().trim());
            } else if(type==3) {
                idx = chosenRoomNo - 31;
                Hotel.hotel_ob.luxury_singleerrom[idx] = new Singleroom(name, contact, gender);
            } else {
                idx = chosenRoomNo - 41;
                Hotel.hotel_ob.deluxe_singleerrom[idx] = new Singleroom(name, contact, gender);
            }
            JOptionPane.showMessageDialog(frame, "Room booked successfully: " + chosenRoomNo, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error booking room: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int[] getAvailableIndexes(Object[] arr) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]==null) list.add(i);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void orderFoodDialog() {
        String rnStr = JOptionPane.showInputDialog(frame, "Enter Room Number (1-60):");
        if(rnStr==null) return;
        int rn;
        try {
            rn = Integer.parseInt(rnStr);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        int type = getTypeFromRoomNumber(rn);
        if(type== -1) { JOptionPane.showMessageDialog(frame, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE); return; }

        int idx = getIndexFromRoomNumber(rn);
        // Check if room is booked
        boolean booked = false;
        switch(type) {
            case 1: booked = (Hotel.hotel_ob.luxury_doublerrom[idx]!=null); break;
            case 2: booked = (Hotel.hotel_ob.deluxe_doublerrom[idx]!=null); break;
            case 3: booked = (Hotel.hotel_ob.luxury_singleerrom[idx]!=null); break;
            case 4: booked = (Hotel.hotel_ob.deluxe_singleerrom[idx]!=null); break;
        }
        if(!booked) { JOptionPane.showMessageDialog(frame, "Room not booked", "Info", JOptionPane.INFORMATION_MESSAGE); return; }

        // Menu loop simple: pick item and qty repeatedly
        String[] items = {"1. Sandwich - Rs50", "2. Pasta - Rs60", "3. Noodles - Rs70", "4. Coke - Rs30"};
        while(true) {
            String sel = (String) JOptionPane.showInputDialog(frame, "Menu:\n(Select item or Cancel to finish)", "Order",
                    JOptionPane.QUESTION_MESSAGE, null, items, items[0]);
            if(sel==null) break;
            int itemno = Integer.parseInt(sel.substring(0,1));
            String qStr = JOptionPane.showInputDialog(frame, "Quantity:");
            if(qStr==null) break;
            int q;
            try { q = Integer.parseInt(qStr); } catch(Exception ex) { JOptionPane.showMessageDialog(frame, "Invalid qty"); break; }
            Food f = new Food(itemno, q);
            switch(type){
                case 1: Hotel.hotel_ob.luxury_doublerrom[idx].food.add(f); break;
                case 2: Hotel.hotel_ob.deluxe_doublerrom[idx].food.add(f); break;
                case 3: Hotel.hotel_ob.luxury_singleerrom[idx].food.add(f); break;
                case 4: Hotel.hotel_ob.deluxe_singleerrom[idx].food.add(f); break;
            }
            int cont = JOptionPane.showConfirmDialog(frame, "Add more?", "Continue", JOptionPane.YES_NO_OPTION);
            if(cont != JOptionPane.YES_OPTION) break;
        }
    }

    private void checkoutDialog() {
        String rnStr = JOptionPane.showInputDialog(frame, "Enter Room Number (1-60) to checkout:");
        if(rnStr==null) return;
        int rn;
        try {
            rn = Integer.parseInt(rnStr);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        int type = getTypeFromRoomNumber(rn);
        if(type== -1) { JOptionPane.showMessageDialog(frame, "Room doesn't exist", "Error", JOptionPane.ERROR_MESSAGE); return; }
        int idx = getIndexFromRoomNumber(rn);

        boolean booked = false;
        String custName = "";
        switch(type) {
            case 1: booked = (Hotel.hotel_ob.luxury_doublerrom[idx]!=null); if(booked) custName = Hotel.hotel_ob.luxury_doublerrom[idx].name; break;
            case 2: booked = (Hotel.hotel_ob.deluxe_doublerrom[idx]!=null); if(booked) custName = Hotel.hotel_ob.deluxe_doublerrom[idx].name; break;
            case 3: booked = (Hotel.hotel_ob.luxury_singleerrom[idx]!=null); if(booked) custName = Hotel.hotel_ob.luxury_singleerrom[idx].name; break;
            case 4: booked = (Hotel.hotel_ob.deluxe_singleerrom[idx]!=null); if(booked) custName = Hotel.hotel_ob.deluxe_singleerrom[idx].name; break;
        }
        if(!booked) { JOptionPane.showMessageDialog(frame, "Room is already empty", "Info", JOptionPane.INFORMATION_MESSAGE); return; }

        int confirm = JOptionPane.showConfirmDialog(frame, "Room used by " + custName + ". Proceed to checkout and print bill (console)?", "Checkout", JOptionPane.YES_NO_OPTION);
        if(confirm != JOptionPane.YES_OPTION) return;

        // Call bill and deallocate (bill prints to console)
        Hotel.bill(idx, type);
        switch(type) {
            case 1: Hotel.hotel_ob.luxury_doublerrom[idx]=null; break;
            case 2: Hotel.hotel_ob.deluxe_doublerrom[idx]=null; break;
            case 3: Hotel.hotel_ob.luxury_singleerrom[idx]=null; break;
            case 4: Hotel.hotel_ob.deluxe_singleerrom[idx]=null; break;
        }
        JOptionPane.showMessageDialog(frame, "Checkout done. Bill printed to console. Room deallocated.", "Done", JOptionPane.INFORMATION_MESSAGE);
    }

    private int getTypeFromRoomNumber(int rn) {
        if(rn>=1 && rn<=10) return 1;
        if(rn>=11 && rn<=30) return 2;
        if(rn>=31 && rn<=40) return 3;
        if(rn>=41 && rn<=60) return 4;
        return -1;
    }

    private int getIndexFromRoomNumber(int rn) {
        if(rn>=1 && rn<=10) return rn-1;
        if(rn>=11 && rn<=30) return rn-11;
        if(rn>=31 && rn<=40) return rn-31;
        if(rn>=41 && rn<=60) return rn-41;
        return -1;
    }

    private void exitAndSave() {
        // reuse your write thread to serialize
        try {
            Thread t = new Thread(new write(Hotel.hotel_ob));
            t.start();
            JOptionPane.showMessageDialog(frame, "Saved to backup. Exiting.", "Exit", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(frame, "Error saving: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        // If you already load backup in your Main.main, make sure Hotel.hotel_ob is already initialized.
        // If not, you can load backup here before launching UI (but your backup mechanism already exists).
        SwingUtilities.invokeLater(() -> new HotelUI());
    }
}
