package bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFile {
	private String path="C:\\apache-tomcat-7.0.52\\webapps\\web\\upload\\";
	private File[] dirinfo;
	private List<String> dirname=new ArrayList();

	public ListFile()
	{
		File dir=new File(path);
		if(!dir.isDirectory())
		{
			System.out.println("is not a dir");
		}
		dirinfo=dir.listFiles();
			System.out.println("dirinfo length="+dirinfo.length);
//			System.out.println(dirinfo[4].getName());
//			dirname[0]=dirinfo[0].getName();
//			System.out.println(dirname[0]);
		for(int i=0;i<dirinfo.length;i++)
		{
			//dirname[i]=new String(dirinfo[i].getName());
			dirname.add(dirinfo[i].getName());
		}
		//System.out.println("haha"+dirname);
	}
	public List getDirname() {
		
		return this.dirname;
	}
	public void setDirname(String[] dirname) {
		
	 
	
	}

}
