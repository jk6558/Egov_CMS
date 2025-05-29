/**
 *  Class Name : EgovMenuGov.java
 *  Description : 메뉴관리 Business Interface class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.02.02    이 용          최초 생성
 *   2022.11.11    김혜준		  시큐어코딩 처리
 *
 *  @author 공통 서비스 개발팀 이 용
 *  @since 2009. 02. 02
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by EGOV  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Vector;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> 20ee72a1a2e5b3ce77bb4f3ad6e29e09616620b4

import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovMenuGov {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * <pre>
	 * Comment : DAT 파일을 파싱하여 메뉴관리화면에 리턴.
	 * </pre>
	 * @param parFile   DAT파일명
	 * @param parChar   구분자
	 * @param parField  필드수
	 * @return Vector list
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */
	public static Vector<List<String>> parsFileByMenuChar(String parFile, String parChar, int parField) throws Exception {
		Vector<List<String>> list = null;
		String FileName = null;
		
		FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(FileName);

		// 파일이며, 존재하면 파싱 시작
		if (file.exists() && file.isFile()) {
			list = EgovFileTool.parsFileByChar(parFile, parChar, parField);
		} else {
			list = new Vector<List<String>>();
		}
		
		return list;
	}

	/**
	 * <pre>
	 * Comment : 메뉴관리 화면의 데이타를 DAT 파일로 생성.
	 * </pre>
	 * @param menuIDArray     ID Array
	 * @param menuNameArray   Name Array
	 * @param menuLevelArray  Lefel Array
	 * @param menuURLArray    URL Array
	 * @return boolean true/false
	 * @version 1.0 (2009.02.04.)
	 * @see
	 */
	public static boolean setDataByDATFile(String parFile, String[] menuIDArray, String[] menuNameArray, String[] menuLevelArray, String[] menuURLArray) throws Exception {
		boolean success = false;
		String FileName = null;

		FileName = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(FileName);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		try {

			for (int i = 0; i < menuIDArray.length; i++) { //nodeId | parentNodeId | nodeName | nodeUrl
				out.write(menuIDArray[i] + "|" + menuLevelArray[i] + "|" + menuNameArray[i] + "|" + menuURLArray[i] + "|");
				out.newLine();
			}
			success = true;
		} finally {
			EgovResourceCloseHelper.close(out);
		}
		return success;
	}
<<<<<<< HEAD
	
	/**
     * 메뉴 파일 생성
     * @param filePath 생성할 파일 경로
     * @param menuContent 메뉴 텍스트 내용
     * @return 성공 여부
     */
    public static boolean createMenuFile(String filePath, String menuContent) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(menuContent);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
=======
>>>>>>> 20ee72a1a2e5b3ce77bb4f3ad6e29e09616620b4

}
