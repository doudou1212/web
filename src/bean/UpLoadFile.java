package bean;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;





import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;;


public class UpLoadFile extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	
	List<String> name=new ArrayList();
	String path="C:\\apache-tomcat-7.0.52\\webapps\\web\\upload\\";
	public UpLoadFile() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		

		int read=0;
		
		System.out.println("niaho1");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		
		//filename="sj.txt";
		
    //String filename=req.getParameter("filename");
		String filename=req.getParameter("file");
        
        System.out.println("filename="+filename);
        System.out.println(filename.length());

        String file=null;
        file=new String(filename.getBytes("ISO-8859-1"),"utf-8");//解决乱码

		System.out.println("file="+file);
		System.out.println("in="+path+file);
       FileInputStream in=new 	FileInputStream(path+file);	
		//FileInputStream in=new 	FileInputStream("C:\\apache-tomcat-7.0.52\\webapps\\web\\upload\\"+"sj.txt");	
		System.out.println("in="+path+file);
//		//ServletOutputStream out=resp.getOutputStream();
		FileOutputStream out=new FileOutputStream("f:\\"+file);

		out.flush();

    
		while((read=in.read())!=-1&&in!=null)
		{
			out.write(read);
		}
		out.flush();
		in.close();
		out.close();
		

//		RequestDispatcher dispatcher=req.getRequestDispatcher("index.jsp");
//		dispatcher.forward(req, resp);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("niaho2");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//开始解析
		sfu.setFileSizeMax(1024*400);
		sfu.setHeaderEncoding("utf-8");
		
		System.out.println("point1");
		

		
		//每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			List<FileItem> items = sfu.parseRequest(req);
			//区分表单域
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				//isFormField为true，表示这不是文件上传表单域
				if(!item.isFormField()){
					ServletContext sctx = getServletContext();//
					//获得存放文件的物理路径
					//upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹
					
					String path = sctx.getRealPath("/upload");
					System.out.println(path);
					//获得文件名
					String fileName = item.getName().toString();
					name.add(fileName);
					System.out.println(fileName);
					//该方法在某些平台(操作系统),会返回路径+文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
	
					File file = new File(path+"\\"+fileName);

					if(!file.exists()){
						item.write(file);
						//将上传图片的名字记录到数据库中
						
						//resp.sendRedirect("/upload/ok.html");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("filename", name);
		RequestDispatcher dispatcher=req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	
	}

}
