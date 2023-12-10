package accounts;
import java.sql.*;
import dd.DBConnectionManager;
public class feesstructureindividualamount{
String matchname[] = new String[1000];
String matchslno[] = new String[1000];
String matchacademicyear[] = new String[1000];
String report[][] = new String[10][10];

    String fdate="";
    String tdate="";
	float printpagerate=0;
	float prndue =0;
	float libfine =0;
	int prndue1 =0;
	float libdue =0;
	float allpaid =0;


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


public String bottommenu()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery("select max(slno) from feesstructureindividualidentify");
if(rs.next())
{
lastslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select min(slno) from feesstructureindividualidentify");
if(rs.next())
{
firstslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select max(slno) from feesstructureindividualidentify where slno<"+slno);
if(rs.next())
{
previousslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select min(slno) from feesstructureindividualidentify where slno>"+slno);
if(rs.next())
{
nextslno=rs.getString(1);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return slno; 
}




public String Maxslno()
{
	int temp=100;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select max(slno) from feesstructureindividualidentify");
if(rs.next())
{
temp=rs.getInt(1);
}
if(temp==0)temp=101;
else temp++;
slno=temp+"";
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return slno; 
}

public String Match(int search)
{count=0;
String quer="select slno,name,academicyear from feesstructureindividualidentify "+
" where slno="+slno;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
if(search==1){
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchslno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	matchacademicyear[count]=rs.getString(3);
   	count++;
   }
rs.close();
}
String tripleh="";
if(!academicyear.equals("0"))
  {
//  	tripleh=" and academicyear = '"+academicyear+"' ";
  	tripleh=" ";
  }
if(search==2){
quer="select slno,name,academicyear from feesstructureindividualidentify "+
" where name like '"+name+"%' "+tripleh+" order by name";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchslno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	matchacademicyear[count]=rs.getString(3);
   	count++;
   }
rs.close();
quer="select slno,name from feesstructureindividualidentify "+
" where head like '%"+name+"%' and head not like '"+name+"%' "+tripleh+" order by head";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchslno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	count++;
   }
rs.close();
}

stmt.close();
con.close();
}catch(Exception e){return quer+e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return "suc";
}

public int InsertCheck()
   {
int found=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from feesstructureindividualidentify where slno="+slno);
if(rs.next())
{
found=1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}
   
public String Insert()
{
int vie=InsertCheck();
if(slno.equals("0") || vie==1)Maxslno();	
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps = con.prepareStatement("insert into feesstructureindividualidentify values (?,?,?)");
ps.setString(1,slno);
ps.setString(2,name);
ps.setString(3,academicyear);
int i;
i = ps.executeUpdate();
ps.close();
for (i=0;i<headcount;i++)
   {
   ps = con.prepareStatement("insert into feesstructureindividualamount values (?,?,?)");	
   ps.setString(1,slno);
   ps.setString(2,headslno[i]);
   ps.setFloat(3,headamount[i]);
   if(headamount[i]!=0)
    ps.executeUpdate();
   ps.close();
   }
con.close();
}catch(Exception e){return "Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}
public String Delete()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
ps = con.prepareStatement("delete from feesstructureindividualamount where fsiino=?");
ps.setString(1,slno); 
int i = ps.executeUpdate();
ps.close();
ps = con.prepareStatement("delete from feesstructureindividualidentify where slno=?");
ps.setString(1,slno); 
i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}

public String Update()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
ps = con.prepareStatement("delete from feesstructureindividualamount where fsiino=?");
ps.setString(1,slno); 
int i = ps.executeUpdate();
ps.close();
for (i=0;i<headcount;i++)
   {
   ps = con.prepareStatement("insert into feesstructureindividualamount values (?,?,?)");	
   ps.setString(1,slno);
   ps.setString(2,headslno[i]);
   ps.setFloat(3,headamount[i]);
   if(headamount[i]!=0)
      ps.executeUpdate();
   ps.close();
   }
con.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
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

int zzz=0;
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
	refunddate[i][zzz]=rs.getString(1);
	refundamount[i][zzz]=rs.getFloat(2);
	rchelan[i][zzz++]=rs.getInt(3);
}
rs.close();

if(Integer.parseInt(headslno[i])==145)
{
quer="select substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),amount,chelan from payment where rollno="+name+" "+
"and head >2005 and head<3000 and head not in (select head from dues where rollno="+name+") order by date";

rs = stmt.executeQuery(quer);
while(rs.next())
{
	paymentdate[i][zz]=rs.getString(1);
	paymentamount[i][zz]=rs.getFloat(2);
	chelan[i][zz++]=rs.getInt(3);
}
rs.close();

quer="select sum(amount) from payment where rollno="+name+" and head >2005 and head<3000 and head not in (select head from dues where rollno="+name+")";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid[i]=paid[i]+rs.getFloat(1);
}
rs.close();

}

quer="select sum(amount) from payment where rollno="+name;
rs = stmt.executeQuery(quer);
if(rs.next())
{
	allpaid=rs.getFloat(1);
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


public int View()
{
String quer="";
int found=0;
quer="select slno,rollno,academicyear from feesstructureindividualidentify where slno="+slno;
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
if(found==0)
   {
//	quer="select slno,rollno,academicyear from feesstructureindividualidentify where rollno='"+name+"' and academicyear='"+academicyear+"'";
	quer="select slno,rollno,academicyear from feesstructureindividualidentify where rollno='"+name;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	{
	slno=rs.getString(1);
	name=rs.getString(2);
	academicyear=rs.getString(3);
	found=1;
	}
	rs.close();   	
   }
if(found>0 && headcount>0)
for(int i=0;i<headcount;i++)
{
quer="select amount from feesstructureindividualamount "+
" where fsiino="+slno+" and fshno="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
headamount[i]=rs.getFloat(1);
totalamount+=headamount[i];
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}

public int Viewheads()
{
String quer="";
int found=0;
quer="select * from feesstructureheads order by slno";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
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

public float getallpaid() 
{ 
return this.allpaid; 
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

public String InsertClassPayment(String date1,String rollno1, String ayear1,String head1,Float amount1,int chellan1)
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
//for (int i=0;i<count1;i++)
   {
   ps = con.prepareStatement("insert into payment values (?,?,?,?,?,?)");	
   ps.setString(1,date1);
   ps.setString(2,rollno1);
   ps.setString(3,ayear1);
   ps.setString(4,head1);
   ps.setFloat(5,amount1);
   ps.setInt(6,chellan1);
  
   if(amount1>0)
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

public void setreport(String[][] report) 
{ 
this.report = report; 
} 
public String[][] getreport() 
{ 
return this.report; 
} 


}



/*
-------------------------------------------------------------------------------------------
<% String address1=""; 
String address2=""; 
String address3=""; 
String cell=""; 
String name=""; 
String phone=""; 
String pincode=""; 
String slno=""; 
%>
<jsp:useBean class="xavier.xavier." id="sss" >
<jsp:setProperty name="sss" property="*" />
<%
address1=sss.getaddress1(); 
address2=sss.getaddress2(); 
address3=sss.getaddress3(); 
cell=sss.getcell(); 
name=sss.getname(); 
phone=sss.getphone(); 
pincode=sss.getpincode(); 
slno=sss.getslno(); 
%>
</jsp:useBean>
-------------------------------------------------------------------------------------------
*/
