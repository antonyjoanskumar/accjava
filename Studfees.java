package sxcce;
import java.sql.*;
import java.util.*;
import dd.DBConnectionManager;
public class Studfees
	{
		String report[][] = new String[35][4000];
		String rollno[] = new String[4000];
		String rep[] = new String[50];
		String feestructure[] = new String[4000];
		String dno="";
		String shortdname="";
		String year="";
//		String date="";
//		int head=0;
//		String reason="";
		String academicyear="";
		int count=0;
		String name="";
		String rno="";
		String slno="";
        int fineamt[] = new int[4000];
        int roll[] = new int[4000];
		
int fslno[] = new int[4000];
String fname[] = new String[4000];
int headcount=0;
		
public int studcert(int roll)
{
int cert=0;
String cdate="";
Calendar cal = new GregorianCalendar();
cdate=(cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
String quer="";

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select count(*)+1 from feecert where rollno="+roll;

rs= stmt.executeQuery(quer);
if(rs.next())
{
	cert=rs.getInt(1);
}
rs.close();

/*
quer="select max(id)+1 from feecert";

rs= stmt.executeQuery(quer);
if(rs.next())
{
	cert=rs.getInt(1);
}
rs.close();
*/
quer="insert into feecert values(?,?,?)";
PreparedStatement ps;
ps = con.prepareStatement(quer);
ps.setInt(1,roll);
ps.setString(2,cdate);
ps.setInt(3,cert);
int i=ps.executeUpdate();
ps.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return cert; 
}


public int studreceiptno(int roll)
{
int cert=0;
String cdate="";
Calendar cal = new GregorianCalendar();
cdate=(cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
String quer="";

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select count(*)+1 from feereceipt where rollno="+roll;

rs= stmt.executeQuery(quer);
if(rs.next())
{
	cert=rs.getInt(1);
}
rs.close();
/*
quer="insert into feereceipt values(?,?,?)";
PreparedStatement ps;
ps = con.prepareStatement(quer);
ps.setInt(1,roll);
ps.setString(2,cdate);
ps.setInt(3,cert);
int i=ps.executeUpdate();
ps.close();
*/
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return cert; 
}

public int studreceipt(int roll)
{
int cert=0;
String cdate="";
Calendar cal = new GregorianCalendar();
cdate=(cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
String quer="";

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select count(*)+1 from feereceipt where rollno="+roll;

rs= stmt.executeQuery(quer);
if(rs.next())
{
	cert=rs.getInt(1);
}
rs.close();

quer="insert into feereceipt values(?,?,?)";
PreparedStatement ps;
ps = con.prepareStatement(quer);
ps.setInt(1,roll);
ps.setString(2,cdate);
ps.setInt(3,cert);
int i=ps.executeUpdate();
ps.close();

stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return cert; 
}




public String Classselect()
{count=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;

if(rno.equals(""))rno="0";
rs = stmt.executeQuery("select stud.rollno,stud.name,year,department.sf from "+
"stud,department,student where stud.rollno=student.rollno and stud.rollno ="+rno+" "+
//"and stud.academicyear='"+academicyear+"' and active in (0,1,2) and "+
"and stud.academicyear='"+academicyear+"' and "+
"stud.department=department.dno order by stud.name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<5;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();

if(rno.equals("0"))
{
rs = stmt.executeQuery("select stud.rollno,stud.name,year,department.sf from "+
"stud,department,student where stud.rollno=student.rollno and  stud.name like '"+name+"%' "+
//"and stud.academicyear='"+academicyear+"' and active in (0,1) and "+
"and stud.academicyear='"+academicyear+"' and "+
"stud.department=department.dno order by stud.name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<5;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();

rs = stmt.executeQuery("select stud.rollno,stud.name,year,department.sf from "+
"stud,department,student where stud.rollno=student.rollno and stud.name like '%"+name+"%' "+
"and stud.name not like '"+name+"%' "+
//"and stud.academicyear='"+academicyear+"' and active in (0,1) and "+
"and stud.academicyear='"+academicyear+"'  and "+
"stud.department=department.dno order by stud.name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<5;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return rno+e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return "sus"; 
}




public String Classselect1()
{count=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
if(rno.equals(""))rno="0";
rs = stmt.executeQuery("select accounts.dbo.feesstructureindividualidentify.rollno,stud.name, "+
"year,department.sf,accounts.dbo.feesstructureindividualidentify.slno from "+
"stud,department,accounts.dbo.feesstructureindividualidentify "+
" where stud.rollno ="+rno+" and "+
"accounts.dbo.feesstructureindividualidentify.rollno=stud.rollno "+
"and stud.academicyear='"+academicyear+"' and "+
"stud.department=department.dno order by name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<6;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();

if(rno.equals("0"))
{
rs = stmt.executeQuery("select accounts.dbo.feesstructureindividualidentify.rollno,stud.name, "+
"year,department.sf,accounts.dbo.feesstructureindividualidentify.slno from "+
"stud,department,accounts.dbo.feesstructureindividualidentify "+
" where  stud.name like '"+name+"%'  and "+
"accounts.dbo.feesstructureindividualidentify.rollno=stud.rollno "+
"and stud.academicyear='"+academicyear+"' and "+
"stud.department=department.dno order by name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<6;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();

rs = stmt.executeQuery("select accounts.dbo.feesstructureindividualidentify.rollno,stud.name, "+
"year,department.sf,accounts.dbo.feesstructureindividualidentify.slno from "+
"stud,department,accounts.dbo.feesstructureindividualidentify "+
" where  stud.name like '"+name+"%' and stud.name not like '"+name+"%' and "+
"accounts.dbo.feesstructureindividualidentify.rollno=stud.rollno "+
"and stud.academicyear='"+academicyear+"' and "+
"stud.department=department.dno order by name,year,department.sf");
while(rs.next())
{
	report[0][count]=(count+1)+"";
	for(int i=1;i<6;i++)
	{
		report[i][count]=rs.getString(i);
	}
	count++;
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return "sus"; 
}



public String report()
{
	
String quer="select rollno,name,collegecategory.cname, "+
"directorlateral,religion,state from "+
"student,collegecategory where rollno in "+
" (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and stud.year="+year+" and "+
//"stud.department="+dno+" and active in (0,1) and "+
"stud.department="+dno+" and "+
"stud.academicyear='"+academicyear+"') "+
"and student.ccategory=collegecategory.slno order by rollno";

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
						
//					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
//					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<7;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();

					quer="select * from accounts.dbo.feesstructureidentify";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	fslno[headcount]=rs.getInt(1);
				  	    fname[headcount]=rs.getString(2);
					  	headcount++;   
					  }
					rs.close();

					quer="select sf from sxcce.dbo.department where dno="+dno;

					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	shortdname=rs.getString(1);
					  }
					rs.close();

					
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					finally{connMgr.freeConnection("xavier",con);}

					return quer;
		    }


public String report1(int roll)
		    {
String quer="select stud.rollno,stud.name,collegecategory.cname, "+
"directorlateral,religion,state,initial,stud.year,dname,academicyear,sex,dno,community from stud,department,"+
"student,collegecategory where stud.rollno=student.rollno and department.dno=student.departmentno "+
" and student.rollno="+roll+
" and student.ccategory=collegecategory.slno and academicyear=(select academicyear from academicyear)";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
						
//					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
//					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<7;i++)
					  	   report[i][count]=rs.getString(i);
					  	for(int i=7;i<14;i++)
					  	   rep[i-7]=rs.getString(i);
					  	   report[33][count]=rep[6];
					  	count++;   
					  }
					rs.close();

					quer="select * from accounts.dbo.feesstructureidentify";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	fslno[headcount]=rs.getInt(1);
				  	    fname[headcount]=rs.getString(2);
					  	headcount++;   
					  }
					rs.close();

					quer="select sf from sxcce.dbo.department where dno="+dno;

					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	shortdname=rs.getString(1);
					  }
					rs.close();

					
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					finally{connMgr.freeConnection("xavier",con);}
					return quer;
		    }


public String assignView()
{
String quer="";
int found=0;
headcount=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
					quer="select * from accounts.dbo.feesstructureidentify";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	fslno[headcount]=rs.getInt(1);
				  	    fname[headcount]=rs.getString(2);
					  	headcount++;   
					  }
					rs.close();
for(int i=0;i<1;i++)
{

report[7][i]="0";
quer="select fslno from feeassign where rollno="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[7][i]=rs.getString(1);
found=1;
}
rs.close();

}					
		stmt.close();
        con.close();
		}catch(Exception e){return quer+e.toString();}
		finally{connMgr.freeConnection("accounts",con);}


		return quer;
 }

public int View()
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
for(int i=0;i<count;i++)
{

report[7][i]="0";
quer="select fslno from feeassign where rollno="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[7][i]=rs.getString(1);
found=1;
}
rs.close();

int famt=0;
report[8][i]="0";
quer="SELECT SUM(amount) FROM feesstructureamount where fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

//er="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i]+"  AND (feesstructureindividualamount.fshno <= 138) AND (feesstructureindividualamount.fshno >= 135)";
quer="SELECT SUM(amount) FROM payment where head<1999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt-rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM payment where head=999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM refund where rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

report[8][i]= Integer.toString(famt);


int iamt=0;
int iiamt=0;
int iiiamt=0;
int ivamt=0;
quer="SELECT SUM(amount) FROM feesstructureamount where fshno not in (144,145,135,136,137,138,999) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=iamt+rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (117,120,123,126,129,147,151,155,159,1002,1006,1010,1014) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (117,120,123,126,129,147,151,155,159,1002,1006,1010,1014) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiamt=iiamt+rs.getInt(1);}
rs.close();
iamt=iamt-iiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (118,121,124,127,131,148,152,156,160,1003,1007,1011,1015) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (118,121,124,127,131,148,152,156,160,1003,1007,1011,1015) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiiamt=iiiamt+rs.getInt(1);}
rs.close();
iamt=iamt-iiiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (102,119,122,125,128,130,149,153,157,161,1004,1008,1012,1016) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ivamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (102,119,122,125,128,130,149,153,157,161,1004,1008,1012,1016) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ivamt=ivamt+rs.getInt(1);}
rs.close();
iamt=iamt-ivamt;

int pamt=0;
//quer="SELECT SUM(amount) FROM payment where head<999 and head not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
quer="SELECT SUM(amount) FROM payment where head<1999 and head not in (144,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { pamt=rs.getInt(1); }
rs.close();

int ramt=0;
quer="SELECT SUM(amount) FROM refund where rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ramt=rs.getInt(1); }
rs.close();


report[9][i]="0";
report[9][i]= Integer.toString(iamt);
report[10][i]="0";
report[10][i]= Integer.toString(iiamt);
report[11][i]="0";
report[11][i]= Integer.toString(iiiamt);
report[12][i]="0";
report[12][i]= Integer.toString(ivamt);
report[13][i]="0";
report[13][i]= Integer.toString(pamt);
report[14][i]="0";
report[14][i]= Integer.toString((iamt+iiamt+iiiamt+ivamt+ramt)-pamt);
report[15][i]="0";
report[15][i]= Integer.toString(ramt);


}
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}
return found; 
}


public int TutionFeeView()
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
for(int i=0;i<count;i++)
{

report[7][i]="0";
quer="select fslno from feeassign where rollno="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[7][i]=rs.getString(1);
found=1;
}
rs.close();

int famt=0;
report[8][i]="0";
quer="SELECT SUM(amount) FROM feesstructureamount where fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

//er="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i]+"  AND (feesstructureindividualamount.fshno <= 138) AND (feesstructureindividualamount.fshno >= 135)";
quer="SELECT SUM(amount) FROM payment where head<1999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt-rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM payment where head=999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM refund where rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

report[8][i]= Integer.toString(famt);


int iamt=0;
int iiamt=0;
int iiiamt=0;
int ivamt=0;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno=101 and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno=101 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=iamt+rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureamount where fshno=126 and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno=126 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiamt=iiamt+rs.getInt(1);}
rs.close();

//iamt=iamt-iiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno=127 and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno=127 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iiiamt=iiiamt+rs.getInt(1);}
rs.close();

//iamt=iamt-iiiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno=128 and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ivamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno=128 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ivamt=ivamt+rs.getInt(1);}
rs.close();

//iamt=iamt-ivamt;

int pamt=0;
//quer="SELECT SUM(amount) FROM payment where head<999 and head not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
quer="SELECT SUM(amount) FROM payment where head in (101,126,127,128) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { pamt=rs.getInt(1); }
rs.close();

int ramt=0;
quer="SELECT SUM(amount) FROM refund where  head in (101,126,127,128) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ramt=rs.getInt(1); }
rs.close();


report[9][i]="0";
report[9][i]= Integer.toString(iamt);

report[10][i]="0";
report[10][i]= Integer.toString(iiamt);

report[11][i]="0";
report[11][i]= Integer.toString(iiiamt);

report[12][i]="0";
report[12][i]= Integer.toString(ivamt);

report[13][i]="0";
report[13][i]= Integer.toString(pamt);

report[14][i]="0";
report[14][i]= Integer.toString((iamt+iiamt+iiiamt+ivamt+ramt)-pamt);

report[15][i]="0";
report[15][i]= Integer.toString(ramt);


}
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}

return found; 
}


public int View2(int roll)
{
String quer="";
int found=0;
int famt=0;
int bamt=0;
int hd=101;
String sl="";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");

Statement stmt = con.createStatement();
ResultSet rs;
hd=101;
if(rep[1].equals("2")) hd=126;
if(rep[1].equals("3")) hd=127;
if(rep[1].equals("4")) hd=128;

report[7][0]="0";
quer="select fslno from feeassign where rollno="+roll;
rs = stmt.executeQuery(quer);
if(rs.next())
{
sl=rs.getString(1);
found=1;
}
rs.close();

famt=0;
quer="SELECT SUM(amount) FROM feesstructureamount where fshno="+ hd +" and fsino ="+sl;
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino  and fshno="+ hd +" and rollno ="+roll;
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

report[33][0]= Integer.toString(famt);

famt=0;
quer="SELECT SUM(amount) FROM payment where head="+hd+" and rollno ="+roll;
rs = stmt.executeQuery(quer);
if(rs.next()) { famt=rs.getInt(1); }
rs.close();
quer="SELECT SUM(amount) FROM refund where head="+hd+" and rollno ="+roll;
rs = stmt.executeQuery(quer);
if(rs.next()){famt=famt-rs.getInt(1);}
rs.close();
report[34][0]= Integer.toString(famt);

bamt=0;
quer="select sum(amount) from payment where  head between 3001 and 3999 and rollno="+roll+" and academicyear='"+rep[3]+"'";
rs = stmt.executeQuery(quer);
if(rs.next()) { bamt=rs.getInt(1); }
rs.close();
quer="select sum(amount) from refund where  head between 3001 and 3999 and rollno="+roll+" and academicyear='"+rep[3]+"'";
rs = stmt.executeQuery(quer);
if(rs.next()){bamt=bamt-rs.getInt(1);}
rs.close();
report[35][0]= Integer.toString(bamt);

stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}

return found; 
}




public int View1()
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
for(int i=0;i<count;i++)
{

report[7][i]="0";
quer="select fslno from feeassign where rollno="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[7][i]=rs.getString(1);
found=1;
}
rs.close();

int famt=0;
report[8][i]="0";
quer="SELECT SUM(amount) FROM feesstructureamount where fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

//er="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i]+"  AND (feesstructureindividualamount.fshno <= 138) AND (feesstructureindividualamount.fshno >= 135)";
quer="SELECT SUM(amount) FROM payment where head<1999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt-rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM payment where head=999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM refund where rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

report[8][i]= Integer.toString(famt);


int siamt=0;

int iamt=0;
int siiamt=0;
int siiiamt=0;
int sivamt=0;

int edfamt=0;
int cadep=0;

int tiamt=0;
int tiiamt=0;
int tiiiamt=0;
int tivamt=0;

/*
int pedf=0;
int psp=0;

int tp1amt=0;
int tp2amt=0;
int tp3amt=0;
int tp4amt=0;

int sp1amt=0;
int sp2amt=0;
int sp3amt=0;
int sp4amt=0;
*/
String pedf="";
String psp="";
String tp1amt="";
String tp2amt="";
String tp3amt="";
String tp4amt="";

String sp1amt="";
String sp2amt="";
String sp3amt="";
String sp4amt="";


quer="SELECT SUM(amount) FROM feesstructureamount where fshno not in (144,145,135,136,137,138,999) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { iamt=iamt+rs.getInt(1);}
rs.close();

/*
psp="";
quer="SELECT SUM(amount) FROM payment where head<999 and head not in (144,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { psp=rs.getInt(1); }
rs.close();


quer="SELECT SUM(amount) FROM refund where head<1000 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { psp=psp-rs.getInt(1); }
rs.close();

*/
quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (106,108,112,115,146,150,1009,1001,1005) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (106,108,112,115,146,150,1009,1001,1005) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siamt=siamt+rs.getInt(1);}
rs.close();
iamt=iamt-siamt;


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (101) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiamt=rs.getInt(1);}
rs.close();


quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (101) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiamt=tiamt+rs.getInt(1);}
rs.close();
iamt=iamt-tiamt;


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (117,120,123,126,129,147,151,155,159,1002,1006,1010,1014) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (117,120,123,126,129,147,151,155,159,1002,1006,1010,1014) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siiamt=siiamt+rs.getInt(1);}
rs.close();
iamt=iamt-siiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (126) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (126) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiiamt=tiiamt+rs.getInt(1);}
rs.close();
iamt=iamt-tiiamt;


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (118,121,124,127,131,148,152,156,160,1003,1007,1011,1015) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siiiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (118,121,124,127,131,148,152,156,160,1003,1007,1011,1015) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { siiiamt=siiiamt+rs.getInt(1);}
rs.close();
iamt=iamt-siiiamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (127) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiiiamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (127) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tiiiamt=tiiiamt+rs.getInt(1);}
rs.close();
iamt=iamt-tiiiamt;


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (102,119,122,125,128,130,149,153,157,161,1004,1008,1012,1016) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { sivamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (102,119,122,125,128,130,149,153,157,161,1004,1008,1012,1016) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { sivamt=sivamt+rs.getInt(1);}
rs.close();
iamt=iamt-sivamt;

quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (128) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tivamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (128) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { tivamt=tivamt+rs.getInt(1);}
rs.close();
iamt=iamt-tivamt;


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (132) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { edfamt=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (132) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { edfamt=edfamt+rs.getInt(1);}
rs.close();


quer="SELECT SUM(amount) FROM feesstructureamount where fshno in (103) and fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { cadep=rs.getInt(1);}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and fshno in (103) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { cadep=cadep+rs.getInt(1);}
rs.close();

iamt=iamt-edfamt-cadep;


int pamt=0;
//quer="SELECT SUM(amount) FROM payment where head<999 and head not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
quer="SELECT SUM(amount) FROM payment where head<1999 and head not in (145,144,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { pamt=rs.getInt(1); }
rs.close();

int ramt=0;
quer="SELECT SUM(amount) FROM refund where head<1999  and head not in (145,144,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ramt=rs.getInt(1); }
rs.close();




quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (101) and rollno ="+report[1][i]+"  group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp1amt=tp1amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (101) and rollno ="+report[1][i]+"  group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp1amt=tp1amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (106,108,112,115,146,150) and rollno ="+report[1][i]+"  group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp1amt=sp1amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (106,108,112,115,146,150) and rollno ="+report[1][i]+"  group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp1amt=sp1amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (126) and rollno ="+report[1][i]+"  group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp2amt=tp2amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (126) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp2amt=tp2amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (117,120,123,129,147,151) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp2amt=sp2amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (117,120,123,129,147,151) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp2amt=sp2amt+"<br>R: "+rs.getString(1); } rs.close();



quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (127) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp3amt=tp3amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (127) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp3amt=tp3amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (118,121,124,131,148,152) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp3amt=sp3amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (118,121,124,131,148,152) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp3amt=sp3amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (128) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp4amt=tp4amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (128) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { tp4amt=tp4amt+"<br>R: "+rs.getString(1); } rs.close();


quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (102,119,122,125,130,149,153) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp4amt=sp4amt+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (102,119,122,125,130,149,153) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { sp4amt=sp4amt+"<br>R: "+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head in (132) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { pedf=pedf+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head in (132) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { pedf=pedf+"<br>R: "+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM payment where head<1999 and head not in (101,126,127,128,132,106,108,112,115,146,150,117,120,123,129,147,151,118,121,124,131,148,152,102,119,122,125,130,149,153,144,145,135,136,137,138,999) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { psp=psp+"<br>"+rs.getString(1); } rs.close();

quer="SELECT '<b>'+ltrim(str(sum(amount)))+'</b> - '+ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))) FROM refund where head<1999 and head not in (101,126,127,128,132,106,108,112,115,146,150,117,120,123,129,147,151,118,121,124,131,148,152,102,119,122,125,130,149,153,144,145,135,136,137,138,999) and rollno ="+report[1][i] +" group by date";
rs = stmt.executeQuery(quer); while(rs.next()) { psp=psp+"<br>R: "+rs.getString(1); } rs.close();


/*
quer="SELECT SUM(amount) FROM payment where head<999 and head in (101) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp1amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (101) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp1amt=tp1amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (106,108,112,115,146,150) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp1amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (106,108,112,115,146,150) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp1amt=sp1amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (126) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp2amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (126) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp2amt=tp2amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (117,120,123,129,147,151) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp2amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (117,120,123,129,147,151) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp2amt=sp2amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (127) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp3amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (127) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp3amt=tp3amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (118,121,124,131,148,152) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp3amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (118,121,124,131,148,152) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp3amt=sp3amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (128) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp4amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (128) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { tp4amt=tp4amt-rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM payment where head<999 and head in (102,119,122,125,130,149,153) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp4amt=rs.getInt(1); } rs.close();
quer="SELECT SUM(amount) FROM refund where head<1000 and head in (102,119,122,125,130,149,153) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { sp4amt=sp4amt-rs.getInt(1); } rs.close();


quer="SELECT SUM(amount) FROM payment where head<999 and head in (132) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { pedf=rs.getInt(1); } rs.close();

quer="SELECT SUM(amount) FROM refund where head<1000 and head in (132) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { pedf=pedf-rs.getInt(1); } rs.close();

quer="SELECT SUM(amount) FROM payment where head<999 and head not in (101,126,127,128,132,106,108,112,115,146,150,117,120,123,129,147,151,118,121,124,131,148,152,102,119,122,125,130,149,153,144,145,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { psp=rs.getInt(1); } rs.close();

quer="SELECT SUM(amount) FROM refund where head<1000 and head not in (101,126,127,128,132,106,108,112,115,146,150,117,120,123,129,147,151,118,121,124,131,148,152,102,119,122,125,130,149,153,144,145,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer); if(rs.next()) { psp=psp-rs.getInt(1); } rs.close();
*/



//quer="SELECT SUM(amount) FROM payment where head=999 and rollno ="+report[1][i];
//rs = stmt.executeQuery(quer);
//if(rs.next()) { pamt=pamt-rs.getInt(1); }
//rs.close();

report[9][i]="0";
report[9][i]= Integer.toString(siamt);

report[10][i]="0";
report[10][i]= Integer.toString(siiamt);

report[11][i]="0";
report[11][i]= Integer.toString(siiiamt);

report[12][i]="0";
report[12][i]= Integer.toString(sivamt);

report[13][i]="0";
report[13][i]= Integer.toString(tiamt);

report[14][i]="0";
report[14][i]= Integer.toString(tiiamt);

report[15][i]="0";
report[15][i]= Integer.toString(tiiiamt);

report[16][i]="0";
report[16][i]= Integer.toString(tivamt);

report[17][i]="0";
report[17][i]= Integer.toString(edfamt);

report[18][i]="0";
report[18][i]= Integer.toString(iamt);


report[19][i]="0";
report[19][i]= Integer.toString(pamt);

report[20][i]="0";
report[20][i]= Integer.toString(ramt);
/*
report[21][i]="0";
report[21][i]= Integer.toString(tp1amt);
report[22][i]="0";
report[22][i]= Integer.toString(tp2amt);
report[23][i]="0";
report[23][i]= Integer.toString(tp3amt);
report[24][i]="0";
report[24][i]= Integer.toString(tp4amt);

report[25][i]="0";
report[25][i]= Integer.toString(sp1amt);
report[26][i]="0";
report[26][i]= Integer.toString(sp2amt);
report[27][i]="0";
report[27][i]= Integer.toString(sp3amt);
report[28][i]="0";
report[28][i]= Integer.toString(sp4amt);
report[29][i]="0";
report[29][i]= Integer.toString(pedf);
report[30][i]="0";
report[30][i]= Integer.toString(psp);

*/
report[21][i]="";
report[21][i]= tp1amt;
report[22][i]="";
report[22][i]= tp2amt;
report[23][i]="";
report[23][i]= tp3amt;
report[24][i]="";
report[24][i]= tp4amt;

report[25][i]="";
report[25][i]= sp1amt;
report[26][i]="";
report[26][i]= sp2amt;
report[27][i]="";
report[27][i]= sp3amt;
report[28][i]="";
report[28][i]= sp4amt;

report[29][i]="";
report[29][i]= pedf;
report[30][i]="";
report[30][i]= psp;

report[31][i]="0";
report[31][i]= Integer.toString(pamt-ramt);

report[32][i]="0";
report[32][i]= Integer.toString(cadep);

}
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}

return found; 
}





public String Insert()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");

PreparedStatement ps;
for(int i=0;i<count;i++)
{
ps = con.prepareStatement("insert into feeassign values (?,?,?)");
ps.setString(1,rollno[i]);
ps.setString(2,academicyear);
ps.setString(3,feestructure[i]);
int j;
j = ps.executeUpdate();
ps.close();
}
con.close();
}catch(Exception e){return "Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}

return(count+"success1");
}

public String Delete()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
ps = con.prepareStatement("delete from feeassign where "+
"rollno in(select rollno from sxcce.dbo.stud where department=? "+
"and year=? and academicyear=?)");
//ps.setString(1,academicyear); 
ps.setString(1,dno);
ps.setString(2,year);
ps.setString(3,academicyear);
int j = ps.executeUpdate();
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

for(int i=0;i<count;i++)
{

ps = con.prepareStatement("delete from feeassign where rollno =?");
ps.setString(1,rollno[i]);
int j;
j = ps.executeUpdate();
ps.close();

ps = con.prepareStatement("insert into feeassign values (?,?,?)");
ps.setString(1,rollno[i]);
ps.setString(2,academicyear);
ps.setString(3,feestructure[i]);
j = ps.executeUpdate();
ps.close();
}
con.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("accounts",con);}

return("success");
}

public String fineInsert(String date,int head,String reason,String login)
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");

Statement stmt = con.createStatement();
ResultSet rs;
PreparedStatement ps;
for(int i=0;i<count;i++)
{
if(fineamt[i]>0)
{
			rs = stmt.executeQuery("select max(head)+1 from dues where rollno="+roll[i]);
			if(rs.next())
					{
						head=rs.getInt(1);
				}
				if (head<2006) head=2006;
				rs.close();

ps = con.prepareStatement("insert into dues values (?,?,?,?,?,?)");
ps.setString(1,date);
ps.setInt(2,roll[i]);
ps.setInt(3,head);
ps.setString(4,reason);
ps.setInt(5,fineamt[i]);
ps.setString(6,login);
int j;
j = ps.executeUpdate();
ps.close();
}
}
con.close();
}catch(Exception e){return "Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}

return(count+date+roll[0]+head+reason+fineamt[0]+"success1");
}



public String discontinue(String jyear)
		    {
String quer="select rollno,name,sf,directorlateral from student,department where departmentno=dno "+
" and year(yearofjoining)="+jyear+" and active=0 order by directorlateral,dname";

DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");

Statement stmt = con.createStatement();
						
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<5;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					finally{connMgr.freeConnection("xavier",con);}

					return quer;
		    }

public int disView()
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
for(int i=0;i<count;i++)
{

report[7][i]="0";
quer="select fslno from feeassign where rollno="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[7][i]=rs.getString(1);
found=1;
}
rs.close();

int famt=0;
report[8][i]="0";
quer="SELECT SUM(amount) FROM feesstructureamount where fsino ="+report[7][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

//er="SELECT SUM(amount) FROM feesstructureindividualidentify,feesstructureindividualamount where feesstructureindividualidentify.slno = feesstructureindividualamount.fsiino and rollno ="+report[1][i]+"  AND (feesstructureindividualamount.fshno <= 138) AND (feesstructureindividualamount.fshno >= 135)";
quer="SELECT SUM(amount) FROM payment where head<1999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt-rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM payment where head=999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

quer="SELECT SUM(amount) FROM refund where rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
famt=famt+rs.getInt(1);
}
rs.close();

report[8][i]= Integer.toString(famt);


int pamt=0;
//quer="SELECT SUM(amount) FROM payment where head<999 and head not in (144,145,135,136,137,138,999) and rollno ="+report[1][i];
quer="SELECT SUM(amount) FROM payment where head<1999 and head not in (144,135,136,137,138,999) and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { pamt=rs.getInt(1); }
rs.close();

int ramt=0;
quer="SELECT SUM(amount) FROM refund where head<1999 and rollno ="+report[1][i];
rs = stmt.executeQuery(quer);
if(rs.next()) { ramt=rs.getInt(1); }
rs.close();

report[13][i]="0";
report[13][i]= Integer.toString(pamt);

report[15][i]="0";
report[15][i]= Integer.toString(ramt);

report[16][i]="0";
report[16][i]= Integer.toString(pamt-ramt);



}
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("accounts",con);}

return found; 
}



		    

		    
public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public String getdno() 
{ 
return this.dno; 
} 
public void setyear(String year) 
{ 
this.year = year; 
} 
public String getyear() 
{ 
return this.year; 
}
public String[][] getreport()
{
	return this.report;
}

public void setcount(int count)
	{
		this.count=count;
	}

public int getcount()
{
	return this.count;
}
public String getacademicyear()
{
	return this.academicyear;
}
public void setacademicyear(String academicyear)
{
	this.academicyear=academicyear;
}


public String[] getfname()
{
	return this.fname;
}
public void setfname(String[] fname)
{
	this.fname=fname;
}

public String[] getrollno()
{
	return this.rollno;
}
public void setrollno(String[] rollno)
{
	this.rollno=rollno;
}

public String[] getrep()
{
	return this.rep;
}
public void setrep(String[] rep)
{
	this.rep=rep;
}

public String[] getfeestructure()
{
	return this.feestructure;
}
public void setfeestructure(String[] feestructure)
{
	this.feestructure=feestructure;
}

public int[] getfslno()
{
	return this.fslno;
}
public void setfslno(int[] fslno)
{
	this.fslno=fslno;
}

public int[] getfineamt()
{
	return this.fineamt;
}
public void setfineamt(int[] fineamt)
{
	this.fineamt=fineamt;
}

public int[] getroll()
{
	return this.roll;
}
public void setroll(int[] roll)
{
	this.roll=roll;
}

public int getheadcount()
{
	return this.headcount;
}
public void setheadcount(int headcount)
{
	this.headcount=headcount;
}


public String getshortdname()
{
	return this.shortdname;
}
public void setshortdname(String shortdname)
{
	this.shortdname=shortdname;
}


public String getname()
{
	return this.name;
}
public void setname(String name)
{
	this.name=name;
}
public String getrno()
{
	return this.rno;
}
public void setrno(String rno)
{
	this.rno=rno;
}

public String getslno()
{
	return this.slno;
}
public void setslno(String slno)
{
	this.slno=slno;
}



}


