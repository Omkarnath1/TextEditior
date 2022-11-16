import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
public class FirstSession implements ActionListener {
    //Creating frame
    JFrame frame;
    //creating the text area
    JTextArea textArea;
    // Creating constructor
    JMenuBar jMenuBar;
    // creating constructor;
    FirstSession(){
        // initiallizing frame
        frame = new JFrame("Text Editor");
        //initialising the text area
        textArea = new JTextArea();

        jMenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File");
        JMenuItem newFile = new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");

        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);

        edit.add(Cut);
        edit.add(Paste);
        edit.add(Copy);
        edit.add(Close);


        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.setJMenuBar(jMenuBar);
        frame.add(textArea);
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main (String args[]){
        FirstSession textEditor = new FirstSession();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if(call == "New File")
        {
            textArea.setText("");
        }else if(call == "Cut")
        {
            textArea.cut();
        }else if(call == "Copy")
        {
            textArea.copy();
        }else if(call == "Paste")
        {
            textArea.paste();
        }else if(call == "Close"){
            frame.setVisible(false);
        }else if(call == "Save File")
        {
            JFileChooser jFileChooser = new JFileChooser( "C:"); // fileChooser => chooses the file
            int ans = jFileChooser.showOpenDialog(null);    //if approved ans=0 else any random positive number
            if(ans==jFileChooser.APPROVE_OPTION)
            {
               File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());// file store absolute path
                BufferedWriter writter = null;    //Buffered writter => feature of java , will let us write over the chosen file
                try {
                    writter = new BufferedWriter(new FileWriter(file,false));// passed file on which buffered writer writes
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try { // if we are getting any error it will give error which will prevent crashing of the program , a good practice
                    writter.write(textArea.getText());// here we write and paste it in the file
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
             else if(call == "Open File")
        {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                File file= new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                String s1="",s2="";
                    BufferedReader bufferedReader= null;
                    try {
                        bufferedReader = new BufferedReader(new FileReader(file));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        s2= bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    while(true){
                        try {
                            if (!((s1= bufferedReader.readLine())!=null)) break;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        s2+=s1+"\n";
                }
                textArea.setText(s2);
                }catch(RuntimeException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
             else if(call== "Print File"){
                 try{
                     textArea.print();
                 }catch(PrinterException ex){
                     throw new RuntimeException(ex);
                 }
        }
    }
}
