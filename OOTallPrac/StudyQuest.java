import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Interface
interface Rewardable {
    int getXp();
}

// Abstract class + constructor overloading + static
abstract class Task implements Rewardable {
    static int count = 0;
    String title;
    int minutes;
    boolean done;

    Task(String title, int minutes) {
        this.title = title;
        this.minutes = minutes;
        count++;
    }

    Task(String title) {
        this(title, 25);
    }

    abstract String type();

    public String toString() {
        return (done ? "[x] " : "[ ] ") + title + " - "
                + minutes + " min - +" + getXp() + " XP (" + type() + ")";
    }
}

// Single inheritance
class StudyTask extends Task {

    StudyTask(String t, int m) {
        super(t, m);
    }

    public int getXp() {
        return minutes;
    }

    String type() {
        return "Study";
    }
}

// Multilevel inheritance
class RevisionTask extends StudyTask {

    RevisionTask(String t, int m) {
        super(t, m);
    }

    public int getXp() {
        return super.getXp() * 2;
    }

    String type() {
        return "Revision";
    }
}

// User-defined exception
class InvalidTaskException extends Exception {

    InvalidTaskException(String msg) {
        super(msg);
    }
}

// Multithreading
class FocusTimer extends Thread {

    JLabel label;
    int secs;

    FocusTimer(JLabel label, int mins) {
        this.label = label;
        secs = mins * 60;
    }

    public void run() {
        for (int s = secs; s >= 0; s--) {
            final int left = s;

            SwingUtilities.invokeLater(() ->
                label.setText(String.format("%02d:%02d",
                        left / 60, left % 60))
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

// Main class
public class StudyQuest extends JFrame {

    ArrayList<Task> tasks = new ArrayList<>();
    DefaultListModel<Task> model = new DefaultListModel<>();
    JList<Task> list = new JList<>(model);

    JTextField titleF = new JTextField(12);

    JSpinner minSp = new JSpinner(
            new SpinnerNumberModel(25, 1, 240, 5)
    );

    JComboBox<String> typeBox = new JComboBox<>(
            new String[]{"Study", "Revision"}
    );

    JLabel xpLabel = new JLabel();
    JLabel timerLabel = new JLabel("00:00");

    FocusTimer timer;
    int xp = 0;

    StudyQuest() {

        super("StudyQuest Lite");

        JButton addBtn = new JButton("Add");
        JButton focusBtn = new JButton("Focus");
        JButton clearBtn = new JButton("Clear Done");
        JButton statsBtn = new JButton("Stats");

        JPanel top = new JPanel();

        top.add(new JLabel("Task:"));
        top.add(titleF);
        top.add(minSp);
        top.add(typeBox);
        top.add(addBtn);

        JPanel bottom = new JPanel();

        bottom.add(xpLabel);
        bottom.add(focusBtn);
        bottom.add(timerLabel);
        bottom.add(clearBtn);
        bottom.add(statsBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Event handling

        addBtn.addActionListener(e -> addTask());

        focusBtn.addActionListener(e -> {

            if (timer != null) {
                timer.interrupt();
            }

            timer = new FocusTimer(
                    timerLabel,
                    (int) minSp.getValue()
            );

            timer.start();
        });

        clearBtn.addActionListener(e -> {

            Iterator<Task> it = tasks.iterator();

            while (it.hasNext()) {
                if (it.next().done) {
                    it.remove();
                }
            }

            refresh();
        });

        statsBtn.addActionListener(e -> {

            HashMap<String, Integer> map = new HashMap<>();

            for (Task t : tasks) {
                map.merge(t.type(), t.minutes, Integer::sum);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Minutes planned: " + map
            );
        });

        // MouseListener
        list.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                Task t = list.getSelectedValue();

                if (e.getClickCount() == 2 && t != null && !t.done) {

                    t.done = true;
                    xp += t.getXp();

                    refresh();
                }
            }
        });

        // WindowListener
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                save();
            }
        });

        load();
        refresh();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method overloading
    void addTask(Task t) {
        tasks.add(t);
        refresh();
    }

    void addTask() {

        try {

            String t = titleF.getText().trim().replace(",", " ");

            if (t.isEmpty()) {
                throw new InvalidTaskException("Title cannot be empty!");
            }

            int m = (int) minSp.getValue();

            addTask(
                    typeBox.getSelectedIndex() == 0
                    ? new StudyTask(t, m)
                    : new RevisionTask(t, m)
            );

            titleF.setText("");

        } catch (InvalidTaskException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // Refresh GUI
    void refresh() {

        model.clear();

        for (Task t : tasks) {
            model.addElement(t);
        }

        xpLabel.setText("XP: " + xp + "  Level: " + (xp / 100 + 1));
    }

    // File I/O - Save data
    void save() {

        try (PrintWriter pw = new PrintWriter("tasks.txt")) {

            pw.println(xp);

            for (Task t : tasks) {

                pw.println(
                        t.type() + "," + t.title + ","
                        + t.minutes + "," + t.done
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    // File I/O - Load data
    void load() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("tasks.txt"))) {

            xp = Integer.parseInt(br.readLine());

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                Task t = p[0].equals("Study")
                        ? new StudyTask(p[1], Integer.parseInt(p[2]))
                        : new RevisionTask(p[1], Integer.parseInt(p[2]));

                t.done = Boolean.parseBoolean(p[3]);

                tasks.add(t);
            }

        } catch (Exception e) {
            // No saved tasks available
        }
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new StudyQuest());
    }
}