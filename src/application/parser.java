package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class parser{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img1;
    
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private AnchorPane pane1;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt6;

    @FXML
    private TextField txt7;

    @FXML
    private TextField txt8;

    @FXML
    private TextField txt_finisher;

    @FXML
    private TextField txt_starter;

    @FXML
    private VBox vb1;

    @FXML
    private VBox vb2;

    @FXML
    private VBox vb3;
    
    @FXML
    private HBox hb1;

    @FXML
    void btn1_Click(ActionEvent event) {
    	unique(txt1);
    }

    @FXML
    void btn2_Click(ActionEvent event) {    	
    	unique(txt2);
    }

    @FXML
    void btn3_Click(ActionEvent event) {
    	list.clear();
    	fonksiyon(null, null, null);	
    	int [] dizi= new int[list.size()];
    	int toplam=0;
    	
    	try {
    		for(int i =0;i<list.size();i++)
        	{	
        		int sonuc = Integer.parseInt(list.get(i).toString());
        		dizi[i]=sonuc;
        	}
    		for(int j =0;j< dizi.length;j++)
    		{
    			toplam+=dizi[j];
    			System.out.println(dizi[j]);
    		}
    		System.out.println(" Toplam Veri Miktari = "+toplam);
    		txt3.setText(" Toplam Veri Miktari = "+toplam);
		} catch (Exception e) {
			System.out.println("Hatalý parse iþlemi!\n\n"+e.getMessage());	
			}
    	
    }

    @FXML
    void btn4_Click(ActionEvent event) {
    	 most("IP : ",txt4);
    }
   
    @FXML
    void btn5_Click(ActionEvent event) {
    	most("ID :",txt5);
    }

    @FXML
    void btn6_Click(ActionEvent event) {
    	list.clear();
    	fonksiyon(null, null, null);
    	ArrayList templist= new ArrayList();
    	int k=0;
    	int basamak=0;
    	int sayi=0;
    	
    	int sonmod=0;
    	
    	for(int i=0; i<list.size(); i++)
    	{	
    		int ilkmod=0;
    		String veri=list.get(i).toString();
    		k=Integer.parseInt(veri);
    		//System.out.println("k :"+k);

    		basamak=String.valueOf(k).length();
    		//System.out.println("basamak : "+basamak);
    		
    		int  kuvvet=(int) Math.pow(10, basamak-1);
    		//System.out.println("kuvvet :"+kuvvet);
    	
    		
    		ilkmod = Math.floorMod(k ,kuvvet);
    		//System.out.println("ilkmod :"+ilkmod);
    		
    		
    		sonmod=(int) (ilkmod%(Math.pow(10, basamak-2)));
    		//System.out.println("sonmod :"+sonmod);

    		sayi=k-sonmod;
    		//System.out.println("sayi :"+sayi);
    		templist.add(sayi);
    		
    		basamak=0;
    		ilkmod=0;
    		sonmod=0;
    	}
    		
    		yuzde("oran :",templist);    		 
    }

    @FXML
    void btn7_Click(ActionEvent event) {
    	list.clear();
    	fonksiyon(null, null, null);
    	yuzde("Tarayýcý : ",list);
    }

    @FXML
    void btn8_Click(ActionEvent event) {
    	list.clear();
    	fonksiyon(null, null, null);
    	yuzde("HTTP Status : ",list);
    }

    @FXML
    private TextField txt_path;
    
    ArrayList list=new ArrayList();
  
    public void fonksiyon(String path, String starter, String finisher)
    {	
    	int sayac=0;
    	String line;
    	path=txt_path.getText().trim();
    	File file = new  File (path);
    	starter=txt_starter.getText();
    	finisher=txt_finisher.getText();
    	
    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        	while(reader.ready()==true)
        	{		
        		try {
        			sayac+=1;
            		line=reader.readLine();
            		int starting_index=line.indexOf(starter);
            		int finishing_index=0;
            		
            		if(line.contains(starter)&&line.contains(finisher))
            		{	
            			String results=line.substring(starting_index+starter.length(),line.length());
            			finishing_index=results.indexOf(finisher);
            			
            			String result=results.substring(0,finishing_index);
            			if(result.equals("-"))
            			{result="Unknown";}
            			list.add(result);
            			
            		}
            		else {continue;}
        		}
        		catch (Exception ex){
        			continue;
        		}
        		
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	System.out.println("\n");
    	System.out.println("Starter :"+starter);
    	System.out.println("Finisher :"+finisher);
    	System.out.println("Path :"+path);
    	System.out.println("Sayaç :"+sayac);
    	System.out.println("Liste Boyutu :"+ list.size());
    	//System.out.println("Listenin 0. Ýndisi "+list.get(0));
    	sayac=0;
    	
    }
    
    @FXML
    void initialize() {
    txt_path.setText("C:/Users/showm/Desktop/nginx.access.log");

    RotateTransition rotate = new RotateTransition();
    rotate.setNode(img1);
    rotate.setDuration(Duration.millis(300));
    rotate.setCycleCount(2);
    rotate.setByAngle(360);
    rotate.play();
    
    FadeTransition fadein=new FadeTransition(Duration.seconds(1.2),vb1);
    fadein.setFromValue(0.0);
    fadein.setToValue(1.0);
    fadein.play();
    
    FadeTransition fadein2=new FadeTransition(Duration.seconds(1.2),vb2);
    fadein2.setFromValue(0.0);
    fadein2.setToValue(0.8);
    fadein2.play();
   
    FadeTransition fadein3=new FadeTransition(Duration.seconds(1.2),vb3);
    fadein3.setFromValue(0.0);
    fadein3.setToValue(1.0);
    fadein3.play();
    
    FadeTransition fadein4=new FadeTransition(Duration.seconds(1.2),hb1);
    fadein4.setFromValue(0.0);
    fadein4.setToValue(1.0);
    fadein4.play();
    
    FadeTransition fadeout=new FadeTransition(Duration.seconds(1.0),img1);
    fadeout.setFromValue(1.0);
    fadeout.setToValue(0.37);
    fadeout.play();

}

    public void most(String mesaj,TextField hedeftxt)
    {
    	list.clear();
    	fonksiyon(null, null, null);
    	Collections.sort(list);
    	String buyuksayi="";
    	int adet=0;
    	int sayac=1;
    	try {
    		for(int i=0;i<list.size()-1;i++)
        	{
        		if(list.get(i).toString().equals(list.get(i+1).toString()))
        		{
        			sayac+=1;
        		}
        		else 
        		{	
        			
        			if(sayac>adet)
        			{
        				buyuksayi=list.get(i-1).toString();
        				adet=sayac;
        				sayac=1;       				
        			}
        			else
        			{	
        				sayac=1;
        			}
        		}
        	}    		
        	System.out.println(mesaj+buyuksayi+"  Adet : "+adet);
        	hedeftxt.setText(mesaj+buyuksayi+"  Adet : "+adet);
        	
		} catch (Exception e) {
			System.out.println("Hata!\n"+ e.getMessage());	
		}

    }
    public void unique(TextField hedeftxt)
    {
    	list.clear();
    	fonksiyon(null, null, null);
    	ArrayList tempList = new ArrayList();
    	Collections.sort(list);
    	tempList.add(list.get(0));
       	for(int i =0; i<list.size()-1 ; i++)
       	{	
       		if(list.get(i).toString().equals(list.get(i+1).toString())) {
       			continue;
       		}
       		else {tempList.add(list.get(i));}
       	}
       	hedeftxt.setText(String.valueOf(tempList.size()));

    }
    public void yuzde(String metin,ArrayList liste)
    {
    	Collections.sort(liste);
    	double sayac=1.0;
    	double yuzde=0.0;
    	String y ="";
    	System.out.println(liste.size());
    	try {
    	for(int i=0;i<liste.size()-1;i++)
    	{	
    		if(i!=list.size()-2 && liste.get(i).toString().equals(liste.get(i+1).toString())) {
       			sayac+=1;
       		}
       		else 
       		{
       			yuzde=(sayac*100)/(liste.size());
       			String f = String.format("%.3f",(yuzde));
       			y=metin+liste.get(i)+ ", yüzde %"+f+", adet :" + sayac;
       			System.out.println(y); 
       			sayac=1;
       		}
    	}
    }
    	catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }


}
