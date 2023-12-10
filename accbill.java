package accounts;
import java.sql.*;
import dd.DBConnectionManager;
public class accbill{
String matchname[] = new String[1000];
String matchslno[] = new String[1000];
String matchacademicyear[] = new String[1000];

    String fdate="";
    String tdate="";
	float printpagerate=0;
	float prndue =0;
	float libfine =0;
	int prndue1 =0;
	float libdue =0;


int count=0;
String slno;
String quer="";
String year="";
String classs="";
String firstslno;
String nextslno;
String previousslno;
String lastslno;
String date="";
String name="";
String rollno="";
String academicyear="";
String headname[] = new String[500];
String headslno[] = new String[500];
float headamount[] = new float[500];
float headamount1[] = new float[500];
float totalamount=0;
int headcount=0;
float total[] = new float[500];
float paid[] = new float[500];
float refunded[] = new float[500];
int totchelan[] = new int[500];
float balance[] = new float[500];
int yesno[] = new int[500];
String paymentdate[][]= new String[500][500];
String refunddate[][]= new String[500][500];
int chelan[][] = new int[500][500];
int rchelan[][] = new int[500][500];
float paymentamount[][] = new float[500][500];
float refundamount[][] = new float[500][500];

float totalpaid=0;
float totalrefunded=0;
float totalbalance=0;

String feesslno="0";
String feesindividualslno="0";


public int Maxchelan()
{
int temp=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
//String sql="select max(chelan) from payment where month(date)=month('"+ date +"') and year(date)=year('"+ date +"')";
String sql="select max(chelan) from payment where year(date)=year('"+ date +"')";
ResultSet rs = stmt.executeQuery(sql);
if(rs.next())
{
temp=rs.getInt(1);
}
if(temp==0)temp=1;
else temp++;
rs.close();
stmt.close();
con.close();
}catch(Exception e){temp=1; }
finally{connMgr.freeConnection("accounts",con);}
return temp; 
}


public String accinfo()
{
String error="OK";
String quer="";
String quer1=" ";
rollno=name;
int ajk=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();

ResultSet rs;
int found=0;
int yr=4;

quer="select year from sxcce.dbo.stud where academicyear in(select academicyear from sxcce.dbo.academicyear) and rollno="+name;
rs = stmt.executeQuery(quer);
if(rs.next())
{
yr=rs.getInt(1);
}
rs.close();

quer1=" ";

if(yr==1) quer1=" uq.slno not in(140,155,156,157,159,160,161,1010,1002,1006,1014,1015,1016,1011,1003,1007,1012,1004,1008,117,151,120,147,123,126,129,118,152,121,148,124,127,131,119,153,122,149,125,128,130) and ";

if(yr==2) quer1=" uq.slno not in(140,156,157,160,161,1011,1003,1007,1015,1016,1012,1004,1008,118,152,121,148,124,127,131,119,153,122,149,125,128,130) and ";

if(yr==3) quer1=" uq.slno not in(140,157,161,1012,1004,1008,1016,119,153,122,149,125,128,130) and ";
													
if(yr==4) quer1=" ";

quer="select uq.slno,uq.head,uq.fees,ISNULL(p.pdate,'---'),ISNULL(p.payment,0),ISNULL(c.refund,0)  from ("+
" select a.slno slno ,a.head head ,ISNULL(f.fees,0)+ISNULL(f1.ifees,0) fees from feesstructureheads a "+
" Left Outer join (select amount fees,fshno from feesstructureamount where fsino=(select fslno from feeassign where rollno="+rollno+")) f on f.fshno=a.slno "+
" left outer join (SELECT amount ifees,fshno f2 FROM feesstructureindividualamount,feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno+") f1 on f1.f2=a.slno "+
" union all SELECT head slno, 'Fine/Due: '+(substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date))))) +' - '+ltrim(rtrim(dues.reason)) head,amount fees FROM dues WHERE rollno = "+rollno+
" union all SELECT head slno, 'BusFee: '+(substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date))))) +' - '+ltrim(rtrim(reason)) head,amount fees FROM busfee WHERE rollno = "+rollno+
" union all SELECT head slno, 'ExamFee: '+(substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date))))) +' - '+ltrim(rtrim(reason)) head,amount fees FROM examfee WHERE rollno = "+rollno+
" union all SELECT head slno, 'ExamFee: '+(substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date))))) +' - '+ltrim(rtrim(reason)) head,amount fees FROM revalfee WHERE rollno = "+rollno+
" union all select 2004 slno,'Printout Due',sum(noofpages)*3 amount from sxcce.dbo.printouts where rollno="+rollno+" and printedorregistered=1 " +
" union all select 2005 slno,'Library Fine',sum(fine)amount from library.dbo.libfine where rollno='"+rollno+"' "+
" ) as uq  left outer join (select substring(('000'+ltrim(rtrim(str(day(max(date)))))), len('000'+ltrim(rtrim(str(day(max(date))))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(max(date)))))), len('000'+ltrim(rtrim(str(month(max(date))))))-1,2)+'/'+ ltrim(rtrim(str(year(max(date))))) pdate, "+
" max(rollno) rollno,max(head) h1,sum(amount) payment from PAYMENT where rollno="+rollno+" group by head) p on uq.slno=p.h1 "+
" left outer join (select sum(amount) refund,max(head) h2 from refund where rollno="+rollno+") c on uq.slno=c.h2 "+
" where "+quer1+" (ISNULL(uq.fees,0)+ISNULL(p.payment,0))!=0  order by uq.head";
rs = stmt.executeQuery(quer);
count=0;
totalamount=0;
totalpaid=0;
totalrefunded=0;
headcount=0;
while(rs.next())
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]=rs.getString(2);
  headamount[headcount]=rs.getFloat(3);
  paid[headcount]=rs.getFloat(5);
  refunded[headcount]=rs.getFloat(6);
  headamount1[headcount]=0;
  total[headcount]=headamount[headcount]+headamount1[headcount];
  balance[headcount]=(total[headcount]+refunded[headcount])-paid[headcount];
  totalamount+=total[headcount];
  totalpaid+=paid[headcount];
  totalrefunded+=refunded[headcount];
  yesno[headcount]=1;

//if(balance[headcount]<=0.0 && total[i]==0.0 && paid[i]==0.0)yesno[i]=0;
if(balance[headcount]==0.0)yesno[headcount]=0;
if(Integer.parseInt(headslno[headcount])==145) {yesno[headcount]=1; ajk=1;}
  headcount++;
}
if(ajk==0)
{
  headslno[headcount]="145";
  headname[headcount]="Extra Paid";
  headamount[headcount]=0;
  paid[headcount]=0;
  refunded[headcount]=0;
  headamount1[headcount]=0;
  total[headcount]=headamount[headcount]+headamount1[headcount];
  balance[headcount]=(total[headcount]+refunded[headcount])-paid[headcount];
  yesno[headcount]=1;
  headcount++;
}
rs.close();
  totalbalance=(totalamount+totalrefunded)-totalpaid;

stmt.close();
con.close();
}catch(Exception e){error=(e.toString()+quer);}
finally{connMgr.freeConnection("accounts",con);}
return error; 
}




public int Viewfees()
{
String quer="";
int found=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
//quer="select fslno from feeassign where academicyear='"+academicyear+"' and rollno="+name;
quer="select fslno from feeassign where rollno="+name;
rs = stmt.executeQuery(quer);
if(rs.next())
{
feesslno=rs.getString(1);
found=1;
}
rs.close();

//quer="select slno from feesstructureindividualidentify where academicyear='"+academicyear+"' and rollno="+name;
quer="select slno from feesstructureindividualidentify where rollno="+name;
rs = stmt.executeQuery(quer);
if(rs.next())
{
feesindividualslno=rs.getString(1);
found=1;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}


public int Viewfullfees()
{

int found=0;
quer="select slno,rollno,academicyear from feesstructureindividualidentify where slno="+feesindividualslno;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
slno=rs.getString(1);
name=rs.getString(2);
academicyear=rs.getString(3);
found=1;
}
rs.close();

//quer="SELECT     head, reason, amount FROM dues WHERE rollno ="+name;


quer="SELECT     dues.head, dues.reason, substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),dues.amount FROM dues WHERE (dues.rollno = "+name+")";
rs = stmt.executeQuery(quer);
while(rs.next())
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]="Fine/Due("+rs.getString(2)+") "+rs.getString(3);
  headamount[headcount]=rs.getFloat(4);
  headamount1[headcount]=0;
  headcount++;
}
rs.close();


quer="SELECT     head, reason, substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),amount FROM busfee WHERE rollno = "+name;
rs = stmt.executeQuery(quer);
if(rs.next())
{
do
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]="Bus Fee("+rs.getString(2)+") "+rs.getString(3);
  headamount[headcount]=rs.getFloat(4);
  headamount1[headcount]=0;
  headcount++;
}while(rs.next());
}
else
{
quer="SELECT head, 'BusFee', substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),amount FROM payment WHERE head>3000 and head<4000 and rollno = "+name;
rs = stmt.executeQuery(quer);
while(rs.next())
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]=rs.getString(2)+" "+rs.getString(3);
  headamount[headcount]=0;
  headamount1[headcount]=0;
  headcount++;
}

	
}

rs.close();


quer="SELECT     head, reason, substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),amount FROM examfee WHERE rollno = "+name;
rs = stmt.executeQuery(quer);
while(rs.next())
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]="Exam Fee("+rs.getString(2)+") "+rs.getString(3);
  headamount[headcount]=rs.getFloat(4);
  headamount1[headcount]=0;
  headcount++;
}
rs.close();


quer="SELECT     head, reason, substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(date))))), len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),amount FROM revalfee WHERE rollno = "+name;
rs = stmt.executeQuery(quer);
while(rs.next())
{
  headslno[headcount]=rs.getString(1);
  headname[headcount]="Revaluation Fee("+rs.getString(2)+") "+rs.getString(3);
  headamount[headcount]=rs.getFloat(4);
  headamount1[headcount]=0;
  headcount++;
}
rs.close();


//quer=PrintLib();

                    quer="select printpagerate from sxcce.dbo.variables";
                    rs = stmt.executeQuery(quer);
                    if(rs.next())
                    {
      	              printpagerate=rs.getFloat(1);
                    }
					rs.close();
                    prndue=0;
                    libfine=0;
					quer="select sum(noofpages) from sxcce.dbo.printouts where rollno="+name+"  and printedorregistered=1";
                    rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							prndue1=rs.getInt(1);
							prndue=((float)prndue1)*printpagerate;
						}
					rs.close();

					quer="select sum(fine) from library.dbo.libfine where rollno='"+name+"'";
                    rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							libfine=rs.getFloat(1);
						}
					rs.close();

						headname[headcount]="Printout Due";
						headslno[headcount]="2004";
						headamount[headcount]=prndue;
						headamount1[headcount]=0;
						headcount++;

						headname[headcount]="Library Fine";
						headslno[headcount]="2005";
						headamount[headcount]=libfine;
						headamount1[headcount]=0;
						headcount++;





float ajk=0;
int i;

for( i=0;i<headcount;i++)
{
quer="select amount from feesstructureamount "+
" where fsino="+feesslno+" and fshno="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
headamount[i]=rs.getFloat(1);
}
rs.close();

quer="SELECT     SUM(feesstructureindividualamount.amount) "+
"FROM feesstructureindividualamount,feesstructureindividualidentify "+
"WHERE   fsiino=slno and rollno="+name+" and feesstructureindividualamount.fshno="+headslno[i];

//quer="select amount from feesstructureindividualamount "+
//" where fsiino="+feesindividualslno+" and fshno="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
headamount1[i]=rs.getFloat(1);
}
rs.close();


//quer="select sum(amount) from payment where rollno="+name+" and academicyear='"+academicyear+"' and head="+headslno[i];
quer="select sum(amount) from payment where rollno="+name+" and head="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid[i]=rs.getFloat(1);
}
rs.close();

quer="select sum(amount) from refund where rollno="+name+" and head="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
	refunded[i]=rs.getFloat(1);
}
rs.close();

int zz=0;
quer="select substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),amount,chelan from payment where rollno="+name+" "+
//"and academicyear='"+academicyear+"' and head="+headslno[i]+" order by date";
"and head="+headslno[i]+" order by date";

rs = stmt.executeQuery(quer);
while(rs.next())
{
	paymentdate[i][zz]=rs.getString(1);
	paymentamount[i][zz]=rs.getFloat(2);
	chelan[i][zz++]=rs.getInt(3);
}
rs.close();

zz=0;
quer="select substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),amount,chelan from refund where rollno="+name+" "+
//"and academicyear='"+academicyear+"' and head="+headslno[i]+" order by date";
"and head="+headslno[i]+" order by date";

rs = stmt.executeQuery(quer);
while(rs.next())
{
	refunddate[i][zz]=rs.getString(1);
	refundamount[i][zz]=rs.getFloat(2);
	rchelan[i][zz++]=rs.getInt(3);
}
rs.close();

yesno[i]=1;
total[i]=headamount[i]+headamount1[i];
balance[i]=(total[i]+refunded[i])-paid[i];
totalamount+=total[i];
totalpaid+=paid[i];
totalrefunded+=refunded[i];
//totalbalance+=balance[i];

if(balance[i]<=0.0 && total[i]==0.0 && paid[i]==0.0)yesno[i]=0;

if(Integer.parseInt(headslno[i])==145)
{
	yesno[i]=1;
	ajk=paid[i];

}

totalbalance=(totalamount+totalrefunded)-totalpaid;

}


stmt.close();
con.close();
}catch(Exception e){return 10;}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}



public int Viewheads1()
{
String quer="";
int found=0;
int yr=4;
quer="select year from sxcce.dbo.stud where academicyear in(select academicyear from sxcce.dbo.academicyear) and rollno="+name;

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);

if(rs.next())
{
yr=rs.getInt(1);
}
rs.close();

quer="select * from feesstructureheads order by slno";

if(yr==1) quer="select * from feesstructureheads where slno not in(140,155,156,157,159,160,161,1010,1002,1006,1014,1015,1016,1011,1003,1007,1012,1004,1008,117,151,120,147,123,126,129,118,152,121,148,124,127,131,119,153,122,149,125,128,130) order by slno";

if(yr==2) quer="select * from feesstructureheads where slno not in(140,156,157,160,161,1011,1003,1007,1015,1016,1012,1004,1008,118,152,121,148,124,127,131,119,153,122,149,125,128,130) order by slno";

if(yr==3) quer="select * from feesstructureheads where slno not in(140,157,161,1012,1004,1008,1016,119,153,122,149,125,128,130) order by slno";

if(yr==4) quer="select * from feesstructureheads order by slno";

rs = stmt.executeQuery(quer);

while(rs.next())
{
headslno[headcount]=rs.getString(1);
headname[headcount]=rs.getString(2);
headcount++;
found=1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}

public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 


public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 

public void setslno(String slno) 
{ 
this.slno = slno; 
} 
public String getslno() 
{ 
return this.slno; 
} 


public String[] getmatchslno()
{
	return this.matchslno;
}
public String[] getmatchname()
{
	return this.matchname;
}

public String[] getmatchacademicyear()
{
	return this.matchacademicyear;
}


public void setheadslno(String[] headslno) 
{ 
this.headslno = headslno; 
} 

public String[] getheadslno()
{
	return this.headslno;
}
public String[] getheadname()
{
	return this.headname;
}


public int getcount()
{
	return this.count;
}

public int getheadcount()
{
	return this.headcount;
}
public void setheadcount(int headcount)
{
	this.headcount=headcount;
}


 public void setfirstslno(String firstslno) 
{ 
this.firstslno = firstslno; 
} 
public String getfirstslno() 
{ 
return this.firstslno; 
} 

 public void setheadamount(float[] headamount) 
{ 
this.headamount = headamount; 
} 
public float[] getheadamount() 
{ 
return this.headamount; 
} 

public void setbalance(float[] balance) 
{ 
this.balance = balance; 
} 
public float[] getbalance() 
{ 
return this.balance; 
} 

public void settotal(float[] total) 
{ 
this.total = total; 
} 
public float[] gettotal() 
{ 
return this.total; 
} 

public void setpaid(float[] paid) 
{ 
this.paid = paid; 
} 
public float[] getpaid() 
{ 
return this.paid; 
} 

public void setrefunded(float[] refunded) 
{ 
this.refunded = refunded; 
} 
public float[] getrefunded() 
{ 
return this.refunded; 
} 

public void setyesno(int[] yesno) 
{ 
this.yesno = yesno; 
} 
public int[] getyesno() 
{ 
return this.yesno; 
} 


public void setheadamount1(float[] headamount1) 
{ 
this.headamount1 = headamount1; 
} 
public float[] getheadamount1() 
{ 
return this.headamount1; 
} 


public void setpaymentamount(float[][] paymentamount) 
{ 
this.paymentamount = paymentamount; 
} 
public float[][] getpaymentamount() 
{ 
return this.paymentamount; 
} 

public void setrefundamount(float[][] refundamount) 
{ 
this.refundamount = refundamount; 
} 
public float[][] getrefundamount() 
{ 
return this.refundamount; 
} 

public void setchelan(int[][] chelan) 
{ 
this.chelan = chelan; 
} 
public int[][] getchelan() 
{ 
return this.chelan; 
} 


public void setrchelan(int[][] rchelan) 
{ 
this.rchelan = rchelan; 
} 
public int[][] getrchelan() 
{ 
return this.rchelan; 
} 

public void settotchelan(int[] totchelan) 
{ 
this.totchelan = totchelan; 
} 
public int[] gettotchelan() 
{ 
return this.totchelan; 
} 

public void setpaymentdate(String[][] paymentdate) 
{ 
this.paymentdate = paymentdate; 
} 
public String[][] getpaymentdate() 
{ 
return this.paymentdate; 
} 

public void setrefunddate(String[][] refunddate) 
{ 
this.refunddate = refunddate; 
} 
public String[][] getrefunddate() 
{ 
return this.refunddate; 
} 


public void setlastslno(String lastslno) 
{ 
this.lastslno = lastslno; 
} 
public String getlastslno() 
{ 
return this.lastslno; 
} 

public void settotalamount(float totalamount) 
{ 
this.totalamount = totalamount; 
} 
public float gettotalamount() 
{ 
return this.totalamount; 
} 

public void settotalpaid(float totalpaid) 
{ 
this.totalpaid = totalpaid; 
} 
public float gettotalpaid() 
{ 
return this.totalpaid; 
} 

public void settotalrefunded(float totalrefunded) 
{ 
this.totalrefunded = totalrefunded; 
} 
public float gettotalrefunded() 
{ 
return this.totalrefunded; 
} 

public void settotalbalance(float totalbalance) 
{ 
this.totalbalance = totalbalance; 
} 
public float gettotalbalance() 
{ 
return this.totalbalance; 
} 


public void setnextslno(String nextslno) 
{ 
this.nextslno = nextslno; 
} 
public String getnextslno() 
{ 
return this.nextslno; 
} 
public void setpreviousslno(String previousslno) 
{ 
this.previousslno = previousslno; 
} 
public String getpreviousslno() 
{ 
return this.previousslno; 
} 

public String getfeesslno()
{
	return this.feesslno;
}
public void setfeesslno(String feesslno)
{
	this.feesslno=feesslno;
}


public String getfeesindividualslno()
{
	return this.feesindividualslno;
}
public void setfeesindividualslno(String feesindividualslno)
{
	this.feesindividualslno=feesindividualslno;
}

public String getclasss()
{
	return this.classs;
}
public void setclasss(String classs)
{
	this.classs=classs;
}

public String getquer()
{
	return this.quer;
}
public void setquer(String quer)
{
	this.quer=quer;
}


public String getyear()
{
	return this.year;
}
public void setyear(String year)
{
	this.year=year;
}

public String getdate()
{
	return this.date;
}
public void setdate(String date)
{
	this.date=date;
}

public String InsertPayment()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
for (int i=0;i<headcount;i++)
   {
   ps = con.prepareStatement("insert into payment values (?,?,?,?,?,?)");	
   ps.setString(1,date);
   ps.setString(2,name);
   ps.setString(3,academicyear);
   ps.setString(4,headslno[i]);
   ps.setFloat(5,paid[i]);
   ps.setInt(6,totchelan[i]);
  
   if(paid[i]>0)
    ps.executeUpdate();
   ps.close();
   
   ps = con.prepareStatement("insert into refund values (?,?,?,?,?,?)");	
   ps.setString(1,date);
   ps.setString(2,name);
   ps.setString(3,academicyear);
   ps.setString(4,headslno[i]);
   ps.setFloat(5,refunded[i]);
   ps.setInt(6,totchelan[i]);
  
   if(refunded[i]>0)
    ps.executeUpdate();
   ps.close();

   }
con.close();
}catch(Exception e){return "Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}


public String OtherPayment(String rollno1,String date1,float amount1,int billno1,int acc1,String reason1,String academicyear1)
{
	String sql="";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
					Statement stmt = con.createStatement();
					ResultSet rs;
int acchead=0;
PreparedStatement ps;
   if(amount1>0)
   {

		if (acc1==1)
		{ 
			  	    rs = stmt.executeQuery("select max(head)+1 from dues where rollno="+rollno1);
					if(rs.next()) 					{						acchead=rs.getInt(1);					}					rs.close();
					if (acchead<2006) acchead=2006;
					sql="insert into dues values(?,?,?,?,?,?)";
		}
		if (acc1==2)
		{ 
					rs = stmt.executeQuery("select max(head)+1 from busfee where rollno="+rollno1);
					if(rs.next())					{						acchead=rs.getInt(1);					}					rs.close();
					if (acchead<3001) acchead=3001;
					sql="insert into busfee values(?,?,?,?,?,?)";
		}	
		if (acc1==3)
		{ 
					rs = stmt.executeQuery("select max(head)+1 from examfee where rollno="+rollno1);
					if(rs.next())					{						acchead=rs.getInt(1);					}					rs.close();
					if (acchead<4001) acchead=4001;
					sql="insert into examfee values(?,?,?,?,?,?)";
		}	

		if (acc1==4)
		{ 
					rs = stmt.executeQuery("select max(head)+1 from revalfee where rollno="+rollno1);
					if(rs.next())					{						acchead=rs.getInt(1);					}					rs.close();
					if (acchead<5001) acchead=5001;
					sql="insert into revalfee values(?,?,?,?,?,?)";
		}	

		if (acc1==5)
		{ 
			acchead=145;
		}
        else
        {
					ps=con.prepareStatement(sql);
					ps.setString(1,date1);
					ps.setString(2,rollno1);
					ps.setInt(3,acchead);
					ps.setString(4,reason1);
					ps.setFloat(5,amount1);
					ps.setString(6,"bill");
					int i = ps.executeUpdate();
					ps.close();
        }

   ps = con.prepareStatement("insert into payment values (?,?,?,?,?,?)");	
   ps.setString(1,date1);
   ps.setString(2,rollno1);
   ps.setString(3,academicyear1);
   ps.setInt(4,acchead);
   ps.setFloat(5,amount1);
   ps.setInt(6,billno1);
    ps.executeUpdate();
   ps.close();
   }

con.close();
}catch(Exception e){return "Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}

}

