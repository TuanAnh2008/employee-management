
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Layout {
    // Constants
    private static int WIDTH = 2000;
    private static int HEIGHT = 2000;
    private static int HEADER_HEIGHT = 130;

    // Swing variables
    private static JFrame f;
    private JLabel l;
    private JPanel p;
    private JTable t;
    private JButton addB;
    private JButton saveB;
    private JScrollPane sp;
    private JTableHeader tableHeader;
    private DefaultTableModel model;

    protected Employee newEmp;
    
    // change later
    private String[] columnNames = { "EmpID", "Name", "Designation", "Gender", "EmailID", "Date of birth" };

    public static void main(String[] args) {}

    Layout() {
        f = new JFrame("Employee management");
        createUI();
    }

    public void show() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLocationRelativeTo(null); // center screen
        f.setVisible(true);
    }

    private void createUI() {
        headerUI();
        ArrayList<String[]> emptyArrList = new ArrayList<>();
        bodyUI(emptyArrList);
        buttons();
    }

    public void headerUI() {
        // Border
        Border blackline = BorderFactory.createLineBorder(Color.black, 5);
        // Label
        l = new JLabel("Employee Management");
        l.setFont(new Font("Arial", Font.ITALIC, 60)); // font, style, size
        l.setForeground(Color.BLACK); // text color
        l.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 0)); // padding

        // Panel
        p = new JPanel();
        p.setPreferredSize(new Dimension(WIDTH, HEADER_HEIGHT)); // set size
        p.setBackground(Color.LIGHT_GRAY); // background color
        p.setBorder(blackline);
        p.setLayout(new BorderLayout());
        p.add(l);

        // add panel to frame
        f.add(p, BorderLayout.NORTH);
    }

    // add
    public void bodyUI(ArrayList<String[]> arrList) {
        // EmpID, name, designation, gender, emailId, date of birth

        String[][] data = new String[arrList.size()][];
        data = arrList.toArray(data);

        // Initializing the JTable
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        t = new JTable(model);
        t.setRowHeight(70);
        t.setFont(new Font("Arial", Font.PLAIN, 20));

        // Customizing columns
        tableHeader = t.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.PLAIN, 20));

        // Padding for every cell
        t.setIntercellSpacing(new Dimension(20, 10));

        // Adding it to JScrollPane
        sp = new JScrollPane(t);
        f.add(sp, BorderLayout.CENTER);

    }

    public void buttons() {
        // Create panel
        p = new JPanel();
        p.setPreferredSize(new Dimension(WIDTH, 70));
        p.setLayout(new FlowLayout(FlowLayout.CENTER));

        addB = buttonUI("Add");
        buttonAddDataListener();
        p.add(addB);

        saveB = buttonUI("Save");
        buttonSaveDataListener();
        p.add(saveB);

        f.add(p, BorderLayout.SOUTH);
    }

    private JButton buttonUI(String text) {
        JButton btn = new JButton(text);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 50));

        return btn;
    }

    protected void buttonAddDataListener() {
        addB.addActionListener(e -> {
            model.addRow(new Object[] { "", "", "", "", "", "" });
        });
    }

    protected void buttonSaveDataListener() {
        String[] empArr = new String[6];
        saveB.addActionListener(e -> {
            // if table is editing
            if (t.isEditing()) {
                // data is saved only when enter, click on other fields 
                // return a column which is still processing and stop edit it
                t.getCellEditor().stopCellEditing();
            }

            int colCount = model.getColumnCount();
            int rowCount = model.getRowCount();
            for (int i = 0; i < colCount; i++) {
            empArr[i] = (String) model.getValueAt(rowCount - 1, i);
            }   
             newEmp = new Employee(empArr);
        }); 
    }
}
