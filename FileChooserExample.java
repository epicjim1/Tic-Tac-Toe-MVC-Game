import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class FileChooserExample extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;
  JButton chooseButton;
  JTextArea textArea;

  public FileChooserExample() {
    super("File Chooser Example");
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    chooseButton = new JButton("Choose File");
    chooseButton.addActionListener(this);
    add(chooseButton, BorderLayout.NORTH);

    textArea = new JTextArea();
    textArea.setEditable(false);
    add(new JScrollPane(textArea), BorderLayout.CENTER);

    setVisible(true);
  }

  public static void main(String[] args) {
    new FileChooserExample();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
          sb.append(line).append("\n");
        }
        textArea.setText(sb.toString());
        reader.close();
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
