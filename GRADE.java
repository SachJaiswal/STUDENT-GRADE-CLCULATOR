import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GRADE {

    public static void main(String[] args) {
        // Create a Swing frame
        JFrame frame = new JFrame("Student Marks and Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a panel for user input
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel numSubjectsLabel = new JLabel("Enter the number of subjects: ");
        JTextField numSubjectsField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(numSubjectsLabel);
        panel.add(numSubjectsField);
        panel.add(submitButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Add button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numSubjects = Integer.parseInt(numSubjectsField.getText().trim());
                    if (numSubjects <= 0) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number of subjects.", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int[] marks = new int[numSubjects];
                    int totalMarks = 0;

                    // Input marks for each subject
                    for (int i = 0; i < numSubjects; i++) {
                        while (true) {
                            String input = JOptionPane.showInputDialog(frame, "Enter marks for subject " + (i + 1) + " (out of 100):");
                            if (input == null) return; // User canceled
                            try {
                                marks[i] = Integer.parseInt(input.trim());
                                if (marks[i] < 0 || marks[i] > 100) {
                                    JOptionPane.showMessageDialog(frame, "Invalid marks. Please enter a value between 0 and 100.", "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        totalMarks += marks[i];
                    }

                    // Calculate average percentage
                    double averagePercentage = (double) totalMarks / numSubjects;

                    // Determine grade based on average percentage
                    char grade;
                    if (averagePercentage >= 90) {
                        grade = 'A';
                    } else if (averagePercentage >= 80) {
                        grade = 'B';
                    } else if (averagePercentage >= 70) {
                        grade = 'C';
                    } else if (averagePercentage >= 60) {
                        grade = 'D';
                    } else {
                        grade = 'F';
                    }

                    // Display results
                    resultArea.setText("Results:\n");
                    resultArea.append("Total Marks: " + totalMarks + "\n");
                    resultArea.append("Average Percentage: " + String.format("%.2f", averagePercentage) + "%\n");
                    resultArea.append("Grade: " + grade + "\n");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
