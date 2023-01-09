import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import io.netty.util.internal.ThreadLocalRandom;

public class Nallas_demo {

	public static String Set1 = "Yet to start";
	public static String Set2 = "Yet to start";
	public static JLabel Set_list1;
	public static JLabel Set_list2;
	public static JLabel Status;
	public static String user_status ="Yet to start";
	public static  String  Verification="Yet to start";
	public static  int Guess_count=0;
	public static JTextField userEnt;
	public static JTextField userEnt1;
	public static  String  finalstring="";
	public static  String  finalstring1="";
	public static  int count;  
	public static Integer [] int_array;
	public static JButton number;
	public static JButton reset;
	public static String finalvalue="empty";
	public static JPanel mainPanel, subPanel1, subPanel2;
	public static ArrayList<Integer> value = new ArrayList<Integer>();
	public static int a[][];
	public static List<String> final_output = new ArrayList<String>();
	public static List<String> final_output1 = new ArrayList<String>();
	public static List<String> final_status = new ArrayList<String>();
	public static String final_result_string;
	public static String final_conversion_string="";
	
	public static List<String> no_match = new ArrayList<String>();
	public static void main(String[] args) 
	{
					
     try
     {

    	 final JFrame frame = new JFrame();
 		frame.setTitle("-----Welcome to NALLAS-----");
 		JPanel pane1 = new JPanel();

 		//---to identify system resolution----
 		int[] scrn_resln = system_resolution();
 		 int width = scrn_resln[0];
 		 int height = scrn_resln[1];
 		 frame.setSize(width/2,height/2);
 		//---Element initialization----
 		 JLabel list_label = new JLabel("String 1");
          userEnt = new JTextField("", 10);
          JLabel list_label2 = new JLabel("String 2");
          userEnt1 = new JTextField("", 10);
          number = new JButton("Compare");
          reset = new JButton("Reset");
          Set1 ="Yet to Start";
          Set_list1  = new JLabel("<html><br>"+"String Conversion Status **"+Set1+" --**</html>");
          pane1.add(list_label);
          pane1.add(userEnt);
          pane1.add(list_label2);
          pane1.add(userEnt1);
          pane1.add(number);
          pane1.add(reset);
          pane1.add(Set_list1);
          frame.add(pane1);
           frame.setVisible(true);
    	 
           number.addActionListener(new ActionListener(){  
 			  public void actionPerformed(ActionEvent e){
 				  reset.setEnabled(true);
 				  userEnt.setEditable(false);
 				 userEnt1.setEditable(false);
 				  number.setEnabled(false);
 				 finalstring = userEnt.getText();
 				 finalstring1 = userEnt1.getText();
 				 if(finalstring.equals("") || finalstring1.equals(""))
 				 {
 					 
 					Set_list1.setText("<html><br>"+"String Conversion Status :"+ "Required input cannot be blank**</html>");
 				 }
 				 else
 				 {
 				try {
					compare(finalstring,finalstring1);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	 
 				if(!final_status.contains("True"))
 				{
 					
 					 String tempv = "Cannot be converted by removing only one substring. Need to remove more than one substring.";
 				
 					Set_list1.setText("<html><br>"+tempv+" --**</html>");
 				}
 				else
 				{
 					Set_list1.setText("<html><br>"+final_conversion_string+" --**</html>");
 					
 				}
 				
 				if(!no_match.contains("No"))
 				{

					 String tempv = "No matching string found. String cannot be converted";
				
					Set_list1.setText("<html><br>"+tempv+" --**</html>");
 				}
 				 }
 				   
 		}});  

           
          reset.addActionListener(new ActionListener(){  
 			  public void actionPerformed(ActionEvent e)
 			  {
 				  number.setEnabled(true);
 				  userEnt.setEditable(true);
 				  userEnt.setText("");
 				 userEnt1.setEditable(true);
 				 userEnt1.setText("");
 				finalstring1="";
 				  reset.setEnabled(false);
 				 Set_list1.setText("<html><br>"+"String Conversion Status :"+" Reset Completed**</html>");
 			 final_result_string="";
 				final_conversion_string="";
 				final_output.clear();
 				final_output1.clear();
 				final_status.clear();
 				no_match.clear();
 			  }});
          
          
	}
	catch(Exception g)
	{
		System.out.println(g.toString());
	}
	}
 	
     public static void compare(String value, String value1) throws IOException
     {
    	int length1 = value.length();
    	int vals =length1+1;
    	int n= 0;
    	String tempstrin = null;
    	
    	for(int a=0; a<length1;a++)
    	{
    		
    		char newchar = value.charAt(a);
    		String valchar = String.valueOf(newchar);
    		tempstrin = valchar;
    		System.out.println("get value : a"+a+" "+length1+" "+tempstrin);
    		if(valchar.equals(value1))
    		{
    			System.out.println("test match");
    			
    			System.out.println("match index" + a);
    			findMissing_Singleindex(a,length1);
    			
    			break;
    		}
    		
    		if(a==length1-1)
    		{
    			break;
    		}
    	   int v= a+1;
    	   String tempval = "A["+a+"]";
    	   String temval = Integer.toString(a);
    		for(int b=v; b<vals;b++)
    		{
    			  
    			char newchar1 = value.charAt(b);
    			tempstrin += String.valueOf(newchar1);
    			//String charstrng = newar.toString();
    			System.out.println("test compare  :"+tempstrin);
    			System.out.println("b and v value"+ b + " "+v);
    			tempval += "A["+b+"]";
    			temval+=","+ Integer.toString(b);
    			String status = "";
    			if(tempstrin.equals(value1))
    			{
    				System.out.println("test Demo match");
    				System.out.println("match index" + a + b);
    				status="True";
    			}
    			else
    			{
    				status +="False";
    			}
    			tempval +=status;
    			temval+=status;
    			final_output.add(tempval);
    			final_output1.add(temval);
    			status ="";
    			tempval = tempval.replace("True", "").replace("False", "");
    			temval = temval.replace("True", "").replace("False", "");
    			int vl = length1-1;
    			if(v==vl)
    			{
    				break;
    			}
    			
    			
    			if(b==vl & v<vl)
        		{
    				
        			v+=1;
        			b=v-1;
        			tempstrin = valchar;
        		    tempval = "A["+a+"]";
        		    temval = Integer.toString(a);
        		}
    			
    			
    			
    		}
    		tempval ="";
    		temval ="";
    	}
    	
    	writecsv();
    	findindex();
    	
     }
	
   
     public static void findindex()
     {
    	 
    	 for(String val:final_output1)
    	 {
    		 
    		 if(val.contains("True"))
    		 {
    			 no_match.add("No");
    			 val = val.replace("True", "");
    			 String[] findind = val.split(",");
    			 int size = findind.length;
    		      int [] arr = new int [size];
    		      ArrayList<Integer> resultlist = new ArrayList<Integer>();
    		      for(String inf: findind)
    		      {
    		    	  System.out.println("inded"+inf);
    		    	  resultlist.add(Integer.parseInt(inf));
    		      }
    		      int length1 = finalstring.length();
    		      findMissing(resultlist,length1);
    		     
    		 }
    		 else
    		 {
    			 no_match.add("Yes");
    		 }
    		 
    		 
    	 }
    	 
     }
     
     @SuppressWarnings("unlikely-arg-type")
	public static void findMissing(List<Integer> arr, int N)
     {
         int i;
      
         
         Set<Integer> set = new HashSet<>();
        
         for (i = 0; i <= N-1; i++)
         {
             set.add(i);
         }
       
        
         set.removeAll(arr);
         
        
         
         System.out.println(set);
        int[] se =  set.stream()
         .mapToInt(Integer::intValue)
         .toArray();
        
        
        String removed_str = find_removeable_string(se);
        check_continous(se,removed_str);
        
     }
     
     
     @SuppressWarnings("unlikely-arg-type")
 	public static void findMissing_Singleindex(int index, int N)
      {
          int i;
       
          
          Set<Integer> set = new HashSet<>();
         
          for (i = 0; i <= N-1; i++)
          {
              set.add(i);
          }
          set.remove(index);
          System.out.println(set);
         int[] se =  set.stream()
          .mapToInt(Integer::intValue)
          .toArray();
         
         
         String removed_str = find_removeable_string(se);
         check_continous(se,removed_str);
         
      }
      
     
     
	public static String find_removeable_string(int[] intst)
	{
		String tempstr ="";
		for(int a : intst)
		{
			char newchar1 = finalstring.charAt(a);
			tempstr += String.valueOf(newchar1);
		}
		return tempstr;
	}
     
     public static void check_continous(int[] intarry, String str)
     {
    	 System.out.println("final method");
    	String status ="True";
    	 Arrays.sort(intarry);
    	 for (int i = 0; i < intarry.length - 1; i++)
    	 {
    	   if (intarry[i] + 1 != intarry[i + 1])
    	   {
    	     System.out.println("its not consicutive");
    	     final_status.add("False");
    	     status="False";
    	   }
    	   
    	 }
    	 int val1 = intarry[0];
    	 int val2 =intarry[intarry.length-1];
    	 
    	 if(status.equalsIgnoreCase("True"))
    	 {
    		 System.out.println("its consicutive");
    		 
    		 final_status.add("True");
    		 String tempv = "--Can be converted by removing -"+str+"- (A["+val1+"]"+" to "+"A["+val2+"])--"+"<br/>";
    		 
    		 System.out.println("final result"+tempv);
    		 final_conversion_string+= tempv;
    		 JOptionPane.showMessageDialog(null,tempv, "String Conversion Status ", JOptionPane.PLAIN_MESSAGE);
    		
    	 }
    	 else
    	 {
    		 final_status.add("False");
    		
    	 }
    	 
    	 
     }
	
public static int[] system_resolution()
{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		int[] val = {width,height};
		return val;
		
}
public static void writecsv() throws IOException 
{
	 String home = System.getProperty("user.home");
	 File file1 = new File(home+"/Downloads/" +"Jsoncomparison_result.csv"); 
	 System.out.println("file name"+file1.toString());
	String filelocation =file1.toString();
	  FileWriter writer = new FileWriter(file1);

	  for (int j = 0; j < final_output.size(); j++) 
	  {
	      writer.append(final_output.get(j));
	      writer.append("\n");
	  }
	  writer.close();
}
	
	

}
