import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.lang.*;
import java.util.*;
class Main{
	public static void main(String[]argv){
		new MyFrame("Pyatnashki");
	}
}
class MyFrame extends Frame implements WindowListener{ 
   String mass[][] ; 
   int n[]; 
   BFive bf[][]; 
   BFive bn; 
   BFive bk;
   BNomber bd; 
   public MyFrame(String title){ 
      super(title); 
       setLocation(200,100); 
       setSize(203,253); 
       setResizable(false); 
       setBackground(Color.blue); 
       mass=new String[6][6]; 
       bf=new BFive[6][6]; 
       for(int i=0; i<6; i++){ 
        for (int j=0; j<6; j++){ 
          mass[i][j]="16"; 
        } 
    }
    n=new int[16]; 
    for(int i=1; i<16; i++){ 
      n[i]=i; 
    } 
    n[0]=-1; 
    int k; 
    String s; 
    //генерируем числа
    for (int i=1; i<=15; i++){ 
     k=number(); 
    s=Integer.toString(k); 
     mass[(i-1)/4+1][(i-1)%4+1]=s; 
     add(bn=new BFive(s,((i-1)%4)*50,((i-1)/4)*50+26,this)); 
     bf[(i-1)/4+1][(i-1)%4+1]=bn; 
    } 
    add(bd=new BNomber("new game",0,226,this));
     mass[4][4]="0"; 
     addWindowListener(this); 
     setLayout(null); 
     setVisible(true); 
  }  
    
  public int number(){ 
    int c=0; 
    int h = 0; 
    int k = 16; 
    int r=-1; 
    while(c==0){ 
    c=1; 
    r = h + (int) (Math.random() * k); 
    if (n[r]==r){ 
    n[r]=-1; 
    return(r); 
    } 
    else { 
     int s=0; 
     for (int i=1; i<16; i++){ 
       if (n[i]==-1){ 
        s++; 
       } 
     } 
     if ((s!=15)|(r==0)){ 
       c=0; 
     } 
  } 
} 
return(r); 
} 
public void windowClosing(WindowEvent we){ System.exit(0);} 
public void windowClosed(WindowEvent we){} 
public void windowOpened(WindowEvent we){} 
public void windowActivated(WindowEvent we){} 
public void windowDeactivated(WindowEvent we){} 
public void windowIconified(WindowEvent we){} 
public void windowDeiconified(WindowEvent we){} 
} 

class BFive extends Button implements ActionListener{ 
  int x; 
  int y; 
  MyFrame win; 
  public BFive(String s, int x, int y, MyFrame win){ 
    super(s); 
    this.x=x; 
    this.y=y; 
    this.win=win; 
    setLocation(x,y); 
    setSize(50,50); 
    setBackground(Color.green); 
    addActionListener(this); 
  } 
  public void actionPerformed(ActionEvent ae){ 
	  //находим координаты
  String s=getLabel(); 
  int x=0;
  int y=0;  
   for (int i=1; i<5; i++){ 
   for (int j=1; j<5; j++){ 
    if ((win.mass[i][j]).equals(s)){ 
      x=i; 
      y=j; 
  }
      System.out.print(win.mass[i][j]+" ");
    } System.out.println();
   
} 
  int xi=(int)getX();
  int yi=(int)getY();
  if ((win.mass[x+1][y]).equals("0")){ 
    win.mass[x+1][y]=s; 
    win.bk=win.bf[x+1][y]; 
    win.bf[x+1][y]=win.bf[x][y]; 
    win.bf[x][y]=win.bk; 
    win.mass[x][y]="0"; 
    new MyThread(xi,yi,xi,yi+50,this).start(); 
  } 
  else if ((win.mass[x-1][y]).equals("0")){ 
   win.mass[x-1][y]=s; 
   win.bk=win.bf[x-1][y]; 
   win.bf[x-1][y]=win.bf[x][y]; 
     win.bf[x][y]=win.bk; 
    win.mass[x][y]="0"; 
  new MyThread(xi,yi,xi,yi-50,this).start(); 
 } 
    else if ((win.mass[x][y-1]).equals("0")){ 
          win.mass[x][y-1]=s; 
         win.bk=win.bf[x][y-1]; 
           win.bf[x][y-1]=win.bf[x][y]; 
            win.bf[x][y]=win.bk; 
             win.mass[x][y]="0"; 
             new MyThread(xi,yi,xi-50,yi,this).start(); 
          } 
    else if ((win.mass[x][y+1]).equals("0")){ 
      win.mass[x][y+1]=s; 
       win.bk=win.bf[x][y+1]; 
        win.bf[x][y+1]=win.bf[x][y]; 
        win.bf[x][y]=win.bk; 
        win.mass[x][y]="0"; 
        new MyThread(xi,yi,xi+50,yi,this).start(); 
    } 
   
} 

} 

class MyThread extends Thread{ 
  BFive bn; 
   int x; 
   int y; 
   int xx; 
   int yy; 
   public MyThread(int x, int y, int xx, int yy, BFive bn){ 
    this.x=x; 
    this.y=y; 
    this.xx=xx; 
    this.yy=yy; 
    this.bn=bn; 
   } 
   public void run(){ 
   try{ 
    if (x<xx){ 
    bn.removeActionListener(bn); //откл прослуш.
     while (x!=xx){ 
      x++; 
      bn.setLocation(x,y); 
      Thread.sleep(3); 
    }
   bn.addActionListener(bn); 
   } 
   else if(x>xx){ 
      bn.removeActionListener(bn); 
       while (x!=xx){ 
        x--; 
         bn.setLocation(x,y); 
        Thread.sleep(3); 
       } 
       bn.addActionListener(bn); 
       } 
       else if(y<yy){ 
       bn.removeActionListener(bn); 
       while (y!=yy){ 
       y++; 
       bn.setLocation(x,y); 
        Thread.sleep(3); 
       } 
        bn.addActionListener(bn); 
       } 
       else if(y>yy){ 
         bn.removeActionListener(bn); 
          while (y!=yy){ 
            y--; 
            bn.setLocation(x,y); 
            Thread.sleep(3); 
          } 
        bn.addActionListener(bn); 
        } 
        } 
        catch (Exception e){ 
        } 
       } 
      }
class BNomber extends Button implements ActionListener{ 
  int x; 
  int y; 
  int[]n;
  MyFrame win; 
  public BNomber(String title, int x, int y, MyFrame win){ 
	  super(title); 
    this.x=x; 
    this.y=y; 
    this.win=win; 
    setLocation(x,y); 
    setSize(203,27); 
    setBackground(Color.green); 
    addActionListener(this); 
  } 
  public int number(){ 
    int c=0; 
    int h = 0; 
    int k = 16; 
    int r=-1; 
    while(c==0){ 
    c=1; 
    r = h + (int) (Math.random() * k); 
    if (n[r]==r){ 
    n[r]=-1; 
    return(r); 
    } 
    else { 
     int s=0; 
     for (int i=1; i<16; i++){ 
       if (n[i]==-1){ 
        s++; 
       } 
     } 
     if ((s!=15)|(r==0)){ 
       c=0; 
     } 
  } 
} 
return(r); 
} 
  public void actionPerformed(ActionEvent ae){ 
	  
//меняем цифры
 n=new int[16];
    for(int i=1; i<16; i++){ 
      n[i]=i; 
    } 
    n[0]=-1; 
    int k; 
    String s; 
    //генерируем числа
    for (int i=1; i<=15; i++){ 
     k=number(); 
    s=Integer.toString(k); 
     win.mass[(i-1)/4+1][(i-1)%4+1]=s; 
     win.bf[(i-1)/4+1][(i-1)%4+1]=win.bn;
  }
}
}

