import java.awt.*;
import javax.swing.*;

public class TaskPanel extends JPanel {

    private JTextArea taskArea;

    private JTextField taskField;

    private JTextField taskNumberField;

    private RoundedButton addButton;

    private RoundedButton completeButton;

    private RoundedButton deleteButton;

    private TaskManager taskManager;

    public TaskPanel() {

        taskManager = new TaskManager();

        setLayout(new BorderLayout());

        setBackground(Theme.BACKGROUND);

        // ================= TITLE =================

        JLabel title = new JLabel("📋 Task Manager");

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        title.setForeground(Color.WHITE);

        title.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        add(title, BorderLayout.NORTH);

        // ================= TASK AREA =================

        taskArea = new JTextArea();

        taskArea.setEditable(false);

        taskArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        taskArea.setBackground(Theme.INPUT);

        taskArea.setForeground(Color.WHITE);

        taskArea.setMargin(new Insets(15,15,15,15));

        JScrollPane scrollPane = new JScrollPane(taskArea);

        add(scrollPane, BorderLayout.CENTER);

        // ================= CONTROLS =================

        JPanel bottom = new JPanel();

        bottom.setBackground(Theme.BACKGROUND);

        bottom.setLayout(new GridLayout(3,2,10,10));

        bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        bottom.add(new JLabel("Task"));

        taskField = new JTextField();

        bottom.add(taskField);

        bottom.add(new JLabel("Task Number"));

        taskNumberField = new JTextField();

        bottom.add(taskNumberField);

        addButton =
                new RoundedButton("Add", new Color(34,197,94));

        completeButton =
                new RoundedButton("Complete", new Color(59,130,246));

        deleteButton =
                new RoundedButton("Delete", new Color(239,68,68));

        bottom.add(addButton);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2,10,10));

        buttonPanel.setOpaque(false);

        buttonPanel.add(completeButton);

        buttonPanel.add(deleteButton);

        bottom.add(buttonPanel);

        add(bottom, BorderLayout.SOUTH);

        loadTasks();

        // ================= EVENTS =================

        addButton.addActionListener(e -> {

            String result = taskManager.addTask(taskField.getText());

            JOptionPane.showMessageDialog(this,result);

            taskField.setText("");

            loadTasks();

        });

        completeButton.addActionListener(e -> {

            try{

                int num = Integer.parseInt(taskNumberField.getText());

                JOptionPane.showMessageDialog(this,
                        taskManager.completeTask(num));

                taskNumberField.setText("");

                loadTasks();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Enter valid task number.");

            }

        });

        deleteButton.addActionListener(e -> {

            try{

                int num = Integer.parseInt(taskNumberField.getText());

                JOptionPane.showMessageDialog(this,
                        taskManager.deleteTask(num));

                taskNumberField.setText("");

                loadTasks();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Enter valid task number.");

            }

        });

    }

    public void loadTasks(){

        taskArea.setText(taskManager.showTasks());

    }

}