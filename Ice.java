import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Ice {
    public static void main(String[] args) {
        IceCreamEntry ie = new IceCreamEntry();
        ie.jf.setVisible(true);
    }
}

class IceCreamEntry {
    JFrame jf;
    JPanel jp;
    JLabel flavorLabels;
    JTextField flavorFields;
    JLabel priceLabels;
    JTextField priceFields;
    JButton submitBtn;
    JButton viewListBtn;

    IceCreamEntry() {
        jf = new JFrame("Seller Entry Page");
        jf.setSize(800, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jp.setLayout(null);
/*
        flavorLabels = new JLabel[5];
        flavorFields = new JTextField[5];

        priceLabels= new JLabel[5];
        priceFields = new JTextField[5];*/

//        for (int i = 0; i < 5; i++) {
            flavorLabels = new JLabel("Flavor ");// + (i + 1) + ":");
            flavorLabels.setBounds(50, 50, 80, 30);
            jp.add(flavorLabels);

            flavorFields = new JTextField();
            flavorFields.setBounds(135, 50, 200, 30);
            jp.add(flavorFields);

            
            priceLabels = new JLabel("Price ");// + (i + 1) + ":");
            priceLabels.setBounds(350, 50 , 80, 30);
            jp.add(priceLabels);


            priceFields = new JTextField();
            priceFields.setBounds(400, 50, 100, 30);
            jp.add(priceFields);
        

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 370, 80, 40);
        submitBtn.addActionListener(new SubmitAction(jf,flavorFields, priceFields));
        jp.add(submitBtn);

       /* viewListBtn = new JButton("Visit List");
        viewListBtn.setBounds(500, 370, 120, 40);
        viewListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuyerPage bp = new BuyerPage();
                jf.setVisible(false);
                bp.jf.setVisible(true);
            }
        });
        jp.add(viewListBtn);
*/
        jf.add(jp);
    }
}

class SubmitAction implements ActionListener {
   JFrame jf;
    JTextField flavorFields;
    JTextField priceFields;
    

  public  SubmitAction(JFrame jf,JTextField flavorFields, JTextField priceFields) {
        this.jf = jf;
        this.flavorFields = flavorFields;
        this.priceFields = priceFields;
       
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println(flavorFields.getText());
        System.out.println(priceFields.getText());
        File f = new File("icecream.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f, true);
            fw.write(flavorFields.getText() + "," + priceFields.getText() + "\n");
             BuyerPage bp = new BuyerPage();
            jf.setVisible(false);
            bp.jf.setVisible(true);

        } catch(IOException ioe) {
            System.out.println("Error while creating a new file");
        } finally {
            try {
                fw.close();
            } catch(Exception ex) {
                System.out.println("Error while closing the file");
            }
        }
    }
}


class BuyerPage {
    JFrame jf;
    JPanel jp;
    JTextArea area;

    //JLabel[] flavorLabels;
    //JButton[] buyBtns;
    //int[] prices;
    //int total = 0;
    //JLabel totalLabel;
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

       // flavorLabels = new JLabel[5];
       // buyBtns = new JButton[5];
       // prices = new int[5];

        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String line;
            String data = "";
            while((line = br.readLine()) != null) {
                data += line + "\n";
            }

            area = new JTextArea(data);
            area.setBounds(50, 50, 700, 350);
            jp.add(area);

        } catch(IOException ioe) {
            System.out.println("Error while reading the file");
        }

     /*   String[] lines = new String[5];
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

*/
/*doneBtn = new JButton("Done");
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

*/
backButton = new JButton("Back to Entry page");
backButton.setBounds(360, 420, 200, 40);
backButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
        IceCreamEntry iceCreamEntry = new IceCreamEntry();
        jf.setVisible(false);
        iceCreamEntry.jf.setVisible(true);
    }
});
jp.add(backButton);



        jf.add(jp);
    }
}