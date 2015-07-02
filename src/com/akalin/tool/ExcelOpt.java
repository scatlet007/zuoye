package com.akalin.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ExcelOpt {
	/**
	*����һ��Excel�ļ� jxl
	*@param fileName Ҫ���ɵ�Excel�ļ���
	*@param list ����Դ������ΪList<List<Object>>
	*@jxl.jar �汾 ��2.6
	*/
	public void writeExcel(String fileName,List<List<Object>> list){
		WritableWorkbook wwb=null;
		try{
			//����Ҫ��Workbook��Ĺ�����������һ����д��Ĺ�����(Workbook)����
			wwb=Workbook.createWorkbook(new File(fileName));
		}catch(IOException e){
			e.printStackTrace();
		}
		if(wwb!=null){
			//����һ����д��Ĺ�����
			//Workbook ��createheet������������������һ���ǹ���������ƣ��ڶ����ǹ������ڹ�������λ��
			WritableSheet ws=wwb.createSheet("�����������",0);
			//���濪ʼ��ӵ�Ԫ��
			for(int i=0;i<list.size();i++){
				for(int j=0;j<list.get(i).size();j++){
					//����Ҫע����ǣ���Excel�У���һ��������ʾ�У��ڶ���������ʾ��
					Label labelC=new Label(j,i,(String)list.get(1+i).get(j)+1);
					try{
						//�����ɵĵ�Ԫ����ӵ���������
						ws.addCell(labelC);
					}catch(RowsExceededException e){
						e.printStackTrace();
					}catch(WriteException e){
						e.printStackTrace();
					}
				}
			}
			try{
				//���ڴ���д���ļ���
				wwb.write();
				//�ر���Դ���ͷ��ڴ�
				wwb.close();
			}catch(IOException e){
				e.printStackTrace();
			}catch(WriteException e){
				e.printStackTrace();
			}
		}
		
	}
	/**
	*����һ��Excel�ļ�POI
	*@param inputFile ����ģ���ļ�·��
	*@param outputFile �����ļ�����ڷ�����·��
	*@param dataList ����������
	*@throws Exception
	*@roseuid:
	*/
	public void exceportExcelFile(String inputFile,String outputFile,List dataList) throws Exception{
		//��ģ���ļ�����poi
		POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(inputFile));
		//����ģ�湤����
		HSSFWorkbook teamplatewb=new HSSFWorkbook(fs);
		//ֱ��ȡģ��ĵ�һ��sheet����
		HSSFSheet templateSheet=teamplatewb.getSheetAt(1);
		//�õ�ģ��ĵ�һ��sheet�ĵ�һ�ж��� Ϊ�˵õ�ģ����ʽ
		HSSFRow templateRow=templateSheet.getRow(0);
		
		//ȡ��Excel�ļ���������
		int columns=templateSheet.getRow((short)0).getPhysicalNumberOfCells();
		//������ʽ����
		HSSFCellStyle styleArray[]=new HSSFCellStyle[columns];
		//һ���Դ��������е���ʽ����������
		for(int s=0;s<columns;s++){
			//�õ�����ʵ��
			styleArray[s]=teamplatewb.createCellStyle();
		}
		//ѭ����ÿһ����Ԫ����и�ֵ
		//��λ��
		for(int rowId=1;rowId<dataList.size();rowId++){
			//����ȡ����rowId�е����� ÿһ��������valueList
			List valueList=(List)dataList.get(rowId -1);
			//��λ��
			for(int columnId=0;columnId<columns;columnId++){
				//����ȡ����Ӧ��columnId�е�ֵ
				//ÿһ����Ԫ���ֵ
				String dataValue=(String)valueList.get(columnId);
				//ȡ��columnId�е�style
				//ģ��ÿһ�е���ʽ
				HSSFCellStyle style=styleArray[columnId];
				//ȥģ���columnId�еĵ�Ԫ�����
				//ģ�浥Ԫ�����
				HSSFCell templateCell=templateRow.getCell((short) columnId);
				//����һ���µ�rowId�ж���
				//�½����ж���
				HSSFRow hssfRow=templateSheet.createRow(rowId);
				//��un�����µ�rowId�� columnId�� ��Ԫ�����
				//�½��ĵ�Ԫ�����
				HSSFCell cell=hssfRow.createCell((short)columnId);
				//��Ӧ��ģ�浥Ԫ�� ��ʽΪ������
				if(templateCell.getCellStyle().getLocked()==false){
					//���ô���styleΪ������
					style.setLocked(false);
					//���õ��µĵ�Ԫ����
					cell.setCellStyle(style);
				}else{
					//������ʽΪ����
					style.setLocked(true);
					//���õ��µ�Ԫ����
					cell.setCellStyle(style);
				}
				//���ñ���
				//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				//����ֵ ͳһΪString
				cell.setCellValue(dataValue);
			}
		}
		//����������
		FileOutputStream fOut=new FileOutputStream(outputFile);
		//��ģ�������д������ļ���
		teamplatewb.write(fOut);
		fOut.flush();
		//�����������ر��ļ�
		fOut.close();
	}
	
	/**
	*��������ΪXLS��ʽ
	*@param fos����Excel�ļ�path
	*@param columnName ����ͷֵ
	*@param ve Ҫ����������ֵ
	*/
	public void writeExcelBo(String fos,String[] columnName,List<List<Object>> ve){
		jxl.write.WritableWorkbook wwb;
		try{
			wwb=Workbook.createWorkbook(new File(fos));
			jxl.write.WritableSheet ws=wwb.createSheet("��������",10);
			//������ͷֵ
			for(int i=0;i<columnName.length;i++){
				ws.addCell(new jxl.write.Label(i,0,columnName[i]));
			}
			//ѭ����ȡÿһ�е�ֵ
			for(int i=0;i<ve.size();i++){
				//ѭ����ȡÿһ��Ԫ���ֵ
				for(int j=0;j<ve.get(i).size();j++){
					//����д��Ԫ���ֵ
					if(ve.get(i).get(j)==null)
						ws.addCell(new jxl.write.Label(j,i+1,""));
					else
						ws.addCell(new jxl.write.Label(j,i+1,ve.get(i).get(j).toString()));
				}
			}
			wwb.write();
			//�رչ���������
			wwb.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(RowsExceededException e){
			e.printStackTrace();
		}catch(WriteException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡExcel������
	 * @param file ����ȡ�ļ�
	 * @param ��������
	 * @return ��������
	 */
	public List<List<Object>> readExcel(File file,String[] columnName){
		//StringBuffer sb=new StringBuffer();
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Integer> col=new ArrayList<Integer>();
		Workbook wb=null;
		try{
			//����Workbook����
			wb=Workbook.getWorkbook(file);
		}catch(BiffException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		if(wb==null){
			return null;
		}
		//��ù���������֮�󣬾Ϳ���ͨ�����õ������������
		Sheet[] sheet=wb.getSheets();
		if(sheet!=null&&sheet.length>0){
			//��ÿ�����������ѭ��
			int a=0;
			for(int i=0;i<sheet.length;i++){
	   			//�õ���ǰ�����������
				int rowNum=sheet[i].getRows();
				//��ƥ���ͷֵ
				Cell[] cell=sheet[0].getRow(0);
				if(a==0){
					for(int c=0;c<columnName.length;c++){
						for(int r=c;r<cell.length;r++){
							if(columnName[c].equals(cell[r].getContents())){
								col.add(r);
							}
						}
					}
					a=1;
				}
				//�õ���ǰ�е����е�Ԫ��
				//if(i!=0){
					for(int j=0;j<rowNum;j++){
						Cell[] cells=sheet[i].getRow(j);
						if(cells!=null&&cells.length>0){
							//��ÿ����Ԫ�����ѭ��
							List<Object> list2=new ArrayList<Object>();
							for(int k=0;k<col.size();k++){
								//��ȡ��ǰ��Ԫ���ֵ
								String cellValue=cells[col.get(k)].getContents();
								list2.add(cellValue);
							}
							list.add(list2);
							//list2.clear();
						}
					}
				//}    
			}
		}
		//���ر���Դ���ͷ��ڴ�
		wb.close();
		return list;
	}
}
