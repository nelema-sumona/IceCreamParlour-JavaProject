import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class IceCreamParlour {
    public static void main(String[] args) {
        IceCreamEntry iceCreamEntry = new IceCreamEntry();
        iceCreamEntry.jf.setVisible(true);
    }
}

class IceCreamEntry {
    JFrame jf;
    JPanel jp;
    JLabel[] flavorLabels;
    JTextField[] flavorFields;
    JLabel[] priceLabels;
    JTextField[] priceFields;
    JButton submitBtn;
    JButton viewListBtn;

    IceCreamEntry() {
        jf = new JFrame("Seller Entry Page");
        jf.setSize(800, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jp.setLayout(null);

        flavorLabels = new JLabel[5];
        flavorFields = new JTextField[5];
        priceLabels= new JLabel[5];
        priceFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            flavorLabels[i] = new JLabel("Flavor " + (i + 1) + ":");
            flavorLabels[i].setBounds(50, 50 + i * 60, 80, 30);
            jp.add(flavorLabels[i]);

            flavorFields[i] = new JTextField();
            flavorFields[i].setBounds(135, 50 + i * 60, 200, 30);
            jp.add(flavorFields[i]);

            
            priceLabels[i] = new JLabel("Price " + (i + 1) + ":");
            priceLabels[i].setBounds(350, 50 + i * 60, 80, 30);
            jp.add(priceLabels[i]);


            priceFields[i] = new JTextField();
            priceFields[i].setBounds(400, 50 + i * 60, 100, 30);
            jp.add(priceFields[i]);
        }

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 370, 80, 40);
        submitBtn.addActionListener(new SubmitAction(flavorFields, priceFields, jf));
        jp.add(submitBtn);

        viewListBtn = new JButton("Visit List");
        viewListBtn.setBounds(500, 370, 120, 40);
        viewListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuyerPage bp = new BuyerPage();
                jf.setVisible(false);
                bp.jf.setVisible(true);
            }
        });
        jp.add(viewListBtn);

        jf.add(jp);
    }
}

class SubmitAction implements ActionListener {
    JTextField[] flavors;
    JTextField[] prices;
    JFrame jf;

  public  SubmitAction(JTextField[] flavors, JTextField[] prices, JFrame jf) {
        this.flavors = flavors;
        this.prices = prices;
        this.jf = jf;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            FileWriter fw = new FileWriter("icecream.txt", false);
            for (int i = 0; i < 5; i++) {
                fw.write(flavors[i].getText() + "," + prices[i].getText() + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error writing file");
        }

        BuyerPage bp = new BuyerPage();
        jf.setVisible(false);
        bp.jf.setVisible(true);
    }
}

class BuyerPage {
    JFrame jf;
    JPanel jp;
    JLabel[] flavorLabels;
    JButton[] buyBtns;
    int[] prices;
    int total = 0;
    JLabel totalLabel;
    JButton doneBtn;
    JButton backButton;

    BuyerPage() {
        jf = new JFrame("Buyer Page");
        jf.setSize(800, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jp.setLayout(null);

            File f = new File("icecream.txt");
            
            FileReader fr = null;
            BufferedReader br = null;

        flavorLabels = new JLabel[5];
        buyBtns = new JButton[5];
        prices = new int[5];

        String[] lines = new String[5];
        try {
            fr = new FileReader(f);

             br = new BufferedReader(fr);
            for (int i = 0; i < 5; i++) {
                lines[i] = br.readLine();

                if (lines[i] != null && !lines[i].isEmpty()) {
                    String[] parts = lines[i].split(",");
                    if (parts.length == 2) {
                        flavorLabels[i] = new JLabel(parts[0] + " - BDT. " + parts[1]);
                        prices[i] = Integer.parseInt(parts[1]);

                        flavorLabels[i].setBounds(50, 50 + i * 60, 200, 30);
                        jp.add(flavorLabels[i]);

                        buyBtns[i] = new JButton("Buy");
                        buyBtns[i].setBounds(300, 50 + i * 60, 80, 30);
                        final int index = i;

                        buyBtns[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                total += prices[index];
                                totalLabel.setText("Total: BDT. " + total);
                            }
                        });
                        jp.add(buyBtns[i]);
                    }
                }
            }
            br.close();
        } catch (IOException ioe) {
            System.out.println("Error reading file");
        }

        totalLabel = new JLabel("Total: BDT. 0");
        totalLabel.setBounds(50, 380, 200, 30);
        jp.add(totalLabel);


doneBtn = new JButton("Done");
doneBtn.setBounds(50, 420, 80, 40);
doneBtn.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
    
        File f = new File("icecream.txt");
        FileWriter fw = null;

        try {
            fw = new FileWriter(f, true);
            fw.write("Total Taka: BDT. " + total + "\n");
            fw.close();
        } catch (IOException ex) {
                      System.out.println("Error reading file");
  
        }
    }
});
jp.add(doneBtn);


backButton = new JButton("Back to Entry page");
backButton.setBounds(360, 420, 200, 40);
backButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        IceCreamEntry iceCreamEntry = new IceCreamEntry();
        jf.setVisible(false);
        iceCreamEntry.jf.setVisible(true);
    }
});
jp.add(backButton);



        jf.add(jp);
    }
}