import java.awt.*;
import java.io.*;
import javax.swing.*;

public class NotesPanel extends JPanel {

    private JTextArea notesArea;
    private RoundedButton saveButton;

    private static final String FILE_NAME = "notes.txt";

    public NotesPanel() {

        setLayout(new BorderLayout());

        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("📝 Notes");

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        add(title, BorderLayout.NORTH);

        notesArea = new JTextArea();

        notesArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        notesArea.setBackground(Theme.INPUT);
        notesArea.setForeground(Color.WHITE);
        notesArea.setCaretColor(Color.WHITE);
        notesArea.setMargin(new Insets(15,15,15,15));

        JScrollPane scrollPane = new JScrollPane(notesArea);

        add(scrollPane, BorderLayout.CENTER);

        saveButton = new RoundedButton("Save Notes", new Color(34,197,94));

        JPanel bottom = new JPanel();

        bottom.setBackground(Theme.BACKGROUND);

        bottom.add(saveButton);

        add(bottom, BorderLayout.SOUTH);

        loadNotes();

        saveButton.addActionListener(e -> saveNotes());

    }

    private void loadNotes() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                sb.append(line).append("\n");

            }

            notesArea.setText(sb.toString());

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this,
                    "Unable to load notes.");

        }

    }

    private void saveNotes() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            writer.write(notesArea.getText());

            JOptionPane.showMessageDialog(this,
                    "Notes Saved!");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this,
                    "Unable to save notes.");

        }

    }

}