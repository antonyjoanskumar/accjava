package accounts;
import java.sql.*;
import dd.DBConnectionManager;
public class feesstructureamount{
String matchname[] = new String[1000];
String matchslno[] = new String[1000];
String matchacademicyear[] = new String[1000];

int count=0;
String slno;
String firstslno;

String nextslno;
String previousslno;
String lastslno;
String name="";
String academicyear="";

String headname[] = new String[500];
String headslno[] = new String[500];
float headamount[] = new float[500];
int headyear[] = new int[500];
float totalamount=0;
int headcount=0;

public String bottommenu()
{
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery("select max(slno) from feesstructureidentify");
if(rs.next())
{
lastslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select min(slno) from feesstructureidentify");
if(rs.next())
{
firstslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select max(slno) from feesstructureidentify where slno<"+slno);
if(rs.next())
{
previousslno=rs.getString(1);
}
rs.close();
rs = stmt.executeQuery("select min(slno) from feesstructureidentify where slno>"+slno);
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
ResultSet rs = stmt.executeQuery("select max(slno) from feesstructureidentify");
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
String quer="select slno,name,academicyear from feesstructureidentify "+
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
  	tripleh=" ";
  }
if(search==2){
quer="select slno,name,academicyear from feesstructureidentify "+
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
quer="select slno,name from feesstructureidentify "+
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
ResultSet rs = stmt.executeQuery("select * from feesstructureidentify where slno="+slno);
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
PreparedStatement ps = con.prepareStatement("insert into feesstructureidentify values (?,?,?)");
ps.setString(1,slno);
ps.setString(2,name);
ps.setString(3,academicyear);
int i;
i = ps.executeUpdate();
ps.close();
for (i=0;i<headcount;i++)
   {
   ps = con.prepareStatement("insert into feesstructureamount values (?,?,?,?)");	
   ps.setString(1,slno);
   ps.setString(2,headslno[i]);
   ps.setInt(3,headyear[i]);
   ps.setFloat(4,headamount[i]);
   if(headamount[i]>0)
    ps.executeUpdate();
   ps.close();
   }
con.close();
}catch(Exception e){return "Insert Error !! "+ e.toString();}
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
ps = con.prepareStatement("delete from feesstructureamount where fsino=?");
ps.setString(1,slno); 
int i = ps.executeUpdate();
ps.close();
ps = con.prepareStatement("delete from feesstructureidentify where slno=?");
ps.setString(1,slno); 
i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return "Delete Error !! "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}

public String Update()
{
int i=0;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("accounts");
PreparedStatement ps;
ps = con.prepareStatement("delete from feesstructureamount where fsino=?");
ps.setString(1,slno); 
i = ps.executeUpdate();
ps.close();
for (i=0;i<headcount;i++)
   {
   ps = con.prepareStatement("insert into feesstructureamount values (?,?,?,?)");	
   ps.setString(1,slno);
   ps.setString(2,headslno[i]);
   ps.setInt(3,headyear[i]);
   ps.setFloat(4,headamount[i]);
   if(headamount[i]>0)
      ps.executeUpdate();
   ps.close();
   }
con.close();
}catch(Exception e){return "Update Error !! "+ slno + " " + headslno[i]+" "+headamount[i]+" "+ e.toString();}
finally{connMgr.freeConnection("accounts",con);}
return("success");
}


public int View()
{
String quer="";
int found=0;
quer="select slno,name,academicyear from feesstructureidentify where slno="+slno;
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
//	quer="select slno,name,academicyear from feesstructureidentify where name='"+name+"' and academicyear='"+academicyear+"'";
	quer="select slno,name,academicyear from feesstructureidentify where name='"+name;
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
quer="select fyear,amount from feesstructureamount "+
" where fsino="+slno+" and fshno="+headslno[i];
rs = stmt.executeQuery(quer);
if(rs.next())
{
headyear[i]=rs.getInt(1);
headamount[i]=rs.getFloat(2);
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

 public void setheadyear(int[] headyear) 
{ 
this.headyear = headyear; 
} 
public int[] getheadyear() 
{ 
return this.headyear; 
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
